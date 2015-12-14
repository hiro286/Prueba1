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

public class DataActivity extends AppCompatActivity {

    private Button btnMakePlan;
    private EditText distInit;
    private EditText minutesInit;
    private EditText secondsInit;
    private EditText distGoal;
    private EditText minutesGoal;
    private EditText secondsGoal;
    private EditText weeks;
    private EditText daysPerWeek;
    private TextInputLayout distInit_Layout;
    private TextInputLayout minutesInit_Layout;
    private TextInputLayout secondsInit_Layout;
    private TextInputLayout distGoal_Layout;
    private TextInputLayout minutesGoal_Layout;
    private TextInputLayout secondsGoal_Layout;
    private TextInputLayout weeks_Layout;
    private TextInputLayout daysPerWeek_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // --- Botones
        btnMakePlan = (Button)findViewById(R.id.btn_data_make_plan);
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

                // ----- Distancia Inicial ----- //
                //Si el EditText esta vacio caera en la exception
                try{
                    String distInit_str = distInit.getText().toString();
                    Integer dist_I = Integer.parseInt(distInit_str);

                    if(dist_I < 1500){
                        distInit_Layout.setError("Debe ser minimo 1,500");
                        return;
                    }else if(dist_I > 10001){
                        distInit_Layout.setError("Debe ser maximo 10,000");
                        return;
                    }else{
                            distInit_Layout.setError(null);
                    }
                }catch (Exception e){
                    distInit_Layout.setError("Campo vacio!");
                    return;
                }

                // ----- Tiempo (MINUTOS) Inicial ----- //
                try{
                    String minutes_Init_str = minutesInit.getText().toString();
                    Integer minutes_Init_Int = Integer.parseInt(minutes_Init_str);
                    if(minutes_Init_Int <= 0){
                        minutesInit_Layout.setError("Cantidad erronea");
                        return;
                    }else
                        minutesInit_Layout.setError(null);
                }catch (Exception e){
                    minutesInit_Layout.setError("Valor erroneo");
                    return;
                }

                // ----- Tiempo (SEGUNDOS) Inicial ----- //
                try{
                    String seconds_Init_str = secondsInit.getText().toString();
                    Integer seconds_Init_Int = Integer.parseInt(seconds_Init_str);
                    if(seconds_Init_Int < 0){
                        secondsInit_Layout.setError("Es menor a 0");
                        return;
                    }else if (seconds_Init_Int > 59){
                        secondsInit_Layout.setError("Es mayor a 59");
                        return;
                    }else
                        secondsInit_Layout.setError(null);
                }catch(Exception e){
                    secondsInit_Layout.setError("Valor erroneo");
                    return;
                }

/*                // ----- Distancia Final ----- //
                String distGoal_str = distInit.getText().toString();
                Integer dist_G = Integer.parseInt(distGoal_str);
                if(dist_I <= 0){
                    distGoal_Layout.setError("Cantidad erronea");
                    return;
                }else if(dist_I < 1500 || dist_I > 10000){
                    distGoal_Layout.setError("Introduce una distancia mayor a 1,500 y menor a 10,000 metros");
                    return;
                }else
                    distGoal_Layout.setError(null);*/

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
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // ------------ finMenu --------------- //

}
