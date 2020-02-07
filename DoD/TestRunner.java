import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result1 = JUnitCore.runClasses(MapTest.class);
      Result result2 = JUnitCore.runClasses(GameLogicTest.class);

      for (Failure failure1 : result1.getFailures()) {
         System.out.println(failure1.toString());
      }
		
      System.out.println(result1.wasSuccessful());
      
      for (Failure failure2 : result2.getFailures()) {
          System.out.println(failure2.toString());
       }
 		
       System.out.println(result2.wasSuccessful());
   }
}