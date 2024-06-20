package com.example.logisticregression

import ai.djl.ndarray._
import org.scalatest.funsuite.AnyFunSuite

class LogisticRegressionTest extends AnyFunSuite {
  test("Logistic Regression Train and Predict") {
    val manager = NDManager.newBaseManager()
    val model = new LogisticRegression(manager, 10)
    val features = manager.ones(new Shape(10, 10))
    val labels = manager.ones(new Shape(10, 1))
    model.train(features, labels, epochs = 10, learningRate = 0.01f)
    val predictions = model.predict(features)
    assert(predictions != null)
  }
}
