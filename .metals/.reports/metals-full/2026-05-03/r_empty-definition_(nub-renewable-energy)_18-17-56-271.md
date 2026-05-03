error id: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala:requests/BaseSession#get.
file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol requests/BaseSession#get.
empty definition using fallback
non-local guesses:

offset: 501
uri: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
text:
```scala
import requests._
import io.github.cdimascio.dotenv.Dotenv
import AnalysisEngine._

object Main extends App {
  private val env = Dotenv.load()
  private val apiKey = env.get("FINGRID_PRIMARY")

  def getData(startTime: String, endTime: String, id: String): String = {
    val url = s"https://data.fingrid.fi/api/datasets/$id/data?startTime=$startTime&endTime=$endTime&format=csv&locale=en&sortBy=startTime&sortOrder=desc"
    val headers = Map("x-api-key" -> apiKey)


    val response = requests.get@@(url, headers = headers)
    response.read()
  }

  // Wind data example. Date not yet implemented
  println(getData("2023-06-28T12:15:00Z", "2023-06-28T12:30:00Z", "246"))
  
  
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 