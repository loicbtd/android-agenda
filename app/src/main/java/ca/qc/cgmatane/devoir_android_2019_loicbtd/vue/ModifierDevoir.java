package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.time.LocalDateTime;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurModifierDevoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class ModifierDevoir extends AppCompatActivity implements VueModifierDevoir, View.OnClickListener {

    protected EditText vueModifierDevoirChampSujet;
    protected EditText vueModifierDevoirChampMatiere;
    protected LocalDateTime horaire;
    protected boolean alarme_active;
    protected Button vueModifierDevoirActionChoisirHoraire;
    protected TimePickerDialog selectionneurHoraire;
    protected DatePickerDialog selectionneurDate;

    protected ControleurModifierDevoir controleurModifierDevoir = new ControleurModifierDevoir(this);
    protected String idDevoirParametre;
    protected Devoir devoir;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_devoir);

        Bundle parametres = this.getIntent().getExtras();
        idDevoirParametre = (String) parametres.get(Devoir.CLE_ID_DEVOIR);

        controleurModifierDevoir.onCreate(getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void enregistrerDevoir() {
        devoir.setMatiere(vueModifierDevoirChampMatiere.getText().toString());
        devoir.setSujet(vueModifierDevoirChampSujet.getText().toString());
        devoir.setHoraire(horaire);
        devoir.setAlarme_active(alarme_active);
        controleurModifierDevoir.actionEnregistrerDevoir(devoir);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afficherDevoir() {
        vueModifierDevoirChampMatiere = (EditText)findViewById(R.id.vue_modifier_devoir_champ_matiere);
        vueModifierDevoirChampMatiere.setText(devoir.getMatiere());

        vueModifierDevoirChampSujet = (EditText)findViewById(R.id.vue_modifier_devoir_champ_sujet);
        vueModifierDevoirChampSujet.setText(devoir.getSujet());

        horaire = devoir.getHoraire();
        alarme_active = devoir.isAlarme_active();

        Switch switchAlarme = (Switch)findViewById(R.id.vue_modifier_devoir_switch_alarme);
        switchAlarme.setChecked(alarme_active);
        switchAlarme.setOnCheckedChangeListener(
                (compoundButton, b) -> alarme_active = b
        );

        Button vueModifierDevoirActionEnregistrer =
                (Button)findViewById(R.id.vue_modifier_devoir_action_enregistrer);
        vueModifierDevoirActionEnregistrer.setOnClickListener(arg0 ->
                enregistrerDevoir());

        vueModifierDevoirActionChoisirHoraire =
                (Button)findViewById(R.id.vue_modifier_devoir_action_choisir_horaire);
        vueModifierDevoirActionChoisirHoraire.setText(horaire.format(Devoir.FORMAT_DATE_AFFICHAGE));
        vueModifierDevoirActionChoisirHoraire.setOnClickListener(this);
    }

    @Override
    public void setDevoir(Devoir devoir) {
        this.devoir = devoir;
    }

    @Override
    public String getIdDevoirParametre() {
        return idDevoirParametre;
    }

    @Override
    public void naviguerAgenda() {
        this.finish();
    }

    @Override
    public void afficherErreur() {
        Toast message = Toast.makeText(this.getApplicationContext(),
                "Le devoir n'est pas valide.",
                Toast.LENGTH_SHORT);
        message.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        selectionneurHoraire = new TimePickerDialog(this, (view1, h, m) -> {
            horaire = LocalDateTime.of(horaire.getYear(),
                    horaire.getMonthValue(), horaire.getDayOfMonth(), h, m);
            vueModifierDevoirActionChoisirHoraire.setText(horaire.format(Devoir.FORMAT_DATE_AFFICHAGE));
        }, horaire.getHour(), horaire.getMinute(), true);

        selectionneurDate = new DatePickerDialog(this, (view2, a, m, j) -> {
            horaire = LocalDateTime.of(a, m+1, j, horaire.getHour(), horaire.getMinute());
            selectionneurHoraire.show();
        }, horaire.getYear(), horaire.getMonthValue()-1, horaire.getDayOfMonth());

        selectionneurDate.show();
    }
}