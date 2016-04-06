package tk.aegisstudios.kenken;

import java.util.HashMap;
import java.util.Map;

final class SolutionMap {
    public static final Map<ProblemKey, Solution> solutionMap = new HashMap<>();
    static {
        solutionMap.put(new ProblemKey("sin", "0"), new Solution("0", ""));
        solutionMap.put(new ProblemKey("cos", "0"), new Solution("1", ""));
        solutionMap.put(new ProblemKey("tan", "0"), new Solution("0", ""));
        solutionMap.put(new ProblemKey("csc", "0"), new Solution("undefined", ""));
        solutionMap.put(new ProblemKey("sec", "0"), new Solution("1", ""));
        solutionMap.put(new ProblemKey("cot", "0"), new Solution("undefined", ""));

        solutionMap.put(new ProblemKey("sin", "30"), new Solution("1", "2"));
        solutionMap.put(new ProblemKey("cos", "30"), new Solution("sqrt(3)", "2"));
        solutionMap.put(new ProblemKey("tan", "30"), new Solution("sqrt(3)", "3"));
        solutionMap.put(new ProblemKey("csc", "30"), new Solution("2", ""));
        solutionMap.put(new ProblemKey("sec", "30"), new Solution("2sqrt(3)", "3"));
        solutionMap.put(new ProblemKey("cot", "30"), new Solution("sqrt(3)", ""));

        solutionMap.put(new ProblemKey("sin", "45"), new Solution("sqrt(2)", "2"));
        solutionMap.put(new ProblemKey("cos", "45"), new Solution("sqrt(2)", "2"));
        solutionMap.put(new ProblemKey("tan", "45"), new Solution("1", ""));
        solutionMap.put(new ProblemKey("csc", "45"), new Solution("2sqrt(2)", "2"));
        solutionMap.put(new ProblemKey("sec", "45"), new Solution("2sqrt(2)", "2"));
        solutionMap.put(new ProblemKey("cot", "45"), new Solution("1", ""));

        solutionMap.put(new ProblemKey("sin", "60"), new Solution("sqrt(3)", "2"));
        solutionMap.put(new ProblemKey("cos", "60"), new Solution("1", "2"));
        solutionMap.put(new ProblemKey("tan", "60"), new Solution("sqrt(3)", ""));
        solutionMap.put(new ProblemKey("csc", "60"), new Solution("2sqrt(3)", "3"));
        solutionMap.put(new ProblemKey("sec", "60"), new Solution("2", ""));
        solutionMap.put(new ProblemKey("cot", "60"), new Solution("sqrt(3)", "3"));

        solutionMap.put(new ProblemKey("sin", "90"), new Solution("1", ""));
        solutionMap.put(new ProblemKey("cos", "90"), new Solution("0", ""));
        solutionMap.put(new ProblemKey("tan", "90"), new Solution("undefined", ""));
        solutionMap.put(new ProblemKey("csc", "90"), new Solution("1", ""));
        solutionMap.put(new ProblemKey("sec", "90"), new Solution("undefined", ""));
        solutionMap.put(new ProblemKey("cot", "90"), new Solution("0", ""));
    }
}