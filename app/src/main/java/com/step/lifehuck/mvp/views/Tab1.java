package com.step.lifehuck.mvp.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.step.lifehuck.R;
import com.step.lifehuck.di.BaseApp;
import com.step.lifehuck.entities.Good;
import com.step.lifehuck.mvp.contracts.MainContract;
import com.step.lifehuck.utils.adapters.LifeHuckRVAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public  class Tab1 extends Fragment implements MainContract.view{


    @Inject
    MainContract.presenter presenter;
    RecyclerView huchsRecyclerView;
    LifeHuckRVAdapter huckRVAdapter;
    ProgressBar progressBar;


    void initRecycler(){
        huckRVAdapter = new LifeHuckRVAdapter();
        huchsRecyclerView = huchsRecyclerView.findViewById(R.id.rvLifeHucks);
        huchsRecyclerView.setAdapter(huckRVAdapter);
        huchsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }



    Button upadateBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
        BaseApp.get(this.getContext()).getInjector().inject(this);
        upadateBtn = upadateBtn.findViewById(R.id.btnUpd);

        progressBar = progressBar.findViewById(R.id.indicator);
        progressBar.setVisibility(View.GONE);

        initRecycler();


        presenter.onAttachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }

    @Override
    public void showGoods(List<Good> goods) {
        huckRVAdapter.setGoods(goods);
    }

    @Override
    public void showIndicator() {
        progressBar.setVisibility(View.VISIBLE);
        huchsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideIndicetor() {
        progressBar.setVisibility(View.GONE);
        huchsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public Observable<Integer> onUpdateClick() {
        PublishSubject<Integer> subject = PublishSubject.create();
        upadateBtn.setOnClickListener(view->subject.onNext(view.getId()));
        return subject;
    }

    @Override
    public Observable<String> onSelectView() {
        return huckRVAdapter.onItemClick();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this.getContext(),s,Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
