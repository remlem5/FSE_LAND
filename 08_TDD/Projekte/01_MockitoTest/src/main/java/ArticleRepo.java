import java.util.List;

public interface ArticleRepo {

    public Article insert(Article article);
    public List<Article> getAll();
    public void geById(long id);

}
