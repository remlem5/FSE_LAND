# pom.xml

JUnit und Mockito in pom.xml einfügen

```xml
<dependencies>

    <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter -->
    <!-- use of this aggregator artifact makes stating individual artifacts obsolete -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter -->
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-junit-jupiter</artifactId>
      <version>4.3.1</version>
      <scope>test</scope>
    </dependency>

  </dependencies>
```

# Testklassen
benötigter Testaufbau
```java
@BeforeEach
void setup(){
    //Objekte erzeugen, mit Testdaten befüllen, ...
}
```

## Testmethoden
was soll getestet werden?
```java
@Test
void testName(){
    Assertions.assertEquals(expected, Objekt.get...());
    Assertions.assertNotEquals(unexpected, Objekt.get...());
    Assertions.assertTrue(boolscher Wert);
    Assertions.assertFalse(boolscher Wert);
    Assertions.assertNotNull(Objekt);
    ...
}
```

# Mockito

## Interface repo
wie immer nur Methodenköpfe schreiben
```java
public interface ArticleRepo {
    public Article insert(Article article);
    public List<Article> getAll();
    public void geById(long id);
}
```

## Service-Klasse
Testobjekt mit Daten füllen
```java
private ArticleRepo repo;

    public ArticleService(ArticleRepo repo) {
        this.repo = repo;
    }

    public Article insert(Article article) {
        article.setId(1);
        article.setText("Text1");
        article.setInStock(3);
        return repo.insert(article);
    }
```

## Test

mit @Mock können Klassen gemockt werden
```java
    @Mock
    private ArticleRepo repo;
    private AutoCloseable closeable;
    @InjectMocks
    private ArticleService artService;
```

### BeforeEach
Testaufbau erzeugen
```java
    @BeforeEach
    void start(){
        closeable = MockitoAnnotations.openMocks(this);
        //repo = mock(ArticleRepo.class);
        //artService = new ArticleService(repo);
    }
```

### AfterEach
das AutoCloseable schließen
```java
    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
```

### Test
was soll getestet werden?
```java
    @Test
    void insertTest(){
        Article article = new Article();

        when(repo.insert(any(Article.class))).then(returnsFirstArg());

        Article insertedArticle = artService.insert(article);

        assertNotNull(insertedArticle.getText());
    }
```

# Parametrisierte Tests
Testdaten werden als CSV-Daten übergeben
```java
    @ParameterizedTest
    @CsvSource({"A, 3, 27", "B, 6, 25", "A,4,17", "B,1,30"})
    void testTicketkaeufeRichtigeParameter(char reihe, int platz, float geld) {
        Assertions.assertNotNull(v1.kaufeTicket(reihe, platz, geld));
    }
```

