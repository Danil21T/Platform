package Test;

import IO.ReaderRecords;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

class TestRunner {

    public static void main(String[] args) {
        Result result1 = JUnitCore.runClasses(plarformTest.class);
        System.out.println("plarform Test: ");
        System.out.println("Total number of tests " + result1.getRunCount());
        System.out.println("Total number of tests failed " + result1.getFailureCount());


        Result result2 = JUnitCore.runClasses(WarriorTest.class);
        System.out.println("Warrior Test: ");
        System.out.println("Total number of tests " + result2.getRunCount());
        System.out.println("Total number of tests failed " + result2.getFailureCount());


        Result result3 = JUnitCore.runClasses(SpeedUpTest.class);
        System.out.println("SpeedUp Test: ");
        System.out.println("Total number of tests " + result3.getRunCount());
        System.out.println("Total number of tests failed " + result3.getFailureCount());

        Result result4 = JUnitCore.runClasses(ReaderRecordsTest.class);
        System.out.println("Reader Records Test: ");
        System.out.println("Total number of tests " + result4.getRunCount());
        System.out.println("Total number of tests failed " + result4.getFailureCount());

    }
}