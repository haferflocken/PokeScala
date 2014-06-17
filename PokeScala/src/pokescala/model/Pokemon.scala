package pokescala.model

import java.time.LocalDateTime
import scala.collection.mutable
import pokescala.net.PokeAPI

class Pokemon(
    val name : String,
    val abilityURIs : Vector[String],
    val eggGroupURIs : Vector[String],
    val evolutions : Vector[Pokemon.Evolution],
    val pokedexEntryURIs : Vector[String],
    val levelUpMoveURIs : Vector[(String, Int)],
    val eggMoveURIs : Vector[String],
    val machineMoveURIs : Vector[String],
    val tutorMoveURIs : Vector[String],
    val typeURIs : Vector[String],
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
  
  def loadAdjacent : Vector[Model[_]] = {
    val buff = new mutable.ArrayBuffer[Model[_]];
    
    for (uri <- abilityURIs; a <- PokeAPI.abilityByURI(uri))
      buff += a;
    for (uri <- eggGroupURIs; e <- PokeAPI.eggGroupByURI(uri))
      buff += e;
    for (e <- evolutions; p <- PokeAPI.pokemonByURI(e.toURI))
      buff += p;
    for (uri <- pokedexEntryURIs; e <- PokeAPI.pokedexEntryByURI(uri))
      buff += e;
    for ((uri, level) <- levelUpMoveURIs; m <- PokeAPI.moveByURI(uri))
      buff += m;
    for (uri <- eggMoveURIs; m <- PokeAPI.moveByURI(uri))
      buff += m;
    for (uri <- machineMoveURIs; m <- PokeAPI.moveByURI(uri))
      buff += m;
    for (uri <- tutorMoveURIs; m <- PokeAPI.moveByURI(uri))
      buff += m;
    for (uri <- typeURIs; t <- PokeAPI.typeByURI(uri))
      buff += t;
    
    return buff.toVector;
  };
  
  override def toString = s"$name; " + super.toString;

}

object Pokemon {
  
  class Evolution(val method : Evolution.Method, val toURI : String) {
    override def toString = s"$method -> $toURI";
  }
  
  object Evolution {
    abstract class Method(val process : String, val detail : String) {
      override def toString = s"$process ($detail)";
    }
    
    object Method {
      case class LevelUp(val level : Int) extends Method("level up", String valueOf level);
      case class Stone(val stone : String) extends Method("stone", stone);
      case class Other(override val detail : String) extends Method("other", detail);
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