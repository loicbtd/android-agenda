package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurAjouterDevoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

// TODO : OK 1
//public class AjouterDevoir extends AppCompatActivity implements View.OnClickListener {
public class AjouterDevoir extends AppCompatActivity implements VueAjouterDevoir {

    protected EditText vueAjouterDevoirChampMatiere;
    protected EditText vueAjouterDevoirChampSujet;
    protected Button vueAjouterDevoirActionChoisirHoraire;

    protected ControleurAjouterDevoir controleurAjouterDevoir = new ControleurAjouterDevoir(this);

    public Button getVueAjouterDevoirActionChoisirHoraire() {
        return this.vueAjouterDevoirActionChoisirHoraire;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_devoir);

        vueAjouterDevoirChampMatiere = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);
        vueAjouterDevoirChampSujet = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);

        Button vueAjouterDevoirActionEnregistrerDevoir =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_enregistrer);
        vueAjouterDevoirActionEnregistrerDevoir.setOnClickListener(
                view -> enregistrerDevoir()
        );

        vueAjouterDevoirActionChoisirHoraire =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_choisir_horaire);
        vueAjouterDevoirActionChoisirHoraire.setText("Choisir l'horaire");
        vueAjouterDevoirActionChoisirHoraire.setOnClickListener(
                view -> controleurAjouterDevoir.choisirHoraire()
        );


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
        // TODO : remplacer le LocalDateTime.now
        Devoir devoir = new Devoir(
                0,
                vueAjouterDevoirChampMatiere.getText().toString(),
                vueAjouterDevoirChampSujet.getText().toString(),
                LocalDateTime.now()
        );
        controleurAjouterDevoir.actionEnregistrerDevoir(devoir);
    }
}
