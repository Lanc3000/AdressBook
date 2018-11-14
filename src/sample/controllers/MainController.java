package sample.controllers;

import javafx.collections.ListChangeListener;
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
import javafx.stage.Window;
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

    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Controller editDialogController;

    private Stage editDialogStage;

    @FXML
    private void initialize(){


        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        adressBookImpl.fillTestData();
        tableAdressBook.setItems(adressBookImpl.getPersonList());
       adressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
           @Override
           public void onChanged(Change<? extends Person> c) {

               updateCountLabel();
           }
       });
       try{
           fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
           fxmlEdit = fxmlLoader.load();
           editDialogController = fxmlLoader.getController();
       }
       catch(IOException e){
           e.printStackTrace();
       }
    }

    private void updateCountLabel(){
        labelCount.setText("Количество записей: " + adressBookImpl.getPersonList().size());
    }


    public void actionButtonPressed(ActionEvent actionEvent){
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)){
            return;
        }
        Button clickedButton = (Button) source;
        Person selectedPerson = (Person) tableAdressBook.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        switch (clickedButton.getId()){
            case "addButton":
                System.out.println("add " + selectedPerson);
                break;
            case "addButtonChange":
                showDialog(parentWindow);
                break;
            case "addButtonDelete":
                System.out.println("delete " + selectedPerson);
                break;
        }
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

    private void showDialog(Window parentWindow){
        if(editDialogStage == null){
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
            editDialogStage.show();
        }
    }
}
