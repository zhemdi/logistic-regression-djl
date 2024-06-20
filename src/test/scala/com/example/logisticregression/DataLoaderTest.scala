package com.example.logisticregression

import org.scalatest.flatspec.AnyFlatSpec
import ai.djl.ndarray.NDManager

class DataLoaderTest extends AnyFlatSpec {

  "DataLoader" should "load training features correctly" in {
    val manager = NDManager.newBaseManager()
    val dataLoader = new DataLoader(manager)
    val features = dataLoader.loadTrainingFeatures()

    assert(features.getShape.get(0) == 4)
    assert(features.getShape.get(1) == 2)
  }

  it should "load training labels correctly" in {
    val manager = NDManager.newBaseManager()
    val dataLoader = new DataLoader(manager)
    val labels = dataLoader.loadTrainingLabels()

    assert(labels.getShape.get(0) == 4)
    assert(labels.getShape.get(1) == 1)
  }
}
