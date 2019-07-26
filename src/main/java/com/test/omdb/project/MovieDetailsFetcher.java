package com.test.omdb.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.test.omdb.project.ParameterStringBuilder.appendUri;
import static com.test.omdb.project.ParameterStringBuilder.getParamsString;

/**
 * author vcchanda on 2019-07-25.
 */
public class MovieDetailsFetcher {

    private static final String OMDB_URL = "https://www.omdbapi.com/?apikey=9ba1f676"; // Ideally API key shouldn't be here but for test project, it is okay
    private static final String OMDB_MOVIE_QUERY_PARAM_KEY = "t";

    static MovieDetails fetchMovieDetailsByName(String movieName) throws URISyntaxException {
        URL url;
        MovieDetails movieDetails = new MovieDetails();
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(OMDB_MOVIE_QUERY_PARAM_KEY, movieName);
            url = appendUri(OMDB_URL, getParamsString(parameters)).toURL();

//            connection.setDoOutput(true);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            System.out.println("connection.getURL() = " + connection.getURL());
            int status = connection.getResponseCode();
            System.out.println("status = " + status);
            StringBuilder content = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            System.out.println("content = " + content);
            movieDetails.setMovieDetails(content.toString());
        } catch (IOException e) {
            System.out.println("Unable to fetch the data due to:" + e.getMessage());
        }
        return movieDetails;
    }
}
