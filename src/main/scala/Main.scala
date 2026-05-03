import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.StdIn

object Main extends App {

  // Initialize the system
  DataStorage.initialize()
  EnergyMonitoringView.showHeader()

  // Main application loop
  private var running = true

  while (running) {
    EnergyMonitoringView.showMenu()
    print("\nEnter your choice (1-9) or command: ")
    val input = StdIn.readLine().trim.toLowerCase

    input match {
      case "1" => handleAddReading()
      case "2" => handleViewAll()
      case "3" => handleViewBySource()
      case "4" => handleFilterByTime()
      case "5" => handleSearchByEnergy()
      case "6" => handleSort()
      case "7" => handleViewSummary()
      case "8" => handleClearData()
      case "9" | "exit" => running = false
      case "help" => EnergyMonitoringView.showHelp()
      case _ => handleCommandInput(input)
    }
  }

  println("\nThank you for using the Renewable Energy Monitoring System. Goodbye!")

  // Command handlers

  private def handleAddReading(): Unit = {
    println("\n--- Add New Energy Reading ---")
    print("Enter energy source (solar/wind/hydro): ")
    val sourceStr = StdIn.readLine().trim.toLowerCase

    EnergySource.fromString(sourceStr) match {
      case Some(source) =>
        print("Enter energy generated (kWh): ")
        val energy = getDoubleInput()

        print("Enter capacity (kW): ")
        val capacity = getDoubleInput()

        val reading = EnergyReading(source, energy, LocalDateTime.now(), capacity)
        DataStorage.addReading(reading) match {
          case scala.util.Success(_) =>
            println(s"✓ Reading added successfully for ${source.name} at ${LocalDateTime.now()}")
          case scala.util.Failure(e) =>
            println(s"✗ Error adding reading: ${e.getMessage}")
        }

      case None =>
        println("✗ Invalid energy source. Please use: solar, wind, or hydro")
    }
  }

  private def handleViewAll(): Unit = {
    val readings = DataStorage.getAllReadings
    println("\n--- All Energy Readings ---")
    EnergyMonitoringView.displayReadings(readings)
  }

  private def handleViewBySource(): Unit = {
    println("\n--- View by Source ---")
    print("Enter energy source (solar/wind/hydro): ")
    val sourceStr = StdIn.readLine().trim.toLowerCase

    EnergySource.fromString(sourceStr) match {
      case Some(source) =>
        val allReadings = DataStorage.getAllReadings
        val filtered = DataAnalyzer.filterBySource(allReadings, source)
        println(s"\n--- ${source.name} Energy Readings ---")
        EnergyMonitoringView.displayReadings(filtered)
        EnergyMonitoringView.displayBySource(filtered)

      case None =>
        println("✗ Invalid energy source.")
    }
  }

  private def handleFilterByTime(): Unit = {
    println("\n--- Filter Readings by Time ---")
    println("1. By Hour")
    println("2. By Day")
    println("3. By Week")
    println("4. By Month")
    print("Enter your choice (1-4): ")
    val choice = StdIn.readLine().trim

    val allReadings = DataStorage.getAllReadings

    choice match {
      case "1" => filterByHour(allReadings)
      case "2" => filterByDay(allReadings)
      case "3" => filterByWeek(allReadings)
      case "4" => filterByMonth(allReadings)
      case _ => println("✗ Invalid choice.")
    }
  }

