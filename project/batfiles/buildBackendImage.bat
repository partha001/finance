call changeDirectory.bat

call mvn clean install -f portfolio-manager\pom.xml

REM ######################### building docker image now #########################
call docker build -f portfolio-manager\Dockerfile   --no-cache=true -t partha011/portfolio-manager:1.0   portfolio-manager\

echo ######################### pushing image to dockerhub #########################
docker push partha011/portfolio-manager:1.0

