//calculates energy data statistics
object AnalysisEngine {
  
  def mean(data: List[Double]): Double = {
    if (data.isEmpty) 0.0
    else data.sum / data.length
  }

  def median(data: List[Double]): Double = {
    if (data.isEmpty) 0.0
    else {
      val sorted = data.sorted
      val n = sorted.length
      if (n % 2 == 0) (sorted(n / 2 - 1) + sorted(n / 2)) / 2
      else sorted(n / 2)
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

  def range(data: List[Double]): Double = {
    if (data.isEmpty) 0.0
    else {
      val sorted = data.sorted
      sorted.last - sorted.head
    }
  }

  def midrange(data: List[Double]): Double = {
    if (data.isEmpty) 0.0
    else {
      val sorted = data.sorted
      (sorted.head + sorted.last) / 2
    }
  }
}