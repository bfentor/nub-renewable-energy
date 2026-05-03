import requests._
import io.github.cdimascio.dotenv.Dotenv

object Main extends App {
  private val env = Dotenv.load()
  private val apiKey = env.get("FINGRID_PRIMARY")

  def getData(date: String, id: String): String = {
    val url = s"https://data.fingrid.fi/api/datasets/$id"
    val headers = Map("x-api-key" -> apiKey)
    val response = requests.get(url, headers = headers)
    response.text()
  }

  // Wind data example. Date not yet implemented
  println(getData("2026-05-03", "246"))

  

  def main(args: Array[String]): Unit = {
    while (true) {
      val input = scala.io.StdIn.readline("Enter input, 1. View power plant status 2. Enter new data 3. Data Analysis! 4. Filter and search records 5. System Alert Check! 6. Exit :(   :")


    }
  }
}