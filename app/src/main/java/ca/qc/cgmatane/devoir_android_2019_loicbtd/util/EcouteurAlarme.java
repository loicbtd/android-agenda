package ca.qc.cgmatane.devoir_android_2019_loicbtd.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurAgenda;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.AjouterDevoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.Alarme;

public class EcouteurAlarme extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentionAlarme = new Intent(context.getApplicationContext(), Alarme.class);
        intentionAlarme.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK
        );

        Bundle parametres = intent.getExtras();
        String idDevoirParametre = (String) parametres.get(Devoir.CLE_ID_DEVOIR);
        intentionAlarme.putExtra(Devoir.CLE_ID_DEVOIR, idDevoirParametre);

        context.startActivity(intentionAlarme);
    }
}
