package game.card;

import org.junit.*;
import java.util.ArrayList;
import java.util.*;

public class IslandsTest {

    @Test
    public void IslandsTest() {

        Islands i1 = new Islands(2);

        String s1 = "[Vous pouvez dépensez 3 gold pour gagner 4 point gloire à tous les tours jusqu'à la fin., Vous pouvez dépensez 3 gold pour gagner 4 point gloire à tous les tours jusqu'à la fin., Recevez 3 gold et 3 lunar., Recevez 3 gold et 3 lunar.]";
        String s2 = "[Recevez les ressources d'un lancé de dé à tous les tours jusqu'à la fin., Recevez les ressources d'un lancé de dé à tous les tours jusqu'à la fin., Recevez 1 gold et 1 lunar ou solar à tous les tours jusqu'à la fin., Recevez 1 gold et 1 lunar ou solar à tous les tours jusqu'à la fin.]";
        String s4 = "[Pas d’effet. Cette carte ne donne que des points de victoire., Pas d’effet. Cette carte ne donne que des points de victoire., Pas d’effet. Cette carte ne donne que des points de victoire., Pas d’effet. Cette carte ne donne que des points de victoire.]";
        String s10 = "[Pas d’effet. Cette carte ne donne que des points de victoire., Pas d’effet. Cette carte ne donne que des points de victoire.]";

        Assert.assertEquals(s1,i1.getIslandAvailables(1).toString());
        Assert.assertEquals(s2,i1.getIslandAvailables(2).toString());
        Assert.assertEquals(s4,i1.getIslandAvailables(4).toString());
        Assert.assertEquals(s10,i1.getIslandAvailables(10).toString());





    }

}
