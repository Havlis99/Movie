package cz.uhk.fim.movies.model;

public enum MovieType {
    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode");

    private String type;

    MovieType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static MovieType fromString(String type) {
        for (MovieType movieType : MovieType.values()) {
            if (movieType.type.equalsIgnoreCase(type)) {
                return movieType;
            }
        }
        return null;
    }

}
