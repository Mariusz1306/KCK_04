package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.fxmisc.easybind.EasyBind;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

public class Main extends Application {

    ObservableList<Data> data = FXCollections.observableArrayList(
            new Data("Styczen", 5000)
    );

    TableView<Data> tableView = new TableView<>();


    @Override
    public void start(Stage primaryStage) throws Exception{

        SplitPane root = new SplitPane();
        root.setDividerPositions(0.3093645484949833);

        AnchorPane leftAnchorPane = new AnchorPane();
        leftAnchorPane.setMaxWidth(182);
        leftAnchorPane.setMinWidth(182);
        AnchorPane rightAnchorPane = new AnchorPane();

        Label monthLabel = new Label("Miesiąc: ");
        monthLabel.setLayoutX(45);
        monthLabel.setLayoutY(265);
        Label salaryLabel = new Label("Zarobki: ");
        salaryLabel.setLayoutX(45);
        salaryLabel.setLayoutY(300);

        TextField monthTextField = new TextField();
        monthTextField.setLayoutX(90);
        monthTextField.setLayoutY(260);
        monthTextField.setPrefWidth(85);
        TextField salaryTextField = new TextField();
        salaryTextField.setLayoutX(90);
        salaryTextField.setLayoutY(295);
        salaryTextField.setPrefWidth(85);

        tableView.setRowFactory(tv -> {
            TableRow<Data> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Data rowData = row.getItem();
                monthTextField.setText(rowData.getMonth());
                salaryTextField.setText(String.valueOf(rowData.getSalary()));
            });
            return row ;
        });

        tableView.setLayoutX(3);
        tableView.setLayoutY(31);
        tableView.setPrefSize(175.0, 200.0);

        tableView.setItems(data);

        data.addAll(new Data("Luty", 2000),
                new Data("Marzec", 1000),
                new Data("Kwiecien", 2000),
                new Data("Maj", 1000),
                new Data("Czerwiec", 3000),
                new Data("Lipiec", 2000));

        TableColumn monthColumn = new TableColumn("Miesiac");
        monthColumn.setPrefWidth(75);
        monthColumn.setCellValueFactory(
                new PropertyValueFactory<>("month"));

        TableColumn salaryColumn = new TableColumn("Zarobki");
        salaryColumn.setPrefWidth(75);
        salaryColumn.setCellValueFactory(
                new PropertyValueFactory<>("salary"));
        tableView.getColumns().addAll(monthColumn,salaryColumn);
        tableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

        Button button = new Button("Aktualizuj");
        button.setLayoutX(100);
        button.setLayoutY(330);

        button.setOnAction((ActionEvent e) -> {
            if (data.contains(new Data(monthTextField.getText(),Integer.parseInt(salaryTextField.getText())))){
                int index = data.indexOf(new Data(monthTextField.getText(),Integer.parseInt(salaryTextField.getText())));
                data.set(index, new Data(monthTextField.getText(),Integer.parseInt(salaryTextField.getText())));
            } else
                data.add(new Data(monthTextField.getText(),Integer.parseInt(salaryTextField.getText())));
        });

        leftAnchorPane.getChildren().addAll(tableView, monthLabel, salaryLabel, monthTextField, salaryTextField, button);



        BorderPane borderPane = new BorderPane();
        Label title = new Label("Zarobki w tys. zł.");
        title.setFont(new Font(24));
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis,yAxis);
        barChart.setPrefSize(390, 280);


        ObservableList<XYChart.Data<String, Number>> dataSet1 = EasyBind.map(data,
                item -> new XYChart.Data<>(item.getMonth(), item.getSalary()));
        XYChart.Series series = new XYChart.Series(dataSet1);
        barChart.getData().add(series);
        barChart.setLegendVisible(false);
        barChart.setAnimated(false);

        borderPane.setTop(title);
        borderPane.setCenter(barChart);
        borderPane.setAlignment(barChart, Pos.CENTER);
        borderPane.setAlignment(title, Pos.CENTER);
        rightAnchorPane.getChildren().addAll(borderPane);



        root.getItems().addAll(leftAnchorPane,rightAnchorPane);



        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
