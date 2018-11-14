package sample.controllers;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.objects.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
public class Controller {
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOK;
    @FXML
    private TextField txtFIO;
    @FXML
    private TextField txtPhone;

    private Person person;

    public void actionClose(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void setPerson(Person person){
        this.person = person;

        txtFIO.setText(person.getFio());
        txtPhone.setText(person.getPhone());
    }

    public void actionSave(ActionEvent actionEvent){
        person.setPhone(txtPhone.getText());
        person.setFio(txtFIO.getText());
        actionClose(actionEvent);
    }
}
