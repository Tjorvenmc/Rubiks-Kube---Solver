package com.rubiks;
import java.util.ArrayList;

public class Kontroller {
    Kube kuben = new Kube(this);
    Kube visningsKube = new Kube(null);
    GUI gui;
    String fargeTilKuben = "BLA";
    String fargeTilKnappen = "-fx-background-color: #140ef1;";
    int vriTeller = 0;
    Boolean feilInput = false;
    Boolean feilKube = false;

    ArrayList<String[]> loesning;

    public Kontroller(GUI gui){
        this.gui = gui;
    }

    public void settRute(int rute, String farge){
        kuben.settRute(rute, farge);
    }

    public void inputRutetrykk(int knappNr){
        kuben.settRute(knappNr, fargeTilKuben);
        visningsKube.settRute(knappNr, fargeTilKuben);
    }

    public void resetKnapp(int knappNr){
        kuben.settRute(knappNr, null);
    }

    public String hentVisningsRute(int ruteNr){
        return visningsKube.hentRute(ruteNr);
    }

    public void velgFargeKnapp(String inputFarge, String fargeKode){
        fargeTilKuben = inputFarge;
        fargeTilKnappen = fargeKode;
    }

    public String hentFargeTilKnappen(){
        return fargeTilKnappen;
    }

    public void loesKnapp(){
        //hent fargene fra kuben og sett knappene til loeseSiden til riktige farger (og paa loesekuben)
        feilInput = false;
        feilKube = false;
        vriTeller = 0;

        try{
        if (kuben.kanKubenLoeses()){
            kuben.loes();
            kuben.ryddVriListe();
            loesning = kuben.hentVriListe();
            String[] siste = {"Gratulerer, " ,"kuben er løst!"};
            loesning.add(siste);

            //printer vridningene i terminalen
            // for (int i = 0; i < loesning.size()-1; i++){
            //     String[] vridning = loesning.get(i);
            //     System.out.println(vridning[0] + " " + vridning[1]);
            // }
        }

        else{
            feilKube = true;
        }
        
        } catch (NullPointerException e){
            feilInput = true;
        }
    }

    public void forrigeVri(){

        if (vriTeller != 0){
            vriKubenTilbake(loesning.get(vriTeller-1));
            vriTeller--;
        }

        else{
            return;
        }
    }
    
    public void nesteVri(){

        if (vriTeller != loesning.size() -1){
            vriKubenFrem(loesning.get(vriTeller));
            vriTeller++;
        }

        else{
            return;
        }
    }

    public ArrayList<String[]> hentLoesningListe(){
        return loesning;
    }

    public int hentVriTeller(){
        return vriTeller;
    }

    public Boolean sjekkFeilInput(){
        return feilInput;
    }

    public Boolean sjekkFeilKube(){
        return feilKube;
    }

    private void vriKubenFrem(String[] vri){
            
        if (vri[1].equals("to ganger")){
            visningsKube.vriMed(vri[0]);
            visningsKube.vriMed(vri[0]);
        }

        else if (vri[1].equals("med")){
            visningsKube.vriMed(vri[0]);
        }

        else if (vri[1].equals("mot")){
            visningsKube.vriMot(vri[0]);
        }
    }

    private void vriKubenTilbake(String[] vri){
            
        if (vri[1].equals("to ganger")){
            visningsKube.vriMed(vri[0]);
            visningsKube.vriMed(vri[0]);
        }

        else if (vri[1].equals("med")){
            visningsKube.vriMot(vri[0]);
        }

        else if (vri[1].equals("mot")){
            visningsKube.vriMed(vri[0]);
        }

    }
}
