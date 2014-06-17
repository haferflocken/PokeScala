package pokescala.test

import pokescala.model._
import pokescala.net.PokeAPI
import scala.collection.mutable

object GetTest extends App {
  
  val startTime = System.currentTimeMillis;
  
  // Loop through the pokemon in the pokedex, loading them.
  for (pokedex <- PokeAPI.pokedex) {
    val todo = new mutable.ArrayBuffer[Model[_]];
    val visited = new mutable.ArrayBuffer[Model[_]];
    todo += pokedex;
    while (todo.nonEmpty) {
      val x = todo.remove(todo.length - 1);
      visited += x;
      
      println("Reached " + x.resourceURI);
      
      val adjacent = x.loadAdjacent;
      todo ++= adjacent.filter(!visited.contains(_));
    }
  }
  
  // Print out the registries.
  println("Loaded " + AbilityRegistry.size + " abilities.");
  println("Loaded " + EggGroupRegistry.size + " egg groups.");
  println("Loaded " + GameRegistry.size + " games.");
  println("Loaded " + MoveRegistry.size + " moves.");
  println("Loaded " + PokedexRegistry.size + " pokedexes.");
  println("Loaded " + PokedexEntryRegistry.size + " pokedex entries.");
  println("Loaded " + PokemonRegistry.size + " pokemon.");
  println("Loaded " + SpriteRegistry.size + " sprites.");
  println("Loaded " + TypeRegistry.size + " types.");
  
  // Show the elapsed time.
  val endTime = System.currentTimeMillis;
  println("Time elapsed: " + (endTime - startTime));
  
}