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

libraryDependencies += "commons-validator" % "commons-validator" % "1.4.1"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"

//libraryDependencies += "org.apache.commons" % "commons-email" % "1.4"

libraryDependencies += "com.sendgrid" % "sendgrid-java" % "2.2.2"



unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")