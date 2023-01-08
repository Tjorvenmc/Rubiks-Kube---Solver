package com.rubiks;
import java.util.HashSet;

public class HvitKryss {
    Kube kuben;

    HvitKryss(Kube kube) {
        kuben = kube;
    }

    //hvis det allerede er hvite biter paa den hvite siden, saa settes
    //de paa plass saann at det er klart for aa hente inn de resterende
    //hvite bitene som skal i krysset.

    //finner ut om det er, og i saa fall hvilke hvite biter som er
    //paa bunnen naar man starter aa loese kuben
    public HashSet<Integer> finnBunnBiter(){

        HashSet<Integer> biterPaaBunn = new HashSet<Integer>();

        int[] hviteMidtBiter = {19,21,23,25};

        //legger alle ruter med hvitt, paa den hvite siden, i en mengde
        //og sjekker hvilke som er riktig ved a sette de true/false
        for (int i = 0; i < 4; i++){
            if (kuben.hentRute(hviteMidtBiter[i]).equals("HVT")){
                biterPaaBunn.add(hviteMidtBiter[i]);
            }
        }

        return biterPaaBunn;
    }

    //flytter de hvite bitene som er paa bunnen naar man starter aa loese kuben
    //og setter de saann at det er klart for aa hente inn de manglende hvite bitene.
    public void settBunnBiter(){

        HashSet<Integer> hvitePaaBunn = finnBunnBiter();

        int antRiktig = hvitePaaBunn.size();

        //hvis alle eller ingen av bitene er paa plass er det ingenting aa fikse
        if (alleFire() || antRiktig == 0){
            return;
        }

        //hvis det er én hvit rute, skal den vaere paa rute 25
        if (antRiktig == 1){

            while (!kuben.hentRute(25).equals("HVT")){
                kuben.vriMed("hvit");
            }
        }

        //Hvis det er to hvite ruter skal de vaere paa 25 og 23
        else if (antRiktig == 2){
        
            //hvis bitene staar ovenfor hverandre
            if (hvitePaaBunn.contains(19) && hvitePaaBunn.contains(25)){
                kuben.vriMot("blaa");
                kuben.vriMot("hvit");
                kuben.vriMed("blaa");
                kuben.vriMed("hvit");
            }

            //hvis bitene staar ovenfor hverandre
            else if (hvitePaaBunn.contains(21) && hvitePaaBunn.contains(23)){
                kuben.vriMed("hvit");
                settBunnBiter();
            }

            //hvis bitene er i sider som er hved siden av hverandre
            else{
                while (!kuben.hentRute(23).equals("HVT") && !kuben.hentRute(25).equals("HVT")){
                    kuben.vriMed("hvit");
                }
            }
        }

        //hvis det er tre hvite skal rute 21 vaere ikke-hvit
        else if (antRiktig == 3){

            while (!kuben.hentRute(21).equals("HVT")){
                kuben.vriMed("hvit");
            }
        }
    }

    //kjoerer til det er fire hvite kantbiter paa den hvite siden, og man
    //har et hvitt kryss.
    public void fyllHviteBiter(){
        while (!alleFire()){
            hentOgSettHvitBit();
        }
    }

