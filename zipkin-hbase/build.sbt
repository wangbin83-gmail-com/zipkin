defaultSettings

parallelExecution in Test := false

libraryDependencies ++= Seq(
  Seq(
    hadoop("common"),
    "com.google.guava"      % "guava"                 % "11.0.2" % "test", //Hadoop needs a deprecated class
    "com.google.guava"      % "guava-io"              % "r03" % "test", //Hadoop needs a deprecated class
    "com.google.protobuf"   % "protobuf-java"         % "2.4.1",
    "commons-logging"       % "commons-logging"       % "1.1.1",
    "commons-configuration" % "commons-configuration" % "1.6",
    "org.apache.zookeeper"  % "zookeeper"             % "3.4.6" % "runtime" notTransitive(),
    "org.apache.hbase"      % "hbase"                 % "0.98.6-cdh5.3.1",
    slf4jLog4j12,
    util("logging"),
    scroogeDep("serializer")
  ),
  many(hbaseDep, "common", "client"),
  many(zipkin, "common", "scrooge")
).flatten ++ testDependencies
