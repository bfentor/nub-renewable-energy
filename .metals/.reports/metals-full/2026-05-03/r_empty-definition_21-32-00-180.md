error id: file:///C:/Users/Naadiya/nub-renewable-energy/src/main/scala/AnalysisEngine.scala:local6
file:///C:/Users/Naadiya/nub-renewable-energy/src/main/scala/AnalysisEngine.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -sorted.
	 -scala/Predef.sorted.
offset: 984
uri: file:///C:/Users/Naadiya/nub-renewable-energy/src/main/scala/AnalysisEngine.scala
text:
```scala
//calculates energy data statistics
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
        if (data.isEmpty) return 0.0
        val frequencyMap = data.groupBy(identity).mapValues(_.length)
        frequencyMap.maxBy(_._2)._1
    }

    // Difference between lowest and highest energy value
  def range(data: List[Double]): Double = {
    if (data.isEmpty) return 0.0
    else {
        val sorted = data.sorted
        }
    
    sorted.last - so@@rted.head
  }

  // halfway point between minimum and maximum energy value
  def midrange(data: List[Double]): Double = {
    val sorted = data.sorted
    (sorted.head + sorted.last) / 2
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 