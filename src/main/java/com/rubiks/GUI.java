package com.rubiks;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.Font;

public class GUI extends Application implements EventHandler<ActionEvent>{

    Kontroller kontroller = new Kontroller(this);
    Stage vindu;
    BorderPane layout = new BorderPane(); //layout for vindu hvor man trykker inn kuben
    BorderPane layout2 = new BorderPane(); //layout i vindu som viser løsning
    VBox inputMeny = new VBox(); //knapper paa bunn i foerste vindu
    VBox bunnMeny = new VBox();
    HBox fargeKnapper= new HBox();
    HBox fargeVisning = new HBox();
    GridPane inputRuter = new GridPane(); //ruter i foerste vindu
    GridPane loeseRuter = new GridPane(); //ruter i andre vindu
    Scene inputCube = new Scene(layout, 1000,800); //scene foerste vindu
    Scene solveCube = new Scene(layout2,1000,800); //scene i andre vindu
    Label valgtFargeLabel = new Label(); //Viser det naavaerende fargevalget
   
    HBox loeseknapper = new HBox();

    Label[] visningsLabels = new Label[48];
    Button[] inputKnapper = new Button[48];
    int visningsTeller = 1;

    String manglendeRuter = "Noen ruter er ikke farget.\nVelg farge for alle ruter.";
    String uloeseligKube = "Denne kuben kan ikke løses.\nDobbeltsjekk at alle ruter har riktig farge.";

    //henter fargene som har blitt trykket inn fra kubeobjekt, saa de
    //kan settes i labelrutenettet som lages under.
    HashMap<String,String> fargeDict = new HashMap<>();
    HashMap<String,String> fargeOrd = new HashMap<>();

    //koordinater til rutene i gridpane inpututer og gridpane loeseruter.
    int[] kolkoordinat = {3,4,5,3,5,3,4,5,0,1,2,3,4,5,6,7,8,9,10,11,0,2,
                          3,5,6,8,9,11,0,1,2,3,4,5,6,7,8,9,10,11,3,4,5,
                          3,5,3,4,5};

    int[] radkoordinat = {0,0,0,1,1,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,
                          4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,6,6,6,7,7,8,8,8};

    int[] kubeindeks = {0,1,2,3,5,6,7,8,9,10,11,18,19,20,27,28,29,36,37,38,
                        12,14,21,23,30,32,39,41,15,16,17,24,25,26,33,34,35,
                        42,43,44,45,46,47,48,50,51,52,53};

    String[] kubefarger = {"BLA","ORN","HVT","ROD","GUL","GRN"};

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //henter fargene som har blitt trykket inn fra kubeobjekt, saa de
        //kan settes i labelrutenettet som lages under.
        fargeDict.put("BLA", "-fx-background-color: #0600ff;");
        fargeDict.put("ORN", "-fx-background-color: #ff9600;");
        fargeDict.put("HVT", "-fx-background-color: #ffffff;");
        fargeDict.put("ROD", "-fx-background-color: #ff0000;");
        fargeDict.put("GUL", "-fx-background-color: #ffea00;");
        fargeDict.put("GRN", "-fx-background-color: #13db21;");
        fargeOrd.put("blaa", "blå");
        fargeOrd.put("oransje","oransje");
        fargeOrd.put("roed", "rød");
        fargeOrd.put("hvit","hvit");
        fargeOrd.put("gul","gul");
        fargeOrd.put("groenn","grønn");


        //legger til knapper hvor man kan inputte fargene paa kuben.
        for (int i = 0; i < 48; i++){
            Button inputKnapp = new Button();
            inputKnapp.setMinHeight(60);
            inputKnapp.setMinWidth(60);
            inputKnapp.setStyle("-fx-background-color: #4f4f4f;");
            int indeks = kubeindeks[i];
            inputKnapp.setOnAction(
                e -> {
                    kontroller.inputRutetrykk(indeks);
                    inputKnapp.setStyle(kontroller.hentFargeTilKnappen());
                });
            inputRuter.add(inputKnapp,kolkoordinat[i],radkoordinat[i]);
            inputKnapper[i] = inputKnapp;
        }

        //knapper i midten av hver side av kuben (som alltid er samme farge
        // saa ingen input trengs)
        int[] kolmidt = {4,1,4,7,10,4};
        int[] radmidt = {1,4,4,4,4,7};

