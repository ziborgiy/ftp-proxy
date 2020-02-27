package ftp.tcp.proxy

import java.net.InetSocketAddress

object TCPProxyConfig {
  def parse(args: Array[String]): Array[TCPProxyConfig] = args map parse

  def parse(arg: String) = {
    val parts = arg split ':'
    val listenPort = parts(0).toInt
    val remoteHost = parts(1)
    val remotePort = parts(2).toInt
    TCPProxyConfig(
      new InetSocketAddress(listenPort),
      new InetSocketAddress(remoteHost, remotePort)
    )
  }
}

case class TCPProxyConfig(
                           listenEndpoint: InetSocketAddress,
                           remoteEndpoint: InetSocketAddress
                         )

