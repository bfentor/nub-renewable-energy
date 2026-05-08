//calculates energy data statistics
object AnalysisEngine {
    def mean(data: List[Double]): Double = {
        val sum = data.sum
        val count = data.length
        if (count > 0) sum / count else 0.0
    }

    def median(data: List[Double]): Double = {
        val sortedData = data.sorted
        val count = sortedData.length
        if (count == 0) return 0.0
        if (count % 2 == 1) {
            sortedData(count / 2)
        } else {
            (sortedData(count / 2 - 1) + sortedData(count / 2)) / 2.0
        }
    }

  def mode(data: List[Double]): Option[Double] = {
    if (data.isEmpty) None
    else {
      val grouped = data.groupBy(identity).mapValues(_.length)
      val maxCount = grouped.values.max
      val modes = grouped.filter(_._2 == maxCount).keys.toList
      if (modes.nonEmpty) Some(modes.head) else None
    }
  }

    // Difference between lowest and highest energy value
  def range(data: List[Double]): Double = {
    if (data.isEmpty) return 0.0
    else {
        val sorted = data.sorted
        sorted.last - sorted.head
        }
  }

  // halfway point between minimum and maximum energy value
  def midrange(data: List[Double]): Double = {
    if (data.isEmpty) return 0.0
    val sorted = data.sorted
    (sorted.head + sorted.last) / 2
  }
}