package com.example.logisticregression.loaders

import ai.djl.ndarray.{NDArray, NDManager}
import org.apache.commons.csv.CSVParser
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.URL
import java.nio.file.Paths
import scala.collection.JavaConverters._

object WineLoader {
  private val datasetDir = "datasets"

  def loadWineDataset(manager: NDManager): (NDArray, NDArray) = {
    val file = downloadDataset("https://archive.ics.uci.edu/ml/machine-learning-databases/wine/wine.data", "wine.data")
    val parser = CSVParser.parse(file, java.nio.charset.StandardCharsets.UTF_8, org.apache.commons.csv.CSVFormat.DEFAULT)
    val featuresList = new java.util.ArrayList[Array[Float]]()
    val labelsList = new java.util.ArrayList[java.lang.Float]()

    parser.asScala.foreach { record =>
      val label = record.get(0).toFloat - 1 // Classes are 1, 2, 3 in dataset, converting to 0, 1, 2
      if (label == 0f || label == 1f) { // Consider only two classes for binary classification
        val features = record.iterator().asScala.toList.slice(1, 14).map(_.toFloat).toArray
        featuresList.add(features)
        labelsList.add(label)
      }
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
