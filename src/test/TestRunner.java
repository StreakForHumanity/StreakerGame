package test;

import logic.configuration.HighScoreException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TestRunner {
    static File testFile;
    static int entries;

    public static void main(String[] args) {
        Class<?>[] classes = new Class<?>[6];
        classes[0] = SuiteAnimatedAndWorld.class;
        classes[1] = SuiteBackgroundAndTerrain.class;
        classes[2] = SuiteCharacterAndGuard.class;
        classes[3] = SuiteLongAndInt.class;
        classes[4] = SuiteStrControllerAndCoin.class;
        classes[5] = SuiteTunnelAndPosition.class;
        Result result = JUnitCore.runClasses(classes);
        entries = result.getFailureCount();

        try {
            testFile = new File(".testResults.dat");
        } catch (Exception e) {
            //do nothing
        }

        for (Failure failure : result.getFailures()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
                for (int i = 0; i < result.getFailureCount(); i++) {
                    writer.write(failure.toString() + "\n");
                }
            } catch (Exception e) {
                //do nothing
            }
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            for (int i = 0; i < result.getFailureCount(); i++) {
                writer.write(result.wasSuccessful() + "\n");
            }
        } catch (Exception e) {
            //do nothing
        }
    }
} 
