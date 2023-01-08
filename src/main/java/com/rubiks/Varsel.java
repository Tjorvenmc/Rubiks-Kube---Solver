package com.rubiks;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.Font;

public class Varsel{

    public static void visVarsel(String navn, String melding){
        Stage vindu = new Stage();

        vindu.initModality(Modality.APPLICATION_MODAL);
        vindu.setTitle(navn);
        vindu.setMinWidth(300);

        Label label = new Label(melding);
        label.setFont( new Font("Roboto",15));
        Button lukk = new Button("Ok");
        lukk.setOnAction(e ->{
            vindu.close();
        });

        VBox layout = new VBox(10);
        layout.setSpacing(30);
        layout.getChildren().addAll(label,lukk);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        vindu.setScene(scene);
        vindu.showAndWait();
    }
}