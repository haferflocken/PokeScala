package pokescala.model

import java.time.LocalDateTime

class Pokemon(
    val name : String,
    val abilities : Vector[String],
    val eggGroups : Vector[String],
    val evolutions : Vector[Pokemon.Evolution],
    val pokedexEntries : Vector[String],
    val levelUpMoves : Vector[(String, Int)],
    val eggMoves : Vector[String],
    val machineMoves : Vector[String],
    val tutorMoves : Vector[String],
    val types : Vector[String],
    val catchRate : Int,
    val species : String,
    val stats : Pokemon.Stats,
    val eggCycles : Int,
    val evYield : String,
    val exp : Int,
    val growthRate : String,
    val height : String,
    val weight : String,
    val happiness : Int,
    val maleFemaleRatio : String,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Pokemon] {
  
  val registry = PokemonRegistry;
  registry.register(this);
  
  override def toString = s"$name; " + super.toString;

}

object Pokemon {
  
  class Evolution(val method : Evolution.Method, val to : String) {
    override def toString = s"$method -> $to";
  }
  
  object Evolution {
    abstract class Method(val process : String, val detail : String) {
      override def toString = s"$process ($detail)";
    }
    
    object Method {
      class LevelUp(val level : Int) extends Method("level up", String valueOf level);
      class Stone(val stone : String) extends Method("stone", stone);
      class Happiness extends Method("happiness", "");
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