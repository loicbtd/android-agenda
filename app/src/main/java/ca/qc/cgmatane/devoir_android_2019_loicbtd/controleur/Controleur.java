package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.content.Context;

// TODO: OK
public interface Controleur {
    void onCreate(Context applicationContext);
    void onPause();
    void onResume();
    void onDestroy();
    void onActivityResult(int activite);
}


