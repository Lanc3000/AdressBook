package sample.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.interfaces.impls.CollectionAdressBook;
import sample.objects.Person;

import java.io.IOException;

public class MainController {

    private CollectionAdressBook adressBookImpl = new CollectionAdressBook();
    private Stage mainStage;

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
        initListener();
        fillData();
        initLoader();
    }

    private void fillData() {
        adressBookImpl.fillTestData();
        tableAdressBook.setItems(adressBookImpl.getPersonList());
    }

    private void initListener(){
        adressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) { updateCountLabel(); }
        });
        tableAdressBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    editDialogController.setPerson((Person) tableAdressBook.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void initLoader(){
        try{
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setMainStage(Stage mainStage){
        this.mainStage = mainStage;
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
               editDialogController.setPerson(new Person());
               showDialog();
               adressBookImpl.add(editDialogController.getPerson());
                break;
            case "addButtonChange":
                editDialogController.setPerson((Person) tableAdressBook.getSelectionModel().getSelectedItem());
                showDialog();
                break;
            case "addButtonDelete":
                adressBookImpl.delete((Person) tableAdressBook.getSelectionModel().getSelectedItem());
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

    private void showDialog(){
        if(editDialogStage == null){
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
            editDialogStage.showAndWait();//для ожидания закрытия окна
        }
    }
}
