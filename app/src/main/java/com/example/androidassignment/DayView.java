package com.example.androidassignment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayView extends Fragment  implements  DayForm.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String Day_Param = "Day 0 ";
    private static final String Day_No ="0";


    // TODO: Rename and change types of parameters
    private String mDay;

    private int mDayNo;
    private OnFragmentInteractionListener mListener;

    public DayView() {
        // Required empty public constructor
    }


    public static DayView newInstance(String day , int no) {
        DayView fragment = new DayView();
        Bundle args = new Bundle();
        System.out.println("THe string day is" + day + "the no is " + no);
        args.putString(Day_Param, day);
        args.putInt(Day_No ,no );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDay = getArguments().getString(Day_Param);
            mDayNo = getArguments().getInt(Day_No);
        }


        dayFragment();

    }
    public void mainFramgent(){
        Fragment newFragment = new MainView();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // and add the transaction to the back stack
        transaction.addToBackStack(null);

//        transaction.hide(DayView.this);
        transaction.replace(android.R.id.content, newFragment);
        transaction.commit();
    }

    public void setViewDate(View view){

        TextView dayText = (TextView) view.findViewById(R.id.dayText);
        dayText.setText("");
        dayText.setText(mDay);
    }
    public void dayFragment(){

        Fragment newFragment = DayForm.newInstance(mDay , mDayNo);
//        Toast.makeText(getActivity() , mDay + Integer.toString(mDayNo) , this.)

        System.out.println("the day form when show the mDay is as follows" + mDay+ " hhha" + mDayNo);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.dayForm , newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction

        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_day_view, container, false);


        Button previousBtn= (Button)view.findViewById(R.id.prevBtn);
        Button nextBtn= (Button)view.findViewById(R.id.nextBtn);
        Button homeBtn= (Button)view.findViewById(R.id.homeBtn);

        setViewDate(view);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View btnView) {
                String[] day = {"Day 1 " , "Day 2 " , "Day 3" , "Day 4" , "Day 5"};
                if(mDayNo == 1){
                    mDay = day[4];
                    mDayNo = 5;
                }
                else{
                    mDayNo--;
                    mDay  = day[mDayNo - 1];
                }
                dayFragment();
                setViewDate(view);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View btnView) {
                String[] day = {"Day 1 " , "Day 2 " , "Day 3" , "Day 4" , "Day 5"};
                if(mDayNo == 5){
                    mDay = day[0];
                    mDayNo = 1;
                }
                else{
                    mDayNo++;
                    mDay  = day[mDayNo - 1];
                }
                dayFragment();
                setViewDate(view);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFramgent();
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
