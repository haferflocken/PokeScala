package pokescala.net

import pokescala.model._
import pokescala.model.Implicits._
import pokescala.parse._
import java.net.URL
import java.io.InputStreamReader
import java.io.BufferedReader
import scala.util.Try
import scala.util.Success
import scala.util.control.NonFatal
import scala.util.Failure
import java.io.File
import java.io.FileWriter

object PokeAPI {
  
  private def getRaw(uri : String) : Try[String] = Try {
    val source = io.Source.fromURL("http://pokeapi.co/" + uri);
    val contents = source.getLines.mkString("\n");
    source.close;
    Cacher.writeRaw(contents, uri);
    contents;
  };
  
  private def getModel[M <: Model[M]](resourceURI : String, registry : ModelRegistry[M], parser : Parser[M]) : Option[M] = {
    if (registry.contains(resourceURI))
      return Some(registry(resourceURI));
    
    val raw = getRaw(resourceURI);
    if (raw.isFailure) {
      println(s"Failed to get raw for $resourceURI: " + raw.failed.get);
      return None;
    }
    
    val parsed = parser.parse(raw.get);
    if (parsed.isFailure)
      println(s"Failed to parse raw from $resourceURI");
    return parsed.toOption;
  };
  
  def abilityById(id : Int) : Option[Ability] = abilityByURI(s"api/v1/ability/$id/");
  
  def abilityByURI(resourceURI : String) : Option[Ability] = getModel(resourceURI, AbilityRegistry, AbilityParser);
  
  def eggGroupById(id : Int) : Option[EggGroup] = eggGroupByURI(s"api/v1/egg/$id/");
  
  def eggGroupByURI(resourceURI : String) : Option[EggGroup] = getModel(resourceURI, EggGroupRegistry, EggGroupParser);
  
  def gameById(id : Int) : Option[Game] = gameByURI(s"api/v1/game/$id/");
  
  def gameByURI(resourceURI : String) : Option[Game] = getModel(resourceURI, GameRegistry, GameParser);
  
  def moveById(id : Int) : Option[Move] = moveByURI(s"api/v1/move/$id/");
  
  def moveByURI(resourceURI : String) : Option[Move] = getModel(resourceURI, MoveRegistry, MoveParser);
  
  def pokedex : Option[Pokedex] = getModel("api/v1/pokedex/1", PokedexRegistry, PokedexParser);
  
  def pokedexEntryById(id : Int) : Option[PokedexEntry] = pokedexEntryByURI(s"api/v1/description/$id/");
  
  def pokedexEntryByURI(resourceURI : String) : Option[PokedexEntry] = getModel(resourceURI, PokedexEntryRegistry, PokedexEntryParser);
  
  def pokemonById(id : Int) : Option[Pokemon] = pokemonByURI(s"api/v1/pokemon/$id/");
  
  def pokemonByName(name : String) : Option[Pokemon] = pokemonByURI(s"api/v1/pokemon/$name/");
  
  def pokemonByURI(resourceURI : String) : Option[Pokemon] = getModel(resourceURI, PokemonRegistry, PokemonParser);
  
  def spriteById(id : Int) : Option[Sprite] = spriteByURI(s"api/v1/sprite/$id/");
  
  def spriteByURI(resourceURI : String) : Option[Sprite] = getModel(resourceURI, SpriteRegistry, SpriteParser);

  def typeById(id : Int) : Option[Type] = typeByURI(s"api/v1/type/$id/");
  
  def typeByURI(resourceURI : String) : Option[Type] = getModel(resourceURI, TypeRegistry, TypeParser);
}