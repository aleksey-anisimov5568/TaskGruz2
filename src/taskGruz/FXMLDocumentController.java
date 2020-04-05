package taskGruz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class FXMLDocumentController implements Initializable {

    Graph graph = new Graph();

    @FXML
    private TextField wayField;

    @FXML
    private Pane draw;

    @FXML
    private TextField bCity;

    @FXML
    private TextField aCity;

    @FXML
    private TextField maxSpeed;

    @FXML
    private TextField maxTime;

    @FXML
    private TextArea pairsArea;

    @FXML
    private TextField maxSpeedField;

    @FXML
    private TextField costField;

    @FXML
    private TextField lengthField;

    @FXML
    private Label infoLabel;

    @FXML
    private Stage stage;

    @FXML
    private Label spendTimeLabel;

    @FXML
    private Label spendCostLabel;

    @FXML
    private Label distanceLabel;

    int count = 0;

    private String getElem(String str) {
        String data = "";
        ArrayList<String> list = new ArrayList<>();
        String[] arr = str.split("\n");
        for (int i = 0; i < arr.length; i++) {
            String a = arr[i].split(" ")[0];
            String b = arr[i].split(" ")[1];
            if (list.indexOf(a) == -1) {
                list.add(a);
                data += a + "\n";
            }
            if (list.indexOf(b) == -1) {
                list.add(b);
                data += b + "\n";
            }
        }
        return data;
    }

    @FXML
    void remove(ActionEvent event) {
        graph = new Graph();
        pairsArea.clear();
        tops = new ArrayList<>();
        draw.getChildren().clear();
        drawData = new ArrayList<>();
        count = 0;
    }

    @FXML
    void found(ActionEvent event) {
        ArrayList<Track> list = graph.found(pairsArea.getText(), aCity.getText(), bCity.getText(), maxSpeed.getText(), maxTime.getText());
        wayField.clear();
        spendTimeLabel.setText("Затраченное время: ");
        spendCostLabel.setText("Стоимость: ");
        distanceLabel.setText("Расстояние: ");
        for(int i = 0; i < list.size(); i++){
            wayField.setText(wayField.getText() + list.get(i).getList() + " ");
            spendTimeLabel.setText(spendTimeLabel.getText() + list.get(i).getTime() + " ");
            spendCostLabel.setText(spendCostLabel.getText() + list.get(i).getCost() + " ");
            distanceLabel.setText(distanceLabel.getText() + list.get(i).getDistance() + " ");
        }
    }

    ArrayList<String> tops = new ArrayList<>();

    @FXML
    void form(ActionEvent event) {
        graph = new Graph();
        String[] arr = pairsArea.getText().split("\n");
        for (int i = 0; i < arr.length; i++) {
            String a = arr[i].split(" ")[0];
            String b = arr[i].split(" ")[1].split(":")[3];
            if (!tops.contains(a)) {
                tops.add(a);
            }
            if (!tops.contains(b)) {
                tops.add(b);
            }
        }
        for (int i = 0; i < tops.size(); i++) {
            graph.add(Integer.parseInt(tops.get(i)));
        }
        for (int i = 0; i < arr.length; i++) {
            graph.add(Integer.parseInt(arr[i].split(" ")[0]), arr[i].split(" ")[1]);
        }
    }

    private class DrawData {

        public int name;
        public double x, y;
        public boolean isDraw;

        public DrawData(int name, double x, double y, boolean id) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.isDraw = id;
        }
    }

    ArrayList<DrawData> drawData = new ArrayList<>();
    DrawData drawContainer = null;

    @FXML
    void change(ActionEvent event) {
        String[] arr1 = pairsArea.getText().split("\n");
        for (int j = 0; j < arr1.length; j++) {
            if (p1 == Integer.parseInt(arr1[j].split(" ")[0]) && p2 == Integer.parseInt(arr1[j].split(" ")[1].split(":")[3])) {
                arr1[j] = p1 + " " + lengthField.getText() + ":" + maxSpeedField.getText() + ":" + costField.getText() + ":" + p2;
                pairsArea.clear();
                for (int i = 0; i < arr1.length; i++) {
                    pairsArea.setText(pairsArea.getText() + ((i == 0) ? ("") : ("\n")) + arr1[i]);
                }
                return;
            }
        }
    }

    int p1, p2 = 0;

    @FXML
    void drawStep(MouseEvent event) {
        String[] arr = pairsArea.getText().split("\n");
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("") && !tops.contains(arr[i].split(" ")[0])) {
                tops.add(arr[i].split(" ")[0]);
            }
            if (!arr[i].equals("") && !tops.contains(arr[i].split(" ")[1].split(":")[3])) {
                tops.add(arr[i].split(" ")[1].split(":")[3]);
            }
        }
        for (int i = 0; i < drawData.size(); i++) {
            if (Math.abs(event.getX() - drawData.get(i).x) < 15 && Math.abs(event.getY() - drawData.get(i).y) < 15) {
                if (drawContainer == null) {
                    drawContainer = drawData.get(i);
                    return;
                } else {
                    if (drawData.get(i).name != drawContainer.name) {
                        String[] arr1 = pairsArea.getText().split("\n");
                        for (int j = 0; j < arr1.length; j++) {
                            if (!arr[j].equals("") && drawContainer.name == Integer.parseInt(arr1[j].split(" ")[0]) && drawData.get(i).name == Integer.parseInt(arr1[j].split(" ")[1].split(":")[3])) {
                                infoLabel.setText("Выбрана пара: " + drawContainer.name + " " + drawData.get(i).name);
                                lengthField.setText(arr1[j].split(" ")[1].split(":")[0]);
                                maxSpeedField.setText(arr1[j].split(" ")[1].split(":")[1]);
                                costField.setText(arr1[j].split(" ")[1].split(":")[2]);
                                p1 = drawContainer.name;
                                p2 = drawData.get(i).name;
                                return;
                            }
                        }
                        drawLine(drawContainer.name, drawData.get(i).name, drawContainer.x, drawContainer.y, drawData.get(i).x, drawData.get(i).y);
                        pairsArea.setText(pairsArea.getText() + "\n" + drawContainer.name + " 100:300:200:" + drawData.get(i).name);
                        drawContainer = null;
                    } else {
                        drawContainer = null;
                    }
                    return;
                }
            }
        }
        drawContainer = null;
        if (count < tops.size() && !tops.get(tops.size() - 1).equals("")) {
            int newElem = Integer.parseInt(tops.get(count));
            drawCircle(event.getX(), event.getY(), newElem);
            drawData.add(new DrawData(newElem, event.getX(), event.getY(), false));
            String[] links = pairsArea.getText().split("\n");
            for (int i = 0; i < links.length; i++) {
                if (newElem == Integer.parseInt(links[i].split(" ")[0])) {
                    for (int j = 0; j < drawData.size(); j++) {
                        if (Integer.parseInt(links[i].split(" ")[1].split(":")[3]) == drawData.get(j).name) {
                            drawLine(newElem, Integer.parseInt(links[i].split(" ")[1].split(":")[3]), event.getX(), event.getY(), drawData.get(j).x, drawData.get(j).y);
                        }
                    }
                }
                if (newElem == Integer.parseInt(links[i].split(" ")[1].split(":")[3])) {
                    for (int j = 0; j < drawData.size(); j++) {
                        if (Integer.parseInt(links[i].split(" ")[0]) == drawData.get(j).name) {
                            drawLine(Integer.parseInt(links[i].split(" ")[0]), newElem, drawData.get(j).x, drawData.get(j).y, event.getX(), event.getY());
                        }
                    }
                }
            }
        } else {
            if (tops.size() == 0 || tops.get(tops.size() - 1).equals("")) {
                tops.add(1 + "");
                drawCircle(event.getX(), event.getY(), 1);
                drawData.add(new DrawData(1, event.getX(), event.getY(), false));
                count++;
                return;
            }
            int newElem = Integer.parseInt(tops.get(tops.size() - 1));
            for (int i = 0; i < tops.size(); i++) {
                if (Integer.parseInt(tops.get(i)) > newElem) {
                    newElem = Integer.parseInt(tops.get(i));
                }
            }
            newElem++;
            while (true) {
                for (int i = 0; i < tops.size(); i++) {
                    if (Integer.parseInt(tops.get(i)) == newElem) {
                        newElem++;
                        continue;
                    }
                }
                break;
            }
            tops.add(newElem + "");
            drawCircle(event.getX(), event.getY(), newElem);
            drawData.add(new DrawData(newElem, event.getX(), event.getY(), false));
        }
        count++;
    }

    private void drawCircle(double x, double y, int name) {
        Circle c = new Circle();
        c.setRadius(5);
        c.setCenterX(x);
        c.setCenterY(y);
        c.setFill(Color.BLACK);
        Text text = new Text("" + name);
        text.setFont(new Font(20));
        text.setX(x - 15);
        text.setY(y - 10);
        draw.getChildren().addAll(c, text);
    }

    private void drawLine(int a, int b, double x1, double y1, double x2, double y2) {
        Line line = new Line();
        line.setStartX(x1);
        line.setEndX(x2);
        line.setStartY(y1);
        line.setEndY(y2);
        Line line1 = new Line();
        Line line2 = new Line();
        line1.setStartX(line.getEndX());
        line1.setStartY(line.getEndY());
        line2.setStartX(line.getEndX());
        line2.setStartY(line.getEndY());
        line1.setEndX(y2);
        line1.setEndX(line.getEndX() - 25 * Math.cos((pointDirection(x1, y1, x2, y2) - 10) * (Math.PI / 180)));
        line1.setEndY(line.getEndY() - 25 * Math.sin((pointDirection(x1, y1, x2, y2) - 10) * (Math.PI / 180)));
        line2.setEndX(line.getEndX() - 25 * Math.cos((pointDirection(x1, y1, x2, y2) + 10) * (Math.PI / 180)));
        line2.setEndY(line.getEndY() - 25 * Math.sin((pointDirection(x1, y1, x2, y2) + 10) * (Math.PI / 180)));
        draw.getChildren().addAll(line, line1, line2);
    }

    private double pointDirection(double x1, double y1, double x2, double y2) {
        double a = Math.acos((x2 - x1) / Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))) / (Math.PI / 180);
        if (y2 - y1 < 0) {
            a = 180 + (180 - a);
        }
        return a;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
