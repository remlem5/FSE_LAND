package observer;

import java.util.ArrayList;
import java.util.List;

public class Lehrer implements Unterricht{

    private List<Zuhoerer> schuelerList = new ArrayList<>();

    @Override
    public void teilnehmen(Zuhoerer s) {
        schuelerList.add(s);
    }

    @Override
    public void nachHauseGehen(Zuhoerer s) {
        schuelerList.remove(s);
    }

    @Override
    public void neuesThema(String message) {
        for (Zuhoerer s : schuelerList){
            s.update(message);
        }
    }
}
