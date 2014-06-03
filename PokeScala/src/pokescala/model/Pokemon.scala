package pokescala.model

import java.time.LocalDateTime

class Pokemon(
    val name : String,
    val id : Int,
    val abilities : Vector[Int],
    val eggGroups : Vector[Int],
    val evolutions : Vector[Pokemon.Evolution],
    val pokedexEntries : Vector[Int],
    val moves : Vector[Int],
    val types : Vector[Int],
    val catchRate : Int,
    val species : String,
    val stats : Pokemon.Stats,
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

object Pokemon {
  
  class Evolution(val method : Evolution.Method, val to : Int) {}
  
  object Evolution {
    abstract class Method(val process : String, val detail : String) {
      override def toString = process + " - " + detail;
    }
    
    object Method {
      class LevelUp(val level : Int) extends Method("level up", String valueOf level);
      class Stone(val stone : String) extends Method("stone", stone);
      class Happiness extends Method("happiness", "");
      class Mega(override val detail : String) extends Method("mega", detail);
      class Other(override val detail : String) extends Method("other", detail);
    }
  }
  
  class Stats(
      val hp : Int,
      val attack : Int,
      val defense : Int,
      val specialAttack : Int,
      val specialDefense : Int,
      val speed : Int) {
    
    val total = hp + attack + defense + specialAttack + specialDefense + speed;
  }

}

object PokemonRegistry extends ModelRegistry[Pokemon] {
  
}