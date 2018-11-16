package game.dice;

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