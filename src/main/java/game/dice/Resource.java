package game.dice;

/**
 * Classe Resource regroupe les différentes formes de ressources
 */
public enum Resource {
    GOLD{
        @Override
       public String resourceName() { return "\033[0;33m"+"GOLD"+"\033[0m"; }
    },
    LUNAR{
        @Override
        public String resourceName() { return "\033[0;34m"+"LUNAR"+"\033[0m"; }
    },
    SOLAR{
        @Override
        public String resourceName() { return "\033[0;31m"+"SOLAR"+"\033[0m"; }
    },
    VICTORY{
        @Override
        public String resourceName() { return "\033[0;32m"+"VICTORY"+"\033[0m"; }
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