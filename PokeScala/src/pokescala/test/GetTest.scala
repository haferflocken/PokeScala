package pokescala.test

import pokescala.model._
import pokescala.net.PokeAPI

object GetTest extends App {
  
  val startTime = System.currentTimeMillis;
  
  // Loop through the pokemon in the pokedex, loading them.
  for (pokedex <- PokeAPI.getPokedex; uri <- pokedex.pokemon; pokemon <- PokeAPI.getPokemon(uri)) {
    // Loop through their abilities.
    for (uri <- pokemon.abilities)
      PokeAPI.getAbility(uri);
    
    // Loop through their egg groups.
    for (uri <- pokemon.eggGroups)
      PokeAPI.getEggGroup(uri);
    
    // Loop through their evolutions.
    for (evolution <- pokemon.evolutions)
      PokeAPI.getPokemon(evolution.to);
    
    // Loop through their pokedex entries.
    for (uri <- pokemon.pokedexEntries)
      PokeAPI.getPokedexEntry(uri);
    
    // Loop through their moves.
    for ((uri, level) <- pokemon.levelUpMoves)
      PokeAPI.getMove(uri);
    for (uri <- pokemon.eggMoves)
      PokeAPI.getMove(uri);
    for (uri <- pokemon.machineMoves)
      PokeAPI.getMove(uri);
    for (uri <- pokemon.tutorMoves)
      PokeAPI.getMove(uri);
    
    // Loop through their types.
    for (uri <- pokemon.types)
      PokeAPI.getType(uri);
    
    println("Loaded " + pokemon.name);
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