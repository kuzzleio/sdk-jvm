import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.*
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.coreClasses.SearchResult
import io.kuzzle.sdk.coreClasses.lang.Lang
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.lang.reflect.Method
import java.util.*;
import kotlin.system.exitProcess

public class Snippets {
  [builded-snippets]
}

fun main(args: Array<String>) {
  if (args.isEmpty()) {
      println("Missing snippet name to execute")
      exitProcess(1)
  }

  // Format the snippet name into the method name
  // Convert "kotlin-searchresultscroll_3e8f25b" into "kotlin_searchresultscroll"
  val methodName: String = args[0].lowercase().replace('-', '_').substring(0, args[0].length - 8)

  var snippets: Snippets = Snippets()
  // Find the method that has the given name using reflection
  var method: Method = snippets.javaClass.getMethod(methodName)
  // execute method
  method.invoke(snippets)
}