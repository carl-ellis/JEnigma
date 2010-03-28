CC=javac
CFLAGS= 

all:
	$(CC) $(CFLAGS) jenigma/ui/*.java jenigma/*.java *.java

docs:
	javadoc -d Docs jenigma/ui/*.java jenigma/*.java *.java

clean:
	rm -rfv Docs
	rm -rfv *.class jenigma/*.class jenigma/ui/*.class
