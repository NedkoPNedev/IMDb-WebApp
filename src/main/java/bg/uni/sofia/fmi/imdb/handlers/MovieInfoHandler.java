package bg.uni.sofia.fmi.imdb.handlers;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.resources.Movie;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.*;

public class MovieInfoHandler extends MovieAbstractHandler {

    private String movieName;
    private String fieldsString;

    public MovieInfoHandler(String movieName, String fieldsString) {
        this.movieName = movieName;
        this.fieldsString = fieldsString;
    }

    public Movie getMovie() throws IOException, MovieSearchException{
        Movie movie = new Movie();
        movie.setTitle(movieName);
        String[] fields = fieldsString.split(",");
        Map<String, Object> movieMap = getMovieMap();
        movie.populateMovieFields(fields, movieMap);
        return movie;
    }

    private Map<String, Object> getMovieMap() throws IOException, MovieSearchException {
        String databaseLocation = System.getProperty("movie.database.location", MOVIES_LOCATION);
        File file = new File(databaseLocation + movieName + JSON);
        if (file.exists()) {
            return getMovieInfoMap(file);
        } else {
            JSONObject jsonObject = getMovieJSONObject(OMDB_API_URL + getNewName(movieName));
            if (jsonObject.containsKey(ERROR)) {
                throw new MovieSearchException(NOT_FOUND_IN_SYSTEM);
            } else {
                Map<String, Object> movieMap = new HashMap<>();
                FileWriter fileWriter =
                        new FileWriter(databaseLocation + movieName + JSON);
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();
                String[] fields = fieldsString.split(",");
                for (String field : fields) {
                    movieMap.put(field, jsonObject.get(field));
                }
                return movieMap;
            }
        }
    }
}
