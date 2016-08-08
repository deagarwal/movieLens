package movie.lens.data.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import movie.lens.data.models.User;
import movie.lens.data.util.MovieLensConstants;
import movie.lens.data.util.MovieUtil;

/**
 * This will create a number of user.
 * Number of users need to be created is defined in the property file.
 */
public class UserCreator {

    private static final Logger LOGGER = Logger.getLogger(UserCreator.class);
    private static Properties props = null;

    /**
     * private default constructor.
     */
    private UserCreator() {

    }

    /** @param args
     *            runtime arguments for user creator. 
     */
    public static void process(String[] args) {
        if (args.length > MovieLensConstants.ONE) {
            LOGGER.info("Started.....");
            props = new Properties();
            MovieUtil.loadProperties(props, args[MovieLensConstants.ONE]);
            createUsers(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
            updateProperties(args[MovieLensConstants.ONE]);
            LOGGER.info("Completed.....");
        } else {
            LOGGER.error("User file creation failed becasue file is not provided.");
        }
    }

    /** 
     * Method will create a file and will write information of a number of new users.
     * @param outputFileLocation
     *         output user file location.
     */
    public static void createUsers(String outputFileLocation) {
        User user = null;
        int userId;
        File file = new File(MovieUtil.getUserFileName(outputFileLocation));
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
            int count = Integer.parseInt(props.getProperty(MovieLensConstants.USERCREATIONCOUNT));
            int endUserId = Integer.parseInt(props.getProperty(MovieLensConstants.ENDUSERID));
            MovieUtil.setUserId(new AtomicInteger(endUserId));

            for (int i = endUserId + MovieLensConstants.ONE; i <= endUserId + count; i++) {
                user = new User();
                userId = MovieUtil.getNextUserId();
                user.setUserId(userId);
                user.setName(MovieUtil.getUserName(userId));
                user.setGender(MovieUtil.getRandomGender());
                user.setAge(MovieUtil.getRandomAge());
                user.setOccupation(MovieUtil.getRandomOccupation());
                user.setPincode(MovieUtil.getRandomPincode());
                fileWriter.write(user.toString());
                fileWriter.write(MovieLensConstants.NEWLINE);
            }
        } catch (Exception e) {
            LOGGER.error("Exception coccured in creating user. Reason [" + e.getMessage() + "]");
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    LOGGER.error("Exception coccured in closing writer. Reason [" + e.getMessage() + "]");
                }
            }
        }
    }

    /** 
     * Method will update last user id to the property file.
     * @param fileLocation
     *         property file path.
     */
    private static void updateProperties(String fileLocation) {
        File file = new File(fileLocation);
        FileWriter fWriter = null;
        try {
            props.setProperty(MovieLensConstants.ENDUSERID, MovieUtil.getUserId().toString());
            fWriter = new FileWriter(file, false);
            props.store(fWriter, null);
        } catch (Exception e) {
            LOGGER.error("Error occured while updating last userId. Reason [" + e.getMessage() + "]");
        } finally {
            if (fWriter != null) {
                try {
                    fWriter.close();
                } catch (IOException e) {
                    LOGGER.error("Error occured while closing writer stream. Reason [" + e.getMessage() + "]");
                }
            }
        }
    }
}
