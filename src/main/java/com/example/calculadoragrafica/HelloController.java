package com.example.calculadoragrafica;

import com.example.calculadoragrafica.MathEngine.calc;
import com.example.calculadoragrafica.MathEngine.tree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.css.Stylesheet;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Locale;
import java.util.Random;

public class HelloController {
    @FXML
    private TextField LabelExpression;
    @FXML
    private void button0(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"0");
    }
    @FXML
    private void button1(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"1");
    }
    @FXML
    private void button2(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"2");
    }
    @FXML
    private void button3(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"3");
    }
    @FXML
    private void button4(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"4");
    }
    @FXML
    private void button5(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"5");
    }
    @FXML
    private void button6(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"6");
    }
    @FXML
    private void button7(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"7");
    }
    @FXML
    private void button8(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"8");
    }
    @FXML
    private void button9(ActionEvent event){
        LabelExpression.setText(LabelExpression.getText()+"9");
    }
    @FXML
    private void buttonC(ActionEvent event){
        LabelExpression.setText("");
    }
    @FXML
    private void buttonsum(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"+");}
    @FXML
    private void buttonsub(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"-");}
    @FXML
    private void buttonmult(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"*");}
    @FXML
    private void buttondiv(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"/");}
    @FXML
    private void buttonraised(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"^");}
    @FXML
    private void buttontan(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"tan(");}
    @FXML
    private void buttoncos(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"cos(");}
    @FXML
    private void buttonsin(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"sin(");}
    @FXML
    private void buttonln(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"ln(");}
    @FXML
    private void buttonponto(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+".");}
    @FXML
    private void buttonpe(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"(");}
    @FXML
    private void buttonpd(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+")");}
    @FXML
    private void buttonfac(ActionEvent event){LabelExpression.setText(LabelExpression.getText()+"!");}
    @FXML
    private Canvas canvas;
    @FXML
    private void drawpoints(Canvas canvas, double x, double y, double size, double borderSize, double prevX,double prevY) {
        GraphicsContext corrds = canvas.getGraphicsContext2D();

        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;
        int GRAPH_SIZE = 10;

        double pointX = centerX + (x * (canvas.getWidth() / (2 * GRAPH_SIZE)));
        double pointY = centerY - (y * (canvas.getHeight() / (2 * GRAPH_SIZE)));

        //corrds.setFill(Color.rgb(234,128,76));
        //corrds.fillOval(pointX , pointY, borderSize, borderSize);

        double prevX1 = centerX + (prevX * (canvas.getWidth() / (2 * GRAPH_SIZE)));
        double prevY1 = centerY - (prevY * (canvas.getHeight() / (2 * GRAPH_SIZE)));
        if (prevX != Double.MAX_VALUE && prevY != Double.MAX_VALUE) {
            corrds.setStroke(Color.rgb(234, 128, 76));
            corrds.setLineWidth(borderSize);
            corrds.strokeLine(prevX1, prevY1, pointX, pointY);
        }
    }
    @FXML
    private void buttonigual(ActionEvent event){
            if(LabelExpression.getText().contains("x")){
                GraphicsContext corrds = canvas.getGraphicsContext2D();
                corrds.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                corrds.setStroke(Color.rgb(250,207,125));
                corrds.strokeText("f(x)="+LabelExpression.getText(), 10, 20);

                double CANVAS_WIDTH = canvas.getWidth();
                double CANVAS_HEIGHT = canvas.getHeight();
                int GRAPH_SIZE = 10;
                // Obter o contexto gráfico

                // Calcular proporções
                double cellWidth = (double) CANVAS_WIDTH / (2 * GRAPH_SIZE);
                double cellHeight = (double) CANVAS_HEIGHT / (2 * GRAPH_SIZE);
                double centerX = CANVAS_WIDTH / 2;
                double centerY = CANVAS_HEIGHT / 2;
                // Desenha linha horizontal
                corrds.setStroke(Color.rgb(250,207,125));
                double y = centerY + 0 * cellHeight;
                corrds.strokeLine(0, y, CANVAS_WIDTH, y);
                // Desenha linha verticai
                double x = centerX + 0 * cellWidth;
                corrds.strokeLine(x, 0, x, CANVAS_HEIGHT);

                tree root1 = new tree(LabelExpression.getText().replace("x", String.valueOf(-10)));
                calc c1 = new calc(root1);
                double prevx=-10,prevy=Double.valueOf(c1.Result());
                for(float i= -10 ; i<=10 ; i= (float) (i + 0.25)) {
                    tree root = new tree(LabelExpression.getText().replace("x", String.valueOf(i)));
                    calc c = new calc(root);
                    String StepByStep = c.Result();
                    drawpoints(canvas,i,Double.valueOf(StepByStep),2,2,prevx,prevy);
                    prevx=i;
                    prevy=Double.valueOf(StepByStep);

                }
                LabelExpression.setText("");
            }else {
                tree root = new tree(LabelExpression.getText());
                calc c = new calc(root);
                String StepByStep = "";
                for (String str : c.StepByStep()) {
                    StepByStep += str + "\n";
                }
                AddResolucion(LabelExpression.getText(), StepByStep);
                LabelExpression.setText("");
            }
    }


    @FXML
    private ScrollPane ShowExpressionPanel; //ScrollPane onde irá aparecer a resolução

    private Random random = new Random();
    private Color[] ColorArray = {Color.CYAN, Color.LIGHTGREEN, Color.SALMON, Color.YELLOW};

    //Adiciona conteudo ao ScrollPane:
    void AddResolucion(String str, String Step){
        Label e = new Label();
        e.setText(str);
        e.setId("Expression");
        e.setTextFill(ColorArray[random.nextInt(ColorArray.length)]);
        e.setFont(new Font(18));
        VBox v = new VBox();
        if (ShowExpressionPanel.getContent() != null){
            v.getChildren().add(ShowExpressionPanel.getContent());
        }
        Label StepByStep = new Label();
        StepByStep.setText(Step);
        StepByStep.setId("StepByStep");
        StepByStep.setMinWidth(ShowExpressionPanel.getWidth() - 25);
        e.setMinWidth(ShowExpressionPanel.getWidth() - 25);
        StepByStep.setFont(new Font(16));
        StepByStep.setTextFill(Color.WHITE);
        v.getChildren().add(e);
        v.getChildren().add(StepByStep);
        v.getChildren().add(new Label());
        ShowExpressionPanel.setPannable(true);
        ShowExpressionPanel.setContent(v);
    }

    @FXML
    void SumitExpression(KeyEvent key){
        if (key.getCode().equals(KeyCode.ENTER) && !LabelExpression.getText().replace(" ", "").equals("")){
            tree root = new tree(LabelExpression.getText());
            calc c = new calc(root);
            String StepByStep = "";
            for(String str : c.StepByStep()){
                StepByStep += str + "\n";
            }
            AddResolucion(LabelExpression.getText(), StepByStep);
            LabelExpression.setText("");
        }
    }

}