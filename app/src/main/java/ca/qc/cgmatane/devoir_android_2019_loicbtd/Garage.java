package ca.qc.cgmatane.devoir_android_2019_loicbtd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Garage extends AppCompatActivity {

    protected ListView vueListeVoiture;
    protected List<HashMap<String, String>> listeVoiture;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_garage);

        vueListeVoiture = (ListView) findViewById(R.id.vue_garage_liste_voiture);

        listeVoiture = preparerListeVoiture();

        SimpleAdapter adapteurVueListeVoiture = new SimpleAdapter(
                this,
                listeVoiture,
                android.R.layout.two_line_list_item,
                new String[] {"modèle", "puissance"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeVoiture.setAdapter(adapteurVueListeVoiture);
    }

    private List<HashMap<String, String>> preparerListeVoiture() {

        List<HashMap<String, String>> listeVoiture =
                new ArrayList<HashMap<String, String>>();

        HashMap<String, String> voiture;

        voiture = new HashMap<String, String>();
        voiture.put("modèle", "Bugatti Chiron");
        voiture.put("puissance", "1500ch");
        listeVoiture.add(voiture);

        voiture = new HashMap<String, String>();
        voiture.put("modèle", "Bugatti Divo");
        voiture.put("puissance", "1500ch");
        listeVoiture.add(voiture);

        voiture = new HashMap<String, String>();
        voiture.put("modèle", "Bugatti Veyron");
        voiture.put("puissance", "1001ch");
        listeVoiture.add(voiture);

        return listeVoiture;
    }
}
