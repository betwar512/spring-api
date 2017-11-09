# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

 eclise Java EE spring tools plugin + gradle buildship v 2 
gradle v > 3.0

 > gradle build
 > gralde bootRun 
 
 ##Build Docker image
 Docker file included in root of project , to build valid docker image , have to keep version number in
 gradle and docker file are a same.
 
 > gradle buildDocker
 
 ##application properties 
 Project included only 1 applicatio.property file 
 hibernate.hbm2ddl.auto=update is set to update and will create new database out of classes if database schema dont exist 
 schema.sql file for oath2 included in project and will get excuted if tables dont exist .
 
 ##Auth configuration setup 
 
 to setup new database for oath , you nedd to create client secret and access level for client Apps , to do that you have to uncomment
add new client into ClientDetailsServiceConfigurer  , go to package config.security AuthorizationServerConfig and uncomment the code in line 59
this will create a new client creditional for your app .

 
 
 
### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact