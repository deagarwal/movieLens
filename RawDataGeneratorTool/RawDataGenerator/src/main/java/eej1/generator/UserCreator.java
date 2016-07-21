package eej1.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import eej1.models.User;
import eej1.util.MovieLensConstants;
import eej1.util.MovieUtil;

public class UserCreator {
	
	private static Properties props = null;
	
	public static void main(String[] args) {
		if(args.length>MovieLensConstants.ZERO){
			System.out.println("Started.....");
			props = new Properties();
			MovieUtil.loadProperties(props, args[MovieLensConstants.ZERO]);
			createUsers(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
			updateProperties(args[MovieLensConstants.ZERO]);
			System.out.println("Completed.....");
		} else {
			System.out.println("User file creation failed becasue file is not provided.");
		}
	}
	
	public static void createUsers(String outputFileLocation){
		User user = null;
		int userId;
		File file = new File(MovieUtil.getUserFileName(outputFileLocation));
		FileWriter fileWriter = null;
		try{
			fileWriter = new FileWriter(file,false);
			int count = Integer.parseInt(props.getProperty(MovieLensConstants.USERCREATIONCOUNT));
			int endUserId = Integer.parseInt(props.getProperty(MovieLensConstants.ENDUSERID));
			MovieUtil.setUserId(new AtomicInteger(endUserId));
			
			for(int i = endUserId+ MovieLensConstants.ONE;i<=endUserId+count;i++){
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
		} catch(Exception e){
			System.out.println("Exception coccured in creating user. Reason ["+e.getMessage()+"]");
		} finally{
			if(fileWriter!=null){
				try {
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Exception coccured in closing writer. Reason ["+e.getMessage()+"]");
				}
			}
		}
	}
	
	private static void updateProperties(String fileLocation){
		File file = new File(fileLocation);
		FileWriter fWriter = null;
		try{
			props.setProperty(MovieLensConstants.ENDUSERID, MovieUtil.getUserId().toString());
			fWriter = new FileWriter(file,false);
			props.store(fWriter, null);
		} catch(Exception e){
			System.out.println("Error occured while updating last userId. Reason ["+e.getMessage()+"]");
		} finally {
			if(fWriter!=null){
				try {
					fWriter.close();
				} catch (IOException e) {
					System.out.println("Error occured while closing writer stream. Reason ["+e.getMessage()+"]");
				}
			}
		}
	}
}
