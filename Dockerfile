ARG IMAGE=intersystems/iris:2019.1.0S.111.0
ARG IMAGE=store/intersystems/irishealth:2019.3.0.308.0-community
ARG IMAGE=store/intersystems/iris-community:2019.3.0.309.0
ARG IMAGE=store/intersystems/iris-community:2019.4.0.379.0
ARG IMAGE=store/intersystems/iris-community:2020.1.0.197.0
ARG IMAGE=intersystemsdc/iris-community:2020.1.0.209.0-zpm
ARG IMAGE=intersystemsdc/iris-community:2020.1.0.215.0-zpm
ARG IMAGE=intersystemsdc/iris-community:2020.2.0.196.0-zpm
FROM $IMAGE

USER root

WORKDIR /opt/irisapp
RUN chown ${ISC_PACKAGE_MGRUSER}:${ISC_PACKAGE_IRISGROUP} /opt/irisapp

COPY  --chown=irisowner irissession.sh /
RUN chmod +x /irissession.sh 

###########################################
#### Install Java 8
RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
	apt-get install -y ant && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;
	
# Fix certificate issues, found as of 
# https://bugs.launchpad.net/ubuntu/+source/ca-certificates-java/+bug/983302
RUN apt-get update && \
	apt-get install -y ca-certificates-java && \
	apt-get clean && \
	update-ca-certificates -f && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Setup JAVA_HOME, this is useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME
ENV JRE_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JRE_HOME
ENV CLASSPATH .:/usr/irissys/dev/java/lib/JDK18/*:/usr/irissys/dev/java/lib/gson/*:/usr/irissys/dev/java/lib/jackson/*
RUN export CLASSPATH

###########################################
#### Set up the irisowner account and load application
USER irisowner

COPY  --chown=irisowner Installer.cls .
COPY  --chown=irisowner src src

# Configure this demo IRIS instance
SHELL ["/irissession.sh"]

# Set up anything you may need in Objectscript
RUN \
  do $SYSTEM.OBJ.Load("Installer.cls", "ck") \
  set sc = ##class(App.Installer).setup() 

ENV PATH="/usr/irissys/bin/:${PATH}"

# bringing the standard shell back
SHELL ["/bin/bash", "-c"]
