package com.mycompany.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

public class DataActivity extends AppCompatActivity {

    public static final String USER_PREFS = "UserPrefsFile";

    private Button btnMakePlan;
    //
    private TextView intensity_Init;
    private TextView intensity_Goal;

    private EditText distInit;
    private EditText minutesInit;
    private EditText secondsInit;
    private EditText distGoal;
    private EditText minutesGoal;
    private EditText secondsGoal;
    private EditText weeks;
    private EditText daysPerWeek;
    //
    private TextInputLayout distInit_Layout;
    private TextInputLayout minutesInit_Layout;
    private TextInputLayout secondsInit_Layout;
    private TextInputLayout distGoal_Layout;
    private TextInputLayout minutesGoal_Layout;
    private TextInputLayout secondsGoal_Layout;
    private TextInputLayout weeks_Layout;
    private TextInputLayout daysPerWeek_Layout;
    //
    private Integer dist_init_Int = 0;
    private Integer minutes_Init_Int = 0;
    private Integer seconds_Init_Int = 0;
    private Integer dist_Goal_Int = 0;
    private Integer minutes_Goal_Int = 0;
    private Integer seconds_Goal_Int = 0;
    private Integer weeks_Int = 0;
    private Integer daysPerWeek_Int = 0;
    private Integer ritmoBaseInt = 0;
    private Integer ritmoMetaInt = 0;
    //
    private String txt_ritmo_base;
    private String txt_ritmo_meta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadPreferences();

        // --- Botones
        btnMakePlan = (Button)findViewById(R.id.btn_data_make_plan);
        // --- TextView
        intensity_Init = (TextView)findViewById(R.id.data_init_ritmo);
        intensity_Goal = (TextView)findViewById(R.id.data_goal_ritmo);
        // --- Variables (EditText)
        distInit    = (EditText)findViewById(R.id.txt_data_distancia_inicial);
        minutesInit = (EditText)findViewById(R.id.txt_data_minutos_init);
        secondsInit = (EditText)findViewById(R.id.txt_data_segundos_init);
        distGoal    = (EditText)findViewById(R.id.txt_data_distancia_meta);
        minutesGoal = (EditText)findViewById(R.id.txt_data_minutos_meta);
        secondsGoal = (EditText)findViewById(R.id.txt_data_segundos_meta);
        weeks       = (EditText)findViewById(R.id.txt_data_total_weeks);
        daysPerWeek = (EditText)findViewById(R.id.txt_data_days_per_week);
        // --- TextInputLayput
        distInit_Layout    = (TextInputLayout)findViewById(R.id.TiL_txt_distancia_inicial);
        minutesInit_Layout = (TextInputLayout)findViewById(R.id.TiL_txt_minutos_inicial);
        secondsInit_Layout = (TextInputLayout)findViewById(R.id.TiL_txt_segundos_inicial);
        distGoal_Layout    = (TextInputLayout)findViewById(R.id.TiL_txt_distancia_meta);
        minutesGoal_Layout = (TextInputLayout)findViewById(R.id.TiL_txt_minutos_meta);
        secondsGoal_Layout = (TextInputLayout)findViewById(R.id.TiL_txt_segundos_meta);
        weeks_Layout       = (TextInputLayout)findViewById(R.id.TiL_total_weeks);
        daysPerWeek_Layout = (TextInputLayout)findViewById(R.id.TiL_days_per_week);
        // --- .setErrorEnabled(true);
        distInit_Layout.setErrorEnabled(true);
        minutesInit_Layout.setErrorEnabled(true);
        secondsInit_Layout.setErrorEnabled(true);
        distGoal_Layout.setErrorEnabled(true);
        minutesGoal_Layout.setErrorEnabled(true);
        secondsGoal_Layout.setErrorEnabled(true);
        weeks_Layout.setErrorEnabled(true);
        daysPerWeek_Layout.setErrorEnabled(true);

