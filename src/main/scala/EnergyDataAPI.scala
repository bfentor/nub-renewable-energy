import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try

object EnergyDataAPI {

  def getAllEnergyData(startTime: String, endTime: String): Try[List[EnergyReading]] = Try {
    println("Generating sample data...")
    
    // Sample because I was in a hurry
    List(
      EnergyReading(EnergySource.Wind, 1250.5, LocalDateTime.parse("2024-12-01T00:00:00"), 1500.0),
      EnergyReading(EnergySource.Wind, 1680.3, LocalDateTime.parse("2024-12-01T06:00:00"), 1500.0),
      EnergyReading(EnergySource.Wind, 2100.8, LocalDateTime.parse("2024-12-01T12:00:00"), 1500.0),
      EnergyReading(EnergySource.Solar, 450.7, LocalDateTime.parse("2024-12-01T12:00:00"), 500.0),
      EnergyReading(EnergySource.Hydro, 1020.4, LocalDateTime.parse("2024-12-01T12:00:00"), 1200.0),
      EnergyReading(EnergySource.Wind, 1950.2, LocalDateTime.parse("2024-12-01T18:00:00"), 1500.0),
      EnergyReading(EnergySource.Wind, 1180.4, LocalDateTime.parse("2024-12-02T00:00:00"), 1500.0),
      EnergyReading(EnergySource.Solar, 120.5, LocalDateTime.parse("2024-12-02T12:00:00"), 500.0),
      EnergyReading(EnergySource.Wind, 1520.6, LocalDateTime.parse("2024-12-02T06:00:00"), 1500.0),
      EnergyReading(EnergySource.Wind, 1890.3, LocalDateTime.parse("2024-12-02T12:00:00"), 1500.0),
      EnergyReading(EnergySource.Hydro, 945.6, LocalDateTime.parse("2024-12-02T12:00:00"), 1200.0)
    )
  }
}