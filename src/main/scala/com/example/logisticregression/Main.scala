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

    if (config("mode") == "train") {
      val (trainFeatures, trainLabels, valFeatures, valLabels, testFeatures, testLabels) = DataLoader.trainValTestSplit(
        manager, features, labels, config("valRatio").toFloat, config("testRatio").toFloat
      )

      val model = new LogisticRegression(manager, features.getShape.get(1))

      

      // Train the model
      model.train(trainFeatures, trainLabels, valFeatures, valLabels, config("epochs").toInt, config("learningRate").toFloat, earlyStop = true)
      
      // Save the trained model
      model.saveModel(config("modelPathToSave"))
      
      // Evaluate on the test set
      val testPredictions = model.predict(testFeatures)
      val testLoss = Utils.binaryCrossEntropyLoss(testLabels, model.predictProbabilities(testFeatures))
      val testAccuracy = model.calculateAccuracy(testPredictions, testLabels)

      println(s"Test Loss: ${testLoss.getFloat()}")
      println(s"Test Accuracy: $testAccuracy")
    } else if (config("mode") == "inference") {
      val model = new LogisticRegression(manager, features.getShape.get(1))
      model.loadModel(config("modelPathToLoad"))

      // Perform inference on the entire dataset
      val predictions = model.predict(features)
      val loss = Utils.binaryCrossEntropyLoss(labels, model.predictProbabilities(features))
      val accuracy = model.calculateAccuracy(predictions, labels)

      println(s"Loss: ${loss.getFloat()}")
      println(s"Accuracy: $accuracy")
    } else {
      println("Invalid mode specified. Please set mode to 'train' or 'inference' in the config file.")
    }
  }
}

