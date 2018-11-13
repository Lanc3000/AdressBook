package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.interfaces.impls.CollectionAdressBook;
import sample.objects.Person;

import java.io.IOException;

public class MainController {

    private CollectionAdressBook adressBookImpl = new CollectionAdressBook();

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label labelCount;
    @FXML
    private TableView tableAdressBook;
    @FXML
    private TableColumn<Person, String> columnFIO;
    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private void initialize(){
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        adressBookImpl.fillTestData();
        tableAdressBook.setItems(adressBookImpl.getPersonList());
        updateCountLabel();
    }

    private void updateCountLabel(){
        labelCount.setText("Количество записей: " + adressBookImpl.getPersonList().size());
    }


    public void showDialog(ActionEvent actionEvent){
        try{
            btnAdd.setText("Clicked!");


            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
            }
    }
    public void showChange(ActionEvent actionEvent){
        btnChange.setText("Change");
    }
    public void showDelete(ActionEvent actionEvent){
        btnDelete.setText("Delete");
    }
}
