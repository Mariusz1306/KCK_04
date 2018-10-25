package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        BarChart<String, Integer> barChart = (BarChart) loader.load();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("Styczeń", 25601.34));
        series1.getData().add(new XYChart.Data("Luty", 20148.82));
        series1.getData().add(new XYChart.Data("Marzec", 10000));
        series1.getData().add(new XYChart.Data("Kwiecień", 35407.15));

        barChart.getData().addAll(series1);


    }




    public static void main(String[] args) {
        launch(args);
    }
}
