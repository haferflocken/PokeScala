package pokescala.test

import pokescala.model._
import pokescala.net.PokeAPI

object GetTest extends App {
  
  val startTime = System.currentTimeMillis;
  
  // Loop through the pokemon in the pokedex, loading them.
  for (pokedex <- PokeAPI.pokedex; uri <- pokedex.pokemonURIs; pokemon <- PokeAPI.pokemonByURI(uri)) {
    // Loop through their abilities.
    for (uri <- pokemon.abilityURIs)
      PokeAPI.abilityByURI(uri);
    
    // Loop through their egg groups.
    for (uri <- pokemon.eggGroupURIs)
      PokeAPI.eggGroupByURI(uri);
    
    // Loop through their pokedex entries.
    for (uri <- pokemon.pokedexEntryURIs)
      PokeAPI.pokedexEntryByURI(uri);
    
    // Loop through their moves.
    for ((uri, level) <- pokemon.levelUpMoveURIs)
      PokeAPI.moveByURI(uri);
    for (uri <- pokemon.eggMoveURIs)
      PokeAPI.moveByURI(uri);
    for (uri <- pokemon.machineMoveURIs)
      PokeAPI.moveByURI(uri);
    for (uri <- pokemon.tutorMoveURIs)
      PokeAPI.moveByURI(uri);
    
    // Loop through their types.
    for (uri <- pokemon.typeURIs)
      PokeAPI.typeByURI(uri);
    
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