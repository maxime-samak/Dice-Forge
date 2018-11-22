package game.dice;

public class DiceCard {

    private int[] value;
    private Resource[] resource;

    public DiceCard(int value, Resource resource) {
        this.resource = new Resource[1];
        this.value = new int[1];
        this.resource[0] = resource;
        this.value[0] = value;
    }
    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public DiceCard(int[] value, Resource[] resource){
        this.resource = new Resource[resource.length];
        this.value = new int[value.length];
        for(int i = 0; i < value.length; i++) {
            this.resource[i] = resource[i];
            this.value[i] = value[i];
        }
    }

    public int getValue() {
        return value[0];
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public int[] getValueArray() {
        return value;
    }

    public String getResource() {
        return resource[0].resourceName();
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public Resource[] getResourceArray() {
        return resource;
    }

    public boolean equals(DiceCard card) {
        for(int i = 0; i < this.getValueArray().length; i++) {
            if(!(this.value[0] == card.getValue() && this.resource[0].resourceName().equals(card.getResource()))) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < this.getValueArray().length; i++) {
            res += value[i] + " " + resource[i].resourceName() + " ";
        }
        return res;
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public String toString2() {
        return value[0] + " " + resource[0].resourceName();
    }
}




