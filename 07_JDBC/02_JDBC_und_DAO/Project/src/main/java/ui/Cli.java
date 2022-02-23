package ui;

import dataaccess.DatabaseException;
import dataaccess.MyCourseRepository;
import domain.Course;
import domain.CourseType;
import domain.InvalidValueException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Cli {

    Scanner scan;
    MyCourseRepository repo;

    public Cli(MyCourseRepository repo) {
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
                    addCourse();
                    break;
                case "2":
                    showAllCourses();
                    break;
                case "3":
                    showCourseDetails();
                    break;
                case "4":
                    updateCourseDetails();
                    break;
                case "5":
                    deleteCourse();
                    break;
                case "6":
                    courseSearch();
                    break;
                case "7":
                    runningCourses();
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scan.close();
    }

    private void showMenue() {
        System.out.println("--------------- KURSMANAGEMENT --------------");
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t (3) Kursdetails anzeigen \t");
        System.out.println("(4) Kursdetails ändern \t (5) Kurs löschen \t (6) Kurssuche");
        System.out.println("(7) Laufende Kurse");
        System.out.println("(x) ENDE");
    }

    private void runningCourses() {
        System.out.println("Aktuell laufende Kurse:");
        List<Course> list = new ArrayList<>();
        try {
            list = repo.findAllRunningCourses();
            for (Course c : list){
                System.out.println(c);
            }
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler bei laufenden Kursen: "+databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei laufenden Kursen: "+e.getMessage());
        }
    }

    private void courseSearch() {
        System.out.println("Geben Sie einen Suchbegriff an!");
        String searchString = scan.nextLine();
        List<Course> courseList;
        try {
            courseList = repo.findAllCoursesByNameOrDescription(searchString);
            for (Course c : courseList){
                System.out.println(c);
            }
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler bei der Kurssuche: "+databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei der Kurssuche: "+e.getMessage());
        }
    }

    private void deleteCourse() {
        System.out.println("Welchen Kurs möchten Sie löschen? (ID)");
        Long courseIdToDelete = Long.parseLong(scan.nextLine());

        try {
            repo.deleteById(courseIdToDelete);
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler beim Löschen: "+databaseException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Löschen: "+e.getMessage());
        }
    }

    private void updateCourseDetails() {
        System.out.println("Für welche Kurs-ID möchten Sie die Details ändern?");
        Long courseId = Long.parseLong(scan.nextLine());

        try {
            Optional<Course> courseOptional = repo.getById(courseId);
            if (courseOptional.isEmpty()) {
                System.out.println("Kurs nicht in der Datenbank");
            } else {
                Course course = courseOptional.get();
                System.out.println("Änderungen für folgenden Kurs: ");
                System.out.println(course);

                String name, description, hours, begindate, enddate, coursetype;

                System.out.println("Bitte neue Kursdaten angeben (Enter, falls keine Änderung gewünscht ist):");
                System.out.println("Name: ");
                name = scan.nextLine();
                System.out.println("Beschreibung:");
                description = scan.nextLine();
                System.out.println("Stundenanzahl:");
                hours = scan.nextLine();
                System.out.println("Startdatum (YYYY-MM-DD):");
                begindate = scan.nextLine();
                System.out.println("Enddatum (YYYY-MM-DD):");
                enddate = scan.nextLine();
                System.out.println("Kurstyp (ZA/BF/FF/OE):");
                coursetype = scan.nextLine();

                Optional<Course> optionalCourseUpdated = repo.update(new Course(
                        course.getId(),
                        name.equals("") ? course.getName() : name,
                        description.equals("") ? course.getDescription() : description,
                        hours.equals("") ? course.getHours() : Integer.parseInt(hours),
                        begindate.equals("") ? course.getBeginDate() : Date.valueOf(begindate),
                        enddate.equals("") ? course.getEndDate() : Date.valueOf(enddate),
                        coursetype.equals("") ? course.getCourseType() : CourseType.valueOf(coursetype)
                ));
                optionalCourseUpdated.ifPresentOrElse(
                        (c)-> System.out.println("Kurs aktualisiert: "+c),
                        () -> System.out.println("Kurs konnte nicht akutalisiert werden!")
                );
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Bearbeiten: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler beim Bearbeiten: " + e.getMessage());
        }
    }

    private void addCourse() {
        String name, description;
        int hours;
        Date beginDate, endDate;
        CourseType courseType;

        try {
            System.out.println("Bitte alle Kursdaten angeben:");
            System.out.println("Name: ");
            name = scan.nextLine();
            if (name.equals("")) throw new IllegalArgumentException("Name darf nicht leer sein");
            System.out.println("Beschreibung: ");
            description = scan.nextLine();
            if (description.equals("")) throw new IllegalArgumentException("Beschreibung darf nicht leer sein");
            System.out.println("Stundenanzahl:");
            hours = Integer.parseInt(scan.nextLine());
            if (hours < 1) throw new IllegalArgumentException("Stunden dürfen nicht kleiner 1 sein");
            System.out.println("Startdatum (YYYY-MM-DD):");
            beginDate = Date.valueOf(scan.nextLine());
            System.out.println("Enddatum (YYYY-MM-DD):");
            endDate = Date.valueOf(scan.nextLine());
            System.out.println("Kurs-Typ (ZA/BF/FF/OE):");
            courseType = CourseType.valueOf(scan.nextLine());

            Optional<Course> optionalCourse = repo.insert(new Course(name, description, hours, beginDate, endDate, courseType));

            if (optionalCourse.isPresent()) {
                System.out.println("Kurs angelegt: " + optionalCourse.get());
            } else {
                System.out.println("Kurs konnte nicht angelegt werden");
            }

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException) {
            System.out.println("Kursdaten nicht korrekt angegeben: " + invalidValueException.getMessage());
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Einfügen: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler beim Einfügen: " + e.getMessage());
        }
    }

    private void showCourseDetails() {
        System.out.println("Für welchen Kurs möchten Sie die Details anzeigen?");
        Long courseId = Long.parseLong(scan.nextLine());
        try {
            Optional<Course> courseOptional = repo.getById(courseId);
            if (courseOptional.isPresent()) {
                System.out.println(courseOptional.get());
            } else {
                System.out.println("Kurs mit ID " + courseId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Kurs-Detail-Anzeige: " + databaseException.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler bei Kurs-Detail-Anzeige: " + e.getMessage());
        }
    }

    private void showAllCourses() {
        List<Course> list = null;

        try {
            list = repo.getAll();
            if (list.size() > 0) {
                for (Course course : list) {
                    System.out.println(course);
                }
            } else {
                System.out.println("Kursliste leer!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Anzeige aller Kurse: " + databaseException.getMessage());
        } catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Anzeige aller Kurse: " + exception.getMessage());
        }
    }

    private void inputError() {
        System.out.println("Bitte nur gültige Zahlen eingeben!");
    }
}
