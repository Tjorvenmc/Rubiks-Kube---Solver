package solver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Kube {
    private Side hvit;
    private Side groenn;
    private Side roed;
    private Side blaa;
    private Side oransje;
    private Side gul;

    //holder fargene paa rutene i kuben. indeks 0-8 = blaa side, 9-17 = oransje side
    // 18-26 = hvit side, 27-35 = roed side, 36-44 = gul side, 45-53 = groenn side
    private String[] ruter = new String[54];
    private String[] farger = { "BLA", "ORN", "HVT", "ROD", "GUL", "GRN" };

    //liste av hvilke vridninger som ble gjort paa kuben for aa loese den
    private ArrayList<String[]> vriListe = new ArrayList<String[]>();

    public void lagLoestKube(){
        //fyller rutene med fargene til en loest kube
        int teller = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                ruter[teller] = farger[i];
                teller++;
            }
        }
    }

    //vri en side med klokken
    public void vriMed(String side){
        switch (side) {
            case "hvit":
                hvit.vriMed();
                break;
            case "groenn":
                groenn.vriMed();
                break;
            case "roed":
                roed.vriMed();
                break;
            case "blaa":
                blaa.vriMed();
                break;
            case "oransje":
                oransje.vriMed();
                break;
            case "gul":
                gul.vriMed();
                break;
            default:
                System.out.println("Input er ikke en farge. Kan ikke vri uten farge.");
                break;
        }

        String[] vridning = {side, "med"};
        vriListe.add(vridning);
    }

    //vri en side mot klokken
    public void vriMot(String side){
        switch (side) {
            case "hvit":
                hvit.vriMot();
                break;
            case "groenn":
                groenn.vriMot();
                break;
            case "roed":
                roed.vriMot();
                break;
            case "blaa":
                blaa.vriMot();
                break;
            case "oransje":
                oransje.vriMot();
                break;
            case "gul":
                gul.vriMot();
                break;
            default:
                System.out.println("Input er ikke en farge. Kan ikke vri uten farge.");
                break;
        }

        String[] vridning = {side, "mot"};
        vriListe.add(vridning);
    }

    //sjekk en rute paa kuben
    public String hentRute(int rute) {
        return ruter[rute];
    }

    //sett en rute paa kuben
    public void settRute(int rute, String farge) {
        ruter[rute] = farge;
    }

    public String[] hentRuter(){
        return ruter;
    }

    //scrambler kuben med én av 100 tilfeldige scrambles
    public void scramble() throws FileNotFoundException{
        try{
        File scrambles = new File("scrambles.txt");
        Scanner skanner = new Scanner(scrambles);
        ArrayList<String[]> scrambleliste = new ArrayList<String[]>();

        for (int i = 0; i < 100; i++){
            String linje = skanner.nextLine();
            String linjebiter[] = linje.strip().split(" ");
            scrambleliste.add(linjebiter);
        }
        skanner.close();

        Random rand = new Random();
        int randint = rand.nextInt(99); 

        String[] scramble = scrambleliste.get(randint);

        for (int i = 0; i < scramble.length; i++){
            String vridning = scramble[i];

            if (vridning.equals("U")){
                blaa.vriMed();
            }

            else if (vridning.equals("U'")){
                blaa.vriMot();
            }

            else if (vridning.equals("U2")){
                blaa.vriMot();
                blaa.vriMot();
            }

            else if (vridning.equals("L")){
                oransje.vriMed();
            }

            else if (vridning.equals("L'")){
                oransje.vriMot();
            }

            else if (vridning.equals("L2")){
                oransje.vriMot();
                oransje.vriMot();
            }

            else if (vridning.equals("F")){
                hvit.vriMed();
            }

            else if (vridning.equals("F'")){
                hvit.vriMot();
            }

            else if (vridning.equals("F2")){
                hvit.vriMot();
                hvit.vriMot();
            }

            else if (vridning.equals("R")){
                roed.vriMed();
            }

            else if (vridning.equals("R'")){
                roed.vriMot();
            }

            else if (vridning.equals("R2")){
                roed.vriMot();
                roed.vriMot();
            }

            else if (vridning.equals("B")){
                gul.vriMed();
            }

            else if (vridning.equals("B'")){
                gul.vriMot();
            }

            else if (vridning.equals("B2")){
                gul.vriMot();
                gul.vriMot();
            }

            else if (vridning.equals("D")){
                groenn.vriMed();
            }

            else if (vridning.equals("D'")){
                groenn.vriMot();
            }

            else if (vridning.equals("D2")){
                groenn.vriMot();
                groenn.vriMot();
            }
        }

        } catch (Exception e){
            System.out.println("Finner ikke scrambles-fil");
        }
    }

    public void loes(){
        vriListe = new ArrayList<String[]>();
        HvitKryss krysset = new HvitKryss(this);
        HviteHjoerner hvitehjoerner = new HviteHjoerner(this);
        Midtlaget midtlaget = new Midtlaget(this);
        GultKryss gultkryss = new GultKryss(this);
        GuleHjoerner gulehjoerner = new GuleHjoerner(this);

        krysset.settBunnBiter();
        krysset.fyllHviteBiter();
        krysset.orienterKrysset();

        hvitehjoerner.settPaaPlassHjoerner();

        midtlaget.fiksMidtlaget();

        gultkryss.lagGultKryss();
        gultkryss.orienterGultKryss();

        gulehjoerner.fiksHjoerner();
        gulehjoerner.orienterHjoerner();
    }

    public ArrayList<String[]> hentVriListe(){
        return vriListe;
    }

    //fjerner unødvendige vridninger som programmet har lagt til for aa
    //f.eks sjekke tilstanden i kuben.
    public void ryddVriListe(){
        ArrayList<String[]> nyListe = vriListe;

        String[] vri1;
        String[] vri2;
        String[] vri3;
        String[] vri4;


        for(int i = 0; i < 3; i++){

            //bytter ut to vridninger samme vei med en dobbel vridning
            for (int j = 0; j < nyListe.size()-1; j++){
                vri1 = nyListe.get(j);
                vri2 = nyListe.get(j + 1);

                if (erArrayLike(vri1, vri2) && !vri1[1].equals("to ganger")){
                    vri2[1] = "to ganger";
                    nyListe.set(j, vri2);
                    nyListe.remove(j + 1);
                }
            }

            //fjerner f.eks "hvit med" etterfulgt av "hvit mot" (og motsatt)
            for (int j = 0; j < nyListe.size()-1; j++){
                vri1 = nyListe.get(j);
                vri2 = nyListe.get(j + 1);

                if (vri1[0].equals(vri2[0]) && !vri1[1].equals(vri2[1])
                    && !vri1[1].equals("to ganger") && !vri2[1].equals("to ganger")){
                    nyListe.remove(j +1);
                    nyListe.remove(j);
                }
            }

            //fjerner to vridninger én vei, etterfulgt av to vridninger andre veien
            for (int j = 0; j < nyListe.size()-3; j++){
                vri1 = nyListe.get(j);
                vri2 = nyListe.get(j + 1);
                vri3 = nyListe.get(j + 2);
                vri4 = nyListe.get(j + 3);

                if (erArrayLike(vri1, vri2) && erArrayLike(vri3, vri4) && !vri1[1].equals(vri3[1])
                    && !vri1[1].equals("to ganger") && !vri3[1].equals("to ganger")){
                        nyListe.remove(j + 3);
                        nyListe.remove(j + 2);
                        nyListe.remove(j + 1);
                        nyListe.remove(j);
                    }
            }

            //fjerner tre vridninger samme vei og bytter det ut med én vri andre veien
            for (int j = 0; j < nyListe.size()-2; j++){
                vri1 = nyListe.get(j + 2);
                vri2 = nyListe.get(j + 1);
                vri3 = nyListe.get(j);

                if (erArrayLike(vri1, vri2) && erArrayLike(vri2, vri3)){

                    if (vri1[1].equals("mot")){
                    vri2[1] = "med";
                    nyListe.set(j, vri2);
                    nyListe.remove(j + 2);
                    nyListe.remove(j + 1);
                    }

                    else{
                        vri2[1] = "mot";
                        nyListe.set(j, vri2);
                        nyListe.remove(j + 2);
                        nyListe.remove(j + 1);
                    }
                }
            }

            //fjerner fire like vridninger etter hverandre
            for (int j = 0; j < nyListe.size() -3 ; j++){
                vri1 = nyListe.get(j);
                vri2 = nyListe.get(j + 1);
                vri3 = nyListe.get(j + 2);
                vri4 = nyListe.get(j + 3);

                if (erArrayLike(vri1, vri2) && erArrayLike(vri1, vri3) && erArrayLike(vri1, vri4)){
                    nyListe.remove(j + 3);
                    nyListe.remove(j + 2);
                    nyListe.remove(j + 1);
                    nyListe.remove(j);
                }
            }

            //Hvis det er en dobbel vridning før eller etter en vridning av samme farge
            for (int j = 0; j < nyListe.size() - 1; j++){
                vri1 = nyListe.get(j);
                vri2 = nyListe.get(j + 1);

                if ((vri1[0].equals(vri2[0]) && vri1[1].equals("to ganger") && vri2[1].equals("mot"))
                    || (vri1[0].equals(vri2[0]) && vri2[1].equals("to ganger") && vri1[1].equals("mot"))){
                        vri2[1] = "med";
                        nyListe.set(j,vri2);
                        nyListe.remove(j + 1);
                }

                else if ((vri1[0].equals(vri2[0]) && vri2[1].equals("to ganger") && vri1[1].equals("med"))
                        || (vri1[0].equals(vri2[0]) && vri1[1].equals("to ganger") && vri2[1].equals("med"))){
                            vri2[1] = "mot";
                            nyListe.set(j,vri2);
                            nyListe.remove(j + 1);
                        }
            }
        }
        vriListe = nyListe;
    }

    private Boolean erArrayLike(String[] arr1, String[] arr2){
        for (int i = 0; i < arr1.length; i++){
            if (!arr1[i].equals(arr2[i])){
                return false;
            }
        }

        return true;
    }

    public Boolean kanKubenLoeses(){
        //sjekker foerst om det er 9 ruter av hver farge
        HashMap<String,Integer> fargeTelleDict = new HashMap<>();
        fargeTelleDict.put("BLA",0);
        fargeTelleDict.put("ORN",0);
        fargeTelleDict.put("HVT",0);
        fargeTelleDict.put("ROD",0);
        fargeTelleDict.put("GUL",0);
        fargeTelleDict.put("GRN",0);

        ArrayList<HashSet<String>> testhjListe = new ArrayList<>();
        HashSet<String> testhj1 = lagSet("BLA", "HVT", "ORN");
        testhjListe.add(testhj1);
        HashSet<String> testhj2 = lagSet("BLA", "HVT", "ROD");
        testhjListe.add(testhj2);
        HashSet<String> testhj3 = lagSet("ROD", "HVT", "GRN");
        testhjListe.add(testhj3);
        HashSet<String> testhj4 = lagSet("ORN", "HVT", "GRN");
        testhjListe.add(testhj4);
        HashSet<String> testhj5 = lagSet("BLA", "ORN", "GUL");
        testhjListe.add(testhj5);
        HashSet<String> testhj6 = lagSet("BLA", "ROD", "GUL");
        testhjListe.add(testhj6);
        HashSet<String> testhj7 = lagSet("GRN", "ROD", "GUL");
        testhjListe.add(testhj7);
        HashSet<String> testhj8 = lagSet("ORN", "GRN", "GUL");
        testhjListe.add(testhj8);

        ArrayList<HashSet<String>> hjListe = new ArrayList<>();
        HashSet<String> hj1 = lagSet(ruter[6], ruter[18], ruter[11]);
        hjListe.add(hj1);
        HashSet<String> hj2 = lagSet(ruter[8], ruter[20], ruter[27]);
        hjListe.add(hj2);
        HashSet<String> hj3 = lagSet(ruter[33], ruter[26], ruter[47]);
        hjListe.add(hj3);
        HashSet<String> hj4 = lagSet(ruter[17], ruter[24], ruter[45]);
        hjListe.add(hj4);
        HashSet<String> hj5 = lagSet(ruter[0], ruter[9], ruter[38]);
        hjListe.add(hj5);
        HashSet<String> hj6 = lagSet(ruter[2], ruter[29], ruter[36]);
        hjListe.add(hj6);
        HashSet<String> hj7 = lagSet(ruter[53], ruter[35], ruter[42]);
        hjListe.add(hj7);
        HashSet<String> hj8 = lagSet(ruter[15], ruter[51], ruter[44]);
        hjListe.add(hj8);

        ArrayList<HashSet<String>> testMidtListe = new ArrayList<>();
        HashSet<String> testmidt1 = lagSet("BLA", "HVT");
        testMidtListe.add(testmidt1);
        HashSet<String> testmidt2 = lagSet("ROD", "HVT");
        testMidtListe.add(testmidt2);
        HashSet<String> testmidt3 = lagSet("GRN", "HVT");
        testMidtListe.add(testmidt3);
        HashSet<String> testmidt4 = lagSet("ORN", "HVT");
        testMidtListe.add(testmidt4);
        HashSet<String> testmidt5 = lagSet("BLA", "ROD");
        testMidtListe.add(testmidt5);
        HashSet<String> testmidt6 = lagSet("ROD", "GRN");
        testMidtListe.add(testmidt6);
        HashSet<String> testmidt7 = lagSet("GRN", "ORN");
        testMidtListe.add(testmidt7);
        HashSet<String> testmidt8 = lagSet("ORN", "BLA");
        testMidtListe.add(testmidt8);
        HashSet<String> testmidt9 = lagSet("BLA", "GUL");
        testMidtListe.add(testmidt9);
        HashSet<String> testmidt10 = lagSet("ROD", "GUL");
        testMidtListe.add(testmidt10);
        HashSet<String> testmidt11 = lagSet("GRN", "GUL");
        testMidtListe.add(testmidt11);
        HashSet<String> testmidt12 = lagSet("ORN", "GUL");
        testMidtListe.add(testmidt12);

        ArrayList<HashSet<String>> midtListe = new ArrayList<>();
        HashSet<String> midt1 = lagSet(ruter[19], ruter[7]);
        midtListe.add(midt1);
        HashSet<String> midt2 = lagSet(ruter[23], ruter[30]);
        midtListe.add(midt2);
        HashSet<String> midt3 = lagSet(ruter[25], ruter[46]);
        midtListe.add(midt3);
        HashSet<String> midt4 = lagSet(ruter[21], ruter[14]);
        midtListe.add(midt4);
        HashSet<String> midt5 = lagSet(ruter[5], ruter[28]);
        midtListe.add(midt5);
        HashSet<String> midt6 = lagSet(ruter[34], ruter[50]);
        midtListe.add(midt6);
        HashSet<String> midt7 = lagSet(ruter[48], ruter[16]);
        midtListe.add(midt7);
        HashSet<String> midt8 = lagSet(ruter[10], ruter[3]);
        midtListe.add(midt8);
        HashSet<String> midt9 = lagSet(ruter[1], ruter[37]);
        midtListe.add(midt9);
        HashSet<String> midt10 = lagSet(ruter[32], ruter[39]);
        midtListe.add(midt10);
        HashSet<String> midt11 = lagSet(ruter[52], ruter[43]);
        midtListe.add(midt11);
        HashSet<String> midt12 = lagSet(ruter[12], ruter[41]);
        midtListe.add(midt12);

        for (int i = 0; i < 54;i++){
            fargeTelleDict.put(ruter[i], fargeTelleDict.get(ruter[i]) +1);
        }

        //hvis det er fler eller faerre enn 9 av en farge
        for (int fargeAnt : fargeTelleDict.values()){
            if (fargeAnt != 9){
                return false;
            }
        }

        //sjekker om hjoernebitene har riktig fargekombinasjoner
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < testhjListe.size(); j++){
                if (hjListe.get(i).equals(testhjListe.get(j))){
                    testhjListe.remove(j);
                }
            }
        }

        //false hvis hjoernene ikke har riktige farger
        if (testhjListe.size() > 0){
            return false;
        }

        //samme som over, bare for midtbiter
        for (int i = 0; i < 12; i++){
            for (int j = 0; j < testMidtListe.size(); j++){
                if (midtListe.get(i).equals(testMidtListe.get(j))){
                    testMidtListe.remove(j);
                }
            }
        }

        if (testMidtListe.size() > 0){
            return false;
        }

        return true;
    }

    public HashSet<String> lagSet(String string1, String string2, String string3){
        HashSet<String> set = new HashSet<>();
        set.add(string1);
        set.add(string2);
        set.add(string3);

        return set;
    }

    public HashSet<String> lagSet(String string1, String string2){
        HashSet<String> set = new HashSet<>();
        set.add(string1);
        set.add(string2);

        return set;
    }

    @Override
    public String toString() {

        String kubeprint = "************-------------" +
                "\n************|" + hentRute(0) + "|" + hentRute(1) + "|" + hentRute(2) + "|" +
                "\n************-------------" +
                "\n************|" + hentRute(3) + "|" + hentRute(4) + "|" + hentRute(5) + "|" +
                "\n************-------------" +
                "\n************|" + hentRute(6) + "|" + hentRute(7) + "|" + hentRute(8) + "|" +
                "\n------------*-----------*-----------*-----------" +

                "\n|" + hentRute(9) + "|" + hentRute(10) + "|" + hentRute(11) + "*" +
                hentRute(18) + "|" + hentRute(19) + "|" + hentRute(20) + "*" + hentRute(27) +
                "|" + hentRute(28) + "|" + hentRute(29) + "*" + hentRute(36) + "|" + hentRute(37) +
                "|" + hentRute(38) + "|" +

                "\n------------*-----------*-----------*-----------" +

                "\n|" + hentRute(12) + "|" + hentRute(13) + "|" + hentRute(14) + "*" +
                hentRute(21) + "|" + hentRute(22) + "|" + hentRute(23) + "*" + hentRute(30) +
                "|" + hentRute(31) + "|" + hentRute(32) + "*" + hentRute(39) + "|" + hentRute(40) +
                "|" + hentRute(41) + "|" +

                "\n------------*-----------*-----------*-----------" +

                "\n|" + hentRute(15) + "|" + hentRute(16) + "|" + hentRute(17) + "*" +
                hentRute(24) + "|" + hentRute(25) + "|" + hentRute(26) + "*" + hentRute(33) +
                "|" + hentRute(34) + "|" + hentRute(35) + "*" + hentRute(42) + "|" + hentRute(43) +
                "|" + hentRute(44) + "|" +

                "\n------------*-----------*-----------*-----------" +
                "\n************|" + hentRute(45) + "|" + hentRute(46) + "|" + hentRute(47) + "|" +
                "\n************-------------" +
                "\n************|" + hentRute(48) + "|" + hentRute(49) + "|" + hentRute(50) + "|" +
                "\n************-------------" +
                "\n************|" + hentRute(51) + "|" + hentRute(52) + "|" + hentRute(53) + "|" +
                "\n************--------------";

        return kubeprint;
    }

}
