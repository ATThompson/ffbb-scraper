FROM maven:3.9.8-sapmachine-21 AS build
COPY . .
RUN mvn clean package

FROM openjdk:21-jdk-bullseye

ARG INSTALL_MAVEN="true"
ARG MAVEN_VERSION=""

ARG INSTALL_GRADLE="false"
ARG GRADLE_VERSION=""

#RUN if [ "${INSTALL_MAVEN}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi \
#    && if [ "${INSTALL_GRADLE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi

# install google chrome

RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -

RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'

RUN apt-get -y update

RUN apt-get install -y google-chrome-stable

# install chromedriver

RUN apt-get install -yqq unzip

RUN wget -O /tmp/chromedriver.zip http://chromedriver.storage.googleapis.com/`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_linux64.zip

RUN unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/
###
# REST OF YOUR DOKCERFILE HERE
###

# WORKDIR workspaces/ffbb-scraper

ARG JAR_FILE=application/target/application-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} ffbb-scraper.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","ffbb-scraper.jar"]
#COPY . .
#RUN mvn clean package -DskipTests
#ARG JAR_FILE=target/ffbb-scraper-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} ffbb-scraper.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","ffbb-scraper.jar"]