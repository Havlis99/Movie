package cz.uhk.fim.movies.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileUtils {
    private static final String FAVORITE_MOVIES = "movies.txt";
    private static final String GENRES = "genres.txt";
    private static final String YEARS = "years.txt";

    public static final int TYPE_ALL = 0;
    public static final int TYPE_GENRES = 1;
    public static final int TYPE_YEARS = 2;

    public static String composeData(HashMap<String, String> dataMap) {
        StringBuilder sb = new StringBuilder();
        for (String cat : dataMap.keySet()) {
            sb.append(String.format("%s:%s%n", cat, dataMap.get(cat)));
        }
        return sb.toString();
    }

    public static HashMap<String, String> decomposeData(String data) {
        HashMap<String, String> dataMap = new HashMap<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] lineValues = line.split(":");
            dataMap.put(lineValues[0], lineValues[1]);
        }
        return dataMap;
    }

    public static String[] decomposeCategory(String data) {
        return data.split(";");
    }

    public static void saveStringToFile(String data, int type) throws IOException {
        File file;
        switch (type) {
            case TYPE_GENRES:
                file = new File(GENRES);
                break;
            case TYPE_YEARS:
                file = new File(YEARS);
                break;
            default:
                file = new File(FAVORITE_MOVIES);
                break;
        }
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(String.format("%s;", data));
        bufferedWriter.close();
        writer.close();
    }

    public static String readStringFromFile(int type) throws IOException {
        switch (type) {
            case TYPE_GENRES:
                return readStringFromFile(GENRES);
            case TYPE_YEARS:
                return readStringFromFile(YEARS);
            default:
                return readStringFromFile(FAVORITE_MOVIES);
        }
    }

    public static String readStringFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
