package com.imdb;

import bg.uni.sofia.fmi.imdb.handlers.MovieInfoHandler;
import bg.uni.sofia.fmi.imdb.handlers.MoviePosterHandler;
import bg.uni.sofia.fmi.imdb.handlers.MoviesListHandler;
import bg.uni.sofia.fmi.imdb.handlers.TVSeriesHandler;
import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.resources.Movie;
import bg.uni.sofia.fmi.imdb.resources.TVSeries;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.MOVIE_NOT_FOUND;
import static bg.uni.sofia.fmi.imdb.handlers.Constants.SET_ERROR;

@Path("/imdb")
public class IMDb {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies/{name}")
    public Movie movieResponseJSON(@PathParam("name") String movieName,
                                   @QueryParam("fields") String fieldsString) throws IOException {
        MovieInfoHandler movieInfoHandler = new MovieInfoHandler(movieName,fieldsString);
        Movie movie;
        try {
            movie = movieInfoHandler.getMovie();
        } catch(MovieSearchException e){
            movie = new Movie();
            movie.setError(MOVIE_NOT_FOUND);
            System.out.println(e.getMessage());
        }
        return movie;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies/")
    public List<Movie> moviesResponseJSON(@QueryParam("order") String order,
                                    @QueryParam("genres") String genres,
                                    @QueryParam("actors") String actors) throws IOException{
        MoviesListHandler moviesListHandler = new MoviesListHandler(order, genres, actors);
        return moviesListHandler.getMoviesList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tv-series")
    public List<TVSeries> tvSeriesResponseJSON(@QueryParam("tvSeriesName") String tvSeriesName,
                                               @QueryParam("seasonValue") String seasonValue)
                                                            throws IOException{
        TVSeriesHandler tvSeriesHandler = new TVSeriesHandler(tvSeriesName, seasonValue);
        List<TVSeries> tvSeriesList;
        try {
            tvSeriesList = tvSeriesHandler.getTVSeriesList();
        } catch(MovieSearchException e) {
            tvSeriesList = new ArrayList<>();
            System.out.println(e.getMessage());
        }
        return tvSeriesList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies/{name}/poster")
    public Movie moviePosterResponseJSON(@PathParam("name") String movieName) throws IOException{
        MoviePosterHandler moviePosterHandler = new MoviePosterHandler(movieName);
        Movie movie;
        try {
            movie = moviePosterHandler.getMoviePoster();
        } catch (MovieSearchException e) {
            movie = new Movie();
            movie.setError(SET_ERROR);
            System.out.println(e.getMessage());
        }
        return movie;
    }
}
