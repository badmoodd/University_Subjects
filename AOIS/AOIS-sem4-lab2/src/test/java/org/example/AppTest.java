package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.Exeptions.LogicalFormulaException;
import org.example.Logic.FormulaParts;
import org.example.Logic.FormulaType;
import org.example.Logic.Parser;
import org.example.Minimization.Minimisable;
import org.example.Minimization.impl.CalculationMethod;
import org.example.Minimization.impl.CalculationTabularMethod;
import org.example.Minimization.impl.KarnoMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {


    public void gluingTest(Minimisable minimisable, String inputString, String expectPDNF, String expectPCNF) throws LogicalFormulaException {
        StringBuilder formula = new StringBuilder(inputString);
        new Parser(formula);
        String fullPDNF = "";
        String fullPCNF = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/Resource/perfectFormulas.txt"))) {
            fullPDNF = reader.readLine().replace("SDNF : ", "");
            fullPCNF = reader.readLine().replace("SKNF : ", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        var pdnf = FormulaParts.parse(fullPDNF, FormulaType.PDNF);
        var pcnf = FormulaParts.parse(fullPCNF, FormulaType.PCNF);

        var minPdnf = minimisable.minimisation(pdnf, FormulaType.PDNF);
        assertEquals(expectPDNF, FormulaParts.fromListToString(minPdnf, true));

        System.out.println();

        var minPcnf = minimisable.minimisation(pcnf, FormulaType.PCNF);
        assertEquals(expectPCNF, FormulaParts.fromListToString(minPcnf, false));

    }

    public void onlyMinimisation(Minimisable minimisable, String inputPDNF, String expectPDNF) {
        var pdnf = FormulaParts.parse(inputPDNF, FormulaType.PDNF);
        var minPdnf = minimisable.minimisation(pdnf, FormulaType.PDNF);
        assertEquals(expectPDNF, FormulaParts.fromListToString(minPdnf, true));
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws LogicalFormulaException {
        assertTrue(true);
    }

    public void testOne() throws LogicalFormulaException {
        gluingTest(new CalculationMethod(), "((A/\\B)\\/C)", "(A&B)|(C)", "(B|C)&(A|C)");
    }

    public void testTwo() throws LogicalFormulaException {
        gluingTest(new CalculationMethod(), "((((!A)/\\B)\\/(C\\/A))/\\A)", "(A)", "(A)");
    }

    public void testThree() throws LogicalFormulaException {
        gluingTest(new CalculationMethod(), "(!(((!A)\\/B)/\\(!(A/\\C))))", "(A&!B)|(A&C)", "(A)&(!B|C)");
    }

    public void testFive() {
        onlyMinimisation(new CalculationMethod(), "(!A&!B&C)|(!A&B&!C)|(A&!B&!C)|(A&B&C)", "(A&!B&!C)|(A&B&C)|(!A&!B&C)|(!A&B&!C)");
    }

    public void testSix() {
        onlyMinimisation(new CalculationMethod(), "(!A&B&C)|(A&!B&C)|(A&B&!C)|(A&B&C)", "(B&C)|(A&B)|(A&C)");
    }

    public void testSeven() {
        onlyMinimisation(new CalculationMethod(),
                "(!A&!B&!C&!D&F)|(A&B&C&D&F)|(!A&B&C&D&F)|(A&!B&C&D&F)|(!A&!B&C&D&F)|(A&B&!C&D&F)|(!A&B&!C&D&F)|(A&!B&!C&D&F)|(!A&!B&!C&D&F)|(A&B&C&!D&F)|(!A&B&C&!D&F)|(A&!B&C&!D&F)|(!A&!B&C&!D&F)|(A&B&!C&!D&F)|(!A&B&!C&!D&F)|(A&!B&!C&!D&F)",
                "(A&!B&F)|(B&!D&F)|(B&C&F)|(!A&!D&F)|(!A&C&F)|(!C&!D&F)|(B&D&F)|(A&B&F)|(A&!C&F)|(!A&D&F)|(!C&D&F)|(!B&!C&F)|(A&C&F)|(A&!D&F)|(C&!D&F)|(!B&!D&F)|(!B&C&F)|(!A&!B&F)|(A&D&F)|(B&!C&F)|(C&D&F)|(!B&D&F)|(!A&!C&F)|(!A&B&F)");
    }

    public void testEight() {
        onlyMinimisation(new CalculationMethod(),
                "(A&!B&F)|(B&!D&F)|(B&C&F)|(!A&!D&F)|(!A&C&F)|(!C&!D&F)|(B&D&F)|(A&B&F)|(A&!C&F)|(!A&D&F)|(!C&D&F)|(!B&!C&F)|(A&C&F)|(A&!D&F)|(C&!D&F)|(!B&!D&F)|(!B&C&F)|(!A&!B&F)|(A&D&F)|(B&!C&F)|(C&D&F)|(!B&D&F)|(!A&!C&F)|(!A&B&F)",
                "(F)");
    }

    public void testNine() {
        onlyMinimisation(new CalculationMethod(),
                "(!A&!B&!C&!D&F)|(!A&B&C&D&F)|(!A&!B&C&D&F)|(!A&B&!C&D&F)|(!A&!B&!C&D&F)|(!A&B&C&!D&F)|(!A&!B&C&!D&F)|(!A&B&!C&!D&F)",
                "(!A&D&F)|(!A&!D&F)|(!A&C&F)|(!A&!C&F)|(!A&B&F)|(!A&!B&F)");

        onlyMinimisation(new CalculationMethod(),
                "(!A&D&F)|(!A&!D&F)|(!A&C&F)|(!A&!C&F)|(!A&B&F)|(!A&!B&F)",
                "(!A&F)");
    }

    public void testTen() {
        onlyMinimisation(new CalculationMethod(),
                "(!A&!B&!C&!D&F)|(!A&!B&C&D&F)|(!A&!B&!C&D&F)|(!A&!B&C&!D&F)",
                "(!A&!B&F)");
    }

    public void testEleven() {
        onlyMinimisation(new CalculationMethod(),
                "(!A&!B&!C&!D&F)|(!A&!B&!C&D&F)",
                "(!A&!B&!C&F)");
    }

    //// calculationTabular
    public void testTwelve() {
        onlyMinimisation(new CalculationTabularMethod(),
                "(!A&B&!C&!D)|(!A&B&!C&D)|(A&!B&C&!D)|(A&!B&C&D)|(A&B&!C&!D)|(A&B&!C&D)|(A&B&C&!D)|(A&B&C&D)",
                "(B&!C)|(A&C)");
    }

    public void testThirteen() {
        onlyMinimisation(new CalculationTabularMethod(),
                "(!A&!B&!C&!D)|(!A&!B&!C&D)|(!A&!B&C&!D)|(!A&!B&C&D)|(A&B&!C&!D)|(A&B&!C&D)|(A&B&C&!D)|(A&B&C&D)",
                "(A&B)|(!A&!B)");
    }

    public void testFourteen() {
        onlyMinimisation(new CalculationTabularMethod(),
                "(!A&!B&C&!D)|(!A&!B&C&D)|(A&!B&!C&!D)|(A&!B&!C&D)|(A&!B&C&!D)|(A&!B&C&D)|(A&B&C&!D)|(A&B&C&D)",
                "(A&!B)|(A&C)|(!B&C)");
    }

    public void testFifteen() {
        onlyMinimisation(new CalculationTabularMethod(),
                "(!A&!B&!C&D)|(!A&!B&C&D)|(!A&B&!C&D)|(!A&B&C&D)|(A&!B&!C&D)|(A&!B&C&D)|(A&B&!C&D)|(A&B&C&D)",
                "(A&D)|(!A&D)");
        onlyMinimisation(new CalculationTabularMethod(),
                "(A&D)|(!A&D)",
                "(D)");

    }

    public void testSixteen() throws LogicalFormulaException {
        gluingTest(new KarnoMethod(), "((A/\\B)\\/((!C)/\\A))", "(A&B)|(A&!C)", "(A)&(B|!C)");
    }


    public void testEighteen() throws LogicalFormulaException {
        gluingTest(new KarnoMethod(), "(!(((!B)\\/A)/\\(!(B/\\C))))", "(B&!A)|(B&C)", "(B)&(!A|C)");
    }


}
