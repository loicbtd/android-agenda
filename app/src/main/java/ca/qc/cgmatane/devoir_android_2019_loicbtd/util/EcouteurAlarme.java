package ca.qc.cgmatane.devoir_android_2019_loicbtd.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class EcouteurAlarme extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();

//        Intent intentionAlarme = new Intent(context.getApplicationContext(), Alarme.class);
//        intentionAlarme.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_NEW_TASK
//        );
//        context.startActivity(intentionAlarme);
    }
}
