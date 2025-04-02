package edu.miracosta.cs112.lab07; //package name here depending on your IDE

import javafx.application.Application;  //abstract class used for JavaFX GUI's
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;              //class for GUI window
import javafx.scene.Scene;              //class for specific view in GUI window
import javafx.scene.layout.VBox;        //class for layout pane, organized top-to-bottom
import javafx.scene.control.Label;      //class for label component
import javafx.scene.control.Button;     //class for button component
import javafx.event.EventHandler;       //interface for handling events
import javafx.event.ActionEvent;        //class for type of event for action (like button or key pressed)
import javafx.scene.layout.BorderPane;

public class HelloApplication extends Application implements EventHandler<ActionEvent> { // Inheriting core functionality + this class will handle events

    /*** GUI COMPONENTS ***/
    // Class reference variables for components
    private Label labelTopLeft;
    private Label labelTopRight;
    private Button buttonBottomLeft;
    private Button buttonBottomRight;
    private TextField centerTextField;
    private int buttonPressCount = 0; // Counter for second button presses

    /*** DRIVER main ***/
    public static void main(String[] args) {
        launch(args); // Method from Application class, must be called to setup javafx application
    }

    @Override
    public void start(Stage primaryStage) {

        // Set Stage Name
        primaryStage.setTitle("Hello GUI: Demo Stage 1");

        // Create controls
        labelTopLeft = new Label("Hello GUI World");
        labelTopRight = new Label("Welcome!");
        buttonBottomLeft = new Button("Set Text");
        buttonBottomRight = new Button("Count Presses");
        centerTextField = new TextField("Enter Text");
        centerTextField.setMaxWidth(200);

        // Register event handlers
        buttonBottomLeft.setOnAction(this);
        buttonBottomRight.setOnAction(this);

        // Create top pane for labels using a BorderPane to align left and right
        BorderPane topPane = new BorderPane();
        topPane.setLeft(labelTopLeft);
        topPane.setRight(labelTopRight);

        // Create bottom pane for buttons similarly
        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(buttonBottomLeft);
        bottomPane.setRight(buttonBottomRight);

        // Main layout: BorderPane with top, center, and bottom sections
        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setCenter(centerTextField);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        /*** Stylization Testing ***/
        // Add margins for both top labels
        BorderPane.setMargin(labelTopLeft, new Insets(5));
        BorderPane.setMargin(labelTopRight, new Insets(5));

        // API Method Styling:
        // Set Font
        labelTopLeft.setFont(Font.font("Serif", 20));

        // Set Text Color
        labelTopLeft.setTextFill(Color.DARKBLUE);

        // Set Underline
        labelTopLeft.setUnderline(true);

        // Add a dropshadow
        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setRadius(5.0);
        dropShadow1.setOffsetX(3.0);
        dropShadow1.setOffsetY(3.0);
        dropShadow1.setColor(Color.color(0.4, 0.5, 0.5));
        // labelTopLeft.setEffect(dropShadow1);

        // Add a 'Bloom' Effect
        Bloom bloom1 = new Bloom();
        bloom1.setThreshold(0.1);
        // labelTopLeft.setEffect(bloom1);

        // Add a reflection effect
        Reflection reflection1 = new Reflection();
        reflection1.setFraction(1.0); // Sets: The fraction of the input that is visible in the reflection.
        reflection1.setBottomOpacity(0.3); // Sets: The opacity of the reflection at its bottom extreme.
        reflection1.setTopOpacity(0.9); // Sets: The opacity of the reflection at its top extreme.
        reflection1.setTopOffset(0.0); // Sets: The distance between the bottom of the input and the top of the reflection.
        // labelTopLeft.setEffect(reflection1);

        // For multiple setEffect()s at once:
        // Chain bloom into drop shadow
        dropShadow1.setInput(bloom1);
        // Chain the previous effects into reflection
        reflection1.setInput(dropShadow1);
        // Now apply the chained effect to the label
        labelTopLeft.setEffect(reflection1);

        // CSS Styling: (See JavaFX CSS Reference Guide) - https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html
        // Set Font, Size, Color, and Underline
//         labelTopRight.setStyle("-fx-font-family: 'DialogInput'; -fx-font-size: 20px; -fx-text-fill: darkblue; -fx-underline: true;");
        // Add a dropshadow - dropshadow(<blur-type> , <color> , <radius> , <spread> , <offsetX> , <offsetY>)
//         labelTopRight.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0.5, 0, 5);");
         // Or Both at once: Must use setStyle() only once
        labelTopRight.setStyle(
                "-fx-font-family: 'Comic Sans MS'; " +
                        "-fx-font-size: 36px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: linear-gradient(from 0% 0% to 100% 100%, red, orange); " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,255,0,0.8), 15, 0.7, 0, 5);"
        );



    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == buttonBottomLeft) {
            // When the first button is pressed, update the top-left label with the text field content.
            labelTopLeft.setText(centerTextField.getText());
        } else if (event.getSource() == buttonBottomRight) {
            // When the second button is pressed, increment a counter and display it on the top-right label.
            buttonPressCount++;
            labelTopRight.setText("Count: " + buttonPressCount);
        }
    }
}