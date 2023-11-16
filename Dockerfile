# Use a base image with JRE
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the WAR file into the container at /app
COPY /var/lib/jenkins/workspace/test_job/webapp/target/webapp.war /app/webapp.war

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "webapp.war"]

#FROM tomcat:latest
#RUN cp -R  /usr/local/tomcat/webapps.dist/*  /usr/local/tomcat/webapps
#COPY ./*.war /usr/local/tomcat/webapps

