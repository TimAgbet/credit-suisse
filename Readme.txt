Run Tests and Build:
To run tests for this application, please use gradle:
The following command:
gradle test

Create a jar and run it:
gradle build && java -jar build/libs/logprocessor-0.0.1-SNAPSHOT.jar <location of json file>
i.e.
gradle build && java -jar build/libs/logprocessor-0.0.1-SNAPSHOT.jar /Users/timothy/work/logprocessor/sampleFile.json

The database will be located one directory up.