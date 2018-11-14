package sample.objects;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty(""); //проиницилизируем пустым значением чтобы в TableView не было ошибок

    public Person(){}

    public Person(String name, String phone){
        this.phone = new SimpleStringProperty(phone);
        this.fio = new SimpleStringProperty(name);
    }


    public String getFio() {
        return fio.get();
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone .set(phone);
    }

    public SimpleStringProperty fioProperty(){
        return fio;
    }
    public SimpleStringProperty phoneProperty(){
        return phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
