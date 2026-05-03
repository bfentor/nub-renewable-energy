//calculates energy data statistics
object AnalysisEngine {
    // Difference between lowest and highest energy value
  def range(data: List[Double]): Double = {
    val sorted = data.sorted
    sorted.last - sorted.head
  }

  // halfway point between minimum and maximum energy value
  def midrange(data: List[Double]): Double = {
    val sorted = data.sorted
    (sorted.head + sorted.last) / 2
  }
}