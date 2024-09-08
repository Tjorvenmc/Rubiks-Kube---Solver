package solver;
import java.util.HashSet;

public class HviteHjoerner {
    Kube kuben;

    HviteHjoerner(Kube kube) {
        kuben = kube;
    }

    public void settPaaPlassHjoerner(){

        if (erAlleRiktig()){
            return;
        }

        while (!erAlleRiktig()){
            settHjoernerNed();
            settHjoernerOpp();
        }
    }

    //setter de hvite hjoernene i det gule laget ned i det hvite laget
    //med riktig orientering
    private void settHjoernerNed(){

        while (ToppenHarHviteHjoerner()){

            //hvis den riktige hjoernebiten i det gule laget er over
            //den riktige plasseringen i det hvite laget, settes biten ned
            if (sjekkHvilketHjoerne(2, 29, 36).equals("blaaRoed")){
                
                while (!erHjoernetRiktig("blaaRoed")){
                    nedOgRundt("roed");
                }
            }
        
            if (sjekkHvilketHjoerne(35,53,42).equals("roedGroenn")){

                while (!erHjoernetRiktig("roedGroenn")){
                    nedOgRundt("groenn");
                }
            }

            if (sjekkHvilketHjoerne(51, 15, 44).equals("groennOransje")){

                while (!erHjoernetRiktig("groennOransje")){
                    nedOgRundt("oransje");
                }
            }

            if (sjekkHvilketHjoerne(0, 9, 38).equals("oransjeBlaa")){

                while (!erHjoernetRiktig("oransjeBlaa")){
                    nedOgRundt("blaa");
                }
            }

            kuben.vriMed("gul");
        }
    }

    //setter feilplaserte hjoerner i det hvite laget opp til det gule laget
    //saa de er klare for aa bli satt ned i riktig hjoerne med riktig orientering
    private void settHjoernerOpp(){

        if (!erHjoernetRiktig("blaaRoed") && erHjoernetHvitt(8,27,20)){
            flyttHjoerneOpp("blaaRoed");
        }

        if (!erHjoernetRiktig("roedGroenn") && erHjoernetHvitt(33,47,26)){
            flyttHjoerneOpp("roedGroenn");
        }

        if (!erHjoernetRiktig("groennOransje") && erHjoernetHvitt(45,17,24)){
            flyttHjoerneOpp("groennOransje");
        }

        if (!erHjoernetRiktig("oransjeBlaa") && erHjoernetHvitt(11,6,18)){
            flyttHjoerneOpp("oransjeBlaa");
        }
    }

    //flytter ett hjoerne fra den hvite siden opp til det gule laget
    private void flyttHjoerneOpp(String hjoerne){
        String side = "";
        int hj1 = 0;
        int hj2 = 0;
        int hj3 = 0;

        if (hjoerne.equals("blaaRoed")){
            side = "roed";
            hj1 = 8;
            hj2 = 20;
            hj3 = 27;
        }

        else if (hjoerne.equals("roedGroenn")){
            side = "groenn";
            hj1 = 47;
            hj2 = 26;
            hj3 = 33;
        }

        else if (hjoerne.equals("groennOransje")){
            side = "oransje";
            hj1 = 17;
            hj2 = 24;
            hj3 = 45;
        }

        else if (hjoerne.equals("oransjeBlaa")){
            side = "blaa";
            hj1 = 6;
            hj2 = 11;
            hj3 = 18;
        }

        while (erHjoernetHvitt(hj1, hj2, hj3)){
            kuben.vriMed(side);
            kuben.vriMed("gul");
            kuben.vriMot(side);
        }
    }

    //sjekker om hjoernet har en hvit rute paa seg
    private Boolean erHjoernetHvitt(int hj1,int hj2,int hj3){

        if (kuben.hentRute(hj1).equals("HVT") || kuben.hentRute(hj2).equals("HVT")
            || kuben.hentRute(hj3).equals("HVT")){
                return true;
        }
        
        else{
            return false;
        }
    }

    //sjekker om hjoerner paa den hvite siden baade er riktig plass,
    // og riktig orientert
    private Boolean erHjoernetRiktig(String hjoerne){

        if (hjoerne.equals("blaaRoed")){
            if (kuben.hentRute(20).equals("HVT") && kuben.hentRute(8).equals("BLA")
                && kuben.hentRute(27).equals("ROD")){
                    return true;
            }
        }

        else if (hjoerne.equals("roedGroenn")){
            if (kuben.hentRute(26).equals("HVT") && kuben.hentRute(33).equals("ROD")
                && kuben.hentRute(47).equals("GRN")){
                    return true;
            }
        }

        else if (hjoerne.equals("groennOransje")){
            if (kuben.hentRute(24).equals("HVT") && kuben.hentRute(45).equals("GRN")
                && kuben.hentRute(17).equals("ORN")){
                    return true;
            }
        }

        else if (hjoerne.equals("oransjeBlaa")){
            if (kuben.hentRute(18).equals("HVT") && kuben.hentRute(11).equals("ORN")
                && kuben.hentRute(6).equals("BLA")){
                    return true;
            }
        }
        
        return false;
    }

    private String sjekkHvilketHjoerne(int hj1, int hj2, int hj3){
        HashSet<String> mengde = new HashSet<String>();
        mengde.add(kuben.hentRute(hj1));
        mengde.add(kuben.hentRute(hj2));
        mengde.add(kuben.hentRute(hj3));

        if (mengde.contains("BLA") && mengde.contains("ROD") && mengde.contains("HVT")){
            return "blaaRoed";
        }

        if (mengde.contains("ROD") && mengde.contains("GRN") && mengde.contains("HVT")){
            return "roedGroenn";
        }

        if (mengde.contains("GRN") && mengde.contains("ORN") && mengde.contains("HVT")){
            return "groennOransje";
        }

        if (mengde.contains("ORN") && mengde.contains("BLA") && mengde.contains("HVT")){
            return "oransjeBlaa";
        }

        return "annet";
    }

    private void nedOgRundt(String side){
        kuben.vriMed(side);
        kuben.vriMed("gul");
        kuben.vriMot(side);
        kuben.vriMot("gul");
    }

    //hvilke av de hvite hjoernene er i det gule laget?
    private Boolean ToppenHarHviteHjoerner(){

        if (erHjoernetHvitt(36, 29, 2) || erHjoernetHvitt(35, 42, 53) ||
            erHjoernetHvitt(44, 15, 51) || erHjoernetHvitt(0, 9, 38)){
            return true;
        }

        else {
            return false;
        }
    }

    private Boolean erAlleRiktig(){
        if (erHjoernetRiktig("blaaRoed") && erHjoernetRiktig("roedGroenn") &&
            erHjoernetRiktig("groennOransje") && erHjoernetRiktig("oransjeBlaa")){
                return true;
        }

        else{
            return false;
        }
    }
}
