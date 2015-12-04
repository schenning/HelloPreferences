package fr.eurecom.android.preferences;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class HelloPreferences extends AppCompatActivity {

    //* @Override *//
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_preferences);
 /**       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); **/
/**        setSupportActionBar(toolbar); **/
        // Initialize preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = preferences.getString("username", "NIL");
                String password = preferences.getString("password", "NIL");
                // A toast is a view containing a quick little message for the user.
                showPrefs(username, password);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(v);
            }

        });


        /**Button button4 = (Button)findViewById(R.id.buttonCN); **/
    }

    private void showPrefs(String username, String password) {
        Toast.makeText(HelloPreferences.this,
                "You kept user: " + username + " and password: " + password,
                Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // We have only one menu option
            case R.id.action_settings:
                // Launch Preference activity
                Intent i = new Intent(HelloPreferences.this, Preferences.class);
                startActivity(i);
                // A toast is a view containing a quick little message for the user.
                Toast.makeText(HelloPreferences.this,
                        "Here you can store your user credentials.",
                        Toast.LENGTH_LONG).show();
                Log.i("Main", "sent an intent to the Preference class");
                break;
        }
        return true;
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_preferences, menu);
        return true;
    }



    public void reset_preferences() {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("username", null);
        edit.putString("password", null);
        edit.commit(); // Apply changes
        // A toast is a view containing a quick little message for the
        // user. We give a little feedback
        Toast.makeText(HelloPreferences.this, "Reset user name and password", Toast.LENGTH_LONG).show();
    }
    public void openDialog(View v) {
        // Create out AlterDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Reset username and password?");
        builder.setCancelable(true);
        builder.setPositiveButton("I wanna do it;) ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Resetting Credential ", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No, svp", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Keep Credential", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    public void createNotification() {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // prepare intent which is triggered if the notification is selected
        Intent intent = new Intent(this, HelloPreferences.class);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Hello Preferences")
                .setContentText("Successfully reset user Credential")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(activity)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Call", activity)
                .addAction(R.mipmap.ic_launcher, "More", activity)
                .build();
        notificationManager.notify(0, notification);
    }

    public void exitPreferences(View view){
        this.finish();
    }
    public void createNotification(View view){
        createNotification();
    }
}





