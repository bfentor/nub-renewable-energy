import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object EnergyMonitoringView {

  private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
  private val separator = "=" * 100

  // Display header
  def showHeader(): Unit = {
    println(separator)
    println(" " * 30 + "RENEWABLE ENERGY MONITORING SYSTEM")
    println(separator)
  }

  // Display all energy readings in a formatted table
  def displayReadings(readings: List[EnergyReading]): Unit = {
    if (readings.isEmpty) {
      println("No readings available.")
      return
    }

    println("\n" + "-" * 100)
    printf("%-15s %-20s %-20s %-20s %-20s\n", 
      "Source", "Energy (kWh)", "Capacity (kW)", "Timestamp", "Utilization %")
    println("-" * 100)

    readings.foreach { reading =>
      val utilization = if (reading.capacity > 0) 
        (reading.energyGenerated / reading.capacity) * 100 
      else 0
      printf("%-15s %-20.2f %-20.2f %-20s %-20.2f%%\n",
        reading.sourceType.name,
        reading.energyGenerated,
        reading.capacity,
        reading.timestamp.format(dateTimeFormatter),
        utilization)
    }
    println("-" * 100)
  }

  // Display summary statistics
  def displaySummary(readings: List[EnergyReading]): Unit = {
    if (readings.isEmpty) {
      println("No data available for summary.")
      return
    }

    val stats = DataAnalyzer.analyzeReadings(readings)
    val mode = DataAnalyzer.getModeEnergy(readings)
    val utilizationRate = DataAnalyzer.getUtilizationRate(readings)

    println("\n" + separator)
    println(" " * 40 + "ENERGY ANALYSIS SUMMARY")
    println(separator)

    println(f"\nEnergy Statistics:")
    println(f"  Mean Energy Generation:        ${stats("energy_mean")}%.2f kWh")
    println(f"  Median Energy Generation:      ${stats("energy_median")}%.2f kWh")
    println(f"  Mode Energy Generation:        ${mode.map(m => f"$m%.2f").getOrElse("N/A")} kWh")
    println(f"  Range:                         ${stats("energy_range")}%.2f kWh")
    println(f"  Midrange:                      ${stats("energy_midrange")}%.2f kWh")
    println(f"  Total Energy Generated:        ${stats("total_energy")}%.2f kWh")

    println(f"\nCapacity Statistics:")
    println(f"  Mean Capacity:                 ${stats("capacity_mean")}%.2f kW")
    println(f"  Median Capacity:               ${stats("capacity_median")}%.2f kW")
    println(f"  Capacity Range:                ${stats("capacity_range")}%.2f kW")

    println(f"\nOperational Metrics:")
    println(f"  Average Utilization Rate:      ${utilizationRate}%.2f%%")
    println(f"  Total Readings:                ${stats("reading_count").toInt}")

    println("\n" + separator)
  }

  // Display readings by source
  def displayBySource(readings: List[EnergyReading]): Unit = {
    println("\n" + separator)
    println(" " * 35 + "ENERGY GENERATION BY SOURCE")
    println(separator)

    val bySource = readings.groupBy(_.sourceType)
    bySource.foreach { case (source, sourceReadings) =>
      println(f"\n${source.name} Energy:")
      println("-" * 50)
      val totalEnergy = sourceReadings.map(_.energyGenerated).sum
      val avgEnergy = if (sourceReadings.nonEmpty) totalEnergy / sourceReadings.length else 0
      println(f"  Total Generated:               ${totalEnergy}%.2f kWh")
      println(f"  Number of Readings:            ${sourceReadings.length}")
      println(f"  Average per Reading:           ${avgEnergy}%.2f kWh")
      println(f"  Utilization Rate:              ${DataAnalyzer.getUtilizationRate(sourceReadings)}%.2f%%")
    }
    println("\n" + separator)
  }

  // Display menu
  def showMenu(): Unit = {
    println("\n" + separator)
    println("AVAILABLE OPERATIONS:")
    println(separator)
    println("1. Add new energy reading")
    println("2. View all readings")
    println("3. View readings by source (Solar/Wind/Hydro)")
    println("4. Filter readings by time period (hourly/daily/weekly/monthly)")
    println("5. Search readings by energy range")
    println("6. Sort readings")
    println("7. View energy analysis summary")
    println("8. Clear all data")
    println("9. Exit")
    println(separator)
  }

  // Display help message
  def showHelp(): Unit = {
    println("\n" + separator)
    println("HELP - SYSTEM COMMANDS")
    println(separator)
    println("Commands:")
    println("  add <source> <energy> <capacity>  - Add a new reading (e.g., add solar 150.5 500)")
    println("  view                               - View all readings")
    println("  view-source <solar|wind|hydro>    - View readings from specific source")
    println("  filter-hour <timestamp>            - Filter readings by hour")
    println("  filter-day <yyyy-MM-dd>            - Filter readings by day")
    println("  filter-week <yyyy-MM-dd>           - Filter readings by week")
    println("  filter-month <yyyy-MM>             - Filter readings by month")
    println("  search-range <min> <max>           - Search by energy range (kWh)")
    println("  sort-time-asc                      - Sort by timestamp (ascending)")
    println("  sort-time-desc                     - Sort by timestamp (descending)")
    println("  sort-energy-asc                    - Sort by energy (ascending)")
    println("  sort-energy-desc                   - Sort by energy (descending)")
    println("  summary                            - View analysis summary")
    println("  clear                              - Clear all data (WARNING)")
    println("  help                               - Show this help message")
    println("  exit                               - Exit the application")
    println(separator)
  }

  // Confirmation prompt
  def confirmAction(message: String): Boolean = {
    print(s"\n$message (yes/no): ")
    scala.io.StdIn.readLine().toLowerCase == "yes"
  }
}
