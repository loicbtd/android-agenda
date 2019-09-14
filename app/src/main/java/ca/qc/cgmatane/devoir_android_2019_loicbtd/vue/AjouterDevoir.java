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
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurAjouterDevoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class AjouterDevoir extends AppCompatActivity implements VueAjouterDevoir, View.OnClickListener {

    protected EditText vueAjouterDevoirChampMatiere;
    protected EditText vueAjouterDevoirChampSujet;
    protected LocalDateTime horaire;
    protected boolean alarme_active;
    protected Button vueAjouterDevoirActionChoisirHoraire;
    protected TimePickerDialog selectionneurHoraire;
    protected DatePickerDialog selectionneurDate;

    protected ControleurAjouterDevoir controleurAjouterDevoir = new ControleurAjouterDevoir(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_devoir);

        vueAjouterDevoirChampMatiere = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_matiere);
        vueAjouterDevoirChampSujet = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);
        horaire = LocalDateTime.now();
        alarme_active = true;

        Switch switchAlarme = (Switch)findViewById(R.id.vue_ajouter_devoir_switch_alarme);
        switchAlarme.setChecked(true);
        switchAlarme.setOnCheckedChangeListener(
                (compoundButton, b) -> alarme_active = b
        );

        Button vueAjouterDevoirActionEnregistrerDevoir =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_enregistrer);
        vueAjouterDevoirActionEnregistrerDevoir.setOnClickListener(
                view -> enregistrerDevoir()
        );

        vueAjouterDevoirActionChoisirHoraire =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_choisir_horaire);
        vueAjouterDevoirActionChoisirHoraire.setText("Choisir l'horaire");
        vueAjouterDevoirActionChoisirHoraire.setOnClickListener(this);

        controleurAjouterDevoir.onCreate(getApplicationContext());
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
    private void enregistrerDevoir() {
        Devoir devoir = new Devoir(
                0,
                vueAjouterDevoirChampMatiere.getText().toString(),
                vueAjouterDevoirChampSujet.getText().toString(),
                horaire,
                alarme_active
        );
        controleurAjouterDevoir.actionEnregistrerDevoir(devoir);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        selectionneurHoraire = new TimePickerDialog(this, (view1, h, m) -> {
            horaire = LocalDateTime.of(horaire.getYear(),
                    horaire.getMonthValue(), horaire.getDayOfMonth(), h, m);
            vueAjouterDevoirActionChoisirHoraire.setText(horaire.format(Devoir.FORMAT_DATE_AFFICHAGE));
        }, horaire.getHour(), horaire.getMinute(), true);

        selectionneurDate = new DatePickerDialog(this, (view2, a, m, j) -> {
            horaire = LocalDateTime.of(a, m+1, j, horaire.getHour(), horaire.getMinute());
            selectionneurHoraire.show();
        }, horaire.getYear(), horaire.getMonthValue()-1, horaire.getDayOfMonth());

        selectionneurDate.show();
    }
}
