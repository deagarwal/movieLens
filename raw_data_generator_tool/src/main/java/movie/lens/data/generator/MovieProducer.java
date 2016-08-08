package movie.lens.data.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import movie.lens.data.models.Movie;
import movie.lens.data.util.MovieLensConstants;
import movie.lens.data.util.MovieUtil;

/**
 * This class creates a MOVIES_<Current Date> file.
 * Each line of the file will contain comma separated information of a movie.
 * Number of movies need to be created is determined from the property file.
 */
public class MovieProducer {

    private static final Logger LOGGER = Logger.getLogger(MovieProducer.class);
    private static Properties props = null;

    /**
     * private default constructor.
     */
    private MovieProducer() {

    }
    
    /** @param args
     *            runtime arguments for movie producer. 
     */
    public static void process(String[] args) {
        try {
            if (args.length > MovieLensConstants.ONE) {
                LOGGER.info("Movies release started....");
                init(args[MovieLensConstants.ONE]);
                releaseMovies(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
                destroy(args[MovieLensConstants.ONE]);
                LOGGER.info("Movies are released....");
            } else {
                LOGGER.error("Atleast file name is required.");
            }
        } catch (Exception e) {
            LOGGER.error("Movie releasing process is failed. Reason [" + e.getMessage() + "]");
        }

    }

    /**This method will take the property file path and will initialize
     * the properties which movie producer will need at run time. 
     * @param filePath
     *            property file path.
     */
    private static void init(String filePath) {
        try {
            props = new Properties();
            MovieUtil.loadProperties(props, filePath);
            String line = props.getProperty(MovieLensConstants.LASTMOVIEID);
            if (line != null && !"".equals(line)) {
                int lastMoiveId = Integer.parseInt(line);
                MovieUtil.setMovieId(new AtomicInteger(lastMoiveId));
            }
        } catch (Exception e) {
            LOGGER.error("Last Movie Id reading process failed. Reason [" + e.getMessage() + "]");
        }
    }
    
    /**It produces the movies and writes to the file. 
     * @param outFileLocation
     *            output file path.
     */
    public static void releaseMovies(String outFileLocation) {
        String finalOutputFileLoc = MovieUtil.getMovieFileName(outFileLocation);
        props.setProperty(MovieLensConstants.LASTMOVIEFILENAME, finalOutputFileLoc);
        File file = new File(MovieUtil.getMovieFileName(outFileLocation));
        FileWriter fW = null;
        Movie m = null;

        try {
            int movieCount = Integer.parseInt(props.getProperty(MovieLensConstants.MOVIECREATIONCOUNT));
            fW = new FileWriter(file, false);
            for (int i = 0; i < movieCount; i++) {
                int mId = MovieUtil.getNextMovieId();
                String title = MovieUtil.getMovieTitle(mId);
                String generes = MovieUtil.getRandomGeneres();
                String directorName = MovieUtil.getRandomDirector();
                m = new Movie(mId, title, generes, directorName);
                fW.write(m.toString());
                fW.write(MovieLensConstants.NEWLINE);
            }
        } catch (Exception e) {
            LOGGER.error("Output file generation failed. Reason [" + e.getMessage() + "]");
        } finally {
            if (fW != null) {
                try {
                    fW.close();
                } catch (IOException e) {
                    LOGGER.error("Output file closing failed. Reason [" + e.getMessage() + "]");
                }
            }
        }

    }

    /**It writes last movie id and output file complete path to the 
     * property file. 
     * @param fileName
     *            property file path.
     */
    public static void destroy(String fileName) {
        File file = new File(fileName);
        FileWriter fWriter = null;
        try {
            fWriter = new FileWriter(file, false);
            props.setProperty(MovieLensConstants.LASTMOVIEID, MovieUtil.getMovieId().toString());
            props.store(fWriter, null);
        } catch (Exception e) {
            LOGGER.error("Last Movie Id writing process failed. Reason [" + e.getMessage() + "]");
        } finally {
            if (fWriter != null) {
                try {
                    fWriter.close();
                } catch (IOException e) {
                    LOGGER.error("file closing failed. Reason [" + e.getMessage() + "]");
                }
            }
        }
    }
}
