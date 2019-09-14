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
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.util.EcouteurAlarme;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAgenda;

import static android.content.Context.ALARM_SERVICE;

public class ControleurAgenda implements Controleur {

    static final public int ACTIVITE_AJOUTER_DEVOIR = 1;
    static final public int ACTIVITE_MODIFIER_DEVOIR = 2;
    static final public int ACTIVITE_AFFICHER_ALARME = 3;

    private AlarmManager alarmManager;
    private ArrayList<PendingIntent> pendingIntentList;

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
        alarmManager = (AlarmManager) applicationContext.getSystemService(ALARM_SERVICE);
        pendingIntentList = new ArrayList<>();
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

            case ACTIVITE_AFFICHER_ALARME:
                vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
                vue.afficherTousLesDevoirs();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void activerLesAlarmes(Context applicationContext) {

        if (pendingIntentList != null) {
            for (PendingIntent pendingIntent: pendingIntentList) {
                alarmManager.cancel(pendingIntent);
            }
        }

        Intent intent;
        PendingIntent pendingIntent;

        Duration duree;
        int difference;

        List<Devoir> listeDevoir = accesseurDevoir.recupererListeDevoir();

        for(Devoir devoir:listeDevoir) {
            if (devoir.isAlarme_active()) {

                duree = Duration.between(LocalDateTime.now(), devoir.getHoraire());
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

                    pendingIntentList.add(pendingIntent);
                }
            }
        }
    }
}
