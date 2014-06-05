package pokescala.net

import java.io.File
import java.io.FileWriter

object Cacher {
  
  var cacheLoc : String = "C:/pokeapi";
  
  private def uriToFilePath(uri : String) : String = {
    if ((uri startsWith "/") && (uri endsWith "/"))
      return cacheLoc + uri.substring(0, uri.length - 1) + ".txt";
    else if (uri startsWith "/")
      return cacheLoc + uri + ".txt";
    else if (uri endsWith "/")
      return cacheLoc + "/" + uri.substring(0, uri.length - 1) + ".txt";
    else
      return cacheLoc + "/" + uri + ".txt";
  }
  
  /*def getRaw(uri : String) : Try[String] = Try {
    val filePath = uriToFilePath(uri);
    val source = scala.io.Source.fromFile(filePath);
    val lines = source.mkString("\n");
    source.close();
    lines;
  };*/
  
  def writeRaw(raw : String, uri : String) : Unit = {
    val saveLoc = new File(uriToFilePath(uri));
    if (!saveLoc.exists)
      saveLoc.createNewFile;
    val writer = new FileWriter(saveLoc, false);
    writer.write(raw);
    writer.close;
  }

}