.PHONY: run test

compile:
	mvn clean package

test:
	mvn test

format:
	mvn formatter:format
