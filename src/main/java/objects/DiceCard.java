package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DiceCard {
    private String actionCard = "";
    private HashMap resources;

    public DiceCard(HashMap<Resource,Integer> resources , String actionCard) {
        this.resources = resources;
        this.actionCard = actionCard;
    }

    public int getValue(Resource resource) {
        return (int)resources.get(resource);
    }

    public String getActionCard() {
        return actionCard;
    }

    public List<Resource> getResource(){
        List resourceList = new ArrayList();
        resourceList.addAll(resources.keySet());
        return resourceList;
    }

    public boolean equals(DiceCard card) {
        /* if(this.value == card.getValue() && this.resources.length == card.getResource().length && this.actionCard == card.getActionCard()) {
            for(int i = 0; i < resources.length; i++) {
                if (this.resources[i] != card.getResource()[i]) {
                    return false;
                }
            }
            return true;
        } */
        return false;
    }
/*
    public String toString(){
        String res = "";

        for(Resource elt: resources) {
            res += elt.resourceName() + " ";
        }
        return res;
    }
    */
}