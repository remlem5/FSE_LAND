public class ArticleService {

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
}
