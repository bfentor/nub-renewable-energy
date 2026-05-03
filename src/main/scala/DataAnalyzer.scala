import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import scala.math.abs

object DataAnalyzer {

  def filterBySource(readings: List[EnergyReading], source: EnergySource): List[EnergyReading] = {
    readings.filter(_.sourceType == source)
  }

  def filterByHour(readings: List[EnergyReading], targetHour: LocalDateTime): List[EnergyReading] = {
    readings.filter { r =>
      r.timestamp.getYear == targetHour.getYear &&
      r.timestamp.getMonth == targetHour.getMonth &&
      r.timestamp.getDayOfMonth == targetHour.getDayOfMonth &&
      r.timestamp.getHour == targetHour.getHour
    }
  }

  def filterByDay(readings: List[EnergyReading], targetDay: LocalDateTime): List[EnergyReading] = {
    readings.filter { r =>
      r.timestamp.getYear == targetDay.getYear &&
      r.timestamp.getMonth == targetDay.getMonth &&
      r.timestamp.getDayOfMonth == targetDay.getDayOfMonth
    }
  }

  def filterByWeek(readings: List[EnergyReading], targetWeek: LocalDateTime): List[EnergyReading] = {
    val startOfWeek = targetWeek.minusDays(targetWeek.getDayOfWeek.getValue - 1)
    val endOfWeek = startOfWeek.plusDays(6)
    readings.filter { r =>
      !r.timestamp.isBefore(startOfWeek) && !r.timestamp.isAfter(endOfWeek)
    }
  }

  def filterByMonth(readings: List[EnergyReading], targetMonth: LocalDateTime): List[EnergyReading] = {
    readings.filter { r =>
      r.timestamp.getYear == targetMonth.getYear &&
      r.timestamp.getMonth == targetMonth.getMonth
    }
  }

  def filterByTimeRange(readings: List[EnergyReading], startTime: LocalDateTime, endTime: LocalDateTime): List[EnergyReading] = {
    readings.filter { r =>
      !r.timestamp.isBefore(startTime) && !r.timestamp.isAfter(endTime)
    }
  }

  def searchByEnergyRange(readings: List[EnergyReading], minEnergy: Double, maxEnergy: Double): List[EnergyReading] = {
    readings.filter(r => r.energyGenerated >= minEnergy && r.energyGenerated <= maxEnergy)
  }

  def searchByEnergyValue(readings: List[EnergyReading], targetEnergy: Double, tolerance: Double = 0.1): List[EnergyReading] = {
    readings.filter(r => abs(r.energyGenerated - targetEnergy) <= tolerance)
  }

  def sortByTimestampAsc(readings: List[EnergyReading]): List[EnergyReading] = {
    readings.sortBy(_.timestamp)
  }

  def sortByTimestampDesc(readings: List[EnergyReading]): List[EnergyReading] = {
    readings.sortBy(_.timestamp)(Ordering[LocalDateTime].reverse)
  }

  def sortByEnergyAsc(readings: List[EnergyReading]): List[EnergyReading] = {
    readings.sortBy(_.energyGenerated)
  }

  def sortByEnergyDesc(readings: List[EnergyReading]): List[EnergyReading] = {
    readings.sortBy(_.energyGenerated)(Ordering[Double].reverse)
  }

  def sortByCapacityAsc(readings: List[EnergyReading]): List[EnergyReading] = {
    readings.sortBy(_.capacity)
  }

  def sortByCapacityDesc(readings: List[EnergyReading]): List[EnergyReading] = {
    readings.sortBy(_.capacity)(Ordering[Double].reverse)
  }

  def analyzeReadings(readings: List[EnergyReading]): Map[String, Double] = {
    val energyValues = readings.map(_.energyGenerated)
    val capacityValues = readings.map(_.capacity)

    Map(
      "energy_mean" -> AnalysisEngine.mean(energyValues),
      "energy_median" -> AnalysisEngine.median(energyValues),
      "energy_range" -> AnalysisEngine.range(energyValues),
      "energy_midrange" -> AnalysisEngine.midrange(energyValues),
      "capacity_mean" -> AnalysisEngine.mean(capacityValues),
      "capacity_median" -> AnalysisEngine.median(capacityValues),
      "capacity_range" -> AnalysisEngine.range(capacityValues),
      "total_energy" -> energyValues.sum,
      "reading_count" -> readings.length.toDouble
    )
  }

  def getModeEnergy(readings: List[EnergyReading]): Option[Double] = {
    AnalysisEngine.mode(readings.map(_.energyGenerated))
  }

  def getUtilizationRate(readings: List[EnergyReading]): Double = {
    if (readings.isEmpty) 0.0
    else {
      val totalUtilization = readings.map(r => if (r.capacity > 0) (r.energyGenerated / r.capacity) * 100 else 0).sum
      totalUtilization / readings.length
    }
  }
}