        //for gridpane inputruter
        for (int i = 0; i < 6; i++){
            Button midtknapp = new Button();
            midtknapp.setStyle(fargeDict.get(kubefarger[i]));
            midtknapp.setMinHeight(60);
            midtknapp.setMinWidth(60);
            inputRuter.add(midtknapp,kolmidt[i],radmidt[i]);
        }

        //for gridpane inputruter
        for (int i = 0; i < 6; i++){
            Label midtFarge = new Label();
            midtFarge.setStyle(fargeDict.get(kubefarger[i]));
            midtFarge.setMinHeight(60);
            midtFarge.setMinWidth(60);
            loeseRuter.add(midtFarge,kolmidt[i],radmidt[i]);
        }

        //gaar fra scene solveCube tilbake til inputCube og nullstiller kuben
        Button tilbakeTilInput = new Button("Løs en annen kube");
        tilbakeTilInput.setOnAction(e -> {
            visningsTeller = 1;

            for (int i = 0; i < 48; i++){
                inputKnapper[i].setStyle("-fx-background-color: #4f4f4f;");
                kontroller.resetKnapp(kubeindeks[i]);
            }

            vindu.setScene(inputCube);

        });
        Label chooseColor = new Label("Velg farge: ");
        fargeKnapper.getChildren().add(chooseColor);

        //setter inn knapper man kan trykke paa for aa velge hvilken farge man
        //vil sette paa en rute
        for (int i = 0; i < 6; i++){
            Button fargeknapp = new Button();
            String inputFarge = kubefarger[i];
            String fargeKode = fargeDict.get(kubefarger[i]);
            fargeknapp.setStyle(fargeKode);
            fargeknapp.setOnAction(e -> {
                kontroller.velgFargeKnapp(inputFarge, fargeKode);
                valgtFargeLabel.setStyle(fargeKode);
            });
            
            fargeknapp.setMinHeight(60);
            fargeknapp.setMinWidth(60);
            fargeKnapper.getChildren().add(fargeknapp);
        }

        //viser hva som er neste trekk i scene solveCube
        Label vridning = new Label("");

        //Holder oversikt over hvor mange trekk man har gjort
        Label vriDisplay = new Label(""); 

        //gaar tilbake til forrige trekk i scene solveCube
        Button tilbake = new Button("Forrige vri");
        tilbake.setOnAction(e -> {
            kontroller.forrigeVri();

            if (visningsTeller > 1){
            int vriSteg = kontroller.hentVriTeller();
            String[] nesteVri = kontroller.hentLoesningListe().get(vriSteg);

            if (nesteVri[1].equals("to ganger")){
                vridning.setText("Vri " + fargeOrd.get(nesteVri[0]) + " side " + nesteVri[1]);
            }

            else{
                vridning.setText("Vri " + fargeOrd.get(nesteVri[0]) + " side " + nesteVri[1] + " klokken"); 
            }

            visningsTeller --;
            vriDisplay.setText(visningsTeller + "/" + kontroller.hentLoesningListe().size());
            oppdaterVisning();
            }
        });

         //gaar til neste trekk i scene solveCube
         Button frem = new Button("Neste vri");
         frem.setOnAction(e -> {
            kontroller.nesteVri();

            if(visningsTeller < kontroller.hentLoesningListe().size()){
                int vriSteg = kontroller.hentVriTeller();

                String[] nesteVri = kontroller.hentLoesningListe().get(vriSteg);

                if (nesteVri[1].equals("to ganger")){
                    vridning.setText("Vri " + fargeOrd.get(nesteVri[0]) + " side " + nesteVri[1]);
                }

                else if(nesteVri[0].equals("Gratulerer, ")){
                    vridning.setText(nesteVri[0] + nesteVri[1]);
                }

                else{
                    vridning.setText("Vri " + fargeOrd.get(nesteVri[0]) + " side " + nesteVri[1] + " klokken"); 
                }

                visningsTeller ++;
                vriDisplay.setText(visningsTeller + "/" + kontroller.hentLoesningListe().size());
                oppdaterVisning();
            }
         });

        //knapp for aa gaa til vinduet hvor trekk for aa loese kuben vises
        Button solvebutton = new Button("Løs Kuben");

