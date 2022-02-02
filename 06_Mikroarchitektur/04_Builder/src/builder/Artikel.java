package builder;

public class Artikel {

    private final long id;
    private final String bezeichnung;
    private final String nummer;
    private final String lagerort;
    private final String zusatzInfo;

    private Artikel(Artikelbuilder builder){
        this.id = builder.id;
        this.bezeichnung = builder.bezeichnung;
        this.nummer = builder.nummer;
        this.lagerort = builder.lagerort;
        this.zusatzInfo = builder.zusatzInfo;
    }

    @Override
    public String toString(){
        return "Artikel: "+this.id+", "+this.bezeichnung+", "+this.nummer+", "+this.lagerort+", "+this.zusatzInfo;
    }

    public static class Artikelbuilder {

        private final long id;
        private final String bezeichnung;
        private String nummer;
        private String lagerort;
        private String zusatzInfo;

        public Artikelbuilder(long id, String bezeichnung){
            this.id = id;
            this.bezeichnung = bezeichnung;
        }

        public Artikelbuilder nummer(String nummer){
            this.nummer = nummer;
            return this;
        }

        public Artikelbuilder lagerort(String lagerort){
            this.lagerort = lagerort;
            return this;
        }

        public Artikelbuilder zusatzInfo(String zusatzInfo){
            this.zusatzInfo = zusatzInfo;
            return this;
        }

        public Artikel build(){
            return new Artikel(this);
        }

    }
}
