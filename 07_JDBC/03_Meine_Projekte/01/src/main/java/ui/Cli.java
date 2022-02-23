package ui;

import dataaccess.MyStundentRepository;
import domain.Student;
import exceptions.DatabaseException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Cli {

    Scanner scan;
    MyStundentRepository repo;

    public Cli(MyStundentRepository repo) {
        this.scan = new Scanner(System.in);
        this.repo = repo;
    }

    public void start() {
        String input = "-";
        while (!input.equals("x")) {
            showMenue();
            input = scan.nextLine();
            switch (input) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    showAllStudents();
                    break;
                case "3":
                    showStudentDetails();
                    break;
                case "4":
                    updateStudent();
                    break;
                case "5":
                    deleteStudentById();
                    break;
                case "6":
                    findByLastName();
                    break;
                case "7":
                    findByFirstName();
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scan.close();
    }

    private void showMenue() {
        System.out.println("----- Studenten -----");
        System.out.println("(1) Student anlegen \t (2) Studenten anzeigen \t (3) Student-Details anzeigen \t");
        System.out.println("(4) Student bearbeiten \t (5) Student löschen");
        System.out.println("(6) Suche nach Nachname \t (7) Suche nach Vorname");
        System.out.println("(x) ENDE");
    }

    private void addStudent() {
        String firstName, lastName;
        Date dateOfBirth;

        try {
            System.out.println("Bitte Studenten-Daten angeben:");
            System.out.println("Vorname: ");
            firstName = scan.nextLine();
            if (firstName.equals("")) throw new IllegalArgumentException("Vorname darf nicht leer sein");
            System.out.println("Nachname: ");
            lastName = scan.nextLine();
            if (lastName.equals("")) throw new IllegalArgumentException("Nachname darf nicht leer sein");
            System.out.println("Geburtsdatum (YYYY-MM-DD): ");
            dateOfBirth = Date.valueOf(scan.nextLine());

            Optional<Student> optionalStudent = repo.insert(new Student(firstName, lastName, dateOfBirth));

            if (optionalStudent.isPresent()) {
                System.out.println("Student wurde angelegt: " + optionalStudent.get());
            } else {
                System.out.println("Student konnte nicht angelegt werden");
            }

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        }
    }

    private void showAllStudents() {
        List<Student> list = null;

        try {
            list = repo.getAll();
            if (list.size() > 0) {
                for (Student s : list) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Keine Studenten in Liste!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler bei showAllStudents(): " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei showAllStudens(): " + e.getMessage());
        }
    }

    private void showStudentDetails() {
        System.out.println("Welcher Student soll angezeigt werden?");
        Long studentId = Long.parseLong(scan.nextLine());
        try {
            Optional<Student> studentOptional = repo.getById(studentId);
            if (studentOptional.isPresent()) {
                System.out.println(studentOptional.get());
            } else {
                System.out.println("Student mit ID " + studentId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler bei Detail-Anzeige: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei Detail-Anzeige: " + e.getMessage());
        }
    }

    private void updateStudent() {
        System.out.println("Welcher Student soll geändert werden?");
        Long studentId = Long.parseLong(scan.nextLine());

        try {
            Optional<Student> studentOptional = repo.getById(studentId);
            if (studentOptional.isEmpty()) {
                System.out.println("Student nicht in der DB");
            } else {
                Student student = studentOptional.get();
                System.out.println("Änderungen für folgenden Studenten:");
                System.out.println(student);

                String firstName, lastName, dateOfBirth;

                System.out.println("Bitte neue Daten eingeben (Enter, wenn Feld gleich bleibt)");
                System.out.println("Vorname: ");
                firstName = scan.nextLine();
                System.out.println("Nachname: ");
                lastName = scan.nextLine();
                System.out.println("Geburtsdatum: ");
                dateOfBirth = scan.nextLine();

                Optional<Student> optionalStudentUpdated = repo.update(new Student(
                        student.getId(),
                        firstName.equals("") ? student.getFirstName() : firstName,
                        lastName.equals("") ? student.getLastName() : lastName,
                        dateOfBirth.equals("") ? student.getDateOfBirth() : Date.valueOf(dateOfBirth)
                ));

                optionalStudentUpdated.ifPresentOrElse(
                        (s)-> System.out.println("Student aktualisiert: " + s),
                        ()-> System.out.println("Student konnte nicht aktualisiert werden!")
                );

            }
        } catch (DatabaseException databaseException){
            System.out.println("DB-Fehler beim Bearbeiten: " + databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Bearbeiten: " + e.getMessage());
        }
    }

    private void deleteStudentById() {
        System.out.println("Welchher Student soll gelöscht werden?");
        Long studentIdToDelete = Long.parseLong(scan.nextLine());

        try {
            repo.deleteById(studentIdToDelete);
        } catch (DatabaseException databaseException){
            System.out.println("DB-Fehler beim Löschen: " + databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Löschen: " + e.getMessage());
        }
    }

    private void findByLastName() {
        System.out.println("Geben Sie den Nachnamen ein:");
        String needle = scan.nextLine();
        List<Student> studentList;

        try{
            studentList = repo.findByLastName(needle);
            for (Student s : studentList){
                System.out.println(s);
            }
        } catch (DatabaseException databaseException){
            System.out.println("DB-Fehler bei Suche nach Nachnamen: " + databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei Suche nach Nachnamen: " + e.getMessage());
        }
    }

    private void findByFirstName() {
        System.out.println("Geben Sie den Vornamen ein:");
        String needle = scan.nextLine();
        List<Student> studentList;

        try{
            studentList = repo.findByFirstName(needle);
            for (Student s : studentList){
                System.out.println(s);
            }
        } catch (DatabaseException databaseException){
            System.out.println("DB-Fehler bei Suche nach Vornamen: " + databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei Suche nach Vornamen: " + e.getMessage());
        }
    }

    private void inputError() {
        System.out.println("Bitte nur gültige Werte eingeben!");
    }

}
