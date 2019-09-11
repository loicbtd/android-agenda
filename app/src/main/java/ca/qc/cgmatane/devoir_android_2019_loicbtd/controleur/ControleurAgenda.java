package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
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
        Intent intent = new Intent(applicationContext, EcouteurAlarme.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis()+5*1000,
                pendingIntent
        );
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
