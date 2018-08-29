package bg.uni.sofia.fmi.imdb.resources;

public class TVSeries {
    private String Episode;
    private String Released;
    private String imdbID;
    private String Title;
    private String imdbRating;

    public String getEpisode() {
        return Episode;
    }

    public void setEpisode(String episode) {
        Episode = episode;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void populateTVSeriesFields(String episode) {
        String episode2 = episode.substring(1);
        String newEpisode = replace(episode2);
        String[] fields = newEpisode.split(",/");
        for (String field: fields) {
            String[] separator = replace(field).split("/");
            switch(separator[0]){
                case "Episode" : setEpisode(separator[2]); break;
                case "Released" : setReleased(separator[2]); break;
                case "imdbID" : setImdbID(separator[2]); break;
                case "Title" : setTitle(separator[2]); break;
                default: setImdbRating(separator[2]);
            }
        }
    }
    private String replace(String field) {
        String newField = "";
        char currentChar;
        int len;
        len = field.length();
        for (int i = 0; i < len; i++) {
            currentChar = field.charAt(i);
            newField += (currentChar == '"')? '/' : currentChar;
        }
        return newField;
    }
}
