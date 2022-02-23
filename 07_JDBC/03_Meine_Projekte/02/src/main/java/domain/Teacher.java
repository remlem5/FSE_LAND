package domain;

import exceptions.InvalidValueException;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends BaseEntity{

    private String firstName;
    private String lastName;
    private String courses;

    public Teacher(Long id, String firstName, String lastName, String course) {
        super(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.courses = course;
    }

    public Teacher(String firstName, String lastName, String course){
        super(null);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.courses = course;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) throws InvalidValueException{
        if (name != null && name.length() > 1) {
            this.firstName = name;
        } else {
            throw new InvalidValueException("Vorname muss mindestens 2 Zeichen lang sein");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) throws InvalidValueException{
        if (name != null && name.length() > 1) {
            this.lastName = name;
        } else {
            throw new InvalidValueException("Nachname muss mindestens 2 Zeichen lang sein");
        }
    }

    public String getCourses() {
        return this.courses;
    }

    public void setCourses(String course) {
        this.courses = course;
    }

    @Override
    public String toString(){
        return "Lehrer {"+
                "id= " + this.getId() + '\'' +
                " Vorname: " + firstName +'\'' +
                " Nachname: " + lastName +'\'' +
                " Kurse: " + String.valueOf(courses) + "}";
    }
}
