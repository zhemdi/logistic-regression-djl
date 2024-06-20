name := "logistic-regression-djl"

version := "0.1"

scalaVersion := "3.4.2"

resolvers ++= Seq(
  "DJL Repository" at "https://djl.ai/maven/repository/",
  Resolver.mavenCentral
)

libraryDependencies ++= Seq(
  "ai.djl.api" % "api" % "0.28.0" from "https://repo1.maven.org/maven2/ai/djl/api/0.28.0/api-0.28.0.jar",
  "ai.djl.basicdataset" % "basicdataset" % "0.28.0" from "https://repo1.maven.org/maven2/ai/djl/basicdataset/0.28.0/basicdataset-0.28.0.jar",
  // "ai.djl.basicmodelzoo" % "basicmodelzoo" % "0.28.0" from "https://repo1.maven.org/maven2/ai/djl/basicmodelzoo/0.28.0/basicmodelzoo-0.28.0.jar",
  "ai.djl.mxnet" % "mxnet-engine" % "0.28.0" from "https://repo1.maven.org/maven2/ai/djl/mxnet/mxnet-engine/0.28.0/mxnet-engine-0.28.0.jar",
  "ai.djl.mxnet" % "mxnet-model-zoo" % "0.28.0" from "https://repo1.maven.org/maven2/ai/djl/mxnet/mxnet-model-zoo/0.28.0/mxnet-model-zoo-0.28.0.jar",
  "org.scalatest" %% "scalatest" % "3.2.11" % Test
)