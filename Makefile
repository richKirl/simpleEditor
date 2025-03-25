JFLAGS = -g -Xlint:deprecation
JC = javac
JFILES = *.java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JFILES)

# This uses the line continuation character (\) for readability
# You can list these all on a single line, separated by a space instead.
# If your version of make can't handle the leading tabs on each
# line, just remove them (these are also just added for readability).
CLASSES = \
        Editor.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

