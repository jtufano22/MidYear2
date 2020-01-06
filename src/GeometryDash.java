import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class GeometryDash extends Application {
    private ImageView dude = new ImageView("squareDude.png");


    private int height = 500;
    private int width = 750;

    private int dudeX = 64;
    private int dudeY = height - 100;

    private boolean jump = false;
    private boolean up = false;
    private int startingPos;

    private Rectangle ground = new Rectangle(0, height-68, width+28, 78);

    Pane pane = new Pane();
    Rectangle r1 = new Rectangle(0,400,30, 30);





    public void start(Stage stage) {
        pane.setStyle("-fx-background-color: radial-gradient(from 100% 100% to 100% 100%, #000000, #FFFFFF);");

        ground.setFill(Color.DARKBLUE);

        newBlock();
        dude.relocate(dudeX, dudeY);
        pane.getChildren().addAll(dude, ground, r1);



        new AnimationTimer() {
            public void handle(long now){
                pane.setOnKeyPressed(e ->
                {
                    KeyCode key = e.getCode();
                    if(key.equals(KeyCode.W) || key.equals(KeyCode.UP) || key.equals(KeyCode.SPACE)) {
                        jump = true;
                        up = true;
                        startingPos = dudeY;
                    }
                });
                if(jump) {
                    jump();
                }
                dude.relocate(dudeX, dudeY);
                pane.requestFocus();


            }
        }.start();

        Scene scene = new Scene(pane, width, height);
        stage.setScene(scene);
        stage.setTitle("Square Dude");
        stage.getIcons().add(new Image("squareDude.png"));
        stage.setResizable(false);
        stage.show();
    }

    public void jump() {
        if(up && startingPos-96 < dudeY) {
            dudeY-=4;
        }
        else if(startingPos-96 >= dudeY) {
            up = false;
        }

        if(!up && height-100 > dudeY) {
            dudeY+=4;
        }
        else if (height-100 <= dudeY) {
            jump = false;
        }
    }

    public void newBlock() {

        Timeline blockMovement = new Timeline(new KeyFrame(Duration.seconds(.01), new EventHandler<ActionEvent>() {

            double i = 1000 ;
            public void handle(ActionEvent event) {
                r1.setX(i);
                i--;
            }
        }));
        blockMovement.setCycleCount(Timeline.INDEFINITE);
        blockMovement.play();
    }
}