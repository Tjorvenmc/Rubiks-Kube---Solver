package solver;
public class Midtlaget {
    Kube kuben;

    Midtlaget(Kube kube){
        kuben = kube;
    }

    //finner ikke-gule midtbiter i det gule laget og flytter de ned dit de skal
    public void fiksMidtlaget(){

        if (erMidtlagetFerdig()){
            return;
        }

        while (!erMidtlagetFerdig()){

            orienterRiktigeBiter();

            if (erMidtlagetFerdig()){
                return;
            }

            while(erToppenBareGul()){
                flyttNoeOpp();
            }

            settBiterNed();
        } 
    }

    //sjekker om midtlaget er loest
    private Boolean erMidtlagetFerdig(){

        if (kuben.hentRute(5).equals("BLA") && kuben.hentRute(28).equals("ROD")
        && kuben.hentRute(34).equals("ROD") && kuben.hentRute(50).equals("GRN")
        && kuben.hentRute(48).equals("GRN") && kuben.hentRute(16).equals("ORN")
        && kuben.hentRute(10).equals("ORN") && kuben.hentRute(3).equals("BLA")){
            return true;
        }

        else{
            return false;
        }
    }

    //sjekker om alle midtbitene i det gule laget har gult i seg
    private Boolean erToppenBareGul(){

        if ((kuben.hentRute(1).equals("GUL") || kuben.hentRute(37).equals("GUL"))
            && (kuben.hentRute(32).equals("GUL") || kuben.hentRute(39).equals("GUL"))
            && (kuben.hentRute(12).equals("GUL") || kuben.hentRute(41).equals("GUL"))
            && (kuben.hentRute(52).equals("GUL") || kuben.hentRute(43).equals("GUL"))){
                return true;
        }

        else{
            return false;
        }
    }

    //tar en midtbit i det gule laget som matcher midtbiten i sidelaget
    //og setter den ned der den hoerer hjemme.
    private void settNedMatchetBit(String farge){
        String side2 = "";
        Boolean mot = true;

        if (farge.equals("blaa")){
            if (kuben.hentRute(37).equals("ORN")){
                side2 = "oransje";
            }

            else{
                side2 = "roed";
                mot = false;
            }
        }
        
        else if (farge.equals("roed")){
            if (kuben.hentRute(39).equals("BLA")){
                side2 = "blaa";
            }

            else{
                side2 = "groenn";
                mot = false;
            }
        }

        else if (farge.equals("groenn")){
            if (kuben.hentRute(43).equals("ROD")){
                side2 = "roed";
            }

            else{
                side2 = "oransje";
                mot = false;
            }
        }

        else if (farge.equals("oransje")){
            if (kuben.hentRute(41).equals("GRN")){
                side2 = "groenn";
            }

            else{
                side2 = "blaa";
                mot = false;
            }
        }

        if (mot){
            kuben.vriMot("gul");
            kuben.vriMot(side2);
            kuben.vriMot("gul");
            kuben.vriMed(side2);
            kuben.vriMed("gul");
            kuben.vriMed(farge);
            kuben.vriMed("gul");
            kuben.vriMot(farge);
        }

        else{
            kuben.vriMed("gul");
            kuben.vriMed(side2);
            kuben.vriMed("gul");
            kuben.vriMot(side2);
            kuben.vriMot("gul");
            kuben.vriMot(farge);
            kuben.vriMot("gul");
            kuben.vriMed(farge);
        }
    }

    //finner biter i topplaget som ikke har gult og setter de ned, til
    //toppen bare har gule biter
    private void settBiterNed(){

        while (!erToppenBareGul()){

            if (kuben.hentRute(1).equals("BLA") && kuben.hentRute(4).equals("BLA")
                && !kuben.hentRute(37).equals("GUL")){
                    settNedMatchetBit("blaa");
            }

            else if (kuben.hentRute(32).equals("ROD") && kuben.hentRute(31).equals("ROD")
                && !kuben.hentRute(39).equals("GUL")){
                    settNedMatchetBit("roed");
            }
            
            else if (kuben.hentRute(49).equals("GRN") && kuben.hentRute(52).equals("GRN")
                && !kuben.hentRute(43).equals("GUL")){
                    settNedMatchetBit("groenn");
            }

            else if (kuben.hentRute(13).equals("ORN") && kuben.hentRute(12).equals("ORN")
                && !kuben.hentRute(41).equals("GUL")){
                    settNedMatchetBit("oransje");
            }

            else{
                kuben.vriMed("gul");
            }
        }
    }

    private void flyttBitOpp(String side1, String side2){
        //side1 er den til hoeyre, side2 til venstre
        kuben.vriMed(side1);
        kuben.vriMed("gul");
        kuben.vriMot(side1);
        kuben.vriMot("gul");
        kuben.vriMot(side2);
        kuben.vriMot("gul");
        kuben.vriMed(side2);
    }

    private void orienterRiktigeBiter(){

        if (kuben.hentRute(5).equals("ROD") && kuben.hentRute(28).equals("BLA")){
            orienterBit("roed", "blaa");
        }

        if (kuben.hentRute(34).equals("GRN") && kuben.hentRute(50).equals("ROD")){
            orienterBit("groenn", "roed");
        }

        if (kuben.hentRute(48).equals("ORN") && kuben.hentRute(16).equals("GRN")){
            orienterBit("oransje", "groenn");
        }

        if (kuben.hentRute(10).equals("BLA") && kuben.hentRute(3).equals("ORN")){
            orienterBit("blaa", "oransje");
        }

    }

    //orienterer en bit som er i riktig posisjon, men har feil orientering
    private void orienterBit(String side1, String side2){
        //side1 er den til hoeyre, side2 til venstre
        kuben.vriMed(side1);
        kuben.vriMed("gul");
        kuben.vriMot(side1);
        kuben.vriMot("gul");
        kuben.vriMot(side2);
        kuben.vriMot("gul");
        kuben.vriMed(side2);
        kuben.vriMot("gul");
        kuben.vriMed(side1);
        kuben.vriMed("gul");
        kuben.vriMot(side1);
        kuben.vriMot("gul");
        kuben.vriMot(side2);
        kuben.vriMot("gul");
        kuben.vriMed(side2);
    }

    private void flyttNoeOpp(){

        if (!(kuben.hentRute(5).equals("BLA") && kuben.hentRute(28).equals("ROD"))){
            flyttBitOpp("roed", "blaa");
        }

        else if (!(kuben.hentRute(34).equals("ROD") && kuben.hentRute(50).equals("GRN"))){
            flyttBitOpp("groenn", "roed");
        }

        else if (!(kuben.hentRute(48).equals("GRN") && kuben.hentRute(16).equals("ORN"))){
            flyttBitOpp("oransje", "groenn");
        }

        else if (!(kuben.hentRute(10).equals("ORN") && kuben.hentRute(3).equals("BLA"))){
            flyttBitOpp("blaa", "oransje");
        }
    }
}
