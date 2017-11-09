# README #

Welcone to Endpoint Spring Hibernate project 

### What is this repository for? ###

* Quick summary 
Spring boot project ,using hibernate with mysql v 5.6 , gradle , oath 2, themeleaf , mangodb , amazon email service 
* Version 0.6.0 Alpha 

### How do I get set up? ###

 Eclise Java EE spring tools plugin + gradle build-ship v 2 
gradle v >= 3.0

 > gradle build
 
 > gradle bootRun 
 
### Build Docker image
 Docker file included in root of project , to build valid docker image , have to keep version number in
 gradle and docker file as a same.
 
 > gradle buildDocker
 
### application properties 
 Project included only 1 applicatio.property file 
 
 hibernate.hbm2ddl.auto=update is set to update and will create new database out of classes if database schema dont exist 
 
 schema.sql file for oath2 included in project and will get excuted if tables don't exist .
 
## OAuth configuration setup 
 
 to setup new database for oath , you need to create client secret and access level for client Apps , to do that you have to uncomment
add new client into ClientDetailsServiceConfigurer  ,
 go to package config.security AuthorizationServerConfig and uncomment the code in line 59
 
this will create a new client creditional for your app .

 
 
 
### Contribution guidelines ###

* Other guidelines

Micro Service system 

Each packages included it own Model , Dao , Dto and service and one controller

 api/* url is protected globoly by oath .
 
 * You can access to user cridicional by extending MainController
 
Controller is directly talks to service class or classes included in package .

service access Database by making call to DAO classes . 

dao classes will return model result that will pars to one of DTO classes and will return to controller.


## Packages 
** accounts --> package setup to handle user information , Used by Config and main package . 

** config  - config file email config + themeleaf themplate configuration . 

-- this package included 

  -   security  OAth2 config + spring security 
  
  -	  database included hibernate datbase config + mangodb config file 

** emailtemplate 

package setup by using themeleaf themplate engine integrated into Spring boot services .

** filesystem -- mangodb fs system , in processs .... TODO 

**institue package  -- todo 

 

### Who do I talk to? ###

* Repo owner Abbas H Safaie ( Betwar )
* betwar512 