package observer;

public class Schueler implements Zuhoerer {

    private String name;

    public Schueler(String name){
        this.name = name;
    }

    @Override
    public void update(String m) {
        System.out.println(this.name +" hat " + m +" gehört");
    }
}
