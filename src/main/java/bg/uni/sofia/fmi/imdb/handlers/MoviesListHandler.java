package bg.uni.sofia.fmi.imdb.handlers;

import bg.uni.sofia.fmi.imdb.resources.Movie;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.*;

public class MoviesListHandler extends MovieAbstractHandler {

    private String order;
    private String genresString;
    private String actorsString;

    public MoviesListHandler(String order, String genres, String actors) {
        this.order = order;
        this.genresString = genres;
        this.actorsString = actors;
    }

    public List<Movie> getMoviesList() throws IOException {
        File dir = new File(MOVIES_LOCATION);
        File[] files = dir.listFiles();
        List<Map<String, Object>> moviesList = new ArrayList<>();
        for(File file: files) {
            moviesList.add(getMovieInfoMap(file));
        }
        List<Map<String, Object>> filteredSortedList = getFilteredAndSortedList(moviesList);
        List<Movie> movies = new ArrayList<>();
        String[] fields = getMovieFields(moviesList.get(0));
        for (Map<String, Object> filteredSortedMovie: filteredSortedList) {
            Movie movie = new Movie();
            movie.populateMovieFields(fields, filteredSortedMovie);
            movies.add(movie);
        }
        return movies;
    }

    private String[] getMovieFields(Map<String,Object> stringObjectMap) {
        String[] fields = new String[NUM_FIELDS];
        int cnt = 0;
        for(Map.Entry<String, Object> entry: stringObjectMap.entrySet()) {
            fields[cnt++] = entry.getKey();
        }
        return fields;
    }

    private List<Map<String, Object>> getFilteredAndSortedList(List<Map<String, Object>> moviesList) {
        String[] genres = genresString.split(",");
        String[] actors = actorsString.split(",");
        return moviesList.stream()
                .filter(e -> {
                    String genresList = e.get(GENRES_TYPE.replace('g','G')
                            .substring(0,GENRE_LEN)).toString();
                    String actorsList = e.get(ACTORS_TYPE.replace('a','A'))
                            .toString();
                    if (genres.length > 0) {
                        for (String genre: genres) {
                            if (!(genresList.toLowerCase()).contains(removeSpaces(genre).toLowerCase())){
                                return false;
                            }
                        }
                    }
                    if (actors.length > 0) {
                        for (String actor: actors) {
                            if (!actorsList.contains(removeSpaces(actor).toLowerCase())) {
                                return false;
                            }
                        }
                    }
                    return true;
                })
                .sorted((o1, o2) -> {
                    double rating_1 = getDouble(o1.get(IMDB_RATING).toString());
                    double rating_2 = getDouble(o2.get(IMDB_RATING).toString());

                    return (order.equals(DESC)) ? cmp(rating_1, rating_2,  -1) :
                            cmp(rating_1, rating_2, 1);
                })
                .collect(Collectors.toList());
    }

    private int cmp(double rating_1, double rating_2, int val) {
        if (rating_1 - rating_2 >= EPSILON) return val;
        else if (rating_1 - rating_2 <= EPSILON && rating_1 - rating_2 >= -EPSILON) return 0;
        else return -val;
    }

    private double getDouble(String value) {
        return (value.equals(N_A)) ? 0.0 : Double.parseDouble(value);
    }

    private String removeSpaces(String oldString) {
        String newString="";
        int len = oldString.length();
        char currentChar;
        for (int i = 0; i < len; i++) {
            currentChar = oldString.charAt(i);
            newString += (currentChar == ' ')? "": currentChar;
        }
        return newString;
    }


}
