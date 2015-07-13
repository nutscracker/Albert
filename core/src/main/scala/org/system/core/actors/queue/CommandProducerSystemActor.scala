package org.system.core.actors.queue

import com.typesafe.config.Config
import org.system.core.actors.System.SystemProducerActor

/**
 * Created by evgeniikorniichuk on 08.11.14.
 */

object CommandProducerSystemActor {
  def apply()(implicit config: Config) = {
    new CommandProducerSystemActor(config getString "commandProducerUrl")
  }
}

class CommandProducerSystemActor(
                                  override val endpointUri: String
                                  )(implicit config: Config) extends SystemProducerActor {

}
