package pokescala.net

import java.io.File
import java.io.FileWriter
import scala.util.Try

object Cacher {
  
  val defaultCacheLoc = "C:/pokeapi";
  
  private var _cacheLoc : String = defaultCacheLoc;
  
  def cacheLoc = _cacheLoc;
  
  def cacheLoc_=(loc : String) : Unit = {
    if ((loc endsWith "/") || (loc endsWith "\\"))
      _cacheLoc = loc.substring(0, loc.length - 1);
    else
      _cacheLoc = loc;
  };
  
  private def uriToFilePath(uri : String) : String = {
    if ((uri startsWith "/") && (uri endsWith "/"))
      return _cacheLoc + uri.substring(0, uri.length - 1) + ".txt";
    else if (uri startsWith "/")
      return _cacheLoc + uri + ".txt";
    else if (uri endsWith "/")
      return _cacheLoc + "/" + uri.substring(0, uri.length - 1) + ".txt";
    else
      return _cacheLoc + "/" + uri + ".txt";
  };
  
  private[net] def readFrom(uri : String) : Try[String] = Try {
    val filePath = uriToFilePath(uri);
    val source = scala.io.Source.fromFile(filePath);
    val lines = source.mkString;
    source.close();
    lines;
  };
  
  private[net] def writeTo(raw : String, uri : String) : Unit = {
    val saveLoc = new File(uriToFilePath(uri));
    if (!saveLoc.exists)
      saveLoc.createNewFile;
    val writer = new FileWriter(saveLoc, false);
    writer.write(raw);
    writer.close;
  };

}