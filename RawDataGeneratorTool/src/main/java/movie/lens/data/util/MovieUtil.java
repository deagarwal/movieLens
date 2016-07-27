package movie.lens.data.util;

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

import org.apache.log4j.Logger;

import movie.lens.data.models.Director;
import movie.lens.data.models.Gender;
import movie.lens.data.models.Generes;
import movie.lens.data.models.Profession;
import movie.lens.data.models.RatingMetric;

/**
 * This class will provide all the utility method which this movie lens data generator may need.
 */
public class MovieUtil {

    private static final Logger LOGGER = Logger.getLogger(MovieUtil.class);
    public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static AtomicInteger movieId;
    private static AtomicInteger userId = new AtomicInteger(MovieLensConstants.ZERO);
    private static AtomicInteger startUserId;
    private static AtomicInteger endUserId;
    private static Random ratingRandomizer = new Random();
    private static DecimalFormat onePrecisonFormat = new DecimalFormat("#.#");

    
    /**
     * private default constructor.
     */
    private MovieUtil() {

    }

    /**
     * This will provide random geners from a defined set of generses.
     */
    public static String getRandomGeneres() {
        int randomGenId = (int) (Math.random() * MovieLensConstants.GENERES_COUNT);
        return Generes.getGenByGenId(randomGenId).name();
    }

    /**
     * This will provide random gender from a defined set of genders.
     */
    public static String getRandomGender() {
        int randomGenderIndex = (int) (Math.random() * MovieLensConstants.TWO);
        return Gender.getGender(randomGenderIndex).name();
    }

    /**
     * This will provide random occupation from a defined set of occupations.
     */
    public static String getRandomOccupation() {
        int randomProId = (int) (Math.random() * Profession.values().length);
        return Profession.getProfession(randomProId).name();
    }

    /**
     * This will provide random age between 16 to 45.
     */
    public static int getRandomAge() {
        int age = (int) (Math.random() * MovieLensConstants.MAXIMUM_AGE);
        if (age < MovieLensConstants.MINIMUM_AGE) {
            age = age + MovieLensConstants.MINIMUM_AGE;
        }
        return age;
    }

    /**
     * @param movieId
     *          id of the movie.
     * @return
     *        returns movie title in the format = Sample Movie {movieId} [{current year}]
     */
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

    /**
     * @return
     *        next movie id.
     */
    public static int getNextMovieId() {
        return movieId.incrementAndGet();
    }

    /**
     * @return
     *        current movie id.
     */
    public static AtomicInteger getMovieId() {
        return movieId;
    }

    /** @param movieId
     *            the current movieId to set */
    public static void setMovieId(AtomicInteger movieId) {
        MovieUtil.movieId = movieId;
    }

    /**
     * @return
     *        next user id.
     */
    public static int getNextUserId() {
        return userId.incrementAndGet();
    }

    /**
     * @return
     *        current user id.
     */
    public static AtomicInteger getUserId() {
        return userId;
    }

    /**
     * @param userId
     *        the current user id to set.
     */
    public static void setUserId(AtomicInteger userId) {
        MovieUtil.userId = userId;
    }

    /**
     * @param userId
     *          id of the user.
     * @return
     *        returns user name in the format = Test User {userId}
     */
    public static String getUserName(int userId) {
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(MovieLensConstants.USER_NAME_PREFIX);
        nameBuilder.append(userId);
        return nameBuilder.toString();
    }

