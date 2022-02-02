package observer;

public interface Unterricht {

    public void teilnehmen(Zuhoerer s);
    public void nachHauseGehen(Zuhoerer s);
    public void neuesThema(String message);

}
