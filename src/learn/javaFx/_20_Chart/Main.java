package learn.javaFx._20_Chart;

import com.labs.lab2.F2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Main extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");

        var f2 = new F2();
        var allX = f2.getAllX(0, 10, 0.1);
        var allY = f2.getAllY(allX, 2.3);

        for (int i = 0; i < allX.length; i++) {
            series1.getData().add(new XYChart.Data(allX[i], allY[i]));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}