    /**
     * @return 
     *        the current date in format dd-MM-yyyy.
     */
    public static String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(MovieLensConstants.DATE_FORMAT);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(MovieLensConstants.GMT));
        return dateFormatGmt.format(currentDate);
    }

    /**
     * @return 
     *        the current timestamp in format yyyy.MM.dd.HH.mm.ss.SSS'Z'.
     */
    public static String getCurrentTimeStamp() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(MovieLensConstants.TIMESTAMP_FORMAT);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(MovieLensConstants.GMT));
        return dateFormatGmt.format(currentDate);
    }

    /**
     * @param directory
     *          output location.
     * @return 
     *        the complete user file path in the format = USERS_{current year}.
     */
    public static String getUserFileName(String directory) {
        StringBuilder sB = new StringBuilder();
        sB.append(directory);
        sB.append(MovieLensConstants.USERS);
        sB.append(MovieLensConstants.UNDERSCORE);
        sB.append(getCurrentDate());
        return sB.toString();
    }

    /**
     * @param directory
     *          output location.
     * @return 
     *        the complete movie file path in the format = MOVIES_{current year}.
     */
    public static String getMovieFileName(String directory) {
        StringBuilder sB = new StringBuilder();
        sB.append(directory);
        sB.append(MovieLensConstants.MOVIES);
        sB.append(MovieLensConstants.UNDERSCORE);
        sB.append(getCurrentDate());
        return sB.toString();
    }

    /**
     * @param directory
     *          output location.
     * @return 
     *        the complete ratings file path in the format = RATINGS_{current year}.
     */
    public static String getRatingFileName(String directory) {
        StringBuilder sB = new StringBuilder();
        sB.append(directory);
        sB.append(MovieLensConstants.RATINGS);
        sB.append(MovieLensConstants.UNDERSCORE);
        sB.append(getCurrentDate());
        return sB.toString();
    }

    /** @return the startUserId */
    public static AtomicInteger getStartUserId() {
        return startUserId;
    }

    /** @param startUserId
     *            the startUserId to set */
    public static void setStartUserId(AtomicInteger startUserId) {
        MovieUtil.startUserId = startUserId;
    }

    /** @return the endUserId */
    public static AtomicInteger getEndUserId() {
        return endUserId;
    }

    /** @param endUserId
     *            the endUserId to set */
    public static void setEndUserId(AtomicInteger endUserId) {
        MovieUtil.endUserId = endUserId;
    }

    /**
     * Method will calculate a random rating based on user id , movie id and its genres.
     * @param userId
     *          user id.
     * @param movieId
     *          movie id.
     * @param geners
     *          geners of the movie.
     * @return 
     *        the complete movie file path in the format = MOVIES_{current year}.
     */
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

    
    /**
     * @param uTName
     *          user taste.
     * @param mFName
     *         whether movie is hit or flop param.
     * @return
     *        rating metric which decides the range of the rating.
     */
    public static String getRatingMetricName(String uTName, String mFName) {
        StringBuilder sB = new StringBuilder();
        sB.append(uTName);
        sB.append(MovieLensConstants.UNDERSCORE);
        sB.append(mFName);
        return sB.toString();
    }

    /**
     * Will read the property file and load to the input container.
     * @param props
     *          properties container in which props will be loaded.
     * @param propFilePath
     *         property file location.
     */
    public static void loadProperties(Properties props, String propFilePath) {
        File file = new File(propFilePath);
        InputStream fIn = null;
        try {
            fIn = new FileInputStream(file);
            props.load(fIn);
        } catch (Exception e) {
            LOGGER.error("Exception occured during loading properties. Reason [" + e.getMessage() + "]");
        } finally {
            if (fIn != null) {
                try {
                    fIn.close();
                } catch (IOException e) {
                    LOGGER.error("Input Stream closing failed. Reason [" + e.getMessage() + "]");
                }
            }
        }
    }

    /**
     * return
     *     random director name from the set of directors.
     */
    public static String getRandomDirector() {
        int directorIndex = (int) (Math.random() * (Director.values().length - 1));
        return Director.getDirector(directorIndex).name();
    }

    /**
     * return
     *     random pincode.
     */
    public static String getRandomPincode() {
        int diff = MovieLensConstants.U_PINCODE - MovieLensConstants.L_PINCODE;
        int randomPincode = MovieLensConstants.L_PINCODE + (int) (Math.random() * diff);
        return String.valueOf(randomPincode);
    }
}