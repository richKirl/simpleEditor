#!/bin/sh
javac Editor.java;
jar -cvfm app.jar MANIFEST.MF *.class;
#options -Dswing.aatext=true -Dawt.useSystemAAFontSettings=on -Dsun.java2d.renderer=sun.java2d.marlin.MarlinRenderingEngine -Dsun.java2d.xrender=true
java -jar ./app.jar;
