package com.example.work.cartapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.work.cartapp.Connection.HttpRequestForListOrder;
import com.example.work.cartapp.Interface.OnHttpResponseListOrder;

import com.example.work.cartapp.Interface.UpdateRecyclerView;
import com.example.work.cartapp.Model.ListModel.ListOrderNewModel.Datum;
import com.example.work.cartapp.Model.ListModel.ParameterListOrder;
import com.example.work.cartapp.Adapter.OrderListAdapter;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class OrderListFragemnt extends Fragment implements OnHttpResponseListOrder,UpdateRecyclerView {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    List<Datum> mDat;
    private ProgressDialog progressBar;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private SearchView searchView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrderListFragemnt() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OrderListFragemnt newInstance() {
        OrderListFragemnt fragment = new OrderListFragemnt();

        return fragment;
    }
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    OrderListAdapter orderListAdapter;
SessionManager sessionManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderlist_layout, container, false);
        progressBar = new ProgressDialog(getActivity(),

                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        sessionManager=new SessionManager(getActivity());
        progressBar.setMessage("Loading...");
        searchView=view.findViewById(R.id.search);
        progressBar.setCancelable(false);

        mySwipeRefreshLayout=view.findViewById(R.id.swiperefresh);

  getServer();
        // Set the adapter
        mDat=new ArrayList<>();

            Context context = view.getContext();
             recyclerView = (RecyclerView) view.findViewById(R.id.list);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new OrderListAdapter(mDat, mListener));
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        getServer();
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.

                    }
                }
        );

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                orderListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                orderListAdapter.getFilter().filter(query);
                return false;
            }
        });

        return view;
    }

    private void getServer() {
        progressBar.show();
        ParameterListOrder parameterListOrder=new ParameterListOrder();
        parameterListOrder.setSalesOrderId(Long.valueOf(0));

        Long shopid=sessionManager.getUserId();
        parameterListOrder.setShopId(shopid);

        HttpRequestForListOrder httpRequestForListOrder=new HttpRequestForListOrder(this);
        httpRequestForListOrder.getItem(parameterListOrder);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnSuccessListOrder(String message, List<Datum> mData) {
        progressBar.cancel();
        mySwipeRefreshLayout.setRefreshing(false);
        orderListAdapter=new OrderListAdapter(mData, mListener);
        recyclerView.setAdapter(orderListAdapter);


    }

    @Override
    public void OnFailedListOrder(String message) {
        progressBar.cancel();
        mySwipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), ""+message, Snackbar.LENGTH_INDEFINITE)
                .setAction("TryAgain", null).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        getServer();
    }

    @Override
    public void updateViews() {
        getServer();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String positon);
    }




}
