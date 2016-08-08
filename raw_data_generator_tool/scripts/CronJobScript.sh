#!/bin/bash
export PROPERTYFILE=/home/IMPETUS/omprakash.soni/DataGenerator/datagenerator.properties
export JAR_PATH=/home/IMPETUS/omprakash.soni/DataGenerator/movielensdata.jar
scriptHome=/home/IMPETUS/omprakash.soni/scripts
dataFileLocation=/home/IMPETUS/omprakash.soni/DataGenerator/data
currentDate=$(date +"%d-%m-%Y")
export userFile=$dataFileLocation/USERS_$currentDate
export movieFile=$dataFileLocation/MOVIES_$currentDate
export ratingFile=$dataFileLocation/RATINGS_$currentDate
export userHdfsLocation=/user/hdfs/movie_lens_data/users
export movieHdfsLocation=/user/hdfs/movie_lens_data/movies
export ratingHdfsLocation=/user/hdfs/movie_lens_data/ratings

createAndUploadUsers (){
	sh $scriptHome/CreateUsers.sh;
	if [ -f "$userFile" ]
		then
			sh $scriptHome/uploadUser.sh;
	else    
		echo "Could not load User file because file [$userFile] not found."
	fi
}

createAndUploadMovies(){
	sh $scriptHome/ReleaseMovies.sh;
	if [ -f "$movieFile" ]
		then
			sh $scriptHome/uploadMovie.sh;
	else    
		echo "Could not load movie file because file [$movieFile] not found."
	fi
}

createAndUploadRatings(){
	sh $scriptHome/RateMovies.sh;
	if [ -f "$ratingFile" ]
		then
			sh $scriptHome/uploadRating.sh;
	else    
		echo "Could not load rating file because file [$ratingFile] not found."
	fi
}

cleanDirectory(){
	sh $scriptHome/CleanData.sh;
}

pushUserDataToHiveViaPig(){
	sh $scriptHome/UserStagingInitiator.sh;
}

pushMovieDataToHiveViaPig(){
	sh $scriptHome/MovieStagingInitiator.sh;
}

pushRatingDataToHiveViaPig(){
	sh $scriptHome/RatingStagingInitiator.sh;
}

moveHdfsMainDataToBkpDirectory(){
	sh $scriptHome/HdfsDataBkp.sh;
}

DAYOFWEEK=$(date +"%u")
if [ "${DAYOFWEEK}" -eq 3 ];  
	then    
		createAndUploadUsers;
fi

#Upload Movies
createAndUploadMovies;

#Upload Ratings
createAndUploadRatings;

#Clean Data Directory
cleanDirectory;

#Push Movie Data to Hive
pushMovieDataToHiveViaPig;

#Push User Data to Hive
if [ "${DAYOFWEEK}" -eq 3 ];  
	then    
		pushUserDataToHiveViaPig;
fi
#Push Rating Data to Hive
pushRatingDataToHiveViaPig;

#Move Main Data to Bkp
moveHdfsMainDataToBkpDirectory;
