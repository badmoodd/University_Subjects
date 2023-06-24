package org.example.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FormulaParts {
    public static List<List<String>> parse(String formula, FormulaType type) {
        String noBrackets = formula
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");

        List<String> parts;
        List<List<String>> constituentArray = new ArrayList<>();
        if (type.equals(FormulaType.PDNF)) {
            parts = new ArrayList<>(Arrays.asList(noBrackets.split("\\|")));
            for (String element : parts) {
                List<String> elementary = new ArrayList<>(Arrays.asList(element.split("\\&")));
                constituentArray.add(elementary);
            }
        }
        if (type.equals(FormulaType.PCNF)) {
            parts = new ArrayList<>(Arrays.asList(noBrackets.split("\\&")));
            for (String element : parts) {
                List<String> elementary = new ArrayList<>(Arrays.asList(element.split("\\|")));
                constituentArray.add(elementary);
            }
        }
        return constituentArray;
    }

    public static String fromListToString(List<List<String>> formula, boolean isPdnf) {
        String internalConnection = isPdnf ? "&" : "|";
        String externalConnection = !isPdnf ? "&" : "|";
        StringBuilder output = new StringBuilder();
        for (List<String> i : formula) {
            output.append("(");
            for (String j : i) {
                output.append(j);
                output.append(internalConnection);
            }
            output.replace(output.length() - 1, output.length(), "");
            output.append(")");
            output.append(externalConnection);
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
