package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object PokemonParser extends Parser[Pokemon] {

  def parse(implicit raw : Map[String, Any]) : Pokemon = {
    val (id, resourceURI, created, modified) = extractModelInfo(raw, "national_id");
    val name = extract[String]("name");
    val abilities = extractResourceURIs(raw("abilities"));
    val eggGroups = extractResourceURIs(raw("egg_groups"));
    
    val rawEvolutions = asVector(raw("evolutions"));
    val evolutionsBuff = new mutable.ArrayBuffer[Pokemon.Evolution];
    
    for (elem <- rawEvolutions) {
      import Pokemon.Evolution
      import Pokemon.Evolution.Method._
      val obj = elem.asInstanceOf[Map[String, Any]];
      val method = extract[String]("method")(obj);
      val uri = extract[String]("resource_uri")(obj);
      if (method equals "level_up") {
        val level = extract[Int]("level")(obj);
        evolutionsBuff += new Evolution(new LevelUp(level), uri);
      }
      else if (method equals "stone") {
        evolutionsBuff += new Evolution(new Stone(""), uri);
      }
      else if (method equals "happiness") {
        evolutionsBuff += new Evolution(new Happiness, uri);
      }
      else if (method equals "mega") {
        evolutionsBuff += new Evolution(new Mega(null), uri);
      }
      else if (method equals "other") {
        evolutionsBuff += new Evolution(new Other(null), uri);
      }
    }
    
    val evolutions = evolutionsBuff.toVector;
    
    val pokedexEntries = extractResourceURIs(raw("descriptions"));
    
    val rawMoves = asVector(raw("moves"));
    val levelUpMovesBuff = new mutable.ArrayBuffer[(String, Int)];
    val eggMovesBuff = new mutable.ArrayBuffer[String];
    val machineMovesBuff = new mutable.ArrayBuffer[String];
    val tutorMovesBuff = new mutable.ArrayBuffer[String];
    
    for (elem <- rawMoves) {
      val obj = elem.asInstanceOf[Map[String, Any]];
      val learnType = extract[String]("learn_type")(obj);
      val uri = extract[String]("resource_uri")(obj);
      if (learnType equals "level up") {
        val level = extract[Int]("level")(obj);
        levelUpMovesBuff += ((uri, level));
      }
      else if (learnType equals "egg move")
        eggMovesBuff += uri;
      else if (learnType equals "machine")
        machineMovesBuff += uri;
      else if (learnType equals "tutor")
        tutorMovesBuff += uri;
    }
    
    val levelUpMoves = levelUpMovesBuff.toVector;
    val eggMoves = eggMovesBuff.toVector;
    val machineMoves = machineMovesBuff.toVector;
    val tutorMoves = tutorMovesBuff.toVector;
    
    val types = extractResourceURIs(raw("types"));
    val catchRate = extract[Int]("catch_rate");
    val species = extract[String]("species");
    
    val hp = extract[Int]("hp");
    val attack = extract[Int]("attack");
    val defense = extract[Int]("defense");
    val specialAttack = extract[Int]("sp_atk");
    val specialDefense = extract[Int]("sp_def");
    val speed = extract[Int]("speed");
    val stats = new Pokemon.Stats(hp, attack, defense, specialAttack, specialDefense, speed);
    
    val eggCycles = extract[Int]("egg_cycles");
    val evYield = extract[String]("ev_yield");
    val exp = extract[Int]("exp");
    val growthRate = extract[String]("growthRate");
    val height = extract[String]("height");
    val weight = extract[String]("weight");
    val happiness = extract[Int]("happiness");
    val maleFemaleRatio = extract[String]("male_female_ratio");
    
    return new Pokemon(
        name, abilities, eggGroups, evolutions, pokedexEntries, levelUpMoves, eggMoves,
        machineMoves, tutorMoves, types, catchRate, species, stats, eggCycles, evYield,
        exp, growthRate, height, weight, happiness, maleFemaleRatio,
        id, resourceURI, created, modified);
  };
  
}