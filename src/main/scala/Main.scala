package org.talgat.shakirov

import rationalNumbers.RationalExample

import java.io.File

object Main {
  //portfolio contains variaty of mini programs with life codding and work tasks that don't have company secrets
  def main(args: Array[String]): Unit = {
    if (args.nonEmpty && args.length < 2) {
      args(0) match {
        case "SantaWayTask" => SantaWayTask.computeHouseCount
        case "ChangeSignTailRec" => ChangeSignTailRec.tailRecExample
        case "ChangeSignFold" => ChangeSignFold.exampleRun
        case "DNS" => DNS.exampleRun
        case "RationalExample" => RationalExample.run
        case "Queens" => Queens.run
        case "CatsFilesExample" => CatsFilesExample.copy(new File("test.txt"),new File("testCopy.txt")) // new have file
        case "zipList" => ZipLists.run()
        case "Futures" => println("Please run future in object")
        case _ => println("Couldn't find mini program you try to run")
      }
    } else {
      println("Something went wrong")
    }
  }
}
