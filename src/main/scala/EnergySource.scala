import java.time.LocalDateTime

sealed trait EnergySource {
  def name: String
}

object EnergySource {
  case object Solar extends EnergySource {
    override def name: String = "Solar"
  }
  case object Wind extends EnergySource {
    override def name: String = "Wind"
  }
  case object Hydro extends EnergySource {
    override def name: String = "Hydro"
  }

  def fromString(s: String): Option[EnergySource] = s.toLowerCase match {
    case "solar" => Some(Solar)
    case "wind" => Some(Wind)
    case "hydro" => Some(Hydro)
    case _ => None
  }
}

case class EnergyReading(
  sourceType: EnergySource,
  energyGenerated: Double,
  timestamp: LocalDateTime,
  capacity: Double
) {
  def toCSV: String = s"${sourceType.name},$energyGenerated,$timestamp,$capacity"
}

object EnergyReading {
  def fromCSV(line: String): Option[EnergyReading] = {
    try {
      val parts = line.split(",")
      if (parts.length != 4) None
      else {
        val source = EnergySource.fromString(parts(0))
        val energy = parts(1).toDouble
        val time = LocalDateTime.parse(parts(2))
        val capacity = parts(3).toDouble
        source.map(s => EnergyReading(s, energy, time, capacity))
      }
    } catch {
      case _: Exception => None
    }
  }
}
