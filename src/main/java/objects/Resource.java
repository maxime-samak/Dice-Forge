package objects;

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
    };

    public abstract String resourceName();

}