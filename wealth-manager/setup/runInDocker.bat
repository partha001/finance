CD ..
ECHO %cd%
CALL mvn clean install -f pom.xml

ECHO %cd%
CD setup

ECHO %cd%
CALL docker-compose up -d