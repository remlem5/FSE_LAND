package ausgangspunkt;

public abstract class WR implements IUmrechnen{

    private double gebuehr;
    private double betrag;

    public WR(){

    }

    public WR(WRBuilder builder) {
        this.betrag = builder.betrag;
        this.gebuehr = builder.gebuehr;
    }

    @Override
    public abstract double umrechnen(double betrag);


    public static class WRBuilder {

        private double gebuehr;
        private double betrag;
        private WR wr;

        public WRBuilder(WR wr){
            this.wr = wr;
            this.betrag = wr.betrag;
            this.gebuehr = wr.gebuehr;
        }

        public WRBuilder gebuehr(double gebuehr) {
            this.gebuehr = gebuehr;
            return this;
        }

        public WRBuilder betrag(double betrag){
            this.betrag = betrag;
            return this;
        }

        public WR build(){
            return this.wr;
        }
    }

}
