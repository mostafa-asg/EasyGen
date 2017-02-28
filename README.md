# EasyGen
Welcome to the world of randomness! EasyGen is a very simple DSL to generate arbitrary amount of string data with ease.All you need 
to do is write your DSL into a file and pass file path to this program.Currently the generated data can be written to file or standard output. 

### How to build
From the project folder run bellow commands to build and run the project:
```
mvn clean package
java -jar Generator-1.0-SNAPSHOT-jar-with-dependencies.jar YOUR_DSL_FILE_PATH
```

## DSL Syntax
### Range
Range in EasyGen is bulding block to generate random data.EasyGen get an item of a Range randomly.There is 3 type of Range in EasyGen:
* LongRange
* CharRange
* StringRange

#### LongRange
Syntax  : [ number1 .. number2 ]  
Example : [1..100]  
Output  : random number between 1 to 100 inclusively  
**Note :** number1 <= number2

#### CharRange
Syntax  : [ char1 .. char2 ]  
Example : [a..z]  
Output  : random char between a to z inclusively   
**Note :** char1 <= char2

#### StringRange
Syntax   : [ S1 | S2 ] or [ S1 | S2 | S3 ] or [ S1 | S2 | ... | Sn ]   
Example1 : [ Hello | World]    
Output 1 : random selection between Hello or World  
Example2 : [ Hello | World | Java | Love]  
Output 2 : random selection between Hello or World or Java or Love  



