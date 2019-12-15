package com.example.androidassignment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidassignment.model.Profile;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Profile pr = new Profile();

    private OnFragmentInteractionListener mListener;

    public ProfileView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileView.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileView newInstance(String param1, String param2) {
        ProfileView fragment = new ProfileView();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.fragment_profile_view, container, false);
        Button saveBtn = (Button) view.findViewById(R.id.saveProfile);
        Button cancelBtn = (Button) view.findViewById(R.id.cancel);
        final EditText profileName = (EditText) view.findViewById(R.id.usernameEdit);
        final EditText password = (EditText)view.findViewById(R.id.passwordEdit);
        final EditText retypePass = (EditText)view.findViewById(R.id.retypePasswordField);
        profileName.setText(Profile.username);
//        password.setText(Profile.password);
//        retypePass.setText(Profile.password);
//        profileName.setText(mParam1);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(retypePass.getText().toString())){
//                    pr.setUsername(profileName.getText().toString());
                    Profile.username = profileName.getText().toString();
                    Profile.password = password.getText().toString();
                    Toast.makeText(getActivity(), "Username is ::" +Profile.username + "Password is : " + Profile.password , Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Save successfully !!! ", Toast.LENGTH_SHORT).show();
                    mainFramgent();
                }
                else{
                    Toast.makeText(getActivity(), "Password Mismatch!!! ", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "password is ::" +password.getText().toString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "retype password is ::" +retypePass.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFramgent();
            }
        });

        return view;

    }

    public void mainFramgent(){
        Fragment newFragment = new MainView();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // and add the transaction to the back stack
        transaction.addToBackStack(null);

        transaction.hide(ProfileView.this);
        transaction.replace(android.R.id.content, newFragment);
        transaction.commit();
    }


    public void setProfile(Profile profile){
        pr = profile;
    }

    public Profile getProfile(){
        return pr;
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
