import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArticleTest {

    @Mock
    private ArticleRepo repo;
    private AutoCloseable closeable;
    @InjectMocks
    private ArticleService artService;

    @BeforeEach
    void start(){
        closeable = MockitoAnnotations.openMocks(this);
        //repo = mock(ArticleRepo.class);
        //artService = new ArticleService(repo);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void insertTest(){
        Article article = new Article();

        when(repo.insert(any(Article.class))).then(returnsFirstArg());

        Article insertedArticle = artService.insert(article);

        assertNotNull(insertedArticle.getText());
    }

}
