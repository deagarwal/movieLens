package movie.lens.data.generator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import movie.lens.data.models.Rating;
import movie.lens.data.util.MovieLensConstants;
import movie.lens.data.util.MovieUtil;

/**
 * This method creates a file which will have rating data.
 * This will pick up each users and will rate the movie.
 * Movies will be picked from last movie file generated.
 * Movie Producer utility must be executed before this.
 * Based on user id and geners of the movie it will randomly generate a rating.
 */
public class RatingDataProducer {

    private static final Logger LOGGER = Logger.getLogger(RatingDataProducer.class);
    private static Properties props = null;

    /**
     * private default constructor.
     */
    private RatingDataProducer() {

    }

    /** @param args
     *            runtime arguments for rating data producer. 
     */
    public static void process(String[] args) {
        if (args.length > MovieLensConstants.ONE) {
            props = new Properties();
            MovieUtil.loadProperties(props, args[MovieLensConstants.ONE]);
            LOGGER.info("Rating file generation started....");
            createRatings(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
            LOGGER.info("Rating file generation ends....");
        }
    }

    /**
     * This will create rating file with its own logic.
     * Will skip those movies rating which has id as a multiple of 1000.
     * Will not allow to rate those users whose id is multiple of 1000.
     * @param outputFileLocation
     *            output rating file path. 
     */
    public static void createRatings(String outputFileLocation) {
        FileReader movieFileReader = null;
        LineNumberReader lNReader = null;
        FileWriter ratingFileWriter = null;
        String[] movieComponents = null;
        Rating rating = null;
        int movieId;
        String generes = null;
        try {
            int endUrerId = Integer.parseInt(props.getProperty(MovieLensConstants.ENDUSERID));
            LOGGER.info("Total Number of Users are [" + endUrerId + "]");

            File ratingFIle = new File(MovieUtil.getRatingFileName(outputFileLocation));
            ratingFileWriter = new FileWriter(ratingFIle, false);

            String lastMovieFile = props.getProperty(MovieLensConstants.LASTMOVIEFILENAME);
            File movieFile = new File(lastMovieFile);
            movieFileReader = new FileReader(movieFile);
            lNReader = new LineNumberReader(movieFileReader);
            String line = lNReader.readLine();
            int movieCounter = MovieLensConstants.ZERO;
            while (line != null) {
                movieComponents = line.split(MovieLensConstants.COMMA);
                if (movieComponents.length > MovieLensConstants.TWO) {
                    movieCounter++;
                    if (movieCounter % MovieLensConstants.THOUSAND != MovieLensConstants.ZERO) {
                        movieId = Integer.parseInt(movieComponents[MovieLensConstants.ZERO]);
                        generes = movieComponents[MovieLensConstants.TWO];
                        for (int i = 1; i <= endUrerId; i++) {
                            if (i % MovieLensConstants.THOUSAND != MovieLensConstants.ZERO) {
                                rating = new Rating();
                                rating.setUserId(i);
                                rating.setMovieId(movieId);
                                rating.setRating(MovieUtil.getRandomRating(i, movieId, generes));
                                rating.setTimestamp(MovieUtil.getCurrentTimeStamp());
                                ratingFileWriter.write(rating.toString());
                                ratingFileWriter.write(MovieLensConstants.NEWLINE);
                            }
                        }
                    }
                }
                line = lNReader.readLine();
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured creating rating file. Reason [" + e.getMessage() + "]");
        } finally {
            if (movieFileReader != null) {
                try {
                    movieFileReader.close();
                } catch (IOException e) {
                    LOGGER.error("Exception occured during closing of file reader. Reason [" + e.getMessage() + "]");
                }
            }

            if (ratingFileWriter != null) {
                try {
                    ratingFileWriter.close();
                } catch (IOException e) {
                    LOGGER.error("Exception occured during closing of file writer. Reason [" + e.getMessage() + "]");
                }
            }
        }
    }
}
