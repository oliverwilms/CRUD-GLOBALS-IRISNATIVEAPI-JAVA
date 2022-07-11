# CRUD-GLOBALS-IRISNATIVEAPI-JAVA
This application is utility tool to manage the globals which are in IRIS instance by using java program.

## Instructions
 To use this application basic knowledge of CACHE Globals structure is required.
 To start with Docker desktop should be installed in the system.

## Inspiration
To provide tool to manage the global data with minimum access.

## What it does
This is JAVA application and it allows to Create,View,Update,Delete and Search Globals which are in IRIS instance. This Java application can be modified in such way that we can install this application in any system and manage the globals which are in different IRIS instance. We can modify this application to restrict the access of globals to the different instances to different users for example we can restrict the CRUD operations only to the user defined Globals and rest all other Globals access can be blocked for CRUD operations in this application.

## Challenges I ran into
No Challanges.

## Accomplishments that I proud of

## What I learned
Docker,VSCode,IRIS Native API,JAVA

## Built with
Using VSCode and JAVA 1.8, IRIS Community Edition in Docker.

## Prerequisites
Make sure you have [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) and [Docker desktop](https://www.docker.com/products/docker-desktop) installed.


Clone/git pull the repo into any local directory e.g. like it is shown below:

```
$ git clone https://github.com/muniganesh84/CRUD-GLOBALS-IRISNATIVEAPI-JAVA.git
```

Open the terminal in this directory and run:

```
$ docker-compose up -d --build
```

## How to Work With it

Start a bash shell to the container by using below command

docker exec -it containerid bash

then goto the directory where the java program resides by using below command

cd src/java

and then execute `run.sh`


## Troubleshooting Docker-Compose

If docker-compose fails, the two most common problems are:

1. Docker version mismatch.  The docker-compose specifies version 3.6. If you have a different version, you should be able to just change the version in `docker-compose.yml`
2. Port unavailable.  If you're already running a container on your machine, you might need to change the port number that is exported.

## Collaboration 
Any collaboration is very welcome! Fork and send Pull requests!
