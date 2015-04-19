package org.system.plugin.worker

import akka.actor.{ActorLogging, Actor}
import org.system.scenario.Scenario

/**
 * Created by evgeniikorniichuk on 17/04/15.
 */
trait WorkerActorPlugin extends Actor with ActorLogging {

  type PluginScenario <: Scenario[PluginScenario]

}
