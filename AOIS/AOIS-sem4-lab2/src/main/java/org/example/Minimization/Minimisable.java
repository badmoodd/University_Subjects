package org.example.Minimization;

import org.example.Logic.FormulaType;

import java.util.List;

public interface Minimisable {
    List<List<String>> minimisation(List<List<String>> constituents, FormulaType type);
}
