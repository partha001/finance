CD ..
ECHO %cd%
CALL mvn clean install -f pom.xml

ECHO %cd%
CD setup

ECHO %cd%
REM CALL docker-compose up -d
CALL docker compose up --build --force-recreate -d