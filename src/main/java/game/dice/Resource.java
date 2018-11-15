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
    GOLDLUNAR{
        @Override
        public String resourceName() { return "PLUSGOLDLUNAR"; }
    },
    VICTORYSOLAR{
        @Override
        public String resourceName() { return "PLUSVICTORYSOLAR"; }
    },
    GOLDSOLARLUNAR{
        @Override
        public String resourceName() { return "GOLDSOLARLUNAR"; }
    };


    public abstract String resourceName();

}