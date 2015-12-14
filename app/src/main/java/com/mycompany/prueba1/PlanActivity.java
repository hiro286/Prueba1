package com.mycompany.prueba1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import com.mycompany.prueba1.PlanMaker;

public class PlanActivity extends AppCompatActivity {

    public static final String USER_PREFS = "UserPrefsFile";

    private Button btnVer;
    private EditText numSemana;
    private EditText numDia;
    private TextInputLayout numSemanaLayout;
    private TextInputLayout numDiaLayout;
    //
    private Integer diaActual;//de la semana
    private Integer semanaActual;
    private Integer diasPorSemana= new Integer(-1);
    private Integer semanas   = new Integer(-1);
    private Integer ritmoBase = new Integer(-1);
    private Integer ritmoMeta = new Integer(-1);
    private Integer distMeta  = new Integer(-1);
    private Integer cantEntrene = new Integer(-1);
    private Integer diaTrabajo = new Integer(-1); //= semana * diaActual
    //
    private Integer tipoEntrene = 0;
    private Integer distEntrene = 0;
    private Integer tiempoTrabajo = 0;
    private Integer tiempoDescanso = 0;
    private Integer numeroVueltas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnVer = (Button)findViewById(R.id.btn_plan_ver);
        numSemana = (EditText)findViewById(R.id.txt_plan_semana);
        numDia    = (EditText)findViewById(R.id.txt_plan_dia);
        numSemanaLayout = (TextInputLayout)findViewById(R.id.TiL_plan_semana);
        numDiaLayout    = (TextInputLayout)findViewById(R.id.TiL_plan_dia);
        //
        numSemanaLayout.setErrorEnabled(true);
        numDiaLayout.setErrorEnabled(true);
        //
        diaActual = new Integer(0);
        semanaActual = new Integer(0);

        loadPreferences();

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bandera = 0;
                try{
                    semanaActual = Integer.parseInt(numSemana.getText().toString());
                    if (semanaActual > semanas){
                        numSemanaLayout.setError("Menor a "+semanas+" !");
                        bandera += 1;
                    }else if (semanaActual <= 0){
                        numSemanaLayout.setError("Mayor a 0 !");
                        bandera += 1;
                    }else{
                        numSemanaLayout.setError(null);
                    }
                }catch (Exception e){
                    numSemanaLayout.setError("Error en parametro");
                    bandera += 1;
                }

                try{
                    diaActual = Integer.parseInt(numDia.getText().toString());
                    if (diaActual > diasPorSemana){
                        numDiaLayout.setError("Menor a "+diasPorSemana+" !");
                        bandera += 1;
                    }else if(diaActual <= 0){
                        numDiaLayout.setError("Mayor a 0 !");
                        bandera += 1;
                    }else{
                        numDiaLayout.setError(null);
                    }
                }catch (Exception e){
                    numDiaLayout.setError("Error en parametro");
                    bandera += 1;
                }

                if (bandera != 0)
                    return;

                makePreferences();
                diaTrabajo = diaActual * semanaActual;

                PlanMaker plan = new PlanMaker(ritmoBase,ritmoMeta,cantEntrene,distMeta);


            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void makePreferences (){
        //Carga las preferencias "USER_PREFS"
        SharedPreferences settings = getSharedPreferences(USER_PREFS, 0);
        //Crea el editor de las preferencias USER_PREFS
        SharedPreferences.Editor editor = settings.edit();
        //Agrega preferencia en "clave" - "valor"
        // Obtengo el contenido del EditText, lo paso a String y lo convierto a Int
        editor.putInt("diaActual",Integer.parseInt(numDia.getText().toString()) );
        editor.putInt("semanaActual",Integer.parseInt(numSemana.getText().toString()));
        // Commit the edits!
        editor.commit();
    }

    private void loadPreferences (){
        SharedPreferences settings = getSharedPreferences(USER_PREFS, 0);
        try{
            if (!settings.contains("distanciaBase"))
                return;
        }catch (Exception e){
            return;
        }
        Integer tmp = 0;
        tmp = settings.getInt("diasPorSemana",-1);
        if(tmp != -1){
            diasPorSemana = tmp;
        }

        tmp = settings.getInt("semanas",-1);
        if(tmp != -1){
            semanas = tmp;
        }

        tmp = settings.getInt("diaActual",-1);
        if(tmp != -1){
            numDia.setText(tmp.toString(), TextView.BufferType.EDITABLE);

        }
        tmp = settings.getInt("semanaActual",-1);
        if(tmp != -1){
            numSemana.setText(tmp.toString(), TextView.BufferType.EDITABLE);
        }
        // ----- Parametros a pasar
        cantEntrene = semanas * diasPorSemana;
        tmp = settings.getInt("ritmoBaseInt",-1);
        if(tmp != -1){
            ritmoBase = tmp;
        }
        tmp = settings.getInt("ritmoMetaInt",-1);
        if(tmp != -1){
            ritmoMeta = tmp;
        }
        tmp = settings.getInt("distanciaMeta",-1);
        if(tmp != -1){
            distMeta = tmp;
        }

    }

    // ------------ Menu --------------- //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_data:
                intent = new Intent(PlanActivity.this, DataActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_plan:
                //intent = new Intent(PlanActivity.this, DataActivity.class);
                //startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // ------------ finMenu --------------- //
}
