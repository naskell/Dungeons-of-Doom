# Define a variable for classpath
CLASS_PATH = ../bin

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

all : libmap.jnilib

# $@ matches the target, $< matches the first dependancy
libmap.jnilib : MapJNI.o
	gcc -dynamiclib -framework JavaVM -o $@ $<

# $@ matches the target, $< matches the first dependancy
MapJNI.o : MapJNI.c MapJNI.h
	gcc -I/Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX10.12.sdk/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers -framework JavaVM -c $< -o $@

# $* matches the target filename without the extension
MapJNI.h : MapJNI.class
	javah -classpath $(CLASS_PATH) $*
	
clean :
	rm MapJNI.h MapJNI.o libmap.jnilib	