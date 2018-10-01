package objects;

public class DiceCard {

    private int value;
    private Resource resource;

    public DiceCard(int value, Resource resource) {
        this.resource = resource;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getResource() {
        return resource.resourceName();
    }
}