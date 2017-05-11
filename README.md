# EasyGen
Welcome to the world of randomness! EasyGen is a very simple DSL to generate arbitrary amount of string data with ease.All you need 
to do is, write your DSL into a file and pass file path to this program.Currently the generated data can be written to file, standard output or socket. 

### How to build
From the project folder run bellow commands to build and run the project:
```
mvn clean package
java -jar Generator-1.0-SNAPSHOT-jar-with-dependencies.jar YOUR_DSL_FILE_PATH
```
### Quick start
**Example 1:**  
The following example create a CSV file that has 5 headers ( UserEmailAddress , UserIP , DestinationIP , VisitedUrl , Date ) and store 100 records of random generated data to a file ( /tmp/output.csv ) :
```
DEFINE( IP AS [0..255].[0..255].[0..255].[0..255] )
DEFINE( URL AS [http|https]:// REP( [a..z] , 3 , 20 ).[com|org|net] )
DEFINE( Email AS REP([a..z],1,20)@[gmail|yahoo|msn].com )
DEFINE( COMMA AS ' , ' )

FILE (
	'UserEmailAddress , UserIP , DestinationIP , VisitedUrl , Date' NEW_LINE()
	REP(
		Email COMMA IP COMMA IP COMMA URL COMMA DATE() NEW_LINE()	
	, 100 )
, /tmp/output.csv)
```

**Example 2:**  
To output a table like bellow to the console :  
```
--------------------------------------------------------------------------------
|UserID            |Username           |Password           |RegisterDate       |
--------------------------------------------------------------------------------
|1                 |wmOsTk             |dsaThwKnoWePoACcEC |3/29/15 2:26 AM    |
--------------------------------------------------------------------------------
|2                 |xnozxgHao          |QkEwuX             |7/29/82 8:04 AM    |
--------------------------------------------------------------------------------
|3                 |fkKLpkDUj          |NEIXIyshWWukmso    |4/15/02 9:56 PM    |
--------------------------------------------------------------------------------
|4                 |FPIkm              |BbZbliZALcdV       |2/8/98 5:01 AM     |
--------------------------------------------------------------------------------
|5                 |vgFWVMwWJ          |qkBZjHAL           |2/7/12 8:07 PM     |
--------------------------------------------------------------------------------
|6                 |kzTwL              |jpHQdBzrHW         |2/15/11 1:23 AM    |
--------------------------------------------------------------------------------
```
you can write:  
```
DEFINE( DRAW_LINE AS REP('-',80) NEW_LINE() )
DEFINE( ID AS IDENTITY(1,1) )
DEFINE( username AS REP([a..z]+[A..Z],3,18) )
DEFINE( Pwd AS REP([a..z]+[A..Z],3,18) )

CONSOLE(

	DRAW_LINE
	'|' PAD_RIGHT(UserID,18,' ') '|' PAD_RIGHT(Username,19,' ') '|' PAD_RIGHT(Password,19,' ') '|' PAD_RIGHT(RegisterDate,19,' ') '|' NEW_LINE()
	DRAW_LINE

	REP(
		'|' PAD_RIGHT(ID,18,' ') '|' PAD_RIGHT(username,19,' ') '|' PAD_RIGHT(Pwd,19,' ') '|' PAD_RIGHT(DATE(),19,' ') '|' NEW_LINE()
		DRAW_LINE
	, 6)
)
```
**Example 3:**  
To generate 25 random number between -500 to 500, in 5 rows and 5 columns, and output the result to both _console_ and _file_ you can write :  
```
DEFINE( RAND_NUM AS [-500..500] )

FILE (
	CONSOLE (

		REP(
			REP(
				PAD_RIGHT(RAND_NUM,8,' ') TAB()
			, 5 )
			NEW_LINE()
		, 5)
	)
, /tmp/output.txt )
```
The output is something like this :  
```
-367    	-259    	189     	200     	340     	
-295    	-240    	146     	234     	-325    	
-387    	-147    	3       	405     	-458    	
-132    	-369    	363     	-306    	-249    	
265     	-135    	466     	-117    	-250
```

