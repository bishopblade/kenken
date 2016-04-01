package tk.aegisstudios.kenken;

import java.util.*;

class Problem {
    private static List<ProblemKey> problemsUsed = new ArrayList<>();

    private static final String[] types = {"sin", "cos", "tan", "csc", "sec", "cot"};
    private static final String[] angles = {"0", "30", "45", "60", "90",
                                                "π/6", "π/4", "π/3", "π/2"};

    private final ProblemKey problem;
    private final String[] solution;

    private Problem(String type, String angle) {
        problem = new ProblemKey(type, angle);
        problemsUsed.add(problem);

        if (angle.equals("π/6")) {
            solution = SolutionMap.solutionMap.get(new ProblemKey(type, "30"));
        } else if (angle.equals("π/4")) {
            solution = SolutionMap.solutionMap.get(new ProblemKey(type, "45"));
        } else if (angle.equals("π/3")) {
            solution = SolutionMap.solutionMap.get(new ProblemKey(type, "60"));
        } else if (angle.equals("π/2")) {
            solution = SolutionMap.solutionMap.get(new ProblemKey(type, "90"));
        } else {
            solution = SolutionMap.solutionMap.get(problem);
        }
    }

    static Problem randomProblem() {
        String type, angle;

        do {
            type = types[new Random().nextInt(types.length)];
            angle = angles[new Random().nextInt(angles.length)];
        } while (problemsUsed.contains(new ProblemKey(type, angle)));

        return new Problem(type, angle);
    }

    String problemToString() {
        return problem.type + " of " + problem.angle;
    }

    String solutionToString() {
        if (solution[1].equals("")) {
            return solution[0];
        } else {
            return solution[0] + "/" + solution[1];
        }
    }
}
