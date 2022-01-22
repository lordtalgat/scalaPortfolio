package org.talgat.shakirov

import scala.util.Random

object SantaWayTask {
  //Santa's helpers made a mistake now Santa's path has been broken.
  // And instead of visiting each house once, Santa will have to visit the house more than once.
  // The task is to find out how many houses Santa visited.
  // Each movement is "^","v","<",">", santa moves without diagonals.
  // Each point is a new house, but if he visited one point twice count as one house.
  // Example ">^<v" santa returned to the starting position and visited 3 houses
  //How many houses visit santa
  //Life coding took 15 minutes

  //I don't remember exact line given by input of string
  //val Input = "^^>v>^<>^vvvv<>^"
  //val arr = Input.toCharArray.toSeq
  //So I convert it like that
  def computeHouseCount = {
    val randNumbers4 = for {v <- 1 to 1500} yield {
      Random.nextInt(4)
    }
    val santaWay = randNumbers4.map {
      case 0 => '^'
      case 1 => 'v'
      case 2 => '>'
      case _ => '<'
    }

    var (x, y) = (0, 0)
    val houses = santaWay.map {
      case '>' => (x += 1, y)
      case '<' => (x -= 1, y)
      case '^' => (x, y += 1)
      case _ => (x, y -= 1)
        (x, y)
    }.distinct.size

    println("Santas plan=" + santaWay.mkString(""))
    printf("----- Sanata will visit %d houses -------", houses)
  }
}
