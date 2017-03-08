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
		Email COMMA IP COMMA IP COMMA URL COMMA DATE('dd/MM/yyyy H:m:s') NEW_LINE()	
	, 100 )
, /tmp/output.csv)
```

**Example 2:**  
To output a table like bellow to the console :  
```
--------------------------------------------------------------------------------
|UserID            |Username           |Password           |RegisterDate       |
--------------------------------------------------------------------------------
|1                 |YJBiPguRIOsyj      |UNaqeYYbMA         |2017/03/06 14:31:17|
--------------------------------------------------------------------------------
|2                 |nwkk               |rfH                |2017/03/06 14:31:17|
--------------------------------------------------------------------------------
|3                 |MXukiLsbspasLJ     |qAnQaIDOEBjfoppY   |2017/03/06 14:31:17|
--------------------------------------------------------------------------------
|4                 |GbIYOTqZXrhQG      |JFJRslE            |2017/03/06 14:31:17|
--------------------------------------------------------------------------------
|5                 |ULhsyhsppJS        |utfhcKppbM         |2017/03/06 14:31:17|
--------------------------------------------------------------------------------
|6                 |xSwWUFDvs          |FjscE              |2017/03/06 14:31:17|
--------------------------------------------------------------------------------
```
you can write:  
```
DEFINE( DRAW_LINE AS REP('-',80) NEW_LINE() )
DEFINE( ID AS IDENTITY(1,1) )
DEFINE( username AS REP([a..z]+[A..Z],3,18) )
DEFINE( Pwd AS REP([a..z]+[A..Z],3,18) )
DEFINE( Date AS DATE('yyyy/MM/dd H:m:s') )

CONSOLE(

	DRAW_LINE
	'|' PAD_RIGHT(UserID,18,' ') '|' PAD_RIGHT(Username,19,' ') '|' PAD_RIGHT(Password,19,' ') '|' PAD_RIGHT(RegisterDate,19,' ') '|' NEW_LINE()
	DRAW_LINE

	REP(
		'|' PAD_RIGHT(ID,18,' ') '|' PAD_RIGHT(username,19,' ') '|' PAD_RIGHT(Pwd,19,' ') '|' PAD_RIGHT(Date,19,' ') '|' NEW_LINE()
		DRAW_LINE
	, 6)
)
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
* [Functions](https://github.com/mostafa-asg/EasyGen/wiki/Functions)
  * [NEW_LINE()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#new_line)
  * [TAB()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#tab)
  * [REP( expression , number )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#rep-expression--number-)
  * [REP( expression , min_number , max_number )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#rep-expression--min_number--max_number-)
  * [DATE()](https://github.com/mostafa-asg/EasyGen/wiki/Functions#date)
  * [DATE( pattern )](https://github.com/mostafa-asg/EasyGen/wiki/Functions#date-pattern-)
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
