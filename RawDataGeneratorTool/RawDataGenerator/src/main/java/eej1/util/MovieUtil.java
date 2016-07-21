package eej1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

import eej1.models.Director;
import eej1.models.Gender;
import eej1.models.Generes;
import eej1.models.Profession;
import eej1.models.RatingMetric;

public class MovieUtil {

	public static final int CURRENT_YEAR = Calendar.getInstance().get(
			Calendar.YEAR);
	private static AtomicInteger movieId;
	private static AtomicInteger userId = new AtomicInteger(
			MovieLensConstants.ZERO);
	private static AtomicInteger startUserId;
	private static AtomicInteger endUserId;
	private static Random ratingRandomizer = new Random();
	private static DecimalFormat onePrecisonFormat = new DecimalFormat("#.#");

	public static String getRandomGeneres() {
		int randomGenId = (int) (Math.random() * MovieLensConstants.GENERES_COUNT);
		return Generes.getGenByGenId(randomGenId).name();
	}

	public static String getRandomGender() {
		int randomGenderIndex = (int) (Math.random() * MovieLensConstants.TWO);
		return Gender.getGender(randomGenderIndex).name();
	}
	
	public static String getRandomOccupation(){
		int randomProId = (int) (Math.random() * Profession.values().length);
		return Profession.getProfession(randomProId).name();
	}

	public static int getRandomAge() {
		int age = (int) (Math.random() * MovieLensConstants.MAXIMUM_AGE);
		if (age < MovieLensConstants.MINIMUM_AGE) {
			age = age + MovieLensConstants.MINIMUM_AGE;
		}
		return age;
	}

	public static String getMovieTitle(int movieId) {
		StringBuilder titleB = new StringBuilder();
		titleB.append(MovieLensConstants.MOVIE_TITLE_PREFIX);
		titleB.append(movieId);
		titleB.append(MovieLensConstants.SPACE);
		titleB.append(MovieLensConstants.OPENING_BRACKET);
		titleB.append(CURRENT_YEAR);
		titleB.append(MovieLensConstants.CLOSING_BRACKET);
		return titleB.toString();
	}

	public static int getNextMovieId() {
		return movieId.incrementAndGet();
	}

	/**
	 * @return the movieId
	 */
	public static AtomicInteger getMovieId() {
		return movieId;
	}

	/**
	 * @param movieId
	 *            the movieId to set
	 */
	public static void setMovieId(AtomicInteger movieId) {
		MovieUtil.movieId = movieId;
	}

	public static int getNextUserId() {
		return userId.incrementAndGet();
	}

	/**
	 * @return the movieId
	 */
	public static AtomicInteger getUserId() {
		return userId;
	}
	
	
	/**
	 * @return the movieId
	 */
	public static void setUserId(AtomicInteger userId) {
		MovieUtil.userId = userId;
	}

	public static String getUserName(int userId) {
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(MovieLensConstants.USER_NAME_PREFIX);
		nameBuilder.append(userId);
		return nameBuilder.toString();
	}

	public static String getCurrentDate() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(
				MovieLensConstants.DATE_FORMAT);
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone(MovieLensConstants.GMT));
		return dateFormatGmt.format(currentDate);
	}

	public static String getCurrentTimeStamp() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(
				MovieLensConstants.TIMESTAMP_FORMAT);
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone(MovieLensConstants.GMT));
		return dateFormatGmt.format(currentDate);
	}

	public static String getUserFileName(String directory) {
		StringBuilder sB = new StringBuilder();
		sB.append(directory);
		sB.append(MovieLensConstants.USERS);
		sB.append(MovieLensConstants.UNDERSCORE);
		sB.append(getCurrentDate());
		return sB.toString();
	}

	public static String getMovieFileName(String directory) {
		StringBuilder sB = new StringBuilder();
		sB.append(directory);
		sB.append(MovieLensConstants.MOVIES);
		sB.append(MovieLensConstants.UNDERSCORE);
		sB.append(getCurrentDate());
		return sB.toString();
	}
	
	public static String getRatingFileName(String directory) {
		StringBuilder sB = new StringBuilder();
		sB.append(directory);
		sB.append(MovieLensConstants.RATINGS);
		sB.append(MovieLensConstants.UNDERSCORE);
		sB.append(getCurrentDate());
		return sB.toString();
	}

	/**
	 * @return the startUserId
	 */
	public static AtomicInteger getStartUserId() {
		return startUserId;
	}

	/**
	 * @param startUserId
	 *            the startUserId to set
	 */
	public static void setStartUserId(AtomicInteger startUserId) {
		MovieUtil.startUserId = startUserId;
	}

	/**
	 * @return the endUserId
	 */
	public static AtomicInteger getEndUserId() {
		return endUserId;
	}

	/**
	 * @param endUserId
	 *            the endUserId to set
	 */
	public static void setEndUserId(AtomicInteger endUserId) {
		MovieUtil.endUserId = endUserId;
	}

	public static float getRandomRating(int userId, int movieId, String geners) {

		int value = (userId % MovieLensConstants.TWO + Generes.getGenByGenName(geners).getGenId() % MovieLensConstants.TWO) % MovieLensConstants.TWO;
		RatingMetric userTaste = RatingMetric.getUserTaste(value);
		value = movieId % MovieLensConstants.TWO;
		RatingMetric movieFuture = RatingMetric.getMovieFuture(value);
		
		String mName = getRatingMetricName(userTaste.name(), movieFuture.name());
		RatingMetric finalMetric = RatingMetric.getRatingMetricByrMName(mName);
		
		float randomPoint = ratingRandomizer.nextFloat();
		float diff = finalMetric.getuLimit() - finalMetric.getlLimit();
		float rating = randomPoint * diff + finalMetric.getlLimit();
		
		return Float.parseFloat(onePrecisonFormat.format(rating));
	}

	public static String getRatingMetricName(String uTName, String mFName) {
		StringBuilder sB = new StringBuilder();
		sB.append(uTName);
		sB.append(MovieLensConstants.UNDERSCORE);
		sB.append(mFName);
		return sB.toString();
	}
	
	public static void loadProperties(Properties props,String propFilePath){
		File file = new File(propFilePath);
		InputStream fIn = null;
		try {
			fIn = new FileInputStream(file);
			props.load(fIn);
		} catch(Exception e){
			System.out.println("Exception occured during loading properties. Reason ["+e.getMessage()+"]");
		} finally {
			if(fIn!=null){
				try {
					fIn.close();
				} catch (IOException e) {
					System.out.println("Input Stream closing failed. Reason ["+e.getMessage()+"]");
				}
			}
		}
	}
	
	public static String getRandomDirector(){
		int directorIndex = (int) (Math.random() * (Director.values().length-1));	
		return Director.getDirector(directorIndex).name();
	}
	
	public static String getRandomPincode(){
        int diff = MovieLensConstants.U_PINCODE-MovieLensConstants.L_PINCODE;
        int randomPincode = MovieLensConstants.L_PINCODE + (int)(Math.random() * diff);
        return String.valueOf(randomPincode);
    }
}