package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.util.UUID
import scala.util.Random
class scriptGetRequest extends Simulation{
println(UUID.randomUUID().toString)
  println(UUID.randomUUID().toString)
  println(UUID.randomUUID().toString)
  println(UUID.randomUUID().toString)

  val rnd = new Random()
  def randomNumericalString(): String = {
    return ((100000.0 + rnd.nextDouble() * (10000000000000.0 - 100000.0)).toLong).toString
  }
  println(randomNumericalString())
  println(randomNumericalString())
  println(randomNumericalString())
  println(randomNumericalString())
  println(randomNumericalString())

  val baseUrl = "https://api.openbrewerydb.org"
  val httpConf = http.baseUrl(baseUrl)
  val scn = scenario("basicgetscenario").exec(http("basicgetrequest").get("/breweries"))

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