        solvebutton.setOnAction(e -> {

            for (int i = 0; i < 48; i++){
                //for loeseRuter
                Label loeseRute = new Label();
                loeseRute.setMinHeight(60);
                loeseRute.setMinWidth(60);
                loeseRute.setStyle(fargeDict.get(kontroller.hentVisningsRute(kubeindeks[i])));
                loeseRuter.add(loeseRute,kolkoordinat[i],radkoordinat[i]);
                visningsLabels[i] = loeseRute;
            }

            kontroller.loesKnapp();
            Boolean feilInput = kontroller.sjekkFeilInput();
            Boolean feilKube = kontroller.sjekkFeilKube();

            if (feilInput){
                Varsel.visVarsel("Tomme ruter i kuben!",manglendeRuter);
                return;
            }

            if (feilKube){
                Varsel.visVarsel("Uløselig kube!", uloeseligKube);
                return;
            }

            if (kontroller.hentLoesningListe().size() != 1){
                String[] nesteVri = kontroller.hentLoesningListe().get(kontroller.hentVriTeller());

                if (nesteVri[1].equals("to ganger")){
                    vridning.setText("Vri " + fargeOrd.get(nesteVri[0]) + " side " + nesteVri[1]);
                }

                else{
                    vridning.setText("Vri " + fargeOrd.get(nesteVri[0]) + " side " + nesteVri[1] + " klokken"); 
                }

            } else{
                vridning.setText("Kuben er løst");
            }

            vriDisplay.setText(visningsTeller + "/" + kontroller.hentLoesningListe().size());

        vindu.setScene(solveCube);
        });

        //tekst som viser hvilken farge man har valgt og rute som viser fargen
        Label valgtFarge = new Label("Farge:");

        //visuelle instillinger for elementer
        inputRuter.setHgap(3);
        inputRuter.setVgap(3);
        loeseRuter.setHgap(3);
        loeseRuter.setVgap(3);
        fargeKnapper.setSpacing(6);
        fargeKnapper.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #b7efff;");
        layout2.setStyle("-fx-background-color: #b7efff;");
        layout.setPadding(new Insets(30,30,30,30));
        layout2.setPadding(new Insets(30,30,30,30));
        layout2.setCenter(loeseRuter);
        loeseknapper.setAlignment(Pos.CENTER);
        loeseknapper.setSpacing(40);
        valgtFarge.setFont(new Font("Roboto",30));
        valgtFargeLabel.setMinHeight(60);
        valgtFargeLabel.setMinWidth(60);
        tilbake.setMinHeight(70);
        tilbake.setMinWidth(100);
        tilbake.setFont(new Font("Roboto", 12));
        frem.setMinHeight(70);
        frem.setMinWidth(100);
        frem.setFont(new Font("Roboto", 12));
        solvebutton.setMinHeight(100);
        solvebutton.setMinWidth(100);
        chooseColor.setFont(new Font("Roboto",30));
        vriDisplay.setFont(new Font("Roboto", 30));
        vridning.setFont(new Font("Roboto", 30));
        tilbakeTilInput.setFont(new Font("Roboto", 12));
        tilbakeTilInput.setMinHeight(70);
        tilbakeTilInput.setMinWidth(100);
        bunnMeny.setAlignment(Pos.CENTER);
        fargeVisning.setPadding(new Insets(0,0,10,243));
        fargeVisning.setSpacing(27);

        //klargjoer visning
        layout.setCenter(inputRuter);
        layout.setRight(solvebutton);
        layout.setBottom(inputMeny);

        loeseknapper.getChildren().addAll(tilbake,vridning,frem);
        layout2.setBottom(bunnMeny);
        bunnMeny.getChildren().addAll(vriDisplay,loeseknapper);
        layout2.setRight(tilbakeTilInput);
        inputMeny.getChildren().addAll(fargeVisning,fargeKnapper);
        fargeVisning.getChildren().addAll(valgtFarge, valgtFargeLabel);

        vindu = primaryStage;
        vindu.setTitle("Tjorvens Kubeløser");
        vindu.setScene(inputCube);
        vindu.show();
    }

    //Oppdaterer farger i rutene i scene solveCube
    private void oppdaterVisning(){
        for (int i = 0; i < 48; i++){
            //for loeseRuter
            visningsLabels[i].setStyle(fargeDict.get(kontroller.hentVisningsRute(kubeindeks[i])));
        }
    }

    @Override
    public void handle(ActionEvent event){
    }
}