        // ******************* Comprobacion de parametros ********************************
        btnMakePlan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int bandera = 0;
                // ----- Distancia Inicial ----- //
                //Si el EditText esta vacio caera en la exception
                dist_init_Int = 0;
                try{
                    String distInit_str = distInit.getText().toString();
                    dist_init_Int = Integer.parseInt(distInit_str);

                    if(dist_init_Int < 1500){
                        distInit_Layout.setError("Debe ser minimo 1,500");
                        bandera += 1;
                    }else if(dist_init_Int > 10001){
                        distInit_Layout.setError("Debe ser maximo 10,000");
                        bandera += 1;
                    }else{
                        distInit_Layout.setError(null);
                        bandera += 0;
                    }
                }catch (Exception e){
                    distInit_Layout.setError("Campo vacio!");
                    bandera += 1;
                }

                // ----- Tiempo (MINUTOS) Inicial ----- //
                minutes_Init_Int = 0;
                try{
                    String minutes_Init_str = minutesInit.getText().toString();
                    minutes_Init_Int = Integer.parseInt(minutes_Init_str);
                    if(minutes_Init_Int <= 0){
                        minutesInit_Layout.setError("Cantidad erronea");
                        bandera += 1;
                    }else {
                        minutesInit_Layout.setError(null);
                        bandera += 0;
                    }
                }catch (Exception e){
                    minutesInit_Layout.setError("Valor erroneo");
                    bandera += 1;
                }

                // ----- Tiempo (SEGUNDOS) Inicial ----- //
                seconds_Init_Int = 0;
                try{
                    String seconds_Init_str = secondsInit.getText().toString();
                    seconds_Init_Int = Integer.parseInt(seconds_Init_str);
                    if(seconds_Init_Int < 0){
                        secondsInit_Layout.setError("Es menor a 0");
                        bandera += 1;
                    }else if (seconds_Init_Int > 59){
                        secondsInit_Layout.setError("Es mayor a 59");
                        bandera += 1;
                    }else {
                        secondsInit_Layout.setError(null);
                        bandera += 0;
                    }
                }catch(Exception e){
                    secondsInit_Layout.setError("Valor erroneo");
                    bandera += 1;
                }

                // ----- Distancia Final ----- //
                dist_Goal_Int = 0;
                try{
                    String dist_Goal_str = distGoal.getText().toString();
                    dist_Goal_Int = Integer.parseInt(dist_Goal_str);
                    if(dist_Goal_Int < 1500){
                        distGoal_Layout.setError("Debe ser minimo 1,500");
                        bandera += 1;
                    }else if(dist_Goal_Int > 10001){
                        distGoal_Layout.setError("Debe ser maximo 10,000");
                        bandera += 1;
                    }else{
                        distGoal_Layout.setError(null);
                        bandera += 0;
                    }
                }catch (Exception e){
                    distGoal_Layout.setError("Campo vacio!");
                    bandera += 1;
                }

                // ----- Tiempo (MINUTOS) Final ----- //
                minutes_Goal_Int = 0;
                try{
                    String minutesGoal_str = minutesGoal.getText().toString();
                    minutes_Goal_Int = Integer.parseInt(minutesGoal_str);
                    if(minutes_Goal_Int <= 0){
                        minutesGoal_Layout.setError("Cantidad erronea");
                        bandera += 1;
                    }else {
                        minutesGoal_Layout.setError(null);
                        bandera += 0;
                    }
                }catch (Exception e){
                    minutesGoal_Layout.setError("Valor erroneo");
                    bandera += 1;
                }

                // ----- Tiempo (SEGUNDOS) Final ----- //
                seconds_Goal_Int = 0;
                try{
                    String seconds_Goal_str = secondsGoal.getText().toString();
                    seconds_Goal_Int = Integer.parseInt(seconds_Goal_str);
                    if(seconds_Goal_Int < 0){
                        secondsGoal_Layout.setError("Es menor a 0");
                        bandera += 1;
                    }else if (seconds_Goal_Int > 59){
                        secondsGoal_Layout.setError("Es mayor a 59");
                        bandera += 1;
                    }else {
                        secondsGoal_Layout.setError(null);
                        bandera += 0;
                    }
                }catch(Exception e){
                    secondsGoal_Layout.setError("Valor erroneo");
                    bandera += 1;
                }

