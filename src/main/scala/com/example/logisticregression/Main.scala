package com.example.logisticregression

import ai.djl.ndarray.NDManager

object Main {
  def main(args: Array[String]): Unit = {
    val manager = NDManager.newBaseManager()
    val dataLoader = new DataLoader(manager)
    val trainingFeatures = dataLoader.loadTrainingFeatures()
    val trainingLabels = dataLoader.loadTrainingLabels()

    val model = new LogisticRegression(manager)
    model.train(trainingFeatures, trainingLabels, 1000, 0.01f)

    val testFeatures = manager.create(Array(5.0f, 6.0f))
    val prediction = model.predict(testFeatures)
    println(s"Prediction: ${prediction.getFloat()}")
  }
}
