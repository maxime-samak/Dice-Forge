package game.dice;

/**
 * Classe Resource regroupe les différentes formes de ressources
 */
public enum Resource {
    GOLD{
        @Override
       public String resourceName() { return "GOLD"; }
    },
    LUNAR{
        @Override
        public String resourceName() { return "LUNAR"; }
    },
    SOLAR{
        @Override
        public String resourceName() { return "SOLAR"; }
    },
    VICTORY{
        @Override
        public String resourceName() { return "VICTORY"; }
    },
    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    QUESTION{
        @Override
        public String resourceName() { return "?"; }
    },
    X3{
        @Override
        public String resourceName() { return "X3"; }
    },
    PLUS{
        @Override
        public String resourceName() { return "PLUS"; }
    },
    CHOICE{
        @Override
        public String resourceName() { return "CHOICE"; }
    };

    public abstract String resourceName();

}