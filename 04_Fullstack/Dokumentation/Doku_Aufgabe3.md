# start.spring.io

* Maven Project
* Java
* Spring Boot 2.5.5
* Packaging Jar
* Java 11


## Dependencies

* Spring Web
* H2 Database

Test mit localhose:8080


## Create simple API

Controller-Klasse

```java
@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(){
        return "Hallo Melmi!";
    }
}
```
wenn localhost:8080/ aufgerufen wird kommt "Hallo Melmi!"

in application.properties
server.port = 8082
ändert Port für Applikation


## Running

localhost:8082


im MainController
statt @RequestMappint(....RequestMethod.GET) einfach nur @GetMapping("/")


## Springboot Devtools

in pom.xml
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
	<optional>true</optional>
</dependency>
```
hinzufügen
damit wird jede Änderung sofort für localhost übernommen und App muss nicht immer wieder neu gestartet werden

Maven-Application neu laden und Einstellungen werden übernommen

Strg+Shift+A -> Registry -> compiler.automake.allow.when.app.running aktivieren oder compiler.automake.allow.parallel aktivieren (wenn nicht schon aktiviert)

File -> Settings -> Build, Execution, Deployment -> Compiler -> Build project automatically aktivieren

App neu starten

Text bei @GetMapping in MainController ändern. Homepage neu laden und neuer Text erscheint.


## Architecture and Example

1. Zuerst REST API
* Get
* Post
* Put
* Delete

2. Service Layer
* ganze Logik

3. Data Access/Repository Layer
* für Interaktion mit DB

4. Database
* zuerst H2
* dann MySQL (um zu verstehen wie man Technologien austauschen kann)


## Adding Dependency H2 & JPA

Spring Data JPA in pom.xml
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

in application.properties:

spring.h2.console.enabled=true //schaltet Webkonsole ein
spring.datasource.url=jdbc:h2:mem:dcbapp //DB-URL
spring.datasource.driver-class-name=org.h2.Driver //H2-Driver
spring.datasource.username=sa //mein Username
spring.datasource.password=password //mein Passwort
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect //für JPA-datasource-platform

im Browser:
localhost:8082/h2-console
öffnet H2-Konsole

Änderungen wurden bei mir nicht gleich übernommen:
JDBC URL: auf jdbc:h2:mem:dcbapp setzen
User Name: sa
Password: password
-> Test Connection -> sollte erfolgreich sein
-> Connect -> öffner H"-Konsole


## Creating Springboot Components

3 Packages erzeugen
* entity
* repository
* service


in entity-package
Department.java erzeugen

```java
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
```
Getter und Setter generieren lassen (Alt+Einfg)

2 Konstruktoren und toString() erzeugen lassen
```java
public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode) {
    this.departmentId = departmentId;
    this.departmentName = departmentName;
    this.departmentAddress = departmentAddress;
    this.departmentCode = departmentCode;
}

public Department() {
}

@Override
public String toString() {
    return "Department{" +
            "departmentId=" + departmentId +
            ", departmentName='" + departmentName + '\'' +
            ", departmentAddress='" + departmentAddress + '\'' +
            ", departmentCode='" + departmentCode + '\'' +
            '}';
    }
```

damit Department mit Datenbank interagieren kann:
```java
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
```

DepartmentController.java in controller-package erzeugen
@RestController wieder hinzufügen

im service-package Interface DepartmentService erzeugen
```java
@Service
public class DepartmentServiceImpl implements DepartmentService{
}
```

im repository-package Interface DepartmentRepository erzeugen
```java
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
```

## Department Save API

```java
public interface DepartmentService {
    public Department saveDepartment(Department department);
}
```
```java
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

}
```
```java
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
```
```java
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
```


@Autowired verbindet Objekt mit Klasse in Spring-Container (damit muss man kein Objekt erzeugen, sondern kann mit Name der Variablen auf Klasse zugreifen)

@RequestBody erzeugt aus JSON-Objekt ein Objekt vom Typ Department




