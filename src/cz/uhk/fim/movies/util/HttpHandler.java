package cz.uhk.fim.movies.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    public static final String API_KEY = "f49d9c1";
    public static final String API_URL = String.format("http://www.omdbapi.com/?apikey=%s", API_KEY);

    public static String searchForMovies(String query){
        String url = String.format("%s&s=%s",API_URL,query.replaceAll(" ","+"));
        String jsonResponse = getHttpResult(url);
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    public static String getDetailById(String id){
        String url = String.format("%s&i=%s",API_URL,id);
        String jsonResponse = getHttpResult(url);
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    private static String getHttpResult(String resourceUrl) {
        String result = "";

        try {
            URL url = new URL(resourceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                result = response.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
