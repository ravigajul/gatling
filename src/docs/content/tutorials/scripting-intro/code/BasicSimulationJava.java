/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//#setup-the-file
//package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
//#setup-the-file

class ComputerDatabaseSimulation1 {
  //#extend-the-simulation-class
  public class ComputerDatabaseSimulation extends Simulation {

  }
  //#extend-the-simulation-class
}

class ComputerDatabaseSimulation2 {
  //#define-the-protocol-class
  public class ComputerDatabaseSimulation extends Simulation {

    // Add the HttpProtocolBuilder:
    HttpProtocolBuilder httpProtocol =
      http.baseUrl("https://computer-database.gatling.io")
        .acceptHeader("application/json")
        .contentTypeHeader("application/json");
  }
  //#define-the-protocol-class
}

class ComputerDatabaseSimulation3 {
  //#write-the-scenario
  public class ComputerDatabaseSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
      http.baseUrl("https://computer-database.gatling.io")
        .acceptHeader("application/json")
        .contentTypeHeader("application/json");

    // Add the ScenarioBuilder:
    ScenarioBuilder myScenario = scenario("My Scenario")
      .exec(http("Request 1")
        .get("/computers/"));

  }
  //#write-the-scenario
}

class ComputerDatabaseSimulation4 {
  //#define-the-injection-profile
  public class ComputerDatabaseSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
      http.baseUrl("https://computer-database.gatling.io")
        .acceptHeader("application/json")
        .contentTypeHeader("application/json");

    ScenarioBuilder myFirstScenario = scenario("My First Scenario")
      .exec(http("Request 1")
        .get("/computers/"))
      .pause(2)
      .exec(http("Request 2")
        .get("/computers/?f=macbook")
        .check(status().is(200)));

    // Add the setUp block:
    {
      setUp(
        myFirstScenario.injectOpen(constantUsersPerSec(2).during(60))
      ).protocols(httpProtocol);
    }
  }
  //#define-the-injection-profile
}

public class BasicSimulationJava {

}