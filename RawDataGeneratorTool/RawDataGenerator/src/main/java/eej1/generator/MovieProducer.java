package eej1.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import eej1.models.Movie;
import eej1.util.MovieLensConstants;
import eej1.util.MovieUtil;

public class MovieProducer {
	
	private static Properties props = null;
	
	public static void main(String[] args) {
		try{
			if(args.length>MovieLensConstants.ZERO){
				System.out.println("Movies release started....");
				init(args[MovieLensConstants.ZERO]);
				releaseMovies(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
				destroy(args[MovieLensConstants.ZERO]);
				System.out.println("Movies are released....");
			} else {
				System.out.println("Atleast file name is required.");
			}
		} catch(Exception e){
			System.out.println("Movie releasing process is failed. Reason ["+e.getMessage()+"]");
		}
		
	}
	
	private static void init(String filePath){
		try {
			props = new Properties();
			MovieUtil.loadProperties(props, filePath);
			String line = props.getProperty(MovieLensConstants.LASTMOVIEID);
			if(line!=null && !"".equals(line)){
				int lastMoiveId = Integer.parseInt(line);
				MovieUtil.setMovieId(new AtomicInteger(lastMoiveId));
			}
		} catch(Exception e){
			System.out.println("Last Movie Id reading process failed. Reason ["+e.getMessage()+"]");
		}
	}
	
	public static void releaseMovies(String outFileLocation){
		String finalOutputFileLoc = MovieUtil.getMovieFileName(outFileLocation);
		props.setProperty(MovieLensConstants.LASTMOVIEFILENAME, finalOutputFileLoc);
		File file = new File(MovieUtil.getMovieFileName(outFileLocation));
		FileWriter fW = null;
		Movie m = null;
		
		
		try{
			int movieCount = Integer.parseInt(props.getProperty(MovieLensConstants.MOVIECREATIONCOUNT));
			fW = new FileWriter(file,false);
			for(int i =0;i<movieCount;i++){
				int mId = MovieUtil.getNextMovieId();
				String title = MovieUtil.getMovieTitle(mId);
				String generes = MovieUtil.getRandomGeneres();
				String directorName = MovieUtil.getRandomDirector();
				m = new Movie(mId,title,generes,directorName);
				fW.write(m.toString());
				fW.write(MovieLensConstants.NEWLINE);
			}
		} catch(Exception e){e.printStackTrace();
			System.out.println("Output file generation failed. Reason ["+e.getMessage()+"]");
		} finally {
			if(fW!=null){
				try {
					fW.close();
				} catch (IOException e) {
					System.out.println("Output file closing failed. Reason ["+e.getMessage()+"]");
				}
			}
		}
		
	}
	
	public static void destroy(String fileName){
		File file = new File(fileName);
		FileWriter fWriter = null;
		try{
			fWriter = new FileWriter(file,false);
			props.setProperty(MovieLensConstants.LASTMOVIEID, MovieUtil.getMovieId().toString());
			props.store(fWriter, null);
		} catch(Exception e){
			System.out.println("Last Movie Id writing process failed. Reason ["+e.getMessage()+"]");
		} finally {
			if(fWriter!=null){
				try {
					fWriter.close();
				} catch (IOException e) {
					System.out.println("file closing failed. Reason ["+e.getMessage()+"]");
				}
			}
		}
	}
}
