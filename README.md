siEhcacheTestApp
================

This component retrieves data from FAA and USGS and stores the results in a distributed cache.

[Setting this project up.]
1) First pull down siehcacheadapter from github:   git://github.com/cppwfs/siehcacheadapter.git
2) run mvn install for the siehcacheadapter.  This will produce a jar required for the project.
3) Now open the pom.xml for the siEhcacheTestApp
   3a) Search for the comment "Update the system path so that it points to the SIEhCache jar file" in the pom file
   3b) Update the systemPath to reference the jar build by the siehcacheadapter project
4) Update the Spring Context File
   4a) open the src/main/resources/META-INF/spring/integration/spring-integration-context.xml
   4b) Update the spring-integration-ehcacheadapter-2.2.xsd location on the schemaLocation to the location in the siehcacheadapter. 
5) run mvn install

[Running the Application]
1) Now import the siEhcacheTestApp into eclipse using the "Import Maven" Option
2) Now open the org.ehcache.myapp.Main
3) From the Run menu option select "Run Configuration"
4) Create new Launch Configuration
5) Set the heap to -Xmx512m
6) 
