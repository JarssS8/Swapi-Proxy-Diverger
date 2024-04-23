build:
	docker build -t swapi-proxy .

down:
	docker stop swapi-proxy

up: build
	docker run --rm -d -p 8080:8080 --name swapi-proxy swapi-proxy:latest

tests:
	mvn clean test

sonar:
	mvn clean install sonar:sonar