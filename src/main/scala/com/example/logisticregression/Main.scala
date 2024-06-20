package com.example.logisticregression

import ai.djl.ndarray.NDManager
import ai.djl.ndarray.types.Shape

object Main extends App {
  val manager: NDManager = NDManager.newBaseManager()

  // Use DataLoader to load training data
  val dataLoader = new DataLoader(manager)
  val features = dataLoader.loadTrainingFeatures()
  val labels = dataLoader.loadTrainingLabels()

  val inputSize: Long = features.getShape.get(1)
  val model = new LogisticRegression(manager, inputSize)

  model.train(features, labels, epochs = 10000, learningRate = 0.001f)
  val predictions = model.predict(features)
  println(predictions)
}
