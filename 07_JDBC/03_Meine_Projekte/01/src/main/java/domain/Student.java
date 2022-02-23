package domain;

import exceptions.InvalidValueException;

import java.sql.Date;
import java.time.LocalDate;

public class Student extends BaseEntity{

    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Student(Long id) {
        super(id);
    }

    public Student(Long id, String firstName, String lastName, Date dateOfBirth){
        super(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateOfBirth(dateOfBirth);
    }

    public Student(String firstName, String lastName, Date dateOfBirth){
        super(null);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateOfBirth(dateOfBirth);
    }

    public void setFirstName(String name) throws InvalidValueException {
        if (name != null && name.length() > 1) {
            this.firstName = name;
        } else {
            throw new InvalidValueException("Vorname muss mindestens 2 Zeichen lang sein");
        }
    }

    public void setLastName(String name) throws InvalidValueException {
        if (name != null && name.length() > 1) {
            this.lastName = name;
        } else {
            throw new InvalidValueException("Nachname muss mindestens 2 Zeichen lang sein");
        }
    }

    public void setDateOfBirth(Date dateOfBirth){
        if (dateOfBirth != null){
            this.dateOfBirth = dateOfBirth;
        } else {
            throw new InvalidValueException("Geburtsdatum darf nicht null sein!");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString(){
        return "Student {"+
                "id= " + this.getId() + '\'' +
                " Vorname: " + firstName +'\'' +
                " Nachname: " + lastName +'\'' +
                " Geburtsdatum: " + dateOfBirth + "}";
    }
}
