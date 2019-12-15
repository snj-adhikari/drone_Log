package com.example.androidassignment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidassignment.model.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidassignment.Database.DbHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayForm.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayForm extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String Day_Param = "Day 1";
    private static final String Day_No_Param = "0";
    private TrackGps gps;
    static EditText  serial;
    static EditText pilot;
    static Spinner spinner;
    static EditText contract;
    // TODO: Rename and change types of parameters
    private static  String mDay = "Day 1";
    private static  int mDayNo = 0;

    private OnFragmentInteractionListener mListener;

    public DayForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param day Parameter 1.
     * @param no Parameter 2.
     * @return A new instance of fragment DayForm.
     */
    // TODO: Rename and change types and number of parameters
    public static DayForm newInstance(String day, int no) {
        DayForm fragment = new DayForm();
        Bundle args = new Bundle();
        args.putString(Day_Param, day);
        args.putInt(Day_No_Param, no);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDay = getArguments().getString(Day_Param);
            mDayNo = getArguments().getInt(Day_No_Param);
        }
//        System.out.println("the day form when show the mDay is as follows" + getArguments().getString(Day_Param)+ " hhha" + mDayNo);

        gps = new TrackGps(getContext());


        System.out.println("THe string day fragment is" + mDay + "the no is " + mDayNo);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//         inflater = getActivity().getLayoutInflater();
         View view = inflater.inflate(R.layout.fragment_day_form, container, false);
         spinner = (Spinner) view.findViewById(R.id.category_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        Button saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        Button vieLogEntries = (Button) view.findViewById(R.id.showLogEntries);

          serial = (EditText) view.findViewById(R.id.serialEditText);
          pilot = (EditText) view.findViewById(R.id.pilotEditText);
            contract = (EditText) view.findViewById(R.id.contractEditText);

        if(pilot.getText().toString().isEmpty() || pilot.getText().toString() == null){
            pilot.setHint("Pilot name");

        }
        else{
            pilot.setHint("");
        }
        if(serial.getText().toString().isEmpty() || serial.getText().toString() == null){

            serial.setHint("numeric");
        }
        else{
            serial.setHint("");
        }
        if(contract.getText().toString().isEmpty() || contract.getText().toString() == null){
            contract.setHint("alphanumeric");
        }
        else{

            contract.setHint("");
        }



        vieLogEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("THe string day fragment is" + mDay + "the no is " + mDayNo);

                Fragment newFragment =  LogView.newInstance(mDay , mDayNo);

                Toast.makeText(getActivity() , mDay +"    " + Integer.toString(mDayNo) , Toast.LENGTH_SHORT );
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
    public void onClick(View view){
        DbHelper db = new DbHelper(getActivity());
        String serialText =  serial.getText().toString();
        String categoryText = spinner.getSelectedItem().toString();
        String pilotText = pilot.getText().toString();
        String contractText = contract.getText().toString();
        double longitude, latitude;
        try {
            int num = Integer.parseInt(serialText);

//                    Log.i("",num+" is a number");


            if(categoryText == null || categoryText.isEmpty() ||
                    pilotText == null  || pilotText.isEmpty() ||
                    contractText == null || categoryText.isEmpty()
            ){
                Toast.makeText(getActivity(), "Sorry , category , pilot and contract field cannot be empty" , Toast.LENGTH_LONG);

            }
            else{
                if(gps.canGetLocation()){
                    longitude = gps.getLongitude();
                    latitude = gps.getLatitude();
//                    Toast.makeText(getActivity(),"Longitude from gps is " + Double.toString(longitude) , Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(),"latitude from gps is " + Double.toString(latitude) , Toast.LENGTH_SHORT).show();
                    Log l  = new Log(mDayNo,mDay,pilotText,num,contractText,categoryText , longitude, latitude);
                    db.addLog(l);
                    Toast.makeText(getActivity(), "Successfully added the log with lat and long" , Toast.LENGTH_SHORT).show();


                }
                else{
                    gps.showSettingsAlert(getActivity() , mDayNo,mDay,pilotText,num,contractText,categoryText);

                }


            }



        } catch (NumberFormatException e) {
//            serial.setText("1384484");
            Toast.makeText(getActivity(), "Serial No must be an integer 0 - 999999 and cannot be null" , Toast.LENGTH_LONG).show();
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
    public void onDestroy(){
        gps.stopListener();
        super.onDestroy();
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
