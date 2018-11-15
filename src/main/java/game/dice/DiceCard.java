package game.dice;

public class DiceCard {

    private int[] value;
    private Resource[] resource;

    public DiceCard(int value, Resource resource) {
        this.resource=new Resource[1];
        this.value = new int[1];
        this.resource[0] = resource;
        this.value[0] = value;
    }
    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public DiceCard(int[] value, Resource[] resource){
        this.resource= resource;
        this.value= value;
    }

    public int getValue() {
        return value[0];
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public int[] getValue2() {
        return value;
    }



    public String getResource() {
        return resource[0].resourceName();
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public Resource[] getResource2() {
        return resource;
    }

    public boolean equals(DiceCard card) {
        if(this.value[0] == card.getValue() && this.resource[0].resourceName().equals(card.getResource())) {
            return true;
        }
        else { return false; }
    }

    public String toString(){
        return value[0] + " " + resource[0].resourceName();
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    public String toString2() {
        return value[0] + " " + resource[0].resourceName();
    }
}