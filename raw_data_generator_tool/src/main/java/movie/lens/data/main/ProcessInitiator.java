package movie.lens.data.main;

import movie.lens.data.generator.DirectoryCleaner;
import movie.lens.data.generator.MovieProducer;
import movie.lens.data.generator.RatingDataProducer;
import movie.lens.data.generator.UserCreator;
import movie.lens.data.util.MovieLensConstants;

import org.apache.log4j.Logger;

/**
 * This class will initiate process 
 * based on first argument.
 *
 */
public class ProcessInitiator {
    
    private static final Logger LOGGER = Logger.getLogger(ProcessInitiator.class);
    
    public static void main(String[] args) {
        if(args.length>MovieLensConstants.ZERO){
            String process = args[MovieLensConstants.ZERO];
            switch(process){
                case  MovieLensConstants.MOVIE_RELEASE :{
                    MovieProducer.process(args);
                } break;
                case  MovieLensConstants.RATING_GENERATION :{
                    RatingDataProducer.process(args);
                } break;
                case  MovieLensConstants.USER_CREATION :{
                    UserCreator.process(args);
                } break;
                case  MovieLensConstants.DIRECTORY_CLEANUP :{
                    DirectoryCleaner.process(args);
                } break;
                default :
                    LOGGER.error("Not able to identify process for ["+process+"]");
            }
            
        } else {
            LOGGER.error("Not able to identify process as argument size is zero.");
        }
    }
}
