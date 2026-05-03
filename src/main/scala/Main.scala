//By Balazs Fentor, Ugne Timonyte, and Naadiya Saikia
//No AI was used in the creation of this code.

import requests._
import io.github.cdimascio.dotenv.Dotenv
import AnalysisEngine._

object Main extends App {
  private val env = Dotenv.load()
  private val apiKey = env.get("FINGRID_PRIMARY")

  def getData(date: String, id: String): String = {
    val url = s"https://data.fingrid.fi/api/datasets/$id"
    val headers = Map("x-api-key" -> apiKey)
    val response = requests.get(url, headers = headers)
    response.text()
    var currentRecords: List[Double] = List()
  }

  // Wind data example. Date not yet implemented
  println(getData("2026-05-03", "246"))
  // println(testFunc("This is a test"))
  

  // def main(): Unit = {
  //   while (true) {
  //     // val input = scala.io.StdIn.readLine("Enter input, 1. View power plant status 2. Enter new data 3. Data Analysis! 4. Filter and search records 5. Exit")

      input match {
        case "1" => println("Viewing power plant status...")
        case "2" => println("Entering new data...")
        case "3" => println("Performing data analysis...")
        case "4" => println("Filtering and searching records...")
        case "5" => println("Checking system alerts...")
        case "6" => 
          println("Exiting the program. Goodbye!")
          System.exit(0)
        case _ => println("Invalid input, please try again.")
      }

  //   }
  // }
}