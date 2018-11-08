package objects;

public abstract class AbstractCard implements InterfaceCard{

    private final int pointGloire;
    private String type; // A optimiser : changer surement en enum(effet immédiat, et deux autres types d'effet à durée permanente
    private final Object[] coutCarte;
    private final boolean carteConseiller;

    public AbstractCard(int pointGloire, String type, Object[] coutCarte, boolean carteConseiller) {
        this.pointGloire = pointGloire;
        this.type = type;
        this.coutCarte = coutCarte;
        this.carteConseiller = carteConseiller;
    }

    public abstract void effetCarte();

}