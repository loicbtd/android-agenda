package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;

public class Agenda extends AppCompatActivity {

    static final public int ACTIVITE_AJOUTER_LIVRE = 1;

    protected ListView vueAgendaListeDevoir;
    protected List<HashMap<String, String>> listeDevoirPourAdapteur;

    protected DevoirDAO accesseurDevoir;

    protected Intent intentionNaviguerAjouterDevoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_agenda);

        afficherTousLesDevoirs();

        intentionNaviguerAjouterDevoir = new Intent(this, AjouterDevoir.class);

        Button vueAgendaActionNaviguerAjouterLivre =
                (Button)findViewById(R.id.vue_agenda_action_ajouter_devoir);

        vueAgendaActionNaviguerAjouterLivre.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivityForResult(intentionNaviguerAjouterDevoir, Agenda.ACTIVITE_AJOUTER_LIVRE);
                    }
                }
        );
    }

    protected void afficherTousLesDevoirs() {
        accesseurDevoir = DevoirDAO.getInstance();

        vueAgendaListeDevoir = (ListView) findViewById(R.id.vue_agenda_liste_devoir);

        listeDevoirPourAdapteur = accesseurDevoir.recupererListeDevoirPourAdapteur();

        SimpleAdapter adapteurVueListeDevoir = new SimpleAdapter(
                this,
                listeDevoirPourAdapteur,
                android.R.layout.two_line_list_item,
                new String[] {"matiere", "sujet"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueAgendaListeDevoir.setAdapter(adapteurVueListeDevoir);
    }
}
