# FakeTime 
Faketime is to support testing time based features. It allows you to change time to simulate test scenarios.

This is fake time utility, that [Manohar](https://github.com/akula1001) developed. It was using remote python server for time source. 

This fork uses in memory time offset that will be added to real clock. System class is decorated with setTimeOffset and getTimeOffset methods to faketime.

    gcc -shared -I /usr/lib/jvm/java-6-sun-1.6.0.26/include -I /usr/lib/jvm/java-6-sun-1.6.0.26/include/linux/ -m32 jvmfaketime.c  -o libjvmfaketime.so
    cp libjvmfaketime.so /usr/lib/jvm/java-6-sun-1.6.0.26/jre/lib/i386/ 
  [ or whatever your jvm folder is ]

    javac -classpath .:javassist.jar ClassModifier.java 
    java -cp .:javassist.jar  ClassModifier

This will create your own version of java.lang.System class with 4 new native methods to support faketime.

Now update rt.jar so that any java app can use faketime.

    jar -uf /usr/lib/jvm/java-6-sun-1.6.0.26/jre/lib/rt.jar  java/


Compile and run test class to verify the setup.

    javac -bootclasspath .:/usr/lib/jvm/java-6-sun-1.6.0.26/jre/lib/rt.jar -cp . Test.java
    java -cp . Test 


