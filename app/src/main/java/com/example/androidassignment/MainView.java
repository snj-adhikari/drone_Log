package com.example.androidassignment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainView extends Fragment implements View.OnClickListener {
    public  String[] day = {"Day 1 " , "Day 2 " , "Day 3" , "Day 4" , "Day 5"};
    public MainView() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View myFragmentView =  inflater.inflate(R.layout.fragment_main_view, container, false);
        Button btn1 = myFragmentView.findViewById(R.id.day1);
        Button btn2 = myFragmentView.findViewById(R.id.day2);
        Button btn3 = myFragmentView.findViewById(R.id.day3);
        Button btn4 = myFragmentView.findViewById(R.id.day4);
        Button btn5 = myFragmentView.findViewById(R.id.day5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

        return myFragmentView;
    }

    @Override
    public void onClick(View view) {
        DayView newFragment = new DayView();
        switch (view.getId()) {
            case R.id.day1:
                newFragment = newFragment.newInstance(day[0],1);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.addToBackStack("xyz");
                transaction.hide(MainView.this);
                transaction.add(android.R.id.content, newFragment, "dayView");
                transaction.commit();
//                Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.day2:
//                newFragment  =DayView.newInstance(day[1] , 2);
//                transaction = getFragmentManager().beginTransaction();
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack
//                transaction.replace(R.id.mainView , newFragment);
//                transaction.addToBackStack(null);
//                // Commit the transaction
//                transaction.commit();
                newFragment = newFragment.newInstance(day[1],2);
                transaction = getActivity().getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.addToBackStack("xyz");
                transaction.hide(MainView.this);
                transaction.add(android.R.id.content, newFragment , "dayView");
                transaction.commit();
//                Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day3:
                newFragment = newFragment.newInstance(day[2],3);
                transaction = getActivity().getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.addToBackStack("xyz");
                transaction.hide(MainView.this);
                transaction.add(android.R.id.content, newFragment , "dayView");
                transaction.commit();
                break;
            case R.id.day4:
                newFragment = newFragment.newInstance(day[3],4);
                transaction = getActivity().getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.addToBackStack("xyz");
                transaction.hide(MainView.this);
                transaction.add(android.R.id.content, newFragment , "dayView");
                transaction.commit();
            case R.id.day5:
                newFragment = newFragment.newInstance(day[4],5);
                transaction = getActivity().getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.addToBackStack("xyz");
                transaction.hide(MainView.this);
                transaction.add(android.R.id.content, newFragment , "dayView");
                transaction.commit();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
