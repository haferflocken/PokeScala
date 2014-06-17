package pokescala.test

import pokescala.net.PokeAPI
import scala.collection.mutable

object TypePercentages extends App {
  
  val types = new mutable.HashMap ++= PokeAPI.allTypes.map(t => (t._2, 0));
  
  for (pokedex <- PokeAPI.pokedex; uri <- pokedex.pokemonURIs; pokemon <- PokeAPI.pokemonByURI(uri)) {
    for (uri <- pokemon.typeURIs; t <- PokeAPI.typeByURI(uri)) {
      types.update(t, types(t) + 1);
    }
  }
  
  var total = 0.0;
  for ((t, c) <- types)
    total += c;
  
  for ((t, c) <- types)
    println(t.name + s": $c -> " + ((c * 100) / total) + "%");

}