package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurAgenda;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

//TODO : OK
public class Agenda extends AppCompatActivity implements VueAgenda{

    protected List<Devoir> listeDevoir;
    protected ControleurAgenda controleurAgenda = new ControleurAgenda(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_agenda);

        Button vueAgendaActionNaviguerAjouterDevoir =
                (Button)findViewById(R.id.vue_agenda_action_ajouter_devoir);

        vueAgendaActionNaviguerAjouterDevoir.setOnClickListener(arg0 ->
            controleurAgenda.actionNaviguerAjouterDevoir());
        
        controleurAgenda.onCreate(getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int activite, int resultat, Intent donnees) {
        super.onActivityResult(activite, resultat, donnees);
        controleurAgenda.onActivityResult(activite);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<HashMap<String, String>> adapterListeDevoirPourListView() {
        List<HashMap<String, String>> listeDevoirPourListView =
                new ArrayList<>();

        for(Devoir devoir: listeDevoir) {
            listeDevoirPourListView.add(devoir.obtenirDevoirPourAdapteur());
        }

        return listeDevoirPourListView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afficherTousLesDevoirs() {
        ListView vueAgendaListeDevoir = (ListView) findViewById(R.id.vue_agenda_liste_devoir);

        SimpleAdapter adapteurVueAgendaListeDevoir = new SimpleAdapter(
                this,
                adapterListeDevoirPourListView(),
                android.R.layout.two_line_list_item,
                new String[] {Devoir.CLE_MATIERE,Devoir.CLE_SUJET},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueAgendaListeDevoir.setAdapter(adapteurVueAgendaListeDevoir);

        vueAgendaListeDevoir.setOnItemClickListener(
                (parent, vue, positionDansAdapteur, positionItem) -> {
                    Log.d("Agenda", "onItemClick");
                    ListView vueAgendaListeDevoirOnClick = (ListView)vue.getParent();

                    HashMap<String,String> devoir =
                            (HashMap<String, String>)
                                    vueAgendaListeDevoirOnClick.getItemAtPosition((int)positionItem);

                    controleurAgenda.actionNaviguerModifierDevoir(devoir.get(Devoir.CLE_ID_DEVOIR));
                }
        );
    }

    @Override
    public void setListeDevoir(List<Devoir> listeDevoir) {
        this.listeDevoir = listeDevoir;
    }

    @Override
    public void naviguerAjouterDevoir() {
        Intent intentionNaviguerAjouterDevoir = new Intent(this, AjouterDevoir.class);
        startActivityForResult(intentionNaviguerAjouterDevoir, ControleurAgenda.ACTIVITE_AJOUTER_DEVOIR);
    }

    @Override
    public void naviguerModifierDevoir(String idDevoir) {
        Intent intentionNaviguerModifierDevoir = new Intent(
                Agenda.this,
                ModifierDevoir.class
        );
        intentionNaviguerModifierDevoir.putExtra(Devoir.CLE_ID_DEVOIR, idDevoir);

        startActivityForResult(intentionNaviguerModifierDevoir,
                ControleurAgenda.ACTIVITE_MODIFIER_DEVOIR);
    }
}
