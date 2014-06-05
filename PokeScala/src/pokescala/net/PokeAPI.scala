package pokescala.net

import pokescala.model._
import pokescala.model.Implicits._
import pokescala.parse._
import java.net.URL
import java.io.InputStreamReader
import java.io.BufferedReader

object PokeAPI {
  
  def getRaw(uri : String) : String = {
    val stream = new URL("http://pokeapi.co/" + uri).openStream;
    val in = new BufferedReader(new InputStreamReader(stream));
    
    val buff = new StringBuilder;
    var line : String = in.readLine;
    while (line != null) {
      buff.append(line);
      line = in.readLine;
    } 
    in.close;
    
    return buff.toString.trim;
  };
  
  def getAbility(id : Int) : Ability = getAbility(s"api/v1/ability/$id/");
  
  def getAbility(resourceURI : String) : Ability = {
    val cached = AbilityRegistry.contains(resourceURI);
    if (cached)
      return AbilityRegistry(resourceURI);
    
    val raw = getRaw(resourceURI);
    val parsed = AbilityParser.parse(raw);
    return parsed;
  };
  
  def getEggGroup(id : Int) : EggGroup = getEggGroup(s"api/v1/egg/$id/");
  
  def getEggGroup(resourceURI : String) : EggGroup = {
    val cached = EggGroupRegistry.contains(resourceURI);
    if (cached)
      return EggGroupRegistry(resourceURI);
    
    val raw = getRaw("api/v1/egg/" + resourceURI);
    val parsed = EggGroupParser.parse(raw);
    return parsed;
  };
  
  def getGame(id : Int) : Game = getGame(s"api/v1/game/$id/");
  
  def getGame(resourceURI : String) : Game = {
    val cached = GameRegistry.contains(resourceURI);
    if (cached)
      return GameRegistry(resourceURI);
    
    val raw = getRaw("api/v1/game/" + resourceURI);
    val parsed = GameParser.parse(raw);
    return parsed;
  };
  
  def getMove(id : Int) : Move =  getMove(s"api/v1/move/$id/");
  
  def getMove(resourceURI : String) : Move = {
    val cached = MoveRegistry.contains(resourceURI);
    if (cached)
      return MoveRegistry(resourceURI);
    
    val raw = getRaw("api/v1/move/" + resourceURI);
    val parsed = MoveParser.parse(raw);
    return parsed;
  };
  
  def getPokedex : Pokedex = {
    val cached = PokedexRegistry.contains(1);
    if (cached)
      return PokedexRegistry(1);
    
    val raw = getRaw("api/v1/pokedex/1");
    val parsed = PokedexParser.parse(raw);
    return parsed;
  };
  
  def getPokedexEntry(id : Int) : PokedexEntry = getPokedexEntry(s"api/v1/description/$id/");
  
  def getPokedexEntry(resourceURI : String) : PokedexEntry = {
    val cached = PokedexEntryRegistry.contains(resourceURI);
    if (cached)
      return PokedexEntryRegistry(resourceURI);
    
    val raw = getRaw(resourceURI);
    val parsed = PokedexEntryParser.parse(raw);
    return parsed;
  };
  
  def getPokemon(id : Int) : Pokemon = getPokemon(s"api/v1/pokemon/$id/");
  
  def getPokemon(resourceURI : String) : Pokemon = {
    val cached = PokemonRegistry.contains(resourceURI);
    if (cached)
      return PokemonRegistry(resourceURI);
    
    val raw = getRaw(resourceURI);
    val parsed = PokemonParser.parse(raw);
    return parsed;
  };
  
  def getSprite(id : Int) : Sprite = getSprite(s"api/v1/sprite/$id/");
  
  def getSprite(resourceURI : String) : Sprite = {
    val cached = SpriteRegistry.contains(resourceURI);
    if (cached)
      return SpriteRegistry(resourceURI);
    
    val raw = getRaw(resourceURI);
    val parsed = SpriteParser.parse(raw);
    return parsed;
  };

  def getType(id : Int) : Type = getType(s"api/v1/type/$id/");
  
  def getType(resourceURI : String) : Type = {
    val cached = TypeRegistry.contains(resourceURI);
    if (cached)
      return TypeRegistry(resourceURI);
    
    val raw = getRaw(resourceURI);
    val parsed = TypeParser.parse(raw);
    return parsed;
  };
}