# EasyGen
Welcome to the world of randomness! EasyGen is a very simple DSL to generate arbitrary amount of string data with ease.All you need 
to do is, write your DSL into a file and pass file path to this program.Currently the generated data can be written to file or standard output. 

### How to build
From the project folder run bellow commands to build and run the project:
```
mvn clean package
java -jar Generator-1.0-SNAPSHOT-jar-with-dependencies.jar YOUR_DSL_FILE_PATH
```

## DSL Syntax
### Range
Range in EasyGen is bulding block to generate random data.EasyGen gets an item of a Range randomly.There is 3 type of range in EasyGen:
* LongRange
* CharRange
* StringRange

#### LongRange
LongRange is a range of numbers.You specify start and end, and EasyGen gets a number randomly from start to end.
Syntax  : [ number1 .. number2 ]  
Example :  
```
[1..100]  
```
**Output**  : random number between 1 to 100 inclusively  
**Note :** number1 <= number2

#### CharRange
CharRange is a range of characters.You specify start and end, and EasyGen gets a character randomly from start to end.
Syntax  : [ char1 .. char2 ]  
Example :  
```
[a..z]  
```
**Output**  : random character between a to z inclusively   
**Note :** char1 <= char2

#### Range Unions
Two Range can be "added" together.For example if you want to generate random character between [a,b,c,d,e] and [v,w,x,y,z] you can write it as :  
```
[a..e]+[v..z]
```
**Output**  : random character in [a,b,c,d,e,v,w,x,y,z]  
**Example**
```
[1..5]+[50..55]+[90..95]
```
**Output** : random number in [1,2,3,4,5,50,51,52,53,54,55,90,91,92,93,94,95]

#### Range Complements
Two sets can also be "subtracted".For instance if you want to generate random nuumber between 1 to 100 but not [55,56,57] you can write it as :
```
[1..100]-[55..57]
```
**Note** : add and subtract operation on Range are left associative.
**Example**
```
[1..10]+[11..20]-[7..9]
```
#### StringRange
Syntax   : [ S1 | S2 ] **or** [ S1 | S2 | S3 ] **or** [ S1 | S2 | ... | Sn ]   
Example 1 :  
```
[ Hello | World]    
```
**Output** : random selection between Hello or World  


Example 2 :  
```
[ Hello | World | Java | Love]  
```
Output : random selection between Hello or World or Java or Love  
**Note :** if you want to generate a string with space, just add **'** to the beginning and end of a string.For example :  
```
[ 'Hello World' | 'This is a test' ]  
```
**Output** : random selection between *Hello World* and *This is a test*.  

### Repetition
Ranges are great to generate only one random data.if you want to repeat this process *N* times, you can use **REP** function:
```
REP( [a..z] , 3 )
```
**output** : generate 3 random character between *a* to *z*. Possible outputs are : **hck** or **dnb**
