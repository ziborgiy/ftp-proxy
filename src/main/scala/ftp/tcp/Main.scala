package ftp.tcp

import akka.actor.ActorSystem
import akka.event.jul.Logger
import ftp.tcp.proxy.{TCPProxy, TCPProxyConfig}


//TODO: Add logging cross project
object Main extends App {
  if (args.isEmpty) {
    println("Args should be in format <proxy_port>:<ftp_hostname>:<ftp_port>")
    sys.exit()
  }
  val system = ActorSystem("ftp-proxy")
  val configs = TCPProxyConfig.parse(args)
  configs.foreach {
    config =>
      system.actorOf(TCPProxy.props(config), s"ftp-proxy-${config.listenEndpoint.getPort}")
  }
}
