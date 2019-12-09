package cz.uhk.fim.movies.util;

import cz.uhk.fim.movies.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieParser {
    public static List<Movie> parseMovieSearch(String query) {
        List<Movie> searchList = new ArrayList<>();

        String jsonResponse = HttpHandler.searchForMovies(query);

        JSONObject rootObject = new JSONObject(jsonResponse);
        JSONArray movieArray = rootObject.getJSONArray("Search");
        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            Movie movie = new Movie();
            movie.setTitle(movieObject.getString("Title"));
            movie.setYear(movieObject.getString("Year"));
            movie.setPoster(ImageHandler.getImageFromUrl(movieObject.getString("Poster")));
            movie.setType(MovieType.fromString(movieObject.getString("Type")));
            movie.setMovieId(movieObject.getString("imdbID"));

            System.out.println(movie);
            searchList.add(movie);
        }

        return searchList;
    }

    public static Movie parseMovieDetail(String id) {
        String jsonResponse = HttpHandler.getDetailById(id);

        JSONObject rootObject = new JSONObject(jsonResponse);
        System.out.println(rootObject);

        String movieId = rootObject.getString("imdbID");
        String title = rootObject.getString("Title");
        String year = rootObject.getString("Year");
        Date releaseDate = DataHandler.getDateFromString(rootObject.getString("Released"));
        int runtime = Integer.parseInt(rootObject.getString("Runtime").replace(" min", ""));

        List<Genre> genres = new ArrayList<>();
        String[] genreArray = rootObject.getString("Genre").split(",");
        for (String s : genreArray) {
            genres.add(new Genre(s));
        }

        String director = rootObject.getString("Director");
        String writer = rootObject.getString("Writer");

        List<Actor> actors = new ArrayList<>();
        for (String s : rootObject.getString("Actors").split(",")) {
            actors.add(new Actor(s));
        }

        String plot = rootObject.getString("Plot");
        String country = rootObject.getString("Country");
        String language = rootObject.getString("Language");
        Image poster = ImageHandler.getImageFromUrl(rootObject.getString("Poster"));

        List<Rating> ratings = new ArrayList<>();
        JSONArray ratingArray = rootObject.getJSONArray("Ratings");
        for (int i = 0; i < ratingArray.length(); i++) {
            JSONObject rating = ratingArray.getJSONObject(i);
            ratings.add(new Rating(
                    rating.getString("Source"),
                    rating.getString("Value")
            ));
        }

        MovieType type = MovieType.fromString(rootObject.getString("Type"));


        return (new Movie(movieId, title, year, releaseDate, runtime, genres, director, writer, actors, plot, country, language, poster, ratings, type));
    }

}
