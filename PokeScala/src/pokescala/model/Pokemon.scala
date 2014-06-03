package pokescala.model

import java.time.LocalDateTime

class Pokemon(
    val name : String,
    val id : Int,
    val abilities : Vector[Int],
    val eggGroups : Vector[Int],
    val evolutions : Vector[Evolution],
    val pokedexEntries : Vector[Int],
    val moves : Vector[Int],
    val types : Vector[Int],
    val catchRate : Int,
    val species : String,
    val hp : Int,
    val attack : Int,
    val defense : Int,
    val specialAttack : Int,
    val specialDefense : Int,
    val speed : Int,
    val statTotal : Int,
    val eggCycles : Int,
    val evYield : String,
    val exp : Int,
    val growthRate : Int,
    val height : Double,
    val weight : Double,
    val happiness : Double,
    val maleFemaleRatio : String,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Pokemon] {
  
  val registry = PokemonRegistry;

}

class Evolution(
    val level : Int,
    val method : String,
    val to : Int) {

}

object EvolutionMethod {
  
}

object PokemonRegistry extends ModelRegistry[Pokemon] {
  
}