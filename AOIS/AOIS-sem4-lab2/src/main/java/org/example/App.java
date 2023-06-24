package org.example;

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
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws LogicalFormulaException {
        System.out.println("Введите формулу: ");
        Scanner scannerString = new Scanner(System.in);
        StringBuilder formula = new StringBuilder(scannerString.next());
        new Parser(formula);

        System.out.println("\n\nМнинимизация");

        System.out.println("_____________________________________________________________________");
        System.out.println("\nРасчетный метод\n");
        minimasing(new CalculationMethod());

        System.out.println("_____________________________________________________________________");
        System.out.println("\nРасчетно табличный метод\n");
        minimasing(new CalculationTabularMethod());

        System.out.println("_____________________________________________________________________");
        System.out.println("\nТабличный метод\n");
        minimasing(new KarnoMethod());
    }

    public static void minimasing(Minimisable minimisable) {
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

        var shortPdnf = minimisable.minimisation(pdnf, FormulaType.PDNF);
        System.out.println("PDNF: " + FormulaParts.fromListToString(shortPdnf, true));


        System.out.println();

        var shortPcnf = minimisable.minimisation(pcnf, FormulaType.PCNF);
        System.out.println("PCNF: " + FormulaParts.fromListToString(shortPcnf, false));


    }
}
