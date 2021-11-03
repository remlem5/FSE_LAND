# Test mit einer Entität

CRUD Operationen funktionieren

Datenbank im localhost/phpmyadmin erstellt (meins1)

GET, POST, DELETE mit Insomnia funktioniert


## Properties

server.port=8088

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/meins1
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true


## Entität

```java
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;

    private String firstName;
    private String lastName;
}
```

## Controller

@RestController
zuständig für Mappings

@Autowired mit empService

@PostMapping("url")
@GetMapping("url")
@DeleteMapping("url")


## Repo

```java
@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {

    public Employee findEmployeeByEmpId(long id);

    public Employee findEmployeeByFirstNameIgnoreCase(String name);

    public Employee findEmployeeByLastNameIgnoreCase(String name);
}
```

## Service

### EmpService

enthält nur Methoden-Köpfe für CRUD-Operationen


### EmpServiceImpl

implements EmpService und ist mit EmpRepo Autowired
```java
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepo empRepo;
```

@Override alle Methoden aus EmpService und Implementierung



