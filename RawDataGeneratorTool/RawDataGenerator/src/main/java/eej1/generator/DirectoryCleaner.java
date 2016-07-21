package eej1.generator;

import java.io.File;
import java.util.Properties;

import eej1.util.MovieLensConstants;
import eej1.util.MovieUtil;

public class DirectoryCleaner {

	private static Properties props = null;
	public static void main(String[] args) {
		if(args.length>MovieLensConstants.ZERO){
			props = new Properties();
			MovieUtil.loadProperties(props, args[MovieLensConstants.ZERO]);
			File file = new File(props.getProperty(MovieLensConstants.OUTPUTDATALOCATION));
			if(file.isDirectory()){
			    File[] files = file.listFiles();
			    for(File f : files){
			        f.delete();
			    }
			    System.out.println("Directory ["+props.getProperty(MovieLensConstants.OUTPUTDATALOCATION)+"] is cleaned.");
			} else {
			    System.out.println("Directory ["+props.getProperty(MovieLensConstants.OUTPUTDATALOCATION)+"] is not valid directory.");
			}
		}
	}
}