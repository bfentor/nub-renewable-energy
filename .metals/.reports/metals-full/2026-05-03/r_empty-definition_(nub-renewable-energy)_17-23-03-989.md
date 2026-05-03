error id: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala:io/github/cdimascio/dotenv/Dotenv#
file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol io/github/cdimascio/dotenv/Dotenv#
empty definition using fallback
non-local guesses:

offset: 143
uri: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
text:
```scala
import requests._
import io.github.cdimascio.dotenv.Dotenv
import AnalysisEngine.testFunc

object Main extends App {
  private val env = Dotenv@@.load()
  private val apiKey = env.get("FINGRID_PRIMARY")

  def getData(date: String, id: String): String = {
    val url = s"https://data.fingrid.fi/api/datasets/$id"
    val headers = Map("x-api-key" -> apiKey)
    val response = requests.get(url, headers = headers)
    response.text()
  }

  // Wind data example. Date not yet implemented
  println(getData("2026-05-03", "246"))

  

  // def main(): Unit = {
  //   while (true) {
  //     // val input = scala.io.StdIn.readline("Enter input, 1. View power plant status 2. Enter new data 3. Data Analysis! 4. Filter and search records 5. Exit")


  //   }
  // }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 