package com.mycompany.prueba1;

import android.support.v7.app.AppCompatActivity;


/**
 * Created by hiro_ on 12/14/2015.
 */
public class PlanMaker extends AppCompatActivity {
    public static final String USER_PREFS = "UserPrefsFile";


    protected Float proporcionTrabajoDescanso;
    protected Integer progrecionRitmo;
    protected Integer progrecionVueltas;
    protected Integer ritmoBase;
    protected Integer ritmoMeta;
    protected Integer ritmoFondo;
    protected Integer cantEntrene;
    protected Integer numEntrene;
    protected Integer distanciaMeta;
    protected Integer tiempoMaximo;
    protected Integer tiempoTrabajo;
    protected Integer tiempoTrabajoFondo;
    protected Integer tiempoDescanso;
    protected Integer tiempoVuelta;
    protected Integer maxVueltas;
    protected Integer minVueltas;
    protected Integer vueltasTrabajo;
    protected Integer distVuelta;
    protected Integer distFondo;

    public PlanMaker(Integer ritmoBa, Integer ritmoMe, Integer cantEnt , Integer distMe){
        super();
        tiempoMaximo = 3600;
        maxVueltas   = 16;
        minVueltas   = 3;
        proporcionTrabajoDescanso = 3f;
        //Parametros
        ritmoBase = ritmoBa;
        ritmoMeta = ritmoMe;
        cantEntrene = cantEnt;
        distanciaMeta = distMe;
        //Calculados
        progrecionRitmo = (ritmoBase - ritmoMeta) / cantEntrene;
        progrecionVueltas = (maxVueltas - minVueltas)/cantEntrene;
        numEntrene = 0;
        ritmoFondo = ritmoBase - (progrecionRitmo * numEntrene);
        vueltasTrabajo = 0;
    }

    public void setNumEntrene(Integer i){
        numEntrene = i;
        // Calculo de
        // --- Velocidad --- //
        vueltasTrabajo = maxVueltas - (progrecionVueltas * numEntrene);
        distVuelta     = (distanciaMeta / vueltasTrabajo) - ( (distanciaMeta / vueltasTrabajo) % 100 );
        tiempoTrabajo  = distVuelta * ritmoMeta;
        tiempoDescanso = (tiempoTrabajo * 3);
        tiempoVuelta   = tiempoTrabajo + tiempoDescanso;
        if( (vueltasTrabajo *tiempoVuelta ) > tiempoMaximo ){
            vueltasTrabajo = tiempoMaximo / tiempoVuelta;
        }
        // --- Fondo --- //
        float f1 = ritmoBase;
        float f2 = progrecionRitmo;
        float f3 = numEntrene;
        ritmoFondo = (int)(f1 - (f2 * f3 * 1.2));
        //ritmoFondo = ritmoBase - (progrecionRitmo * numEntrene);// * 1.2
        if ( (ritmoFondo*distanciaMeta) > tiempoMaximo ){
            tiempoTrabajoFondo = tiempoMaximo;
            distFondo = tiempoMaximo/ ritmoFondo;
        }else{
            tiempoTrabajoFondo = ritmoFondo * distanciaMeta;
            distFondo = distanciaMeta;
        }
    }

    public int getTipoEntrene(){
        return numEntrene%2;
        // 1 = velocidad, 0 = Fondo
    }

}
