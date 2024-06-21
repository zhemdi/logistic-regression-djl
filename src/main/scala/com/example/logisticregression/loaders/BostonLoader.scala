package com.example.logisticregression.loaders

import ai.djl.ndarray.{NDArray, NDManager}
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.URL
import java.nio.file.Paths
import scala.collection.JavaConverters._

object BostonLoader {
  private val datasetDir = "datasets"

  def loadBostonDataset(manager: NDManager): (NDArray, NDArray) = {
    val file = downloadDataset("https://archive.ics.uci.edu/ml/machine-learning-databases/housing/housing.data", "housing.data")
    val lines = FileUtils.readLines(file, java.nio.charset.StandardCharsets.UTF_8).asScala
    val featuresList = new java.util.ArrayList[Array[Float]]()
    val labelsList = new java.util.ArrayList[java.lang.Float]()

    val medvValues = lines.map { line =>
      val values = line.trim.split("\\s+")
      values.last.toFloat
    }

    // Calculate mean of medv
    val medvMean = medvValues.sum / medvValues.size
    println(s"Mean of medv: $medvMean")

    lines.foreach { line =>
      val values = line.trim.split("\\s+")
      if (values.length == 14) { // Ensure it has all columns
        val features = values.take(13).map(_.toFloat)
        val label = if (values.last.toFloat < medvMean) 0f else 1f
        featuresList.add(features)
        labelsList.add(label)
      } else {
        println(s"Skipping malformed line: ${line.toString}")
      }
    }

    println(s"Loaded ${featuresList.size()} records from Boston dataset")

    if (featuresList.isEmpty || labelsList.isEmpty) {
      throw new RuntimeException("Failed to load Boston dataset: no data found")
    }

    val featuresArray = featuresList.toArray(new Array[Array[Float]](featuresList.size()))
    val labelsArray = labelsList.toArray(new Array[java.lang.Float](labelsList.size())).map(_.toFloat)

    val features = manager.create(featuresArray)
    val labels = manager.create(labelsArray)

    (features, labels)
  }

  private def downloadDataset(url: String, fileName: String): File = {
    val filePath = Paths.get(datasetDir, fileName).toString
    val file = new File(filePath)
    if (!file.exists()) {
      println(s"Downloading $fileName...")
      FileUtils.copyURLToFile(new URL(url), file)
    }
    file
  }
}
