import java.io._
import scala.io.Source
import scala.util.{Try, Using}

object DataStorage {
  private val dataFile = "data/energy_readings.csv"
  private val dataDir = "data"

  // Initialize data directory
  def initialize(): Unit = {
    val dir = new File(dataDir)
    if (!dir.exists()) {
      dir.mkdirs()
    }
    
    // Create CSV file with headers if it doesn't exist
    if (!new File(dataFile).exists()) {
      val writer = new PrintWriter(new File(dataFile))
      writer.println("SourceType,EnergyGenerated,Timestamp,Capacity")
      writer.close()
    }
  }

  // Store energy reading to file
  def addReading(reading: EnergyReading): Try[Unit] = Try {
    val writer = new PrintWriter(new FileWriter(dataFile, true))
    writer.println(reading.toCSV)
    writer.close()
  }

  // Store multiple readings
  def addReadings(readings: List[EnergyReading]): Try[Unit] = Try {
    val writer = new PrintWriter(new FileWriter(dataFile, true))
    readings.foreach(r => writer.println(r.toCSV))
    writer.close()
  }

  // Load all readings from file
  def getAllReadings: List[EnergyReading] = {
    initialize()
    Try {
      Using(Source.fromFile(dataFile)) { source =>
        source.getLines().drop(1) // Skip header
          .flatMap(line => EnergyReading.fromCSV(line))
          .toList
      }.get
    }.getOrElse(List())
  }

  // Clear all data
  def clearData(): Try[Unit] = Try {
    val writer = new PrintWriter(new File(dataFile))
    writer.println("SourceType,EnergyGenerated,Timestamp,Capacity")
    writer.close()
  }
}
