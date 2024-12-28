.PHONY: run test test compile format

compile:
	mvn clean package

run:
	mvn clean spring-boot:run

test:
	mvn test

format:
	mvn formatter:format
