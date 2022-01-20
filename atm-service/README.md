

#Introduction
ATM Service display services for ATM. It is simple "thin client" for connect to Bank Service. ATM Service contains some important services for communicate with clients

#Requirements
**Java 11**

**Maven 3.8.4+**

**Docker version 20.10.12**


#Dependencies
ATM Service use Bank Services as core app, it is necessary to configure all service url from Bank Service(configuration is described bellow)


Here is urls for connect Bank Service:
atm-service\src\main\java\egc\atmservice\configuration\ConstantVariables.java
It is possible to update properties parameters here


#Build and deploy
* It is possible to build and deploy application with docker command **docker compose up**

