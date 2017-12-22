package com.example.work.cartapp.Model.LoginModel;

import java.util.List;

/**
 * Created by intellyelabs on 13/03/17.
 */

public interface OnHttpResponceForLogin {
    void OnLoginSuccess(String stautus, Data userInfos);
    void OnLoginFaild(String message);
}
