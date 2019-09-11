package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurAlarme;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurModifierDevoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class Alarme extends AppCompatActivity implements VueAlarme {

    protected TextView vueAlarmeLabelMatiere;
    protected TextView vueAlarmeLabelSujet;
    protected TextView vueAlarmeLabelHoraire;

    protected String idDevoirParametre;
    protected Devoir devoir;

    protected ControleurAlarme controleurAlarme = new ControleurAlarme(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_alarme);

        Bundle parametres = this.getIntent().getExtras();
        idDevoirParametre = (String) parametres.get(Devoir.CLE_ID_DEVOIR);

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

        DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/YYYY à HH:mm");
        vueAlarmeLabelSujet.setText(devoir.getHoraire().format(formateur));

        Button vueAlarmeActionRepeter = (Button)findViewById(R.id.vue_alarme_action_repeter);
        vueAlarmeActionRepeter.setOnClickListener(
                view -> repeterAlarme()
        );

        Button vueAlarmeActionArreter = (Button)findViewById(R.id.vue_alarme_action_arreter);
        vueAlarmeActionArreter.setOnClickListener(
                view -> arreterAlarme()
        );
    }

    @Override
    public String getIdDevoirParametre() {
        return this.idDevoirParametre;
    }

    @Override
    public void setDevoir(Devoir devoir) {
        this.devoir = devoir;
    }

    @Override
    public void naviguerAgenda() {
        this.finish();
    }

    private void repeterAlarme() {
        naviguerAgenda();
    }

    private void arreterAlarme() {
        naviguerAgenda();
    }
}
