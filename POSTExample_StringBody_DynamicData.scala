package basicpackage

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import java.util.UUID

import scala.util.Random

class POSTExample_StringBody_DynamicData extends Simulation {

  //feeder injects data into user session and returns new session instance
  // val destination="https://www.qamilestone.com/" //read this data from csv feeder
  // val slashtag = "myurl2"// read this data from csv feeder

  val apiKey="9d02943b6f854cd893ea667e6a5a40ac"
  val linkshorten = csv("data/linkshorten.csv").circular //this csv is stored at data folder
  //val title = "mytitle2" //keep it as it is and add to session as attribute using set method

  val rnd = new Random()
  def randomNumericalString(): String = {
    return ((100000.0 + rnd.nextDouble() * (10000000000000.0 - 100000.0)).toLong).toString
  }
 // val title = UUID.randomUUID().toString
 val title = randomNumericalString()



  //Create http configuration
  val httpProtocol = http.baseUrl("https://api.rebrandly.com")
    .headers(Map("Content-Type" -> "application/json", "apikey" -> apiKey))

  //Create scenario

  val scn = scenario("CreateLink").exec(session => {
    //set slashtag as session attribute
    session.set("title", title)
  }).feed(linkshorten)
    .exec(http("createnewlink").post("/v1/links").body(StringBody("""{"destination":"${destination}","slashtag":"${slashtag}","title":"${title}"}""")).asJson) //send post request

  //inject user to send http request
  setUp(scn.inject(atOnceUsers(12))).protocols(httpProtocol) //inject one user

}
