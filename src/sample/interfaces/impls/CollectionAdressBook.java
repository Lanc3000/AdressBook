package sample.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.interfaces.AdressBook;
import sample.objects.Person;

import java.util.ArrayList;

public class CollectionAdressBook implements AdressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {
        //если бы реализовывали через БД, то заполнили
        //а для коллекции не нужно
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList(){
        return personList;
    }

    public void print(){
        int number = 0;
        System.out.println();
        for(Person person: personList){
            number++;
            System.out.println(number + ") fio =" + person.getFio()  + " phone: " + person.getPhone());
        }
    }

    public void fillTestData(){
        personList.add(new Person("John Silver", "3252352"));
        personList.add(new Person("John Gold", "32457352"));
        personList.add(new Person("John Diamond", "32576852"));
        personList.add(new Person("John Emerald", "39889352"));
        personList.add(new Person("John Ferrum", "32523568"));
        personList.add(new Person("John Wood", "3255852"));
        personList.add(new Person("John Glass", "3252672"));
        personList.add(new Person("John Banana", "3285675652"));
    }
}
