package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.util.EcouteurAlarme;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.Agenda;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAlarme;

import static android.content.Context.ALARM_SERVICE;

public class ControleurAlarme implements Controleur {

    private VueAlarme vue;
    private Devoir devoir;
    private DevoirDAO accesseurDevoir;

    public ControleurAlarme(VueAlarme vue) {
        this.vue = vue;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        int idDevoir = vue.getIdDevoirParametre();
        this.accesseurDevoir = DevoirDAO.getInstance();
        devoir = accesseurDevoir.chercherDevoirParIdDevoir(idDevoir);
        vue.setDevoir(devoir);
        vue.afficherDevoir();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int activite) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void repeterAlarme(Context applicationContext) {

        AlarmManager alarmManager= (AlarmManager) applicationContext.getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(applicationContext, EcouteurAlarme.class);
        intent.putExtra(Devoir.CLE_ID_DEVOIR,devoir.getId_devoir());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis()+10*1000,
                pendingIntent
        );
        vue.naviguerAgenda();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void arreterAlarme() {
        devoir.setAlarme_active(false);
        accesseurDevoir.modifierDevoir(devoir);
        vue.naviguerAgenda();
    }
}
