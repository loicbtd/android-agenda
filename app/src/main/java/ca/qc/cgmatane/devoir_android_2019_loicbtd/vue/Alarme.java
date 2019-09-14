package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurAlarme;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class Alarme extends AppCompatActivity implements VueAlarme {

    protected TextView vueAlarmeLabelMatiere;
    protected TextView vueAlarmeLabelSujet;
    protected TextView vueAlarmeLabelHoraire;

    protected int idDevoirParametre;
    protected Devoir devoir;

    protected ControleurAlarme controleurAlarme = new ControleurAlarme(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_alarme);

        Bundle parametres = this.getIntent().getExtras();
        idDevoirParametre = (int) parametres.get(Devoir.CLE_ID_DEVOIR);

        controleurAlarme.onCreate(getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afficherDevoir() {
        vueAlarmeLabelMatiere = (TextView) findViewById(R.id.vue_alarme_label_matiere);
        vueAlarmeLabelSujet = (TextView)findViewById(R.id.vue_alarme_label_sujet);
        vueAlarmeLabelHoraire = (TextView)findViewById(R.id.vue_alarme_label_horaire);

        vueAlarmeLabelMatiere.setText(devoir.getMatiere());
        vueAlarmeLabelSujet.setText(devoir.getSujet());

        vueAlarmeLabelHoraire.setText(devoir.getHoraire().format(Devoir.FORMAT_DATE_AFFICHAGE));

        Button vueAlarmeActionRepeter = (Button)findViewById(R.id.vue_alarme_action_repeter);
        vueAlarmeActionRepeter.setOnClickListener(
                view -> controleurAlarme.repeterAlarme(getApplicationContext())
        );

        Button vueAlarmeActionArreter = (Button)findViewById(R.id.vue_alarme_action_arreter);
        vueAlarmeActionArreter.setOnClickListener(
                view -> controleurAlarme.arreterAlarme()
        );
    }

    @Override
    public int getIdDevoirParametre() {
        return this.idDevoirParametre;
    }

    @Override
    public void setDevoir(Devoir devoir) {
        this.devoir = devoir;
    }

    @Override
    public void naviguerAgenda() {
        this.finish();
        startActivity(new Intent(getApplicationContext(), Agenda.class));
    }
}
