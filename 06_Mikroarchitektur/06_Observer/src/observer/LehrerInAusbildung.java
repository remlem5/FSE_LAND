package observer;

public class LehrerInAusbildung implements Zuhoerer {

    private String name;

    public LehrerInAusbildung(String name){
        this.name = name;
    }

    @Override
    public void update(String m) {
        System.out.println(this.name +" hat "+m+" auch gehört!");
    }
}
