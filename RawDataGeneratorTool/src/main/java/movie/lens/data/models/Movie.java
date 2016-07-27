package movie.lens.data.models;

import movie.lens.data.util.MovieLensConstants;

public class Movie {

    private int movieId;
    private String title;
    private String geners;
    private String directorName;
    
    public Movie(int movieId,String title,String geners,String directorName){
    	this.movieId = movieId;
    	this.title = title;
    	this.geners = geners;
    	this.directorName = directorName;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param movieName the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the geners
     */
    public String getGeners() {
        return geners;
    }
    /**
     * @param geners the geners to set
     */
    public void setGeners(String geners) {
        this.geners = geners;
    }
    
    /**
	 * @return the directorName
	 */
	public String getDirectorName() {
		return directorName;
	}

	/**
	 * @param directorName the directorName to set
	 */
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	@Override
    public String toString(){
    	
		StringBuilder mDetails = new StringBuilder();
    	mDetails.append(movieId);
    	mDetails.append(MovieLensConstants.COMMA);
    	mDetails.append(title);
    	mDetails.append(MovieLensConstants.COMMA);
    	mDetails.append(geners);
    	mDetails.append(MovieLensConstants.COMMA);
    	mDetails.append(directorName);
    	
		return mDetails.toString();
    	
    }
}
