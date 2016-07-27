#!/bin/bash
echo "Going to initiate User data Staging and population to Hive."
pig -x mapreduce -useHCatalog -param CurrentDate=$(date +"%d-%m-%Y") -f hdfs://EETeamJ1/user/pig/pig-scripts/UserDataStaging.pig
echo "Completed User data Staging and population to Hive."
