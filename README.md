# EasyGen
Welcome to the world of randomness! EasyGen is a very simple DSL to generate arbitrary amount of string data with ease.All you need 
to do is, write your DSL into a file and pass file path to this program.Currently the generated data can be written to file or standard output. 

### How to build
From the project folder run bellow commands to build and run the project:
```
mvn clean package
java -jar Generator-1.0-SNAPSHOT-jar-with-dependencies.jar YOUR_DSL_FILE_PATH
```
### Quick start
The following example create a CSV file that has 5 headers ( UserEmailAddress , UserIP , DestinationIP , VisitedUrl , Date ) and store 100 records of random generated data to a file ( /tmp/output.csv ) :
```
DEFINE( IP AS [0..255].[0..255].[0..255].[0..255] )
DEFINE( URL AS [http|https]:// REP( [a..z] , 3 , 20 ).[com|org|net] )
DEFINE( Email AS REP([a..z],1,20)@[gmail|yahoo|msn].com )
DEFINE( COMMA AS ' , ' )

FILE (
	'UserEmailAddress , UserIP , DestinationIP , VisitedUrl , Date' NEW_LINE()
	REP(
		Email COMMA IP COMMA IP COMMA URL COMMA DATE('dd/MM/yyyy H:m:s') NEW_LINE()	
	, 100 )
, /tmp/output.csv)
```

## DSL Syntax
* [Ranges](https://github.com/mostafa-asg/EasyGen/wiki/Ranges)
    * [LongRange](https://github.com/mostafa-asg/EasyGen/wiki/LongRange)
    * [CharRange](https://github.com/mostafa-asg/EasyGen/wiki/CharRange)
    * [Range Unions](https://github.com/mostafa-asg/EasyGen/wiki/Range-Unions)
    * [Range Complements](https://github.com/mostafa-asg/EasyGen/wiki/Range-Complements)
    * [StringRange](https://github.com/mostafa-asg/EasyGen/wiki/StringRange)
* [Static data](https://github.com/mostafa-asg/EasyGen/wiki/Static-data)    
* [Repetition](https://github.com/mostafa-asg/EasyGen/wiki/Repetition)     