  private def filterByHour(readings: List[EnergyReading]): Unit = {
    print("Enter hour (yyyy-MM-dd HH): ")
    try {
      val timeStr = StdIn.readLine().trim + ":00:00"
      val dateTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      val filtered = DataAnalyzer.filterByHour(readings, dateTime)
      println(s"\n--- Readings for hour: ${dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00"))} ---")
      EnergyMonitoringView.displayReadings(filtered)
    } catch {
      case _: Exception => println("✗ Invalid date format. Use: yyyy-MM-dd HH")
    }
  }

  private def filterByDay(readings: List[EnergyReading]): Unit = {
    print("Enter day (yyyy-MM-dd): ")
    try {
      val dayStr = StdIn.readLine().trim + " 00:00:00"
      val dateTime = LocalDateTime.parse(dayStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      val filtered = DataAnalyzer.filterByDay(readings, dateTime)
      println(s"\n--- Readings for day: ${dateTime.toLocalDate} ---")
      EnergyMonitoringView.displayReadings(filtered)
    } catch {
      case _: Exception => println("✗ Invalid date format. Use: yyyy-MM-dd")
    }
  }

  private def filterByWeek(readings: List[EnergyReading]): Unit = {
    print("Enter a date in the week (yyyy-MM-dd): ")
    try {
      val dayStr = StdIn.readLine().trim + " 00:00:00"
      val dateTime = LocalDateTime.parse(dayStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      val filtered = DataAnalyzer.filterByWeek(readings, dateTime)
      println(s"\n--- Readings for the week containing ${dateTime.toLocalDate} ---")
      EnergyMonitoringView.displayReadings(filtered)
    } catch {
      case _: Exception => println("✗ Invalid date format. Use: yyyy-MM-dd")
    }
  }

  private def filterByMonth(readings: List[EnergyReading]): Unit = {
    print("Enter month (yyyy-MM): ")
    try {
      val monthStr = StdIn.readLine().trim + "-01 00:00:00"
      val dateTime = LocalDateTime.parse(monthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      val filtered = DataAnalyzer.filterByMonth(readings, dateTime)
      println(s"\n--- Readings for month: ${dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"))} ---")
      EnergyMonitoringView.displayReadings(filtered)
    } catch {
      case _: Exception => println("✗ Invalid date format. Use: yyyy-MM")
    }
  }

  private def handleSearchByEnergy(): Unit = {
    println("\n--- Search Readings by Energy Range ---")
    print("Enter minimum energy (kWh): ")
    val minEnergy = getDoubleInput()

    print("Enter maximum energy (kWh): ")
    val maxEnergy = getDoubleInput()

    val allReadings = DataStorage.getAllReadings
    val results = DataAnalyzer.searchByEnergyRange(allReadings, minEnergy, maxEnergy)
    println(s"\n--- Readings with energy between $minEnergy and $maxEnergy kWh ---")
    EnergyMonitoringView.displayReadings(results)
  }

  private def handleSort(): Unit = {
    println("\n--- Sort Readings ---")
    println("1. By timestamp (ascending)")
    println("2. By timestamp (descending)")
    println("3. By energy generated (ascending)")
    println("4. By energy generated (descending)")
    println("5. By capacity (ascending)")
    println("6. By capacity (descending)")
    print("Enter your choice (1-6): ")
    val choice = StdIn.readLine().trim

    val allReadings = DataStorage.getAllReadings
    val sorted = choice match {
      case "1" => DataAnalyzer.sortByTimestampAsc(allReadings)
      case "2" => DataAnalyzer.sortByTimestampDesc(allReadings)
      case "3" => DataAnalyzer.sortByEnergyAsc(allReadings)
      case "4" => DataAnalyzer.sortByEnergyDesc(allReadings)
      case "5" => DataAnalyzer.sortByCapacityAsc(allReadings)
      case "6" => DataAnalyzer.sortByCapacityDesc(allReadings)
      case _ => allReadings
    }

    println("\n--- Sorted Readings ---")
    EnergyMonitoringView.displayReadings(sorted)
  }

  private def handleViewSummary(): Unit = {
    val allReadings = DataStorage.getAllReadings
    EnergyMonitoringView.displaySummary(allReadings)
    EnergyMonitoringView.displayBySource(allReadings)
  }

  private def handleClearData(): Unit = {
    if (EnergyMonitoringView.confirmAction("Are you sure you want to clear all data?")) {
      DataStorage.clearData() match {
        case scala.util.Success(_) =>
          println("✓ All data has been cleared.")
        case scala.util.Failure(e) =>
          println(s"✗ Error clearing data: ${e.getMessage}")
      }
    } else {
      println("Clear operation cancelled.")
    }
  }

  private def handleCommandInput(command: String): Unit = {
    val parts = command.split("\\s+")
    parts.headOption match {
      case Some("add") if parts.length == 4 =>
        val source = parts(1)
        val energy = parts(2).toDoubleOption
        val capacity = parts(3).toDoubleOption
        (source.toLowerCase, energy, capacity) match {
          case (src, Some(e), Some(c)) =>
            EnergySource.fromString(src) match {
              case Some(energySource) =>
                val reading = EnergyReading(energySource, e, LocalDateTime.now(), c)
                DataStorage.addReading(reading) match {
                  case scala.util.Success(_) =>
                    println(s"✓ Reading added: ${energySource.name} - ${e} kWh at ${LocalDateTime.now()}")
                  case scala.util.Failure(err) =>
                    println(s"✗ Error: ${err.getMessage}")
                }
              case None => println("✗ Invalid source.")
            }
          case _ => println("✗ Invalid parameters.")
        }

      case Some("view") if parts.length == 1 =>
        handleViewAll()

      case Some("summary") if parts.length == 1 =>
        handleViewSummary()

      case _ =>
        println("✗ Unknown command or invalid syntax.")
        println("Type 'help' for available commands.")
    }
  }

  private def getDoubleInput(): Double = {
    try {
      StdIn.readLine().trim.toDouble
    } catch {
      case _: Exception =>
        println("✗ Invalid number. Please enter a valid double value.")
        getDoubleInput()
    }
  }
}
