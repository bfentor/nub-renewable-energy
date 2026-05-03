object AnalysisEngine {
    def mean(data: Array[Double]): Double = {
        val sum = data.sum
        val count = data.length
        if (count > 0) sum / count else 0.0
    }

    def median(data: Array[Double]): Double = {
        val sortedData = data.sorted
        val count = sortedData.length
        if (count == 0) return 0.0
        if (count % 2 == 1) {
            sortedData(count / 2)
        } else {
            (sortedData(count / 2 - 1) + sortedData(count / 2)) / 2.0
        }
    }

    def mode(data: Array[Double]): Double = {
        val frequencyMap = data.groupBy(identity).mapValues(_.length)
        frequencyMap.maxBy(_._2)._1
    }

}
