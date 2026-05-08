import java.io._
import scala.io.Source
import scala.util.{Try, Using}

object DataStorage {
  private val dataFile = "data/energy_readings.csv"
  private val dataDir = "data"

  def initialize(): Unit = {
    val dir = new File(dataDir)
    if (!dir.exists()) {
      dir.mkdirs()
    }
    
    if (!new File(dataFile).exists()) {
      val writer = new PrintWriter(new File(dataFile))
      writer.println("SourceType,EnergyGenerated,Timestamp,Capacity")
      writer.close()
    }
  }

  def addReading(reading: EnergyReading): Try[Unit] = Try {
    val writer = new PrintWriter(new FileWriter(dataFile, true))
    writer.println(reading.toCSV)
    writer.close()
  }

  def addReadings(readings: List[EnergyReading]): Try[Unit] = Try {
    val writer = new PrintWriter(new FileWriter(dataFile, true))
    readings.foreach(r => writer.println(r.toCSV))
    writer.close()
  }

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

  def clearData(): Try[Unit] = Try {
    val writer = new PrintWriter(new File(dataFile))
    writer.println("SourceType,EnergyGenerated,Timestamp,Capacity")
    writer.close()
  }
}
