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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.work.cartapp.Connection.HttpRequestForGetListItem;
import com.example.work.cartapp.Connection.HttpRequestForGetListItemByShop;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItem;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShop;

import com.example.work.cartapp.Model.ListItemsShop.Datum;
import com.example.work.cartapp.Model.POST.GetListItembyShopmodel;
import com.example.work.cartapp.Adapter.MyItemRecyclerViewAdapter;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;
import com.example.work.cartapp.Extras.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemListFragment extends Fragment implements OnHttpResponsegetListItem, OnHttpResponsegetListItemByShop {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
List<Datum> data;
    private ProgressDialog progressBar;
//    private SwipeRefreshLayout mySwipeRefreshLayout;
    private SearchView searchView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }
    public static ItemListFragment newInstance() {
        ItemListFragment fragment = new ItemListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    RecyclerView recyclerView;
     MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        data=new ArrayList<>();
        progressBar = new ProgressDialog(getActivity(),
                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(false);

//        mySwipeRefreshLayout=view.findViewById(R.id.swiperefresh);
        getDataFromServer();
        searchView=view.findViewById(R.id.search);
        recyclerView=view.findViewById(R.id.list);

            Context context = view.getContext();

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


//        mySwipeRefreshLayout.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//
//                        getDataFromServer();
//                        // This method performs the actual data-refresh operation.
//                        // The method calls setRefreshing(false) when it's finished.
//
//                    }
//                }
//        );


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                myItemRecyclerViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                myItemRecyclerViewAdapter.getFilter().filter(query);
                return false;
            }
        });

        return view;
    }

    private void getDataFromServer() {
        progressBar.show();
        SessionManager sessionManager=new SessionManager(getActivity());
        Long shopid=sessionManager.getUserId();



        GetListItembyShopmodel getListItembyShopmodel=new GetListItembyShopmodel();
        getListItembyShopmodel.setItemDataId((long) 0);
        getListItembyShopmodel.setShopId(shopid);
        Log.e( "SHOP ID: ",""+shopid );
        HttpRequestForGetListItemByShop httpRequestForGetListItemByShop=new HttpRequestForGetListItemByShop(this);
        httpRequestForGetListItemByShop.getEvents(getListItembyShopmodel);
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

//    @Override
//    public void OnSuccessListItem(String message, List<Datum> mData) {
////        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(mData, mListener));
//        progressBar.cancel();
//
//    }

    @Override
    public void OnSuccessListItem(String message, List<com.example.work.cartapp.Model.ListModel.Datum> mData) {
        progressBar.cancel();

    }

    @Override
    public void OnFailedListItem(String message) {
        progressBar.cancel();
        Snackbar.make(getView(), ""+message, Snackbar.LENGTH_INDEFINITE)
                .setAction("TryAgain", null).show();

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
        void onListFragmentInteraction(DummyItem item);
    }


    @Override
    public void OnSuccessListItemByShop(String message, List<com.example.work.cartapp.Model.ListItemsShop.Datum> mData) {
        myItemRecyclerViewAdapter=new MyItemRecyclerViewAdapter(mData, mListener);
        recyclerView.setAdapter(myItemRecyclerViewAdapter);
        progressBar.cancel();
//        mySwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void OnFailedListItemByShop(String message) {
        progressBar.cancel();
//        mySwipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), ""+message, Snackbar.LENGTH_LONG)
                .setAction("TryAgain", null).show();

    }

}
