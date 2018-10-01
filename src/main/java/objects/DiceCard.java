package objects;

public class DiceCard {

    private int value;
    private Resource resource;

    public DiceCard(Resource resource, int value) {
        this.resource = resource;
        this.value = value;
    }

    private enum stone {
        gold,
        moon,
        solar,
        victory;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getValue() {
        return value;
    }

    public Resource getResource() {
        return resource;
    }
}