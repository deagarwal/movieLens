#!/bin/bash
echo "Going to initiate Rating data Staging and population to Hive."
pig -x mapreduce -useHCatalog -param CurrentDate=$(date +"%d-%m-%Y") -f hdfs://EETeamJ1/user/pig/pig-scripts/RatingDataStaging.pig
echo "Completed Rating data Staging and population to Hive."
