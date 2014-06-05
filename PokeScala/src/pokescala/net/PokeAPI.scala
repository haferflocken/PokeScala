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
  
  def getAbility(id : Int) : Option[Ability] = getAbility(s"api/v1/ability/$id/");
  
  def getAbility(resourceURI : String) : Option[Ability] = getModel(resourceURI, AbilityRegistry, AbilityParser);
  
  def getEggGroup(id : Int) : Option[EggGroup] = getEggGroup(s"api/v1/egg/$id/");
  
  def getEggGroup(resourceURI : String) : Option[EggGroup] = getModel(resourceURI, EggGroupRegistry, EggGroupParser);
  
  def getGame(id : Int) : Option[Game] = getGame(s"api/v1/game/$id/");
  
  def getGame(resourceURI : String) : Option[Game] = getModel(resourceURI, GameRegistry, GameParser);
  
  def getMove(id : Int) : Option[Move] = getMove(s"api/v1/move/$id/");
  
  def getMove(resourceURI : String) : Option[Move] = getModel(resourceURI, MoveRegistry, MoveParser);
  
  def getPokedex : Option[Pokedex] = getModel("api/v1/pokedex/1", PokedexRegistry, PokedexParser);
  
  def getPokedexEntry(id : Int) : Option[PokedexEntry] = getPokedexEntry(s"api/v1/description/$id/");
  
  def getPokedexEntry(resourceURI : String) : Option[PokedexEntry] = getModel(resourceURI, PokedexEntryRegistry, PokedexEntryParser);
  
  def getPokemon(id : Int) : Option[Pokemon] = getPokemon(s"api/v1/pokemon/$id/");
  
  def getPokemon(resourceURI : String) : Option[Pokemon] = getModel(resourceURI, PokemonRegistry, PokemonParser);
  
  def getSprite(id : Int) : Option[Sprite] = getSprite(s"api/v1/sprite/$id/");
  
  def getSprite(resourceURI : String) : Option[Sprite] = getModel(resourceURI, SpriteRegistry, SpriteParser);

  def getType(id : Int) : Option[Type] = getType(s"api/v1/type/$id/");
  
  def getType(resourceURI : String) : Option[Type] = getModel(resourceURI, TypeRegistry, TypeParser);
}