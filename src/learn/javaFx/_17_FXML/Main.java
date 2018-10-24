package learn.javaFx._17_FXML;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    public void start(Stage window) throws Exception {
        window.setTitle("FXML");

        Canvas canv = new Canvas();
        GraphicsContext ctx = canv.getGraphicsContext2D();
        canv.setHeight(500);
        canv.setWidth(500);

        VBox root = FXMLLoader.load(getClass().getResource("main.fxml"));
        root.getChildren().addAll(canv);

        Scene scene = new Scene(root, 500, 300);
        window.setScene(scene);

        window.show();

        draw(ctx);

        new AnimationTimer() {
            long prev = System.nanoTime();

            public void handle(long currentNanoTime) {
                clean(ctx);
                draw(ctx);
                prev = currentNanoTime;
            }
        }.start();
    }

    private double x = 10;
    private double y = 10;

    public void draw(GraphicsContext ctx) {
        ctx.fillRect(x++, y++, 10, 10);
    }

    public void clean(GraphicsContext ctx) {
        ctx.clearRect(0,0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
