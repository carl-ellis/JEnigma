CC=javac
CFLAGS=

all:
	$(CC) $(CFLAGS) jenigma/*.java *.java

docs:
	javadoc -d Docs jenigma/*.java *.java

clean:
	rm -rfv Docs
	rm -rfv *.class jenigma/*.class
