package com.example.logisticregression

import ai.djl.ndarray.NDManager
import com.example.logisticregression.utils.ConfigLoader

object Main {
  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: sbt run <config-file-path>")
      System.exit(1)
    }

    val configFileName = args(0)
    val configFilePath = s"./config/$configFileName"
    val manager = NDManager.newBaseManager()

    val config = ConfigLoader.loadConfig(configFilePath)

    println(config("datasetName"))

    val (rawFeatures, labels) = DataLoader.loadDataset(manager, config)

    // Normalize features
    val features = Utils.normalizeFeatures(rawFeatures)

    val (trainFeatures, trainLabels, valFeatures, valLabels, testFeatures, testLabels) = DataLoader.trainValTestSplit(
      manager, features, labels, config("valRatio").toFloat, config("testRatio").toFloat
    )

    // println(features.getShape)
    // println(labels.getShape)

    val model = new LogisticRegression(manager, features.getShape.get(1))

    model.train(trainFeatures, trainLabels, valFeatures, valLabels, config("epochs").toInt, config("learningRate").toFloat, earlyStop = true)
    
    
    // Evaluate on the test set
    val testPredictions = model.predict(testFeatures)
    val testLoss = Utils.binaryCrossEntropyLoss(testLabels, model.predictProbabilities(testFeatures))
    val testAccuracy = model.calculateAccuracy(testPredictions, testLabels)

    println(s"Test Loss: ${testLoss.getFloat()}")
    println(s"Test Accuracy: $testAccuracy")
  }
}
