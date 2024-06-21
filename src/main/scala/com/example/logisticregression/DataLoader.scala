package com.example.logisticregression

import ai.djl.ndarray.{NDArray, NDManager}
import ai.djl.ndarray.index.NDIndex
import com.example.logisticregression.loaders._

object DataLoader {
  def loadDataset(manager: NDManager, config: Map[String, String]): (NDArray, NDArray) = {
    config("datasetName") match {
      case "iris" => IrisLoader.loadIrisDataset(manager)
      case "boston" => BostonLoader.loadBostonDataset(manager)
      case "wine" => WineLoader.loadWineDataset(manager)
      case "breast_cancer" => BreastCancerLoader.loadBreastCancerDataset(manager)
      case _ => FileLoader.loadDatasetFromFile(manager, config("datasetPath"))
    }
  }

  def trainValTestSplit(manager: NDManager, features: NDArray, labels: NDArray, valRatio: Float, testRatio: Float): (NDArray, NDArray, NDArray, NDArray, NDArray, NDArray) = {
    val numSamples = features.getShape.get(0).toInt
    val indices = scala.util.Random.shuffle((0 until numSamples).toList).toArray

    val trainEnd = (numSamples * (1 - valRatio - testRatio)).toInt
    val valEnd = (numSamples * (1 - testRatio)).toInt

    val trainIndices = manager.create(indices.slice(0, trainEnd).map(_.toLong))
    val valIndices = manager.create(indices.slice(trainEnd, valEnd).map(_.toLong))
    val testIndices = manager.create(indices.slice(valEnd, numSamples).map(_.toLong))

    val trainFeatures = features.get(trainIndices)
    val trainLabels = labels.get(trainIndices)
    val valFeatures = features.get(valIndices)
    val valLabels = labels.get(valIndices)
    val testFeatures = features.get(testIndices)
    val testLabels = labels.get(testIndices)

    (trainFeatures, trainLabels, valFeatures, valLabels, testFeatures, testLabels)
  }
}
