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

    public boolean equals(DiceCard card) {
        if(this.value == card.getValue() && this.resource.resourceName().equals(card.getResource())) {
            return true;
        }
        else { return false; }

    }

    public String toString(){
        return value + " " + resource.resourceName();
    }
}