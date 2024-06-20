name := "logistic-regression-djl"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "ai.djl.api" % "api" % "0.15.0",
  "ai.djl.basicdataset" % "basicdataset" % "0.15.0",
  "ai.djl.basicmodelzoo" % "basicmodelzoo" % "0.15.0",
  "ai.djl.mxnet" % "mxnet-engine" % "0.15.0",
  "ai.djl.mxnet" % "mxnet-model-zoo" % "0.15.0",
  "org.scalatest" %% "scalatest" % "3.2.8" % Test
)
