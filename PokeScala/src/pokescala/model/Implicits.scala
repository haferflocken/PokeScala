package pokescala.model

object Implicits {
  
  implicit def idToAbility(id : Int) = AbilityRegistry(id);
  
  implicit def idToEggGroup(id : Int) = EggGroupRegistry(id);
  
  implicit def idToGame(id : Int) = GameRegistry(id);
  
  implicit def idToMove(id : Int) = MoveRegistry(id);
  
  implicit def idToPokedex(id : Int) = PokedexRegistry(id);
  
  implicit def idToPokedexEntry(id : Int) = PokedexEntryRegistry(id);
  
  implicit def idToPokemon(id : Int) = PokemonRegistry(id);
  
  implicit def idToSprite(id : Int) = SpriteRegistry(id);
  
  implicit def idToType(id : Int) = TypeRegistry(id);

}