                // ---------- Calculo de Ritmos / Intencidades --------- //
                if(bandera == 0){
                    // Tiempos en segundos
                    Integer tiempo_init = (minutes_Init_Int * 60) + seconds_Init_Int;
                    Integer tiempo_goal    = (minutes_Goal_Int * 60) + seconds_Goal_Int;
                    // Distancia en kilometros
                    Integer distancia_init = dist_init_Int / 1000;
                    Integer distancia_goal = dist_Goal_Int / 1000;
                    // Intecidad neta decimal
                    Integer intensity_init = tiempo_init / distancia_init;
                    Integer intensity_goal = tiempo_goal / distancia_goal;
                    ritmoBaseInt = intensity_init;
                    ritmoMetaInt = intensity_goal;

                    //La intensidad esta en segundos por kilometro, por lo cual
                    // la meta debe ser a menor tiempo para que sea mayor su
                    // intencidad, si no carece de sentido.
                    if(intensity_goal > intensity_init){
                        distGoal_Layout.setError("La meta debe ser mejor");
                        minutesGoal_Layout.setError("La meta debe ser mejor");
                        secondsGoal_Layout.setError("La meta debe ser mejor");
                        bandera += 1;
                    }

                    // Intencidad en minutos y segundos
                    Integer intensity_min_init  = intensity_init / 60;
                    Integer intensity_seg_init  = intensity_init % 60;
                    Integer intensity_min_goal  = intensity_goal / 60;
                    Integer intensity_seg_goal  = intensity_goal % 60;

                    String segundos_Base = "";
                    String segundos_Meta = "";
                    if (intensity_seg_init < 10){
                        segundos_Base = "0" + intensity_seg_init.toString();
                    }else
                        segundos_Base = intensity_seg_init.toString();

                    if (intensity_seg_goal < 10){
                        segundos_Meta = "0" + intensity_seg_goal.toString();
                    }else
                        segundos_Meta = intensity_seg_goal.toString();

                    txt_ritmo_base = "Ritmo: \n" +
                            intensity_min_init.toString() + ":" +
                            segundos_Base + " min/Km";
                    txt_ritmo_meta = "Ritmo: \n" +
                            intensity_min_goal.toString() + ":" +
                            segundos_Meta + " min/Km";
                    intensity_Init.setText(txt_ritmo_base);
                    intensity_Goal.setText(txt_ritmo_meta);

                    //intensity_Init.setText([String]);
                }

                // ----- Weeks ----- //
                weeks_Int = 0;
                try{
                    String weeks_str = weeks.getText().toString();
                    weeks_Int = Integer.parseInt(weeks_str);
                    if(weeks_Int < 4){
                        weeks_Layout.setError("Es menor a 4");
                        bandera += 1;
                    }else if (weeks_Int > 52){
                        weeks_Layout.setError("Es mayor a 52");
                        bandera += 1;
                    }else {
                        weeks_Layout.setError(null);
                        bandera += 0;
                    }
                }catch(Exception e){
                    weeks_Layout.setError("Valor erroneo");
                    bandera += 1;
                }

                // ----- DaysPerWeek ----- //
                daysPerWeek_Int = 0;
                try{
                    String daysPerWeek_str = daysPerWeek.getText().toString();
                    daysPerWeek_Int = Integer.parseInt(daysPerWeek_str);
                    if(daysPerWeek_Int < 3){
                        daysPerWeek_Layout.setError("Es menor a 3");
                        bandera += 1;
                    }else if (daysPerWeek_Int > 7){
                        daysPerWeek_Layout.setError("Es mayor a 7");
                        bandera += 1;
                    }else {
                        daysPerWeek_Layout.setError(null);
                        bandera += 0;
                    }
                }catch(Exception e){
                    daysPerWeek_Layout.setError("Valor erroneo");
                    bandera += 1;
                }

                //Si entra en cualquier condicion de error se retorna
                // ocupa no entrar a ninguna condicion de error
                if(bandera != 0)
                    return;

                // ---------- Bundle / Preferences ---------- //
                makePreferences();

