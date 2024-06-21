package com.example.logisticregression

import com.example.logisticregression.loaders._
import org.scalatest.flatspec.AnyFlatSpec
import ai.djl.ndarray.NDManager

class DataLoaderTest extends AnyFlatSpec {

  "BostonLoader" should "load Boston dataset correctly" in {
    val manager = NDManager.newBaseManager()
    val (features, labels) = BostonLoader.loadBostonDataset(manager)

    assert(features.getShape.get(0) == 506) // Assuming the dataset has 506 samples
    assert(features.getShape.get(1) == 13) // 13 features
    assert(labels.getShape.get(0) == 506) // 506 labels
  }

  "BreastCancerLoader" should "load Breast Cancer dataset correctly" in {
    val manager = NDManager.newBaseManager()
    val (features, labels) = BreastCancerLoader.loadBreastCancerDataset(manager)

    assert(features.getShape.get(0) == 569) // Assuming the dataset has 569 samples
    assert(features.getShape.get(1) == 30) // 30 features
    assert(labels.getShape.get(0) == 569) // 569 labels
  }

  "IrisLoader" should "load Iris dataset correctly" in {
    val manager = NDManager.newBaseManager()
    val (features, labels) = IrisLoader.loadIrisDataset(manager)

    assert(features.getShape.get(0) == 100) // Assuming the dataset has 100 samples for binary classification
    assert(features.getShape.get(1) == 4) // 4 features
    assert(labels.getShape.get(0) == 100) // 100 labels
  }

  "WineLoader" should "load Wine dataset correctly" in {
    val manager = NDManager.newBaseManager()
    val (features, labels) = WineLoader.loadWineDataset(manager)

    assert(features.getShape.get(0) == 130) // Assuming the dataset has 130 samples for binary classification
    assert(features.getShape.get(1) == 13) // 13 features
    assert(labels.getShape.get(0) == 130) // 130 labels
  }
}
