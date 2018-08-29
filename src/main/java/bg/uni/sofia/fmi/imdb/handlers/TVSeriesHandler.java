package bg.uni.sofia.fmi.imdb.handlers;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.resources.TVSeries;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static bg.uni.sofia.fmi.imdb.handlers.Constants.*;

public class TVSeriesHandler extends MovieAbstractHandler {

    private String tvSeriesName;
    private String seasonValue;

    public TVSeriesHandler(String tvSeriesName, String seasonValue) {
        this.tvSeriesName = tvSeriesName;
        this.seasonValue = seasonValue;
    }

    public List<TVSeries> getTVSeriesList() throws IOException, MovieSearchException {
        if (seasonValue.length() > 2) {
            throw new MovieSearchException(INVALID_SEASON_VAL);
        }
        JSONObject jsonObject = getTVSeriesJSON();
        if (jsonObject.size() == 2) {
            throw  new MovieSearchException(SERIES_NOT_FOUND);
        }
        String episodes = jsonObject.get(EPISODES).toString();
        List<TVSeries> tvSeriesList = new ArrayList<>();
        char currentChar;
        int episodeLength = episodes.length();
        String episode = "";
        for (int i = 2; i < episodeLength; i++) {
            currentChar = episodes.charAt(i);
            if (currentChar == '}') {
                TVSeries tvSeries = new TVSeries();
                tvSeries.populateTVSeriesFields(episode);
                tvSeriesList.add(tvSeries);
                episode = "";
                i += 2;
            } else {
                episode += currentChar;
            }
        }
        return tvSeriesList;
    }

    private JSONObject getTVSeriesJSON() throws IOException {
        String query = String.format
                (OMDB_API_URL + SEASON_FORMAT,
                        URLEncoder.encode(getNewName(tvSeriesName), CHARSET),
                        URLEncoder.encode(seasonValue, CHARSET));
        URLConnection connection = new URL(query).openConnection();
        InputStream movieDescriptionStream = connection.getInputStream();
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        JSONObject jsonObject = new JSONObject();
        try (InputStreamReader inputStreamReader = new InputStreamReader(movieDescriptionStream, CHARSET)) {
            jsonObject = (JSONObject) jsonParser.parse
                    (inputStreamReader);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        movieDescriptionStream.close();
        return jsonObject;
    }
}