    //ser etter en hvit kantbit og setter den inn paa den ledige
    // //plassen (rute 21) paa den hvite siden, og gjoer plassen ledig igjen.
    private void hentOgSettHvitBit(){

        if (kuben.hentRute(3).equals("HVT")){
            kuben.vriMed("oransje");
            kuben.vriMot("hvit");
        }

        else if (kuben.hentRute(7).equals("HVT")){
            kuben.vriMed("blaa");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(1).equals("HVT")){
            kuben.vriMed("gul");
            kuben.vriMot("oransje");
            kuben.vriMot("hvit");
            kuben.vriMed("groenn");
        }

        else if (kuben.hentRute(5).equals("HVT")){
            kuben.vriMed("hvit");
            kuben.vriMed("hvit");
            kuben.vriMot("roed");
            kuben.vriMed("hvit");
        }

        else if (kuben.hentRute(16).equals("HVT")){
            kuben.vriMot("hvit");
            kuben.vriMed("groenn");
        }

        else if (kuben.hentRute(12).equals("HVT")){
            kuben.vriMot("oransje");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(10).equals("HVT")){
            kuben.vriMot("oransje");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(14).equals("HVT")){
            kuben.vriMed("oransje");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(28).equals("HVT")){
            kuben.vriMed("hvit");
            kuben.vriMed("blaa");
            kuben.vriMed("hvit");
            kuben.vriMed("hvit");
        }

        else if (kuben.hentRute(30).equals("HVT")){
            kuben.vriMed("roed");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(34).equals("HVT")){
            kuben.vriMot("hvit");
            kuben.vriMot("groenn");
        }

        else if (kuben.hentRute(32).equals("HVT")){
            kuben.vriMed("gul");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(48).equals("HVT")){
            kuben.vriMot("oransje");
            kuben.vriMot("hvit");
        }

        else if (kuben.hentRute(46).equals("HVT")){
            kuben.vriMot("groenn");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(50).equals("HVT")){
            kuben.vriMed("hvit");
            kuben.vriMed("hvit");
            kuben.vriMed("roed");
            kuben.vriMed("hvit");
        }

        else if (kuben.hentRute(52).equals("HVT")){
            kuben.vriMot("gul");
            kuben.vriMot("oransje");
            kuben.vriMot("hvit");
            kuben.vriMed("groenn");
        }

        else if (kuben.hentRute(41).equals("HVT")){
            kuben.vriMed("oransje");
            kuben.vriMed("oransje");
            kuben.vriMot("hvit");
        }

        else if (kuben.hentRute(43).equals("HVT")){
            kuben.vriMot("gul");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(37).equals("HVT")){
            kuben.vriMed("gul");
            hentOgSettHvitBit();
        }

        else if (kuben.hentRute(39).equals("HVT")){
            kuben.vriMed("gul");
            hentOgSettHvitBit();
        }
    }

    //sjekker om alle fire bitene i krysset er paa plass
    private boolean alleFire(){
        if (kuben.hentRute(19).equals("HVT") && kuben.hentRute(21).equals("HVT")
        && kuben.hentRute(23).equals("HVT") && kuben.hentRute(25).equals("HVT")){
            return true;
        }

        else return false;
    }

    //bytter om plassene til de hvite bitene i krysset hvis de ikke
    //matcher midtbiden til sin respektive side.
    public void orienterKrysset(){
        HashSet<Integer> matcher = sjekkMidtMatch();

        //vrir kuben til to (eller alle fire) sidene matcher.
        while (matcher.size() < 2){
            kuben.vriMed("hvit");
            matcher = sjekkMidtMatch();
        }

        //hvis alle sidene matcher
        if (matcher.size() > 2){
            return;
        }

        if (matcher.contains(19) && matcher.contains(23)){
            byttToBiter("groenn");
        }

        else if (matcher.contains(23) && matcher.contains(25)){
            byttToBiter("oransje");
        }

        else if (matcher.contains(21) && matcher.contains(25)){
            byttToBiter("blaa");
        }

        else if (matcher.contains(19) && matcher.contains(21)){
            byttToBiter("roed");
        }

        else if (matcher.contains(19) && matcher.contains(25)){
            byttToMotstaaendeBiter("oransje");
        }

        else if (matcher.contains(21) && matcher.contains(23)){
            byttToMotstaaendeBiter("blaa");
        }
    }

    //sjekker hvilke av bitene i det hvite krysset som ogsa matcher
    //midtfargen paa den andre siden av biten. Returnerer hvilke(t) par
    //som matcher i en lsite
    private HashSet<Integer> sjekkMidtMatch(){

        HashSet<Integer> matcher = new HashSet<Integer>();
        int[] sideRuter = {7,30,46,14};
        int[] toppRuter = {19,23,25,21};
        String[] farger = {"BLA", "ROD", "GRN", "ORN"};

        for (int i = 0; i < 4; i++){
            if (kuben.hentRute(sideRuter[i]).equals(farger[i])){
                matcher.add(toppRuter[i]);
            }
        }

        return matcher;
    }

    //bytter om plassen til to biter i det hvite krysset
    private void byttToBiter(String side){
        kuben.vriMot(side);
        kuben.vriMot("hvit");
        kuben.vriMed(side);
        kuben.vriMed("hvit");
        kuben.vriMot(side);
    }

    private void byttToMotstaaendeBiter(String side){
        kuben.vriMot(side);
        kuben.vriMed("hvit");
        kuben.vriMed("hvit");
        kuben.vriMed(side);
        kuben.vriMed("hvit");
        kuben.vriMed("hvit");
        kuben.vriMot(side);
    }
}

