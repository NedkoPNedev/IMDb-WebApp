package bg.uni.sofia.fmi.imdb.resources;

import java.util.Map;

public class Movie {
    private String Metascore;
    private String BoxOffice;
    private String Website;
    private String imdbRating;
    private String imdbVotes;
    private String Ratings;
    private String Runtime;
    private String Language;
    private String Rated;
    private String Production;
    private String Released;
    private String imdbID;
    private String Plot;
    private String Director;
    private String Title;
    private String Actors;
    private String Response;
    private String Type;
    private String Awards;
    private String DVD;
    private String Year;
    private String Poster;
    private String Country;
    private String Genre;
    private String Writer;
    private String Error;

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getWebsite() {
        return Website;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getRatings() {
        return Ratings;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getLanguage() {
        return Language;
    }

    public String getRated() {
        return Rated;
    }

    public String getProduction() {
        return Production;
    }

    public String getReleased() {
        return Released;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPlot() {
        return Plot;
    }

    public String getDirector() {
        return Director;
    }

    public String getTitle() {
        return Title;
    }

    public String getActors() {
        return Actors;
    }

    public String getResponse() {
        return Response;
    }

    public String getType() {
        return Type;
    }

    public String getAwards() {
        return Awards;
    }

    public String getDVD() {
        return DVD;
    }

    public String getYear() {
        return Year;
    }

    public String getPoster() {
        return Poster;
    }

    public String getCountry() {
        return Country;
    }

    public String getGenre() {
        return Genre;
    }

    public String getWriter() {
        return Writer;
    }

    public String getError() {
        return Error;
    }

    public void populateMovieFields(String[] fields, Map<String, Object> movieMap) {
        for (String field: fields) {
            field = getStringWithoutSpaces(field);
            switch (field.toLowerCase()){
                case "metascore" : setMetascore(String.valueOf(movieMap.get("Metascore"))); break;
                case "boxoffice" : setBoxOffice(String.valueOf(movieMap.get("BoxOffice"))); break;
                case "website" : setWebsite(String.valueOf(movieMap.get("Website"))); break;
                case "imdbrating" : setImdbRating(String.valueOf(movieMap.get("imdbRating"))); break;
                case "imdbvotes" : setImdbVotes(String.valueOf(movieMap.get("imdbVotes"))); break;
                case "ratings" : setRatings(String.valueOf(movieMap.get("Ratings"))); break;
                case "runtime" : setRuntime(String.valueOf(movieMap.get("Runtime"))); break;
                case "language" : setLanguage(String.valueOf(movieMap.get("Language"))); break;
                case "rated" : setRated(String.valueOf(movieMap.get("Rated"))); break;
                case "production" : setProduction(String.valueOf(movieMap.get("Production"))); break;
                case "released" : setReleased(String.valueOf(movieMap.get("Released"))); break;
                case "imdbid" : setImdbID(String.valueOf(movieMap.get("imdbID"))); break;
                case "plot" : setPlot(String.valueOf(movieMap.get("Plot"))); break;
                case "director" : setDirector(String.valueOf(movieMap.get("Director"))); break;
                case "title" : setTitle(String.valueOf(movieMap.get("Title"))); break;
                case "actors" : setActors(String.valueOf(movieMap.get("Actors"))); break;
                case "response" : setResponse(String.valueOf(movieMap.get("Response"))); break;
                case "type" : setType(String.valueOf(movieMap.get("Type"))); break;
                case "awards" : setAwards(String.valueOf(movieMap.get("Awards"))); break;
                case "dvd" : setDVD(String.valueOf(movieMap.get("DVD"))); break;
                case "year" : setYear(String.valueOf(movieMap.get("Year"))); break;
                case "poster" : setPoster(String.valueOf(movieMap.get("Poster"))); break;
                case "country" : setCountry(String.valueOf(movieMap.get("Country"))); break;
                case "genre" : setGenre(String.valueOf(movieMap.get("Genre"))); break;
                case "writer" : setWriter(String.valueOf(movieMap.get("Writer"))); break;
                default: setError("No fields checked!");
            }
        }
    }

    private String getStringWithoutSpaces(String field) {
        String newField="";
        int len = field.length();
        char currentChar;
        for (int i  = 0; i < len; i++) {
            currentChar = field.charAt(i);
            newField += (currentChar != ' ')? currentChar : "";
        }
        return newField;
    }
}
