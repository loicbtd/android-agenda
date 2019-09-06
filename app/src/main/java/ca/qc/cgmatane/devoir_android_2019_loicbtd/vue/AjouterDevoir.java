package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class AjouterDevoir extends AppCompatActivity {

    protected EditText vueAjouterDevoirChampMatiere;
    protected EditText vueAjouterDevoirChampSujet;

    protected DevoirDAO accesseurDevoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_devoir);

        vueAjouterDevoirChampMatiere = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);
        vueAjouterDevoirChampSujet = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);

        Button vueAjouterDevoirActionEnregistrerDevoir =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_enregistrer);

        vueAjouterDevoirActionEnregistrerDevoir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enregistrerDevoir();
                    }
                }
        );
    }

    private void enregistrerDevoir() {

        accesseurDevoir = DevoirDAO.getInstance();

        Devoir devoir = new Devoir(
                vueAjouterDevoirChampMatiere.getText().toString(),
                vueAjouterDevoirChampSujet.getText().toString(), 0
        );

        accesseurDevoir.ajouterDevoir(devoir);

        naviguerRetourAgenda();
    }

    private void naviguerRetourAgenda() {
        this.finish();
    }
}
