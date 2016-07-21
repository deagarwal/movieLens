package eej1.models;

import eej1.util.MovieLensConstants;

public class Rating {
    
    private int userId;
    private int movieId;
    private float rating;
    private String timestamp;
    
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }
    /**
     * @param usedId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * @return the movieId
     */
    public int getMovieId() {
        return movieId;
    }
    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    /**
     * @return the rating
     */
    public float getRating() {
        return rating;
    }
    /**
     * @param rating the rating to set
     */
    public void setRating(float rating) {
        this.rating = rating;
    }
    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString(){
    	StringBuilder rBuilder = new StringBuilder();
    	rBuilder.append(userId);
    	rBuilder.append(MovieLensConstants.COMMA);
    	rBuilder.append(movieId);
    	rBuilder.append(MovieLensConstants.COMMA);
    	rBuilder.append(rating);
    	rBuilder.append(MovieLensConstants.COMMA);
    	rBuilder.append(timestamp);
    	return rBuilder.toString();
    }
    
}
