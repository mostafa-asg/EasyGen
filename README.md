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
**NOTE :** number1 <= number2

#### CharRange
CharRange is a range of characters.You specify start and end, and EasyGen gets a character randomly from start to end.
Syntax  : [ char1 .. char2 ]  
Example :  
```
[a..z]  
```
**Output**  : random character between a to z inclusively   
**NOTE :** char1 <= char2

#### Range Unions
Two Range can be "added" together.For example if you want to generate random character between [a,b,c,d,e] and [v,w,x,y,z] you can write it as :  
```
[a..e]+[v..z]
```
**Another Example**
```
[1..5]+[50..55]+[90..95]
```

#### Range Complements
Two sets can also be "subtracted".For instance if you want to generate random nuumber between 1 to 100 but not [55,56,57] you can write it as :
```
[1..100]-[55..57]
```
**NOTE :** add and subtract operation on Range are left associative.
**Example**
```
[1..10]+[11..20]-[7..9]
```
The above statment first add [1..10]+[11..20] then subtrat it from [7..9]

#### StringRange
If you want to select randomly between words, you can use StringRange.The syntax is like LongRange and CharRange but you must seperate your data with **|** :
Syntax   : **[ S1 | S2 ]** or **[ S1 | S2 | S3 ]** or **[ S1 | S2 | ... | Sn ]**
Example 1 :  
```
[ Hello | World]    
```
**Output** : random selection between Hello or World  

Example 2 :  
```
[ Hello | World | Java | 6572]  
```
**Output** : random selection between Hello or World or Java or 6572  
**NOTE :** if you want to generate a string with space, you can just add **'** to the beginning and end of a string.For example :  
```
[ 'Hello World' | 'This is a test' ]  
```
**Output** : random selection between *Hello World* and *This is a test*.  

### Static data
In EasyGen,you can always mix static string with EasyGen DSL syntax to produce new data.Example:  
```
[a..c]Hello[A..C]
```
The above example could generate 9 possible random string which starts with lower case *a* or *b* or *c* and ends with capital *A* or *B* or *C*.Possible random data could be : **bHelloC** or **aHelloA**
**NOTE :** EasyGen does not take into account spaces.So above example could be written as :  
```
[   a    ..  c   ]   H  e l    l o [ A   .. C ]
```
Its generated output is exactly same as **[a..c]Hello[A..C]**.

### Repetition
Ranges are great to generate only one random data.if you want to repeat this process *N* times, you can use **REP** function:
```
REP( [a..z] , 3 )
```
**output** : generate 3 random character between *a* to *z*.Some possible outputs could be : **hck** or **dnb**
The above statment generate random string which lengths is exactly 3.Rep can also use to generate random variable length string.Example:  
```
REP( [a..z] , 3 , 10 )
```
The above statment generate random length string which minimum length is 3 and maximum length is 10.Some possible outputs could be : **hyrtd** or **byqtrwzp**

**Example** : to generate random string which has exact length of 10, and start with **f** and ends with **x** or **y** or **z** you can write :
```
f REP( [a..z] , 8 ) [x..z] 
```
**Example** : generate random url with minimum length of 3 and maximum length of 20 that contains [a..z] characters and start with **http** or **https** and ends with **.com** or **.net** or **.org**
```
[http | https] :// REP( [a..z] , 3 , 20 ) [ .com | .org | .net ]
```
