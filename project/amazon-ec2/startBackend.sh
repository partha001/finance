echo '*************  pulling image from docker hub *****************'
docker pull partha011/portfolio-manager:1.0

echo  '************** stopping previous backend instance **'
docker stop portfolio-manager

echo  '************* spinning up new backend instance ***************'
docker run -d  --rm  -p 8080:8080 --name  portfolio-manager partha011/portfolio-manager:1.0
