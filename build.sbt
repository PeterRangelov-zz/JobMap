name := "jobmap"

version := "1.0"

lazy val `jobmap` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs )

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.35"

libraryDependencies += "org.projectlombok" % "lombok" % "1.16.4"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies += "com.ecwid" % "ecwid-mailchimp" % "2.0.1.0"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")