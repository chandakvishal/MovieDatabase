package com.test.omdb.project;

import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * author vcchanda on 2019-07-25.
 */
public class MovieActivity {

    public static void main(String[] args) throws URISyntaxException {

        System.out.println("Enter a movie name to get the details for: ");

        Scanner scanner = new Scanner(System.in);
        String movieName = scanner.next();

        MovieDetails movieDetails = getMovieDetails(movieName);
        System.out.println("movieDetails.getMovieDetails() = " + movieDetails.getMovieDetails());
    }

    private static MovieDetails getMovieDetails(String movieName) throws URISyntaxException {
        return MovieDetailsFetcher.fetchMovieDetailsByName(movieName);
    }
}
