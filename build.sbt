name := "jobmap"

version := "1.0"

lazy val `jobmap` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs )

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.35"

libraryDependencies += "org.projectlombok" % "lombok" % "1.16.4"




unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )