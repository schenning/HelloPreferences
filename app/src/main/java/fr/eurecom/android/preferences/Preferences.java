package fr.eurecom.android.preferences;

/**
 * Created by schei on 11/29/15.
 */
import android.os.Bundle;
import android.preference.PreferenceActivity;



public class Preferences extends PreferenceActivity {
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load the preferences from an xml rsource
        addPreferencesFromResource(R.xml.preferences);
    }
}