                // ---------- Intent ---------- //
                Intent i = new Intent(DataActivity.this, PlanActivity.class);
                startActivity(i);
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
        editor.putInt("distanciaBase",Integer.parseInt(distInit.getText().toString()) );
        editor.putInt("minutosBase",Integer.parseInt(minutesInit.getText().toString()) );
        editor.putInt("segundosBase",Integer.parseInt(secondsInit.getText().toString()) );
        editor.putInt("distanciaMeta",Integer.parseInt(distGoal.getText().toString()) );
        editor.putInt("minutosMeta",Integer.parseInt(minutesGoal.getText().toString()) );
        editor.putInt("segundosMeta", (Integer.parseInt(secondsGoal.getText().toString()) / 1000) );
        editor.putInt("semanas",Integer.parseInt(weeks.getText().toString()) );
        editor.putInt("diasPorSemana",Integer.parseInt(daysPerWeek.getText().toString()) );
        editor.putInt("ritmoBaseInt",ritmoBaseInt);
        editor.putInt("ritmoMetaInt",ritmoMetaInt);
        editor.putString("ritmoBase", txt_ritmo_base);
        editor.putString("ritmoMeta",txt_ritmo_meta);
        // Commit the edits!
        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences settings = getSharedPreferences(USER_PREFS, 0);
        try{
            if (!settings.contains("distanciaBase"))
                return;
        }catch (Exception e){
            return;
        }
        Integer distBase = settings.getInt("distanciaBase",-1);
        if(distBase != -1){
            dist_init_Int = distBase;
            distInit    = (EditText)findViewById(R.id.txt_data_distancia_inicial);
            distInit.setText(dist_init_Int.toString(),TextView.BufferType.EDITABLE);
        }

        Integer minuBase = settings.getInt("minutosBase",-1);
        if (minuBase != -1){
            minutes_Init_Int = minuBase;
            minutesInit = (EditText)findViewById(R.id.txt_data_minutos_init);
            minutesInit.setText(minutes_Init_Int.toString(),TextView.BufferType.EDITABLE);
        }

        Integer segBase = settings.getInt("segundosBase",-1);
        if (segBase != -1){
            seconds_Init_Int = segBase;
            secondsInit = (EditText)findViewById(R.id.txt_data_segundos_init);
            secondsInit.setText(seconds_Init_Int.toString(),TextView.BufferType.EDITABLE);
        }

        Integer distMeta = settings.getInt("distanciaMeta",-1);
        if (distMeta != -1){
            dist_Goal_Int = distMeta;
            distGoal = (EditText)findViewById(R.id.txt_data_distancia_meta);
            distGoal.setText(dist_Goal_Int.toString(),TextView.BufferType.EDITABLE);
        }

        Integer minMeta = settings.getInt("minutosMeta",-1);
        if (minMeta != -1){
            seconds_Goal_Int = minMeta;
            minutesGoal = (EditText)findViewById(R.id.txt_data_minutos_meta);
            minutesGoal.setText(seconds_Goal_Int.toString(), TextView.BufferType.EDITABLE);
        }

        Integer segMeta = settings.getInt("segundosMeta",-1);
        if (segMeta != -1){
            seconds_Goal_Int = segMeta;
            secondsGoal = (EditText)findViewById(R.id.txt_data_segundos_meta);
            secondsGoal.setText(seconds_Goal_Int.toString(), TextView.BufferType.EDITABLE);
        }

        Integer weeks = settings.getInt("semanas", -1);
        if (weeks != -1){
            weeks_Int = weeks;
            this.weeks  = (EditText)findViewById(R.id.txt_data_total_weeks);
            this.weeks.setText(weeks_Int.toString(), TextView.BufferType.EDITABLE);
        }

        Integer daysWeek = settings.getInt("diasPorSemana", -1);
        if (daysWeek != -1){
            daysPerWeek_Int = daysWeek;
            daysPerWeek  = (EditText)findViewById(R.id.txt_data_days_per_week);
            daysPerWeek.setText(daysPerWeek_Int.toString(), TextView.BufferType.EDITABLE);
        }

        intensity_Init = (TextView)findViewById(R.id.data_init_ritmo);
        intensity_Goal = (TextView)findViewById(R.id.data_goal_ritmo);
        intensity_Init.setText(settings.getString("ritmoBase","Ritmo:\n"));
        intensity_Goal.setText(settings.getString("ritmoMeta","Ritmo:\n"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //makePreferences();
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
                //intent = new Intent(DataActivity.this, DataActivity.class);
                //startActivity(intent);
                return true;
            case R.id.action_plan:
                intent = new Intent(DataActivity.this, PlanActivity.class);
                //makePreferences();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // ------------ finMenu --------------- //
}