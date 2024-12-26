.PHONY: run test

run:
	mvn compile exec:java -Dexec.mainClass=com.chat.app.App

test:
	mvn test

format:
	mvn formatter:format
