package solver;
import java.util.HashSet;

public class GuleHjoerner {
    Kube kuben;

    GuleHjoerner(Kube kube){
        kuben = kube;
    }

    //setter hjoernebiter i riktig hjoerne
    public void fiksHjoerner(){

        if (erAlleHjoernerPlassert()){
            return;
        }

        while (true){

            if (erHjoernetRiktigPlassert(36,29,2)){

                while (!erAlleHjoernerPlassert()){
                    rokereHjoerner("roed", "oransje");
                }

                return;
            }

            if (erHjoernetRiktigPlassert(42,35,53)){

                while (!erAlleHjoernerPlassert()){
                    rokereHjoerner("groenn", "blaa");
                }

                return;
            }

            if (erHjoernetRiktigPlassert(44,15,51)){

                while (!erAlleHjoernerPlassert()){
                    rokereHjoerner("oransje", "roed");
                }

                return;
            }

            if (erHjoernetRiktigPlassert(38,9,0)){

                while (!erAlleHjoernerPlassert()){
                    rokereHjoerner("blaa", "groenn");
                }

                return;
            }

            rokereHjoerner("roed", "oransje");
        }
    }

    public void orienterHjoerner(){

        if (erKubenLoest()){
            return;
        }

        while(true){

            while (!kuben.hentRute(36).equals("GUL")){
                kuben.vriMed("blaa");
                kuben.vriMed("hvit");
                kuben.vriMot("blaa");
                kuben.vriMot("hvit");
            }

            kuben.vriMed("gul");

            if (kuben.hentRute(0).equals(kuben.hentRute(1)) &&
                kuben.hentRute(1).equals(kuben.hentRute(2)) &&
                kuben.hentRute(29).equals(kuben.hentRute(32)) &&
                kuben.hentRute(32).equals(kuben.hentRute(35)) &&
                kuben.hentRute(51).equals(kuben.hentRute(52)) &&
                kuben.hentRute(52).equals(kuben.hentRute(53)) &&
                kuben.hentRute(9).equals(kuben.hentRute(12)) &&
                kuben.hentRute(12).equals(kuben.hentRute(15))){

                    while (!erKubenLoest()){
                        kuben.vriMed("gul");
                    }

                    return;
                }
        }
    }

    private Boolean erKubenLoest(){

        if (kuben.hentRute(36).equals("GUL") && kuben.hentRute(38).equals("GUL")
            && kuben.hentRute(42).equals("GUL") && kuben.hentRute(44).equals("GUL")
            && kuben.hentRute(29).equals("ROD") && kuben.hentRute(35).equals("ROD")
            && kuben.hentRute(0).equals("BLA") && kuben.hentRute(2).equals("BLA")
            && kuben.hentRute(9).equals("ORN") && kuben.hentRute(15).equals("ORN")
            && kuben.hentRute(51).equals("GRN") && kuben.hentRute(53).equals("GRN")){
            return true;
            }

            else{
                return false;
            }
    }

    private boolean erAlleHjoernerPlassert(){
        
        if (erHjoernetRiktigPlassert(36,29,2) && erHjoernetRiktigPlassert(42,35,53)
            && erHjoernetRiktigPlassert(44,15,51) && erHjoernetRiktigPlassert(38,9,0)){
            return true;
        }

        else {
            return false;
        }
    }

    private Boolean erHjoernetRiktigPlassert(int rute1, int rute2, int rute3){
        HashSet<String> farger = new HashSet<String>();
        HashSet<Integer> ruter = new HashSet<Integer>();

        farger.add(kuben.hentRute(rute1));
        farger.add(kuben.hentRute(rute2));
        farger.add(kuben.hentRute(rute3));

        ruter.add(rute1);
        ruter.add(rute2);
        ruter.add(rute3);

        if (ruter.contains(36)){

            if (farger.contains("BLA") && farger.contains("ROD")){
                return true;
            }

            else{
                return false;
            }
        }

        if (ruter.contains(38)){

            if (farger.contains("BLA") && farger.contains("ORN")){
                return true;
            }

            else{
                return false;
            }
        }

        if (ruter.contains(44)){

            if (farger.contains("ORN") && farger.contains("GRN")){
                return true;
            }

            else{
                return false;
            }
        }

        if (ruter.contains(42)){

            if (farger.contains("ROD") && farger.contains("GRN")){
                return true;
            }

            else{
                return false;
            }
        }

        return false;
    }

    private void rokereHjoerner(String side1, String side2){
        //side1 til hoeyre, side2 til venstre
        kuben.vriMed("gul");
        kuben.vriMed(side1);
        kuben.vriMot("gul");
        kuben.vriMot(side2);
        kuben.vriMed("gul");
        kuben.vriMot(side1);
        kuben.vriMot("gul");
        kuben.vriMed(side2);
    }
}
