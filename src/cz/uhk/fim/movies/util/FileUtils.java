package cz.uhk.fim.movies.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private static final String FAVORITE_MOVIES = "movies.txt";

    public static void saveStringToFile(String data) throws IOException {
        File file = new File(FAVORITE_MOVIES);
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(String.format("%s;",data));
        bufferedWriter.close();
        writer.close();
    }
}
