.PHONY: run test

compile:
	mvn clean assembly:single

test:
	mvn test

format:
	mvn formatter:format
