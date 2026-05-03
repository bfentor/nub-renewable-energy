error id: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala:local13
file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol local13
empty definition using fallback
non-local guesses:

offset: 3353
uri: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
text:
```scala
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.StdIn

object Main extends App {

  private var currentData: List[EnergyReading] = List()

  EnergyMonitoringView.showHeader()
  println("\nWelcome to the Renewable Energy Monitoring System.")
  println("This system works exclusively with real-time data from the power plant API.")
  println("You must fetch data to begin analysis.")

  private var running = true

  while (running) {
    EnergyMonitoringView.showMenu()
    print("\nEnter your choice (1-8) or command: ")
    val input = StdIn.readLine().trim.toLowerCase

    input match {
      case "1" => handleFetchFromAPI()
      case "2" => handleViewAll()
      case "3" => handleViewBySource()
      case "4" => handleViewSummary()
      case "5" | "exit" => running = false
      case "help" => EnergyMonitoringView.showHelp()
      case _ => println("Unknown command. Type 'help' for available commands.")
    }
  }

  println("\nThank you for using the Renewable Energy Monitoring System. Goodbye!")

  private def handleFetchFromAPI(): Unit = {
    println("\n--- Fetch Data from Power Plant API ---")
    println("Format: yyyy-MM-dd (example: 2024-12-01)")
    print("Enter start date: ")
    val startDate = StdIn.readLine().trim
    print("Enter end date: ")
    val endDate = StdIn.readLine().trim
    
    try {
      val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val parsedStart = LocalDateTime.parse(startDate + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
      val parsedEnd = LocalDateTime.parse(endDate + "T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
      
      if (parsedEnd.isBefore(parsedStart)) {
        println("Error: End date must be after start date")
        return
      }
      
      val validStart = LocalDateTime.parse("2024-12-01T00:00:00")
      val validEnd = LocalDateTime.parse("2024-12-03T23:59:59")
      
      if (parsedStart.isBefore(validStart) || parsedEnd.isAfter(validEnd)) {
        println("No data found for the specified date range.")
        println("  (Sample data only available from 2024-12-01 to 2024-12-03)")
        return
      }
      
      println("\nFetching data from API...")
      EnergyDataAPI.getAllEnergyData(startDate, endDate) match {
        case scala.util.Success(readings) =>
          if (readings.nonEmpty) {
            currentData = readings
            println(s"Successfully fetched ${readings.length} readings")
            println(s"Wind readings: ${readings.count(_.sourceType == EnergySource.Wind)}")
            println(s"Solar readings: ${readings.count(_.sourceType == EnergySource.Solar)}")
            println(s"Hydro readings: ${readings.count(_.sourceType == EnergySource.Hydro)}")
          } else {
            println("No data found.")
          }
        case scala.util.Failure(e) =>
          println(s"Error: ${e.getMessage}")
      }
    } catch {
      case _: Exception => println("Invalid date format. Use: yyyy-MM-dd")
    }

    val dataDir = new java.io.File("data")
    if (!dataDir.exists()) {
      dataDir.mkdirs()
    }
    
    val csvFile = new java.io.File("data/fetched_data.csv")
    val writer = new java.io.PrintWriter(new java.io.FileWriter(csvFile))
    writer.println("SourceType,EnergyGenerated,Timestamp,Capacity")
    currentData.foreach(r => writer@@.println(s"${r.sourceType.name},${r.energyGenerated},${r.timestamp},${r.capacity}"))
    writer.close()
    
    if (csvFile.exists() && csvFile.length() > 0) {
      println(s"\n✓ Data saved to data/fetched_data.csv (${csvFile.length()} bytes)")
    } else {
      println(s"\n✗ Failed to save CSV file")
    }
  }

  private def handleViewAll(): Unit = {
    if (currentData.isEmpty) {
      println("\nNo data loaded. Please fetch data from the API first (option 1).")
      return
    }
    println("\n--- All Energy Readings ---")
    EnergyMonitoringView.displayReadings(currentData)
  }

  private def handleViewBySource(): Unit = {
    if (currentData.isEmpty) {
      println("\nNo data loaded. Please fetch data from the API first (option 1).")
      return
    }

    println("\n--- View by Source ---")
    print("Enter energy source (solar/wind/hydro): ")
    val sourceStr = StdIn.readLine().trim.toLowerCase

    EnergySource.fromString(sourceStr) match {
      case Some(source) =>
        val filtered = DataAnalyzer.filterBySource(currentData, source)
        println(s"\n--- ${source.name} Energy Readings ---")
        EnergyMonitoringView.displayReadings(filtered)
        EnergyMonitoringView.displayBySource(filtered)

      case None =>
        println("Invalid energy source.")
    }
  }

  private def handleFilterByTime(): Unit = {
    if (currentData.isEmpty) {
      println("\nNo data loaded. Please fetch data from the API first (option 1).")
      return
    }

    println("\n--- Filter Readings by Time ---")
    println("1. By Hour")
    println("2. By Day")
    println("3. By Week")
    println("4. By Month")
    print("Enter your choice (1-4): ")
    val choice = StdIn.readLine().trim

    choice match {
      case "1" => filterByHour(currentData)
      case "2" => filterByDay(currentData)
      case "3" => filterByWeek(currentData)
      case "4" => filterByMonth(currentData)
      case _ => println("Invalid choice.")
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
      case _: Exception => println("Invalid date format. Use: yyyy-MM-dd HH")
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
      case _: Exception => println("Invalid date format. Use: yyyy-MM-dd")
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
      case _: Exception => println("Invalid date format. Use: yyyy-MM-dd")
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
      case _: Exception => println("Invalid date format. Use: yyyy-MM")
    }
  }

  private def handleSearchByEnergy(): Unit = {
    if (currentData.isEmpty) {
      println("\nNo data loaded. Please fetch data from the API first (option 1).")
      return
    }

    println("\n--- Search Readings by Energy Range ---")
    print("Enter minimum energy (kWh): ")
    val minEnergy = getDoubleInput()

    print("Enter maximum energy (kWh): ")
    val maxEnergy = getDoubleInput()

    val results = DataAnalyzer.searchByEnergyRange(currentData, minEnergy, maxEnergy)
    println(s"\n--- Readings with energy between $minEnergy and $maxEnergy kWh ---")
    EnergyMonitoringView.displayReadings(results)
  }

  private def handleSort(): Unit = {
    if (currentData.isEmpty) {
      println("\nNo data loaded. Please fetch data from the API first (option 1).")
      return
    }

    println("\n--- Sort Readings ---")
    println("1. By timestamp (ascending)")
    println("2. By timestamp (descending)")
    println("3. By energy generated (ascending)")
    println("4. By energy generated (descending)")
    println("5. By capacity (ascending)")
    println("6. By capacity (descending)")
    print("Enter your choice (1-6): ")
    val choice = StdIn.readLine().trim

    val sorted = choice match {
      case "1" => DataAnalyzer.sortByTimestampAsc(currentData)
      case "2" => DataAnalyzer.sortByTimestampDesc(currentData)
      case "3" => DataAnalyzer.sortByEnergyAsc(currentData)
      case "4" => DataAnalyzer.sortByEnergyDesc(currentData)
      case "5" => DataAnalyzer.sortByCapacityAsc(currentData)
      case "6" => DataAnalyzer.sortByCapacityDesc(currentData)
      case _ => currentData
    }

    println("\n--- Sorted Readings ---")
    EnergyMonitoringView.displayReadings(sorted)
  }

  private def handleViewSummary(): Unit = {
    if (currentData.isEmpty) {
      println("\nNo data loaded. Please fetch data from the API first (option 1).")
      return
    }

    EnergyMonitoringView.displaySummary(currentData)
    EnergyMonitoringView.displayBySource(currentData)
  }

  private def getDoubleInput(): Double = {
    try {
      StdIn.readLine().trim.toDouble
    } catch {
      case _: Exception =>
        println("Invalid number. Please enter a valid double value.")
        getDoubleInput()
    }
  }
}

// Code by: Ugne Timonyte, Naadiya Saikia, Balazs Fentor
```


#### Short summary: 

empty definition using pc, found symbol in pc: 