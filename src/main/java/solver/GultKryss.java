package solver;
public class GultKryss {
    Kube kuben;

    GultKryss(Kube kube){
        kuben = kube;
    }

    public void lagGultKryss(){

        if (erKryssetLaget()){
            return;
        }
        
        Ingen();
        HalvKryss();
        Linje();
    }

    //flytter om paa bitene i krysset saa de blir paa riktig plass
    public void orienterGultKryss(){

        String matcher = Matcher();

        while (matcher.equals("ikkeMatch")){
            kuben.vriMed("gul");
            matcher = Matcher();
        }

        if (matcher.equals("alle")){
            return;
        }

        if (matcher.equals("blaaRoed")){
            byttToBiter("blaa");
        }

        else if (matcher.equals("roedGroenn")){
            byttToBiter("roed");            
        }

        else if (matcher.equals("groennOransje")){
            byttToBiter("groenn");            
        }

        else if (matcher.equals("oransjeBlaa")){
            byttToBiter("oransje");  
        }

        else if (matcher.equals("blaaGroenn")){
            byttToBiter("groenn");
            kuben.vriMed("gul");
            byttToBiter("oransje");
        }

        else if (matcher.equals("roedOransje")){
            byttToBiter("oransje");
            kuben.vriMed("gul");
            byttToBiter("blaa");
        }
    }

    //sjekker hvilke av midtbitene som er paa riktig plass i krysset
    private String Matcher(){

        if (kuben.hentRute(1).equals("BLA") && kuben.hentRute(32).equals("ROD") &&
        kuben.hentRute(52).equals("GRN") && kuben.hentRute(12).equals("ORN")){
            return "alle";
        }

        else if (kuben.hentRute(1).equals("BLA") && kuben.hentRute(32).equals("ROD")){
                return "blaaRoed";
        }

        else if (kuben.hentRute(32).equals("ROD") && kuben.hentRute(52).equals("GRN")){
                return "roedGroenn";
        }

        else if (kuben.hentRute(52).equals("GRN") && kuben.hentRute(12).equals("ORN")){
                return "groennOransje";
        }

        else if (kuben.hentRute(12).equals("ORN") && kuben.hentRute(1).equals("BLA")){
                return "oransjeBlaa";
        }

        else if (kuben.hentRute(1).equals("BLA") && kuben.hentRute(52).equals("GRN")){
                return "blaaGroenn";
        }

        else if (kuben.hentRute(32).equals("ROD") && kuben.hentRute(12).equals("ORN")){
                return "roedOransje";
        }
        
        else{
            return "ikkeMatch";
        }
    }

    //bytter plassene til to midtbider
    private void byttToBiter(String side){
        kuben.vriMed(side);
        kuben.vriMed("gul");
        kuben.vriMot(side);
        kuben.vriMed("gul");
        kuben.vriMed(side);
        kuben.vriMed("gul");
        kuben.vriMed("gul");
        kuben.vriMot(side);
        kuben.vriMed("gul");
    }

    //hvis det ikke er noen gule biter paa toppen, lag et halvkryss og sett
    //det i det ene hjoernet
    private void Ingen(){

        if (!kuben.hentRute(37).equals("GUL") && !kuben.hentRute(39).equals("GUL")
        && !kuben.hentRute(41).equals("GUL") && !kuben.hentRute(43).equals("GUL")){

            nedOgRundt();
            kuben.vriMed("gul");
            kuben.vriMed("gul");
        }
    }

    //hvis det er er halvkryss, finn det og sett det i samme hjoernet som i
    //Ingen()-metoden, og lag en linje
    private void HalvKryss(){
        if ((kuben.hentRute(39).equals("GUL") && kuben.hentRute(37).equals("GUL") &&
            !(kuben.hentRute(41).equals("GUL") || kuben.hentRute(43).equals("GUL"))) ||

            (kuben.hentRute(37).equals("GUL") && kuben.hentRute(41).equals("GUL") &&
            !(kuben.hentRute(39).equals("GUL") || kuben.hentRute(43).equals("GUL"))) ||

            (kuben.hentRute(43).equals("GUL") && kuben.hentRute(39).equals("GUL") &&
            !(kuben.hentRute(37).equals("GUL") || kuben.hentRute(41).equals("GUL"))) ||

            (kuben.hentRute(41).equals("GUL") && kuben.hentRute(43).equals("GUL") &&
            !(kuben.hentRute(37).equals("GUL") || kuben.hentRute(39).equals("GUL")))){

                while (!(kuben.hentRute(41).equals("GUL") && kuben.hentRute(43).equals("GUL"))){
                    kuben.vriMed("gul");
                }

                nedOgRundt();
            }
    }

    //hvis det er en linje, gjoer det til et kryss
    private void Linje(){

        if ((kuben.hentRute(37).equals("GUL") && kuben.hentRute(43).equals("GUL"))
            && !kuben.hentRute(39).equals("GUL") && !kuben.hentRute(41).equals("GUL")){
                kuben.vriMed("gul");
                nedOgRundt();
        }

        else{
            nedOgRundt();
        }

    }

    //algoritme som gaar igjen naar man lager det gule krysset
    private void nedOgRundt(){
        kuben.vriMed("blaa");
        kuben.vriMed("roed");
        kuben.vriMed("gul");
        kuben.vriMot("roed");
        kuben.vriMot("gul");
        kuben.vriMot("blaa");
    }

    private Boolean erKryssetLaget(){

        if (kuben.hentRute(37).equals("GUL") && kuben.hentRute(39).equals("GUL")
            && kuben.hentRute(41).equals("GUL") && kuben.hentRute(43).equals("GUL")){
                return true;
            }

        else{
            return false;
        }
    }
}