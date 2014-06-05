package pokescala.test

import pokescala.net.PokeAPI

object GetTest extends App {
  
  val test = PokeAPI.getType(5);
  println(test);
  
}