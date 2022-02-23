package ui;

import dataaccess.MySQLTeacherRepository;
import dataaccess.MyTeacherRepository;
import domain.Teacher;
import exceptions.DatabaseException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TeacherCli {

    Scanner scan;
    MyTeacherRepository teacherRepo;


    public TeacherCli(MySQLTeacherRepository mySQLTeacherRepository) {
        this.scan = new Scanner(System.in);
        this.teacherRepo = mySQLTeacherRepository;
    }

    public void start() {
        String input = "-";
        while (!input.equals("z")) {
            showTeacherMenue();
            input = scan.nextLine();
            switch (input) {
                case "1" -> addTeacher();
                case "2" -> showAllTeachers();
                case "3" -> showTeacherDetails();
                case "4" -> updateTeacher();
                case "5" -> deleteTeacherById();
                case "6" -> findTeacherByLastName();
                case "7" -> findTeacherByFirstName();
                case "8" -> findTeacherByCourse();
                default -> inputError();
            }
        }
    }

    private void showTeacherMenue() {
        System.out.println("----- Lehrer -----");
        System.out.println("(1) Lehrer anlegen \t (2) Lehrer anzeigen \t (3) Lehrer-Details anzeigen \t");
        System.out.println("(4) Lehrer bearbeiten \t (5) Lehrer löschen");
        System.out.println("(6) Suche nach Nachname \t (7) Suche nach Vorname \t (8) Suche nach Fach");
        System.out.println("(z) ZURÜCK");
    }

    private void addTeacher() {
        String firstName, lastName, courses;

        try {
            System.out.println("Bitte Lehrer-Daten angeben:");
            System.out.println("Vorname: ");
            firstName = scan.nextLine();
            if (firstName.equals("")) throw new IllegalArgumentException("Vorname darf nicht leer sein");
            System.out.println("Nachname: ");
            lastName = scan.nextLine();
            if (lastName.equals("")) throw new IllegalArgumentException("Nachname darf nicht leer sein");
            System.out.println("Fach (POS/FSE/DBI/SYP): ");
            courses = scan.nextLine();

            Optional<Teacher> optionalTeacher = teacherRepo.insert(new Teacher(firstName, lastName, courses));

            if (optionalTeacher.isPresent()) {
                System.out.println("Lehrer wurde angelegt: " + optionalTeacher.get());
            } else {
                System.out.println("Lehrer konnte nicht angelegt werden");
            }

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        }
    }

    private void showAllTeachers() {
        List<Teacher> list = null;

        try {
            list = teacherRepo.getAll();
            if (list.size() > 0) {
                for (Teacher t : list) {
                    System.out.println(t);
                }
            } else {
                System.out.println("Keine Lehrer in Liste!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler bei showAllTeachers(): " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei showAllTeachers(): " + e.getMessage());
        }
    }

    private void showTeacherDetails() {
        System.out.println("Welcher Lehrer soll angezeigt werden?");
        Long teacherId = Long.parseLong(scan.nextLine());
        try {
            Optional<Teacher> teacherOptional = teacherRepo.getById(teacherId);
            if (teacherOptional.isPresent()) {
                System.out.println(teacherOptional.get());
            } else {
                System.out.println("Lehrer mit ID " + teacherId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler bei Detail-Anzeige: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei Detail-Anzeige: " + e.getMessage());
        }
    }

    private void updateTeacher() {
        System.out.println("Welcher Lehrer soll geändert werden?");
        Long teacherId = Long.parseLong(scan.nextLine());

        try {
            Optional<Teacher> teacherOptional = teacherRepo.getById(teacherId);
            if (teacherOptional.isEmpty()) {
                System.out.println("Lehrer nicht in der DB");
            } else {
                Teacher teacher = teacherOptional.get();
                System.out.println("Änderungen für folgenden Studenten:");
                System.out.println(teacher);

                String firstName, lastName, course;

                System.out.println("Bitte neue Daten eingeben (Enter, wenn Feld gleich bleibt)");
                System.out.println("Vorname: ");
                firstName = scan.nextLine();
                System.out.println("Nachname: ");
                lastName = scan.nextLine();
                System.out.println("Fach: ");
                course = scan.nextLine();

                Optional<Teacher> optionalTeacherUpdated = teacherRepo.update(new Teacher(
                        teacher.getId(),
                        firstName.equals("") ? teacher.getFirstName() : firstName,
                        lastName.equals("") ? teacher.getLastName() : lastName,
                        course.equals("") ? teacher.getCourses() : course
                ));

                optionalTeacherUpdated.ifPresentOrElse(
                        (s) -> System.out.println("Lehrer aktualisiert: " + s),
                        () -> System.out.println("Lehrer konnte nicht aktualisiert werden!")
                );

            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler beim Bearbeiten: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler beim Bearbeiten: " + e.getMessage());
        }
    }

    private void deleteTeacherById() {
        System.out.println("Welchher Lehrer soll gelöscht werden?");
        Long teacherIdToDelete = Long.parseLong(scan.nextLine());

        try {
            teacherRepo.deleteById(teacherIdToDelete);
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler beim Löschen: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler beim Löschen: " + e.getMessage());
        }
    }

    private void findTeacherByLastName() {
        System.out.println("Geben Sie den Nachnamen ein:");
        String needle = scan.nextLine();
        List<Teacher> teacherList;

        try {
            teacherList = teacherRepo.findByLastName(needle);
            for (Teacher t : teacherList) {
                System.out.println(t);
            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler bei Suche nach Nachnamen: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei Suche nach Nachnamen: " + e.getMessage());
        }
    }

    private void findTeacherByFirstName() {
        System.out.println("Geben Sie den Vornamen ein:");
        String needle = scan.nextLine();
        List<Teacher> teacherList;

        try {
            teacherList = teacherRepo.findByFirstName(needle);
            for (Teacher t : teacherList) {
                System.out.println(t);
            }
        } catch (DatabaseException databaseException) {
            System.out.println("DB-Fehler bei Suche nach Vornamen: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei Suche nach Vornamen: " + e.getMessage());
        }
    }

    private void findTeacherByCourse() {
        System.out.println("Geben Sie das Fach ein:");
        String needle = scan.nextLine();
        List<Teacher> teacherList;

        try {
            teacherList = teacherRepo.findByCourse(needle);
            for (Teacher t : teacherList){
                System.out.println(t);
            }
        }catch (DatabaseException databaseException){
            System.out.println("DB-Fehler bei Suche nach Fach: " + databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei Suche nach Fach: " + e.getMessage());
        }
    }

    private void inputError() {
        System.out.println("Bitte nur gültige Werte eingeben!");
    }

}
