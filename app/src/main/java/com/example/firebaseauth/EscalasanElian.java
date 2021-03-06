package com.example.firebaseauth;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EscalasanElian extends AppCompatActivity {
    TextView textView2;
    TabLayout tabLayout;
    ViewPager2 Pager2;
    Fragmentadapter adapter;
    Toast activo;
    boolean cancelartoast = false;

    String rutdelpaciente, llave = "rutdelpaciente";
    String nombreherida, key = "nombreherida";
   // Button btnfalanges, btnmetatarsiano, btntarsal;
    int locinicial,locactual,numheridas,isquemia, infeccion, edema, neuropatia, area, profundidad, cicatrizacion;
    String slocinicial,slocactual,snumheridas,sisquemia, sinfeccion, sedema, sneuropatia, sarea, sprofundidad, scicatrizacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevasanelian);
        Bundle bundle = getIntent().getExtras();
        tabLayout = findViewById(R.id.tab_layout); //https://www.youtube.com/watch?v=5-RWOvJ9oq8&ab_channel=Code2Develop
        Pager2 = findViewById(R.id.viewpager2);
        TextView textinfo;
        textinfo = findViewById(R.id.textView11);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new Fragmentadapter(fm, getLifecycle());
        Pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Localizaci??n"));
        tabLayout.addTab(tabLayout.newTab().setText("Estado"));
        tabLayout.addTab(tabLayout.newTab().setText("Dimensi??n"));
       // btnfalanges=findViewById(R.id.btnfalange);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Pager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //textView2=findViewById(R.id.textView2);
        rutdelpaciente = bundle.getString(llave);
        nombreherida = bundle.getString(key);
        //textView2.setText(rutdelpaciente);

    }

    public void informaciondeescala(View view) {
        TextView textinfo;
        textinfo = findViewById(R.id.textView11);
        textinfo.setVisibility(View.VISIBLE);
    }

    private void cancelartoast(){
        if(activo!=null){
            activo.cancel();}

    }
    private void showtoast(String caracteristica){
        activo = Toast.makeText(this,caracteristica,Toast.LENGTH_LONG);
        activo.show();

    }

    //botones de estcala san elian fragment firstde localizacion

    public void setcolorfalange(View view) {
        Button btnfalange = findViewById(R.id.btnfalange);
        Button btnmetatarso= findViewById(R.id.btnmetatarsal);
        Button btntarsal = findViewById(R.id.btntarsal);
        locinicial = 1;
        slocinicial="Falanges: dedos del pie";

        cancelartoast();
        showtoast(slocinicial);


        btnfalange.setBackgroundResource(R.drawable.colorclick);
        btnmetatarso.setBackgroundResource(R.drawable.templatebotonamarillo);
        btntarsal.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void setmetatarsal(View view) {
        Button btnfalange = findViewById(R.id.btnfalange);
        Button btnmetatarso= findViewById(R.id.btnmetatarsal);
        Button btntarsal = findViewById(R.id.btntarsal);
        locinicial=2;
        slocinicial="Metatarsal: de los dedos hasta la mitad del pie";
        cancelartoast();
        showtoast(slocinicial);

        btnfalange.setBackgroundResource(R.drawable.templatebotonverde);
        btnmetatarso.setBackgroundResource(R.drawable.colorclick);
        btntarsal.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void settarsal(View view) {
        Button btnfalange = findViewById(R.id.btnfalange);
        Button btnmetatarso= findViewById(R.id.btnmetatarsal);
        Button btntarsal = findViewById(R.id.btntarsal);
        locinicial= 3;
        slocinicial= "Tarsak zona del talon";
        cancelartoast();
        showtoast(slocinicial);

        btnfalange.setBackgroundResource(R.drawable.templatebotonverde);
        btnmetatarso.setBackgroundResource(R.drawable.templatebotonamarillo);
        btntarsal.setBackgroundResource(R.drawable.colorclick);
    }
    //botones localizacion actual

    public void setdorsal(View view) {
        Button btndorsal= findViewById(R.id.btndorsal);
        Button btnmedial = findViewById(R.id.btnmedial);
        Button btndosomas = findViewById(R.id.btndosomas);
        locactual = 1;
        slocactual="Dorsal o Plantar, dedos de pie";
        cancelartoast();
        showtoast(slocactual);


        btndorsal.setBackgroundResource(R.drawable.colorclick);
        btnmedial.setBackgroundResource(R.drawable.templatebotonamarillo);
        btndosomas.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void setmedial(View view) {
        Button btndorsal= findViewById(R.id.btndorsal);
        Button btnmedial = findViewById(R.id.btnmedial);
        Button btndosomas = findViewById(R.id.btndosomas);
        locactual = 2;
        slocactual="Medial o lateral, tobillo";
        cancelartoast();
        showtoast(slocactual);

        btndorsal.setBackgroundResource(R.drawable.templatebotonverde);
        btnmedial.setBackgroundResource(R.drawable.colorclick);
        btndosomas.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void setdosomas(View view) {
        Button btndorsal= findViewById(R.id.btndorsal);
        Button btnmedial = findViewById(R.id.btnmedial);
        Button btndosomas = findViewById(R.id.btndosomas);
        locactual = 3;
        slocactual="Dos o mas zonas";
        cancelartoast();
        showtoast(slocactual);
        btndorsal.setBackgroundResource(R.drawable.templatebotonverde);
        btnmedial.setBackgroundResource(R.drawable.templatebotonamarillo);
        btndosomas.setBackgroundResource(R.drawable.colorclick);
    }


    public void setuno(View view) {
        Button btnuna = findViewById(R.id.btnuna);
        Button btndos = findViewById(R.id.btndos);
        Button btntresomas = findViewById(R.id.btntresomas);
        numheridas = 1;
        snumheridas="una zona afectada";
        cancelartoast();
        showtoast(snumheridas);

        btnuna.setBackgroundResource(R.drawable.colorclick);
        btndos.setBackgroundResource(R.drawable.templatebotonamarillo);
        btntresomas.setBackgroundResource(R.drawable.templatebotonrojo);

    }
    public void setdos(View view) {
        Button btnuna = findViewById(R.id.btnuna);
        Button btndos = findViewById(R.id.btndos);
        Button btntresomas = findViewById(R.id.btntresomas);
        numheridas = 2;
        snumheridas="Dos zonas afectada";
        cancelartoast();
        showtoast(snumheridas);

        btnuna.setBackgroundResource(R.drawable.templatebotonverde);
        btndos.setBackgroundResource(R.drawable.colorclick);
        btntresomas.setBackgroundResource(R.drawable.templatebotonrojo);
    }
    public void settresomas(View view) {
        Button btnuna = findViewById(R.id.btnuna);
        Button btndos = findViewById(R.id.btndos);
        Button btntresomas = findViewById(R.id.btntresomas);
        numheridas = 3;
        snumheridas="Tres o mass";
        cancelartoast();
        showtoast(snumheridas);

        btnuna.setBackgroundResource(R.drawable.templatebotonverde);
        btndos.setBackgroundResource(R.drawable.templatebotonamarillo);
        btntresomas.setBackgroundResource(R.drawable.colorclick);
    }

    //Boteones secondactiviti
    public void sinisquemia(View view) {
        Button sinisquemia = findViewById(R.id.sinisquemia);
        Button pulsopalpable= findViewById(R.id.palpablesdebiles);
        Button pocopalpable = findViewById(R.id.pocopalpables);
        Button sinpulso = findViewById(R.id.sinpulso);
        isquemia = 0;
        sisquemia = "sin isquemia";
        cancelartoast();
        showtoast(sisquemia);
        sinisquemia.setBackgroundResource(R.drawable.colorclick);
        pulsopalpable.setBackgroundResource(R.drawable.templatebotonverde);
        pocopalpable.setBackgroundResource(R.drawable.templatebotonamarillo);
        sinpulso.setBackgroundResource(R.drawable.templatebotonrojo);

    }

    public void pulsopalpable(View view) {
        Button sinisquemia = findViewById(R.id.sinisquemia);
        Button pulsopalpable= findViewById(R.id.palpablesdebiles);
        Button pocopalpable = findViewById(R.id.pocopalpables);
        Button sinpulso = findViewById(R.id.sinpulso);
        isquemia = 1;
        sisquemia = "Pulsos palpables levemente disminuidos";
        cancelartoast();
        showtoast(sisquemia);
        sinisquemia.setBackgroundResource(R.drawable.botontemplate);
        pulsopalpable.setBackgroundResource(R.drawable.colorclick);
        pocopalpable.setBackgroundResource(R.drawable.templatebotonamarillo);
        sinpulso.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void pocopalpable(View view) {
        Button sinisquemia = findViewById(R.id.sinisquemia);
        Button pulsopalpable= findViewById(R.id.palpablesdebiles);
        Button pocopalpable = findViewById(R.id.pocopalpables);
        Button sinpulso = findViewById(R.id.sinpulso);
        isquemia = 2;
        sisquemia = "Pulsos debiles poco palpables";
        cancelartoast();
        showtoast(sisquemia);
        sinisquemia.setBackgroundResource(R.drawable.botontemplate);
        pulsopalpable.setBackgroundResource(R.drawable.templatebotonverde);
        pocopalpable.setBackgroundResource(R.drawable.colorclick);
        sinpulso.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void sinpulsos(View view) {
        Button sinisquemia = findViewById(R.id.sinisquemia);
        Button pulsopalpable= findViewById(R.id.palpablesdebiles);
        Button pocopalpable = findViewById(R.id.pocopalpables);
        Button sinpulso = findViewById(R.id.sinpulso);
        isquemia = 3;
        sisquemia = "sin pulsos palpables";
        cancelartoast();
        showtoast(sisquemia);
        sinisquemia.setBackgroundResource(R.drawable.botontemplate);
        pulsopalpable.setBackgroundResource(R.drawable.templatebotonverde);
        pocopalpable.setBackgroundResource(R.drawable.templatebotonamarillo);
        sinpulso.setBackgroundResource(R.drawable.colorclick);
    }


    public void btnsinedema(View view) {
        Button sinedema = findViewById(R.id.sinedema);
        Button edemaleve = findViewById(R.id.leve);
        Button edemamoderado = findViewById(R.id.edemamoderado);
        Button edemagrave = findViewById(R.id.edemagrave);

        edema= 0;
        sedema = "sin edema aparente";
        cancelartoast();
        showtoast(sedema);

        sinedema.setBackgroundResource(R.drawable.colorclick);
        edemaleve.setBackgroundResource(R.drawable.templatebotonverde);
        edemamoderado.setBackgroundResource(R.drawable.templatebotonamarillo);
        edemagrave.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void btnleve(View view) {
        Button sinedema = findViewById(R.id.sinedema);
        Button edemaleve = findViewById(R.id.leve);
        Button edemamoderado = findViewById(R.id.edemamoderado);
        Button edemagrave = findViewById(R.id.edemagrave);

        cancelartoast();
        edema= 1;
        sedema = "Edema leve <2cm, inflamcion, dolor local, induracion, calor, secrecion porulenta de la ulcera afecta tejido subcutaneo";
        showtoast(sedema);

        sinedema.setBackgroundResource(R.drawable.botontemplate);
        edemaleve.setBackgroundResource(R.drawable.colorclick);
        edemamoderado.setBackgroundResource(R.drawable.templatebotonamarillo);
        edemagrave.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void btnmoderadoedema(View view) {
        Button sinedema = findViewById(R.id.sinedema);
        Button edemaleve = findViewById(R.id.leve);
        Button edemamoderado = findViewById(R.id.edemamoderado);
        Button edemagrave = findViewById(R.id.edemagrave);

        edema= 2;
        sedema = "Edema Moderado: afecta >50% en un solo pie";
        cancelartoast();
        showtoast(sedema);
        sinedema.setBackgroundResource(R.drawable.botontemplate);
        edemaleve.setBackgroundResource(R.drawable.templatebotonverde);
        edemamoderado.setBackgroundResource(R.drawable.colorclick);
        edemagrave.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void edemagrave(View view) {
        Button sinedema = findViewById(R.id.sinedema);
        Button edemaleve = findViewById(R.id.leve);
        Button edemamoderado = findViewById(R.id.edemamoderado);
        Button edemagrave = findViewById(R.id.edemagrave);
        cancelartoast();

        edema= 3;
        sedema = "Edema Grave: ambas extremidades comprometidas";
        cancelartoast();
        showtoast(sedema);

        sinedema.setBackgroundResource(R.drawable.botontemplate);
        edemaleve.setBackgroundResource(R.drawable.templatebotonverde);
        edemamoderado.setBackgroundResource(R.drawable.templatebotonamarillo);
        edemagrave.setBackgroundResource(R.drawable.colorclick);
    }


    public void sineuropatia(View view) {
        Button sinneuro = findViewById(R.id.sinneuropatia);
        Button neuroinicial = findViewById(R.id.neuropatiainicial);
        Button neuroavanzada = findViewById(R.id.neuropatiavanzada);
        Button neurograve = findViewById(R.id.neurograve);
        neuropatia = 0;
        sneuropatia= "sin sintomas";
        cancelartoast();
        showtoast(sneuropatia);


        sinneuro.setBackgroundResource(R.drawable.colorclick);
        neuroinicial.setBackgroundResource(R.drawable.templatebotonverde);
        neuroavanzada.setBackgroundResource(R.drawable.templatebotonamarillo);
        neurograve.setBackgroundResource(R.drawable.templatebotonrojo);    }

    public void neuropatialeve(View view) {
        Button sinneuro = findViewById(R.id.sinneuropatia);
        Button neuroinicial = findViewById(R.id.neuropatiainicial);
        Button neuroavanzada = findViewById(R.id.neuropatiavanzada);
        Button neurograve = findViewById(R.id.neurograve);

        neuropatia = 1;
        sneuropatia= "sensibilidad disminuida 2 de 3 puntos";
        cancelartoast();
        showtoast(sneuropatia);

        sinneuro.setBackgroundResource(R.drawable.botontemplate);
        neuroinicial.setBackgroundResource(R.drawable.colorclick);
        neuroavanzada.setBackgroundResource(R.drawable.templatebotonamarillo);
        neurograve.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void neuropatiamoderada(View view) {
        Button sinneuro = findViewById(R.id.sinneuropatia);
        Button neuroinicial = findViewById(R.id.neuropatiainicial);
        Button neuroavanzada = findViewById(R.id.neuropatiavanzada);
        Button neurograve = findViewById(R.id.neurograve);

        neuropatia = 2;
        sneuropatia= "Avanzada sensibilidad protectora ausente en 2 de 3 puntos";
        cancelartoast();
        showtoast(sneuropatia);

        sinneuro.setBackgroundResource(R.drawable.botontemplate);
        neuroinicial.setBackgroundResource(R.drawable.templatebotonverde);
        neuroavanzada.setBackgroundResource(R.drawable.colorclick);
        neurograve.setBackgroundResource(R.drawable.templatebotonrojo);
    }
    public void neuropatiagrave(View view) {
        Button sinneuro = findViewById(R.id.sinneuropatia);
        Button neuroinicial = findViewById(R.id.neuropatiainicial);
        Button neuroavanzada = findViewById(R.id.neuropatiavanzada);
        Button neurograve = findViewById(R.id.neurograve);

        neuropatia = 3;
        sneuropatia= "presencia de pie charcot neuro-osteoartropatia diabetica";
        cancelartoast();
        showtoast(sneuropatia);
        sinneuro.setBackgroundResource(R.drawable.botontemplate);
        neuroinicial.setBackgroundResource(R.drawable.templatebotonverde);
        neuroavanzada.setBackgroundResource(R.drawable.templatebotonamarillo);
        neurograve.setBackgroundResource(R.drawable.colorclick);
    }


    public void sininfeccion(View view) {
        Button sininf = findViewById(R.id.sininfeccion);
        Button infleve = findViewById(R.id.infleve);
        Button infmoderada = findViewById(R.id.infmoderada);
        Button infgrave = findViewById(R.id.infgrave);

        infeccion = 0;
        sinfeccion= "no presenta signos de infeccion bajo los criterios SIRS";
        cancelartoast();
        showtoast(sinfeccion);

        sininf.setBackgroundResource(R.drawable.colorclick);
        infleve.setBackgroundResource(R.drawable.templatebotonverde);
        infmoderada.setBackgroundResource(R.drawable.templatebotonamarillo);
        infgrave.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void infleve(View view) {
        Button sininf = findViewById(R.id.sininfeccion);
        Button infleve = findViewById(R.id.infleve);
        Button infmoderada = findViewById(R.id.infmoderada);
        Button infgrave = findViewById(R.id.infgrave);
        infeccion = 1;
        sinfeccion= "Eritema >2 cm, exposicion de musculo, tendon, articulacion o hueso abcesos";
        cancelartoast();
        showtoast(sinfeccion);
        sininf.setBackgroundResource(R.drawable.botontemplate);
        infleve.setBackgroundResource(R.drawable.colorclick);
        infmoderada.setBackgroundResource(R.drawable.templatebotonamarillo);
        infgrave.setBackgroundResource(R.drawable.templatebotonrojo);

    }
    public void infmoderada(View view) {
        Button sininf = findViewById(R.id.sininfeccion);
        Button infleve = findViewById(R.id.infleve);
        Button infmoderada = findViewById(R.id.infmoderada);
        Button infgrave = findViewById(R.id.infgrave);
        infeccion = 2;
        sinfeccion= "Eritema >2 cm, exposicion de musculo, tendon, articulacion o hueso abcesos";
        cancelartoast();
        showtoast(sinfeccion);
        sininf.setBackgroundResource(R.drawable.botontemplate);
        infleve.setBackgroundResource(R.drawable.templatebotonverde);
        infmoderada.setBackgroundResource(R.drawable.colorclick);
        infgrave.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void infgrave(View view) {
        Button sininf = findViewById(R.id.sininfeccion);
        Button infleve = findViewById(R.id.infleve);
        Button infmoderada = findViewById(R.id.infmoderada);
        Button infgrave = findViewById(R.id.infgrave);
        infeccion = 3;
        sinfeccion= "Singos de compromiso sistemico y/orespuesta inflamatoria sistemica";
        cancelartoast();
        showtoast(sinfeccion);
        sininf.setBackgroundResource(R.drawable.botontemplate);
        infleve.setBackgroundResource(R.drawable.templatebotonverde);
        infmoderada.setBackgroundResource(R.drawable.templatebotonamarillo);
        infgrave.setBackgroundResource(R.drawable.colorclick);
    }

    //tercer fragment de evaluacion

    public void psuperficial(View view) {
        Button psuperficial = findViewById(R.id.profundidad);
        Button pparcial = findViewById(R.id.pparcial);
        Button profunda = findViewById(R.id.profunda);

        profundidad = 1;
        sprofundidad = "epidermis,dermis,hipodermis";
        cancelartoast();
        showtoast(sprofundidad);
        psuperficial.setBackgroundResource(R.drawable.colorclick);
        pparcial.setBackgroundResource(R.drawable.templatebotonamarillo);
        profunda.setBackgroundResource(R.drawable.templatebotonrojo);


    }

    public void pparcial(View view) {
        Button psuperficial = findViewById(R.id.profundidad);
        Button pparcial = findViewById(R.id.pparcial);
        Button profunda = findViewById(R.id.profunda);

        profundidad = 2;
        sprofundidad = "afectamusculo, fascias o tendones";
        cancelartoast();
        showtoast(sprofundidad);
        psuperficial.setBackgroundResource(R.drawable.templatebotonverde);
        pparcial.setBackgroundResource(R.drawable.colorclick);
        profunda.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void profunda(View view) {
        Button psuperficial = findViewById(R.id.profundidad);
        Button pparcial = findViewById(R.id.pparcial);
        Button profunda = findViewById(R.id.profunda);

        profundidad = 3;
        sprofundidad = "huesos y articulaciones";
        cancelartoast();
        showtoast(sprofundidad);
        psuperficial.setBackgroundResource(R.drawable.templatebotonverde);
        pparcial.setBackgroundResource(R.drawable.templatebotonamarillo);
        profunda.setBackgroundResource(R.drawable.colorclick);
    }


    public void heridapequena(View view) {
        Button Hchica = findViewById(R.id.heridapeque??a);
        Button Hmediana = findViewById(R.id.heridamediana);
        Button Hgrande = findViewById(R.id.heridagrande);

        area = 1;
        sarea = "menor a 10 cm2 (equivalente a 2 monedas de 500)";
        cancelartoast();
        showtoast(sarea);

        Hchica.setBackgroundResource(R.drawable.colorclick);
        Hmediana.setBackgroundResource(R.drawable.templatebotonamarillo);
        Hgrande.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void heridamediana(View view) {
        Button Hchica = findViewById(R.id.heridapeque??a);
        Button Hmediana = findViewById(R.id.heridamediana);
        Button Hgrande = findViewById(R.id.heridagrande);

        area = 2;
        sarea = "10 a 40 cm2";
        cancelartoast();
        showtoast(sarea);
        Hchica.setBackgroundResource(R.drawable.templatebotonverde);
        Hmediana.setBackgroundResource(R.drawable.colorclick);
        Hgrande.setBackgroundResource(R.drawable.templatebotonrojo);
    }

    public void heridagrande(View view) {
        Button Hchica = findViewById(R.id.heridapeque??a);
        Button Hmediana = findViewById(R.id.heridamediana);
        Button Hgrande = findViewById(R.id.heridagrande);

        area = 3;
        sarea = "mayor a 40cm2)";
        cancelartoast();
        showtoast(sarea);
        Hchica.setBackgroundResource(R.drawable.templatebotonverde);
        Hmediana.setBackgroundResource(R.drawable.templatebotonamarillo);
        Hgrande.setBackgroundResource(R.drawable.colorclick);
    }


    public void inflamado(View view) {
        Button inflamado = findViewById(R.id.inflamado);
        Button epitelizado = findViewById(R.id.epitelizacion);
        Button granulacion = findViewById(R.id.granulacion);

        cicatrizacion = 3;
        scicatrizacion = "fase inflamatoria";
        cancelartoast();
        showtoast(scicatrizacion);
        inflamado.setBackgroundResource(R.drawable.colorclick);
        epitelizado.setBackgroundResource(R.drawable.templatebotonverde);
        granulacion.setBackgroundResource(R.drawable.templatebotonamarillo);
    }

    public void granulacion(View view) {
        Button inflamado = findViewById(R.id.inflamado);
        Button epitelizado = findViewById(R.id.epitelizacion);
        Button granulacion = findViewById(R.id.granulacion);

        cicatrizacion = 2;
        scicatrizacion = "fase Granulatoria";
        cancelartoast();
        showtoast(scicatrizacion);
        inflamado.setBackgroundResource(R.drawable.templatebotonrojo);
        epitelizado.setBackgroundResource(R.drawable.templatebotonverde);
        granulacion.setBackgroundResource(R.drawable.colorclick);
    }

    public void cicatrizacion(View view) {
        Button inflamado = findViewById(R.id.inflamado);
        Button epitelizado = findViewById(R.id.epitelizacion);
        Button granulacion = findViewById(R.id.granulacion);

        cicatrizacion = 1;
        scicatrizacion = "fase epitelizacion";
        cancelartoast();
        showtoast(scicatrizacion);
        inflamado.setBackgroundResource(R.drawable.templatebotonrojo);
        epitelizado.setBackgroundResource(R.drawable.colorclick);
        granulacion.setBackgroundResource(R.drawable.templatebotonamarillo);
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference load = db.collection("datosPacienteins").document("paciente");
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String currentDateandTime = sdf.format(new Date());

    public void guardardatos(View view) {
        int puntaje = locinicial + locactual +numheridas+isquemia+infeccion+ edema+ neuropatia+ area+ profundidad+ cicatrizacion;
        resultadospuntaje result = new resultadospuntaje(slocinicial,slocactual,snumheridas,sisquemia,sinfeccion,sedema,sneuropatia,sarea,scicatrizacion,sprofundidad,puntaje);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(rutdelpaciente);
        myRef.child("resultados").setValue(result);
        db.collection("datosPacienteins").document(rutdelpaciente).collection("resultados").document(nombreherida + currentDateandTime).set(result)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        cancelartoast();
                        Toast.makeText(EscalasanElian.this,"Paciente registrado correctamente",Toast.LENGTH_LONG).show();
                        //leerdatos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

}
//resultadospuntaje(puntaje,slocinicial,slocactual,snumheridas,sisquemia,sinfeccion,
//                sedema,sneuropatia,sarea,scicatrizacion,sprofundidad)