package movie.lens.data.generator;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;

import movie.lens.data.util.MovieLensConstants;
import movie.lens.data.util.MovieUtil;

/** This class will read the output data directory from the property file and deletes all the files from there. */
public class DirectoryCleaner {

    private static final Logger LOGGER = Logger.getLogger(DirectoryCleaner.class);
    private static Properties props = null;

    /** private default constructor. */
    private DirectoryCleaner() {

    }

    /** @param args
     *            runtime arguments for directory cleaner. */
    public static void process(String[] args) {
        if (args.length > MovieLensConstants.ONE) {
            props = new Properties();
            MovieUtil.loadProperties(props, args[MovieLensConstants.ONE]);
            File file = new File(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                boolean result = false;
                for (File f : files) {
                    result = f.delete();
                    LOGGER.info("File [" + f.getName() + "] is deleted [" + result + "]");
                }
                LOGGER.info("Directory [" + props.getProperty(MovieLensConstants.OUTPUTDATALOCATION) + "] is cleaned.");
            } else {
                LOGGER.error("Directory [" + props.getProperty(MovieLensConstants.OUTPUTDATALOCATION) + "] is not valid directory.");
            }
        }
    }
}