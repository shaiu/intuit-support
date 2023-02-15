### Intuit support aggregation application

This is developed using spring boot.

To run this code you will need:

1. run a http server to serve the cases from different CRMs (because they are fake for now)
   1. you do this with a simple npm package (json-server) - https://www.npmjs.com/package/json-server
   2. after you install run: `json-server --watch db.json --host 127.0.0.1`

2. Now you can build this project to produce a jar. 
   1. run `./gradlew build`
   2. execute the program - `java -jar ./build/libs/intuit-support-0.0.1-SNAPSHOT.jar`

3. now you can query the REST API as follow:
   `curl --location --request GET 'http://localhost:8080/aggregation'`