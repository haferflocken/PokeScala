package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import scala.util.Try

object PokemonParser extends Parser[Pokemon] {

  def parse(implicit raw : Map[String, Any]) : Try[Pokemon] = Try {
    val (id, resourceURI, created, modified) = extractModelInfo("national_id");
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
        if (obj contains "level") {
          val level = extract[Double]("level")(obj).toInt;
          evolutionsBuff += new Evolution(new LevelUp(level), uri);
        }
        else {
          evolutionsBuff += new Evolution(new LevelUp(-1), uri);
        }
      }
      else if (method equals "stone") {
        evolutionsBuff += new Evolution(new Stone(""), uri);
      }
      else if (method equals "other") {
        val detail = Try { extract[String]("detail")(obj) };
        if (detail.isSuccess)
          evolutionsBuff += new Evolution(new Other(detail.get), uri);
        else
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
        val level = extract[Double]("level")(obj).toInt;
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
    val catchRate = extract[Double]("catch_rate").toInt;
    val species = extract[String]("species");
    
    val hp = extract[Double]("hp").toInt;
    val attack = extract[Double]("attack").toInt;
    val defense = extract[Double]("defense").toInt;
    val specialAttack = extract[Double]("sp_atk").toInt;
    val specialDefense = extract[Double]("sp_def").toInt;
    val speed = extract[Double]("speed").toInt;
    val stats = new Pokemon.Stats(hp, attack, defense, specialAttack, specialDefense, speed);
    
    val eggCycles = extract[Double]("egg_cycles").toInt;
    val evYield = extract[String]("ev_yield");
    val exp = extract[Double]("exp").toInt;
    val growthRate = extract[String]("growth_rate");
    val height = extract[String]("height");
    val weight = extract[String]("weight");
    val happiness = extract[Double]("happiness").toInt;
    val maleFemaleRatio = extract[String]("male_female_ratio");
    
    new Pokemon(
        name, abilities, eggGroups, evolutions, pokedexEntries, levelUpMoves, eggMoves,
        machineMoves, tutorMoves, types, catchRate, species, stats, eggCycles, evYield,
        exp, growthRate, height, weight, happiness, maleFemaleRatio,
        id, resourceURI, created, modified);
  };
  
}