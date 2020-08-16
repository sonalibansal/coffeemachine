## Coffee machine

### Install JAVA JDK (version 8)
You can use this article : [https://www.cs.dartmouth.edu/~scot/cs10/mac_install/mac_install.html](https://www.cs.dartmouth.edu/~scot/cs10/mac_install/mac_install.html)

check with this command if JAVA_HOME environment variable is available in your system:
```sh
echo $JAVA_HOME
```
If it does not return something lets set its value , run this command in another terminal :
```sh
/usr/libexec/java_home -v 1.8
```
the output will look something like :
```sh
/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home
```
copy this and replace the path in command below :
```sh
echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home' >> ~/.bash_profile
```
open new terminal and run this command to verify if our JAVA_HOME is working
```sh
$JAVA_HOME/bin/java -version
```
*It should return your java version (1.8)*

---
### Lets install Maven

    brew install maven
---
###  Project Set-up 🎮

** About the project **
1. This project includes the code base of working coffee machine. This supports 2 functions right now
- 1. Make beverage
- 2. Refill an ingredient

2. It is a menu driven program which does the above instructions on the basis of user inputs. It supports some kinds of beverages and ingredients that will be generally used in the coffee machine. For any additional beverage or ingredient we just need to add another sub type in our beverage or ingredient class and the rest will be handled by the code base.

1. **Clone the repository**
```sh
git clone git@github.com:sonalibansal/coffeemachine.git
```
2. **Run the repository**
- Go to CoffeeMachine class and run main function from there .

3. ** Running the test cases **
- Integration tests are written for this code base which you can find inside
```/coffee-machine/src/test/java/coffeemachine/services/BeverageServiceImplTest.java```

###Done


