package test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
	  Class<?>[] classes = new Class<?>[6];
	  classes[0] = SuiteAnimatedAndWorld.class;
	  classes[1] = SuiteBackgroundAndTerrain.class;
	  classes[2] = SuiteCharacterAndGuard.class;
	  classes[3] = SuiteLongAndInt.class;
	  classes[4] = SuiteStrControllerAndCoin.class;
	  classes[5] = SuiteTunnelAndPosition.class;
	  
      Result result = JUnitCore.runClasses(classes);

      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
} 
