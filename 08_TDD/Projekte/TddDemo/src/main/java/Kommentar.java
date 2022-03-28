public class Kommentar {

    private String text;
    private String autor;
    private int bewertung;
    private int stimmen;

    public String getAutor() {
        return this.autor;
    }

    public int getBewertung() {
        return this.bewertung;
    }

    public Kommentar(String text, String autor, int bewertung){
        this.text = text;
        this.autor = autor;
        this.bewertung = bewertung;
    }

    public int getStimmen() {
        return this.stimmen;
    }

    public String getKommentarText() {
        return this.text;
    }
}
