package domain;

import exceptions.InvalidValueException;

public class Site extends BaseEntity{

    private String name;
    private String ort;
    private boolean aktiv;

    public Site(Long id, String name, String ort, boolean aktiv) throws InvalidValueException {
        super(id);
        this.setName(name);
        this.setOrt(ort);
        this.aktiv = aktiv;
    }

    public Site(String name, String ort, boolean aktiv) throws InvalidValueException {
        super(null);
        this.setName(name);
        this.setOrt(ort);
        this.aktiv = aktiv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValueException {
        if (name != null && name.length()>1){
            this.name = name;
        } else {
            throw new InvalidValueException("Baustellen-Name muss mindestens 2 Zeichen lang sein!");
        }
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) throws InvalidValueException {
        if (ort != null && ort.length()>1){
            this.ort = ort;
        } else {
            throw new InvalidValueException("Baustellen-Ort muss mindestens 2 Zeichen lang sein!");
        }
    }

    public boolean isAktiv() {
        return aktiv;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id= " +this.getId() + '\'' +
                "name='" + name + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
