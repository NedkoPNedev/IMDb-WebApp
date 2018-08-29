package bg.uni.sofia.fmi.imdb.handlers;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.resources.Movie;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.JSON;
import static bg.uni.sofia.fmi.imdb.handlers.MovieInfoHandlerTest.MOVIE_LOCATION;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class MoviePosterHandlerTest {

    private File file = new File(MOVIE_LOCATION + "Ted" + JSON);
    @Before
    public void prepare() {
        file.delete();
        System.setProperty("movie.database.location", MOVIE_LOCATION);
        File file1 = new File(MOVIE_LOCATION);
        if (!file1.exists()) {
            file1.mkdir();
        }
    }

    @Test
    public void getMoviePoster() throws IOException, MovieSearchException {
        MoviePosterHandler moviePosterHandler = new MoviePosterHandler("Ted");
        MoviePosterHandler spy = spy(moviePosterHandler);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Poster", "https:\\/\\/m.media-amazon.com\\/images\\/M\\/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA@@._V1_SX300.jpg");
        doReturn(jsonObject).when(spy).getMovieJSONObject(anyString());
        Movie movie = moviePosterHandler.getMoviePoster();
        assertEquals("https://m.media-amazon.com/images/M/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA@@._V1_SX300.jpg",
                movie.getPoster());
    }
}