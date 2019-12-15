package com.example.androidassignment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidassignment.Database.DbHelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mDay = "Return to day";
    private int mDayNo;

    private OnFragmentInteractionListener mListener;

    public LogView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogView.
     */
    // TODO: Rename and change types and number of parameters
    public static LogView newInstance(String param1, int param2) {
        LogView fragment = new LogView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDay = getArguments().getString(ARG_PARAM1);
            mDayNo = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_log_view, container, false);
        ListView listView = (ListView) view.findViewById(R.id.logList);

        DbHelper databaseHelper = new DbHelper(getActivity());

        ArrayList<String> logModelArrayList = databaseHelper.getLogsString(mDayNo);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.log_list_view, logModelArrayList);


        listView.setAdapter(adapter);

        Button btn = (Button) view.findViewById(R.id.returnBack);
        btn.setText("Return to "+ mDay);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment =  DayView.newInstance(mDay , mDayNo);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.addToBackStack("xyz");
//                Toast.makeText(getActivity(),"hello clicked" ,Toast.LENGTH_SHORT);
                transaction.replace(android.R.id.content, newFragment);
                transaction.commit();
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
