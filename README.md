Mobile Cloud Middleware
========================

Service-oriented middleware utilized to delegate mobile tasks to multiple clouds. The middleware abstracts Web APIs from different cloud vendors using Clojure.


![alt text](https://raw.github.com/huberflores/MobileCloudMiddleware/master/UT-BasicDelegationModel.png "Service-oriented model")

[Figure's reference](http://www.sciencedirect.com/science/article/pii/S0164121213002318)

Requirements
-------------

Maven : version >= 3.0.4

m2e Eclipse Plugin (optional for development)

Web server with Servlet container (e.g. Tomcat)


Installation
-------------

```xml
$ git clone https://github.com/huberflores/MobileCloudMiddleware.git
````

[InteroperabilityAPI installation](https://github.com/huberflores/InteroperableWebAPI.git)


```xml
$ mvn clean install
````

Locate the generated wars into the Web server


USAGE
----

- Invoke the service using the unique interface <mcminteroperability> ([Gist example](https://gist.github.com/huberflores/5747779)) 

FAQ
----

Since gcm-server.jar is not provided in a specific maven repository, a local maven repository that includes the library should be created. Alternatively, gcm-server repository can be establish using the following [link](https://github.com/slorber/gcm-server-repository)

```xml
$ mvn install:install-file -Dfile=/home/huber/.m2/repository/gcm-server-1.0.3.jar -DgroupId=com.google.android.gcm.server -DartifactId=gcm-server -Dversion=1.0.3 -Dpackaging=jar
````
```xml
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-install-plugin:2.3:install-file (default-cli) @ standalone-pom ---
[INFO] Installing /home/huber/.m2/repository/gcm-server-1.0.3.jar to /home/huber/.m2/repository/com/google/android/gcm/server/gcm-server/1.0.3/gcm-server-1.0.3.jar
[INFO] Installing /tmp/mvninstall3661972569570313695.pom to /home/huber/.m2/repository/com/google/android/gcm/server/gcm-server/1.0.3/gcm-server-1.0.3.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.610s
[INFO] Finished at: Sun Oct 13 17:23:12 EEST 2013
[INFO] Final Memory: 6M/118M
[INFO] ------------------------------------------------------------------------
````

Dependecy is defined in pom.xml as
```xml
<dependency>
    <groupId>com.google.android.gcm.server</groupId>
    <artifactId>gcm-server</artifactId>
    <version>1.0.3</version>
</dependency>
````

Similarly, mcm-wrapper must be installed in a local maven repository

```xml
$ mvn install:install-file -Dfile=/home/huber/.m2/repository/mcm-wrapper/mcm-wrapper-1.0.0.jar -DgroupId=WebApiWrapper.mcminteroperability -DartifactId=mcm-wrapper -Dversion=1.0.0 -Dpackaging=jar
````

```xml
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-install-plugin:2.3:install-file (default-cli) @ MobileCloudMiddleware ---
[INFO] Installing /home/huber/.m2/repository/mcm-wrapper/mcm-wrapper-1.0.0.jar to /home/huber/.m2/repository/WebApiWrapper/mcminteroperability/mcm-wrapper/1.0.0/mcm-wrapper-1.0.0.jar
[INFO] Installing /tmp/mvninstall6326015323439677465.pom to /home/huber/.m2/repository/WebApiWrapper/mcminteroperability/mcm-wrapper/1.0.0/mcm-wrapper-1.0.0.pom
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] MobileCloudMiddleware ............................. SUCCESS [0.346s]
[INFO] Manager Maven Webapp .............................. SKIPPED
[INFO] Handler Maven Webapp .............................. SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.622s
[INFO] Finished at: Sun Oct 13 17:23:12 EEST 2013
[INFO] Final Memory: 7M/149M
[INFO] ------------------------------------------------------------------------
````

Dependecy is defined in pom.xml as
```xml
<dependency>
    <groupId>WebApiWrapper.mcminteroperability</groupId>
    <artifactId>mcm-wrapper</artifactId>
    <version>1.0.0</version>
</dependency>
````

How to cite
-----------
If you are using the tool for your research, please do not forget to cite

- Flores, Huber, and Satish Srirama. ["Mobile Cloud Middleware"](http://www.sciencedirect.com/science/article/pii/S0164121213002318) Journal of Software and Systems, Volume 92, June 2014, Pages 82–94.
