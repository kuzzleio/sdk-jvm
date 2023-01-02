import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.protocol.*;
import io.kuzzle.sdk.coreClasses.responses.Response;
import io.kuzzle.sdk.coreClasses.SearchResult;
import io.kuzzle.sdk.coreClasses.lang.Lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SnippetTest {

  [builded-snippets]

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Missing snippet name to execute");
      System.exit(1);
    }

    // Format the snippet name into the method name
    // Convert "java-searchresultscroll_3e8f25b" into "java_searchresultscroll"
    String methodName = args[0].toLowerCase().replace("-","_").substring(0, args[0].length() - 8);

    try {
        // Find the static method that has the given name using reflection
        Method method = SnippetTest.class.getMethod(methodName);
        // Execute the static method
        method.invoke(null);
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
  }

}