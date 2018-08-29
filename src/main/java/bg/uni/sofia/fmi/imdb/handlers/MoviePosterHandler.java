package bg.uni.sofia.fmi.imdb.handlers;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.resources.Movie;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.*;

public class MoviePosterHandler extends MovieAbstractHandler {

    private String movieName;

    public MoviePosterHandler(String movieName) {
        this.movieName = movieName;
    }

    public Movie getMoviePoster() throws IOException, MovieSearchException {
        String newMovieName = getNewName(movieName);
        Movie movie = new Movie();
        String location = System.getProperty(MOVIE_DATABASE_LOCATION, MOVIES_LOCATION);
        File file = new File(location + movieName + JSON);
        if (file.exists()) {
            Map<String, Object> movieMap = getMovieInfoMap(file);
            movie.setPoster(String.valueOf(movieMap.get(POSTER)));
        } else {
            JSONObject jsonObject = getMovieJSONObject(OMDB_API_URL + newMovieName);
            if (String.valueOf(jsonObject.get(POSTER)).equals(NULL)) {
                throw new MovieSearchException(MOVIE_NOT_FOUND);
            }
            movie.setPoster(String.valueOf(jsonObject.get(POSTER)));
        }
        return movie;
    }
}
