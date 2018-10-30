package sample.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.interfaces.impls.CollectionAdressBook;
import sample.objects.Person;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        testData();


    }

    private void testData(){
        CollectionAdressBook  adressBook = new CollectionAdressBook();
        adressBook.fillTestData();
    }


    public static void main(String[] args) {
        launch(args);
    }

}