**Example 4:**  
An example of sending the data to a socket.For example to send 100 metrics to [OpenTSDB](http://opentsdb.net/) you can write:  
```
DEFINE( TSD_ADDRESS AS 127.0.0.1:5140 )

REP(

	SOCKET(
		'put sys.cpu.user 149'[1190000000..1209999999] ' ' [10..100].[0..9] ' ' host=webserver PAD_LEFT([0..20],2,'0') ' ' cpu=[0..15] NEW_LINE()
        , TSD_ADDRESS)

, 100 )	
```
It will send metrics to [OpenTSDB](http://opentsdb.net/) that is like :  
```
put sys.cpu.user 1491209684404 87.4 host=webserver08 cpu=4
put sys.cpu.user 1491199717808 99.0 host=webserver08 cpu=1
put sys.cpu.user 1491199318893 33.7 host=webserver09 cpu=14
put sys.cpu.user 1491200493212 30.9 host=webserver07 cpu=12
put sys.cpu.user 1491196496576 70.1 host=webserver10 cpu=10
```
which is valid [OpenTSDB](http://opentsdb.net/) syntax.

## DSL Syntax
* [Ranges](https://github.com/mostafa-asg/EasyGen/wiki/Ranges)
    * [LongRange](https://github.com/mostafa-asg/EasyGen/wiki/LongRange)
    * [CharRange](https://github.com/mostafa-asg/EasyGen/wiki/CharRange)
    * [Range Unions](https://github.com/mostafa-asg/EasyGen/wiki/Range-Unions)
    * [Range Complements](https://github.com/mostafa-asg/EasyGen/wiki/Range-Complements)
    * [StringRange](https://github.com/mostafa-asg/EasyGen/wiki/StringRange)
* [Static data](https://github.com/mostafa-asg/EasyGen/wiki/Static-data)    
* [Repetition](https://github.com/mostafa-asg/EasyGen/wiki/Repetition)     
* [Functions](https://github.com/mostafa-asg/EasyGen/wiki/Functions)
  * [NEW_LINE()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#new_line)
  * [TAB()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#tab)
  * [REP( expression , number )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#rep-expression--number-)
  * [REP( expression , min_number , max_number )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#rep-expression--min_number--max_number-)
  * [TODAY()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#today)
  * [TODAY( pattern )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#today-pattern-)
  * [DATE()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#date)
  * [DATE( startDate )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#date-startdate-)
  * [DATE( startDate , outputPattern )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#date-startdate--outputpattern-)
  * [DATE( startDate , endDate , outputPattern )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#date-startdate--enddate--outputpattern-)
  * [TIMESTAMP()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#timestamp)
  * [TIMESTAMP(from)](https://github.com/mostafa-asg/EasyGen/wiki/Functions#timestampfrom)
  * [TIMESTAMP(from,to](https://github.com/mostafa-asg/EasyGen/wiki/Functions#timestampfromto)
  * [PAD_LEFT( expression , minLength , fillChar )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#pad_left-expression--minlength--fillchar-)
  * [PAD_RIGHT( expression , minLength , fillChar )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#pad_right-expression--minlength--fillchar-)
  * Unique Identifier
    * [IDENTITY( seed , increment )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#identity-seed--increment-)
    * [MD2()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#md2)
    * [MD5()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#md5)
    * [SHA1()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#sha1)
    * [SHA256()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#sha256)
    * [SHA512()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#sha512)
* [Output](https://github.com/mostafa-asg/EasyGen/wiki/Output)
  * [CONSOLE](https://github.com/mostafa-asg/EasyGen/wiki/Output#console-expression-)
  * [FILE](https://github.com/mostafa-asg/EasyGen/wiki/Output#file-expression--path-)
  * [SOCKET( expression , host:port )](https://github.com/mostafa-asg/EasyGen/wiki/Output#socket-expression--hostport-)
