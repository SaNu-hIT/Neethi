package com.example.work.cartapp.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.work.cartapp.Model.LoginModel.Data;
import com.example.work.cartapp.Model.LoginModel.HttpRequestForLogin;
import com.example.work.cartapp.Model.LoginModel.OnHttpResponceForLogin;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;

/**
 * Created by work on 10/18/2017.
 */

public class LoginActivity extends BaseActivity implements OnHttpResponceForLogin {
    EditText email, password;
    Button btnSignIn;
    String passwordd, emaill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        setProgressBar();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0) {
                    if (password.getText().toString().trim().length() == 0) {
                        password.setError("Field can't be blank.");
                    }
                    if (email.getText().toString().trim().length() == 0) {
                        email.setError("Field can't be blank");
                    }
                } else {
                    if (isValidEmail(email.getText().toString().trim())) {
                        loginApi(password.getText().toString().trim(), email.getText().toString().trim());
                    } else {
                        email.setError("Invalide Email-id");
                    }
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void loginApi(String password, String email) {
        passwordd = password;
        emaill = email;
        showProgress("Please wait Login!");
        HttpRequestForLogin httpRequestForGetListItem = new HttpRequestForLogin(this);
        httpRequestForGetListItem.LoginUser(email, password);
    }


    @Override
    public void OnLoginSuccess(String stautus, Data userInfos) {

//        Toast.makeText(this, stautus, Toast.LENGTH_SHORT).show();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        cancelProgress();
        sessionManager.SaveCredentials(emaill, passwordd);
        sessionManager.SaveValuesInitial(userInfos.getShopId(), userInfos.getShopName(), userInfos.getGSTIN());
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void OnLoginFaild(String message) {
        cancelProgress();
        Snackbar.make(getCurrentFocus(), ""+message, Snackbar.LENGTH_INDEFINITE)
                .setAction("TryAgain", null).setActionTextColor(R.color.red_btn_bg_color).show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
