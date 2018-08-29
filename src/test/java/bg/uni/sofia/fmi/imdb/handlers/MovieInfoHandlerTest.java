package bg.uni.sofia.fmi.imdb.handlers;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.resources.Movie;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.JSON;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class MovieInfoHandlerTest {

    public static final String MOVIE_LOCATION = "C:\\Users\\C5275150\\Desktop\\IMDb_v2.0\\target\\movieDataBase\\";
    private File file = new File(MOVIE_LOCATION + "Titanic" + JSON);

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
    public void testGetMovie() throws IOException, MovieSearchException {
        MovieInfoHandler movieInfoHandler = new MovieInfoHandler("Titanic", "Actors");
        MovieInfoHandler spy = spy(movieInfoHandler);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Actors", "Leonardo DiCaprio, Kate Winslet, Billy Zane, Kathy Bates");
        jsonObject.put("Title", "Titanic");
        doReturn(jsonObject).when(spy).getMovieJSONObject(anyString());
        Movie movie = spy.getMovie();
        assertEquals(jsonObject.get("Actors"), movie.getActors());
        assertEquals(jsonObject.get("Title"), movie.getTitle());
        assertTrue(file.exists());
    }
}