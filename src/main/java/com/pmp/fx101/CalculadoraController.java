/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pmp.fx101;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
/**
 * FXML Controller class
 *
 * @author obetancourth
 */
public class CalculadoraController implements Initializable {


    @FXML
    private Label txtDisplay;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnPrint;
    @FXML
    private Button btnPrct;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btnMult;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btnSubtract;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btn0;
    @FXML
    private Button btnPoint;
    @FXML
    private Button btnRslt;
    @FXML
    private Button btnNeg;
    @FXML
    private Label lblCurrentOperation;
    @FXML
    private Label lblOperand;
    @FXML
    private ListView<String> lstOperaciones;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtDisplay.setText(valueToProcess);
        lstOperaciones.setItems(FXCollections.observableArrayList(operationsToDisplay));
    }    
    
    private String valueToProcess = "";
    private boolean dotPending = false;
    private float operand = 0;
    private String operation = "";
    private boolean clearNext = false;
    private ArrayList<String> operationsToDisplay = new ArrayList();
    @FXML
    private void btnOnClicked(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();
        System.out.println("Se hizo click en boton (" + btnClicked.getId() + ") " + btnClicked.getText());
        if (clearNext) {
            clearNext = false;
            valueToProcess = "";
        }
        switch (btnClicked.getId()) {
            case "btn0":
            case "btn1":
            case "btn2":
            case "btn3" , "btn4", "btn5", "btn6", "btn7", "btn8", "btn9":
                if (dotPending) {
                    valueToProcess = valueToProcess + ".";
                    dotPending = false;
                }
                valueToProcess = valueToProcess + btnClicked.getText();
                break;
            case "btnPoint":
                if (!valueToProcess.contains(".")) {
                    if(valueToProcess.length() == 0){
                        valueToProcess = "0";
                    }
                    dotPending = true;
                }
                break;
            case "btnClear":
                operationsToDisplay.add("C-----------------");
                valueToProcess = "";
                dotPending = false;
                operand = 0;
                operation = "";
                break;
            case "btnAdd" , "btnSubtract", "btnMult", "btnDiv", "btnRslt", "btnPrct":
                if (operation == "") {
                    operand = (valueToProcess.isEmpty()) ? 0 : Float.parseFloat(valueToProcess);
                    operation = btnClicked.getText();
                    operationsToDisplay.add(String.valueOf(operand)+ "  ");
                } else {
                    float tmpOperand = (valueToProcess.isEmpty()) ? 0 : Float.parseFloat(valueToProcess);
                    operationsToDisplay.add(String.valueOf(tmpOperand) + " " + operation);
                    switch (operation) {
                        case "+":
                            operand = operand + tmpOperand;
                            break;
                        case "-":
                            operand = operand - tmpOperand;
                            break;
                        case "X":
                            operand = operand * tmpOperand;
                            break;
                        case "/":
                            if (tmpOperand > 0 ) {
                                operand = operand / tmpOperand;
                            }
                            break;
                        case "%":
                            operand = operand * tmpOperand / 100;
                            break;
                    }
                    operation = btnClicked.getText();
                    if (btnClicked.getId() == "btnRslt") {
                        operation = "";
                    }
                    valueToProcess = String.valueOf(operand);
                    operationsToDisplay.add(String.valueOf(operand) + " =");
                }
                lblCurrentOperation.setText(operation);
                lblOperand.setText(String.valueOf(operand));
                clearNext = true;
                break;
        }
        txtDisplay.setText(valueToProcess);
        if (operationsToDisplay.size()>0){
            lstOperaciones.setItems(FXCollections.observableArrayList(operationsToDisplay));
            lstOperaciones.scrollTo(operationsToDisplay.size()-1);
        }
    }
}
