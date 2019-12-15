package com.example.androidassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androidassignment.Database.DbHelper;
import com.example.androidassignment.model.Profile;

public class MainActivity extends AppCompatActivity implements ProfileView.OnFragmentInteractionListener, DayView.OnFragmentInteractionListener , DayForm.OnFragmentInteractionListener , LogView.OnFragmentInteractionListener, MainView.OnFragmentInteractionListener{

    private Profile pr = new Profile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create new fragment and transaction
//        DbHelper help= new DbHelper(this);
//        help.deleteLogTable();

        if (savedInstanceState == null) {
            Fragment newFragment = new MainView();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


            transaction.replace(android.R.id.content , newFragment , "mainView");
            transaction.addToBackStack(null);

            // Commit the transaction

            transaction.commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure ? This will delete all the entries in DB");
            builder.setMessage("This will delete entries in db first !!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    DbHelper dbHelper = new DbHelper(MainActivity.this);
//                    dbHelper.deleteLogTable();
//                    finish();

//                    final GMailSender sender = new GMailSender("username@gmail.com",       "password");
                    Toast.makeText(MainActivity.this , "Sending email" , Toast.LENGTH_SHORT);
                    new AsyncTask<Void, Void, Void>() {
                        @Override public Void doInBackground(Void... arg) {
                            try {
                                sendmail();

                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this , "Error on sending mail" , Toast.LENGTH_SHORT);
                            }
                            return null;}
                    }.execute();
//                    sendmail();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();



        }
        if (id == R.id.action_save){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save Log ? ");
            builder.setMessage("Are you sure you want to save ? ");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if (id == R.id.action_profile){
            ProfileView newFragment = new ProfileView();
            newFragment.setProfile(pr);
            pr  = newFragment.getProfile();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//                transaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.addToBackStack(null);
//                Toast.makeText(getActivity(),"hello clicked" ,Toast.LENGTH_SHORT);
            transaction.replace(android.R.id.content, newFragment);
            transaction.commit();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void sendmail(){
        String recipientList = getResources().getString(R.string.email_list);
        String[] recipents = recipientList.split(",");
        String subject = "Drone Log";
        DbHelper db = new DbHelper(MainActivity.this);
        String message = Profile.username + "\n";
        message += db.getLogsToEmail();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL , recipents);
        intent.putExtra(Intent.EXTRA_SUBJECT , subject);
        intent.putExtra(Intent.EXTRA_TEXT , message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

}
