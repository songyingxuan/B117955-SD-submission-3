# B117955-SD-submission-3

In Linux system, there is no default compiler environment set and users have to install and configure to compile in Linux.

## Download jdk5.0 for linux
  . To the sun's homepage http://java.sun.com/j2se/1.5.0/download.jsp , download the jdk installation file


## Install java environment
  1. Create a new terminal, enter jdk storage directory, execute: 
  ./jdk-1_5_0-linux-i586-rpm.bin
  
  2.The jdk installation license appears. After reading it, you will be asked if you agree to the installation. Type "yes" to enter the installation.
  
  3.After the execution, there will be a jdk-1_5_0-linux-i586-rpm file in the current directory. You can use the ls command to view it. Then execute:
    rpm -ivh jdk-1_5_0-linux-i586-rpm
  In this way, jdk is installed in the /usr directory by default.

## The environment variable that need to configure
   1. PATH environment variable. The role is to specify the command search path, when the command is executed under the shell, it will look in the path specified by the PATH variable to see if it can find the corresponding command program. We need to add the bin directory under the jdk installation directory to the existing PATH variable. The bin directory contains frequently used executable files such as javac/java/javadoc. After the PATH variable is set, it can be in any directory. Under the implementation of javac / java and other tools.
   
   2. CLASSPATH environment variable. The role is to specify the class search path, to use already written classes, the premise is of course to be able to find them, the JVM is to find the class through CLASSPTH. We need to set dt.jar and tools.jar in the lib subdirectory under the jdk installation directory to the CLASSPATH. Of course, the current directory "." must also be added to this variable.

   3. JAVA_HOME environment variable. It points to the jdk installation directory. Eclipse/NetBeans/Tomcat and other software finds and uses the installed jdk by searching for the JAVA_HOME variable.
   
   
 ## Test jdk
    1. Create a new Test.java file with a text editor, enter the following code in it and save it:
      public class test {
　　　　　　public static void main(String args[]) {
　　　　　　　　System.out.println("A new jdk test !");
　　　　　　}
　 　　　}
     
     2. Compile: Execute the command in the shell terminal javac Test.java
     
     3. Run: Execute the command in the shell terminal Java Test

             When the words “A new jdk test !” appear under the shell, jdk runs normally.
