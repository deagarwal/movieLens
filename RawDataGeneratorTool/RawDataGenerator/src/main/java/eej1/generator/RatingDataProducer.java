package eej1.generator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Properties;

import eej1.models.Rating;
import eej1.util.MovieLensConstants;
import eej1.util.MovieUtil;

public class RatingDataProducer {

	private static Properties props = null;
	
	public static void main(String[] args) {
		if(args.length>MovieLensConstants.ZERO){
			props = new Properties();
			MovieUtil.loadProperties(props, args[MovieLensConstants.ZERO]);
			System.out.println("Rating file generation started....");
			createRatings(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
			System.out.println("Rating file generation ends....");
		}
	}
	
	public static void createRatings(String outputFileLocation){
		FileReader movieFileReader = null;
		LineNumberReader lNReader = null;
		FileWriter ratingFileWriter = null;
		String[] movieComponents = null;
		Rating rating = null;
		int movieId;
		String generes = null;
		try{
			int endUrerId = Integer.parseInt(props.getProperty(MovieLensConstants.ENDUSERID));
			System.out.println("Total Number of Users are ["+endUrerId+"]");
			
			File ratingFIle = new File(MovieUtil.getRatingFileName(outputFileLocation));
			ratingFileWriter = new FileWriter(ratingFIle,false);
			
			String lastMovieFile = props.getProperty(MovieLensConstants.LASTMOVIEFILENAME);
			File movieFile = new File(lastMovieFile);
			movieFileReader = new FileReader(movieFile);
			lNReader = new LineNumberReader(movieFileReader);
			String line = lNReader.readLine();
			int movieCounter = MovieLensConstants.ZERO;
			while(line!= null){
				movieComponents = line.split(MovieLensConstants.COMMA);
				if(movieComponents.length>MovieLensConstants.TWO){
					movieCounter++;
					if(movieCounter%MovieLensConstants.THOUSAND!=MovieLensConstants.ZERO){
						movieId = Integer.parseInt(movieComponents[MovieLensConstants.ZERO]);
						generes = movieComponents[MovieLensConstants.TWO];
						for(int i = 1; i<=endUrerId; i++){
							if(i%MovieLensConstants.THOUSAND!=MovieLensConstants.ZERO){
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
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception occured creating rating file. Reason ["+e.getMessage()+"]");
		} finally{
			if(movieFileReader!=null){
				try {
					movieFileReader.close();
				} catch (IOException e) {
					System.out.println("Exception occured during closing of file reader. Reason ["+e.getMessage()+"]");
				}
			}
			
			if(ratingFileWriter!=null){
				try {
					ratingFileWriter.close();
				} catch (IOException e) {
					System.out.println("Exception occured during closing of file writer. Reason ["+e.getMessage()+"]");
				}
			}
		}
	}
}
