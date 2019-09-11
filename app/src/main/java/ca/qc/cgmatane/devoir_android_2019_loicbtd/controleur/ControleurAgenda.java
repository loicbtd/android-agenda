package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.util.EcouteurAlarme;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAgenda;

import static android.content.Context.ALARM_SERVICE;

// TODO: OK
public class ControleurAgenda implements Controleur {

    static final public int ACTIVITE_AJOUTER_DEVOIR = 1;
    static final public int ACTIVITE_MODIFIER_DEVOIR = 2;

    private VueAgenda vue;
    private DevoirDAO accesseurDevoir;

    public ControleurAgenda(VueAgenda vue) {
        this.vue = vue;
    }

    public void actionNaviguerAjouterDevoir() {
        vue.naviguerAjouterDevoir();
    }

    public void actionNaviguerModifierDevoir(String idDevoir) {
        vue.naviguerModifierDevoir(idDevoir);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        accesseurDevoir = DevoirDAO.getInstance();
        vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
        vue.afficherTousLesDevoirs();

        AlarmManager alarmManager= (AlarmManager) applicationContext.getSystemService(ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent;
        Duration duree;
        int difference;

        List<Devoir> listeDevoir = accesseurDevoir.recupererListeDevoir();
        for(Devoir devoir:listeDevoir) {

            duree = Duration.between(LocalDateTime.now().minusHours(4), devoir.getHoraire());
            difference = (int)duree.toMillis();

            if(difference > 0) {
                intent = new Intent(applicationContext, EcouteurAlarme.class);
                intent.putExtra(Devoir.CLE_ID_DEVOIR,devoir.getId_devoir());
                pendingIntent = PendingIntent.getBroadcast(
                        applicationContext,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis()+difference,
                        pendingIntent
                );
            }
        }
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int activite) {
        switch (activite) {
            case ACTIVITE_AJOUTER_DEVOIR:
                vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
                vue.afficherTousLesDevoirs();
                break;

            case ACTIVITE_MODIFIER_DEVOIR:
                vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
                vue.afficherTousLesDevoirs();
                break;
        }
    }
}
