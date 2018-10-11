package objects;

public enum Resource {
    GOLD{
        @Override
        String resourceName() { return "GOLD"; }
    },
    LUNAR{
        @Override
        String resourceName() { return "LUNAR"; }
    },
    SOLAR{
        @Override
        String resourceName() { return "SOLAR"; }
    },
    VICTORY{
        @Override
        String resourceName() { return "VICTORY"; }
    };

    abstract String resourceName();

}
