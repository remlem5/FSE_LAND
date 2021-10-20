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


## Testing API's using Rest Client

in Insomnia neuen POST-Request erstellen
http://localhost:8082/departments
im JSOM-Format die Daten eingeben
```JSON
{
	"departmentName":"IT",
	"departmentAddress":"Vils",
	"departmentCode":"IT-0815"
}
```
aus "send" klicken: "200 OK" heißt dass Request erfolgreich war
in H2-Console auf "Run" klicken: dann sollte ein Datensatz hinzugefügt werden
Ich hab null Plan wie das jetzt funktioniert hat...

zweites Department hinzufügen
```JSON
{
	"departmentName":"CE",
	"departmentAddress":"Vils",
	"departmentCode":"CE-0816"
}
```

Output in Insomnia
```JSON
{
  "departmentId": 2,
  "departmentName": "CE",
  "departmentAddress": "Vils",
  "departmentCode": "CE-0816"
}
```
das bedeutet dass "save()" funktioniert

Weil wir mit H2-DB arbeiten, werden bei jedem Neustart der Applikation, die Einträge gelöscht.


## GetMapping

Im DepartmentController 
```java
@GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        return departmentService.fetchDepartmentList();
    }
```

In DepartmentServiceImpl
```java
    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }
```
Methode muss natürlich auch im DepartmentService-Interface vorhanden sein

In Insomnia neuen GET-Request "Get Department" erstellen und URL aus Save Department kopieren

"Send" klicken: Status "200 OK"
Output:
```JSON
[
  {
    "departmentId": 1,
    "departmentName": "CE",
    "departmentAddress": "Vils",
    "departmentCode": "CE-0816"
  },
  {
    "departmentId": 2,
    "departmentName": "IT",
    "departmentAddress": "Vils",
    "departmentCode": "IT-0815"
  },
  {
    "departmentId": 3,
    "departmentName": "EZ",
    "departmentAddress": "Imst",
    "departmentCode": "EZ-0817"
  }
]
```

## Fetching Data by ID

gleich wie oben in 2 Klassen und im Interface Methode erstellen

DepartmentServiceImpl
```java
@Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }
```

In Insomnia kommen bei Get-Request jetzt keine Daten zurück, da sie jedes mal gelöscht werden wenn App neu geladen wird.
Also Daten über "Save Department" wieder einfügen.

Wenn Daten eingefügt worden sind:
im Get Department http://localhost:8082/departments/2 als URL einfügen
Output:
```JSON
{
  "departmentId": 2,
  "departmentName": "IT",
  "departmentAddress": "Vils",
  "departmentCode": "IT-0815"
}
```


## Deleting Data

wieder in allen 3 Klassen die Methode erzeugen
DepartmentServiceImpl.java
```java
@Override
    public void deleteDepartmentByID(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
```

Departments in Insomnia hinzufügen
Neuen Delete-Request "Delete Department" erzeugen
gleich URL wie bei Get Department:
http://localhost:8082/departments/1

Status 200 OK
"Department deleted successfully!"

Get Department nochmals ausführen. Department mit ID 1 ist gelöscht


## Updating Data

DepartmentController.java
```java
@PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);
    }
```

Methode wieder im DepartmentService Interface
und in der DepartmentServiceImpl-Klasse erzeugen

DepartmentServiceImpl.java
```java
@Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        return departmentRepository.save(depDB);
    }
```
Jeder Wert in DB des übergebenen Departments wird überprüft ob der Wert null oder "" ist

Neuen "PUT-Request" Update Department erzeugen. Bei "No Body" "JSON" auswählen

URL und JSON-Objekt aus POST-Request kopieren und einfügen. An URL noch DepartmentId anhängen (/2)

DepartmentName ändern und "Send"
Status 200 OK
Neues JSON Objekt erscheint im rechten Fenster
Änderung auch in H2-Konsole sichtbar

Wenn man im PUT-Request nur
```JSON
{
	"departmentAddress":"Imst"
}
```
schreibt und DepartmentId in URL mitgibt, wird nur die DepartmentAddress verändert.



