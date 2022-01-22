package org.talgat.shakirov

import javax.naming.directory.{Attribute, InitialDirContext}

object DNS {
  //Program get Cname for host that given to get alias of host
  //Program for work task
  def exampleRun = {
    val host = "s.multiscreensite.com"
    println(s"test for $host")
    lookupCNames(host).fold(println("No Cname for host")) { a =>
      println("host Cname is =" +a)
    }
  }

  private def lookupCNames(host: String): Option[List[String]] = {
    try {
      val dirContext = new InitialDirContext
      val attributes = dirContext.getAttributes("dns://8.8.8.8/%s" format host, Array[String]("A", "CNAME"))
      val list = {
        val attributeEnumeration = attributes.getAll
        var list = List[Attribute]()
        while (attributeEnumeration.hasMore)
          list = attributeEnumeration.next :: list
        attributeEnumeration.close()
        list.reverse
      }
      Some(list.map(x => x.get().toString))
    } catch {
      case e: Exception => None
    }
  }
}
