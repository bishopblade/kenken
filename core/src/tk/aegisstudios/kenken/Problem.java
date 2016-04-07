package tk.aegisstudios.kenken;

import java.util.*;

class Problem {
    private static List<ProblemKey> problemsUsed = new ArrayList<>();

    private static final String[] types = {"sin", "cos", "tan", "csc", "sec", "cot"};
    private static final String[] angles = {"0", "30", "45", "60", "90",
                                                "π/6", "π/4", "π/3", "π/2"};

    final ProblemKey problem;
    final Solution solution;

    Problem(String type, String angle) {
        problem = new ProblemKey(type, angle);

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

        if (problemsUsed.size() == 30) {
            return new Problem("sin", "-1000");
        }

        do {
            type = types[new Random().nextInt(types.length)];
            angle = angles[new Random().nextInt(angles.length)];
        } while (problemsUsed.contains(new ProblemKey(type, angle)));

        problemsUsed.add(new ProblemKey(type, angle));
        return new Problem(type, angle);
    }

    boolean isAnswerCorrect(Solution choice) {
        return choice.equals(solution);
    }

    void resetProblemsUsed() {
        problemsUsed = new ArrayList<>();
    }

    Solution[] solutionChoices() {
        Solution[] choices = new Solution[4];
        String[] typesUsed = new String[4];

        choices[0] = solution;
        typesUsed[0] = problem.type;

        for (int i=1; i < 4; i++) {
            String type;
            Random random = new Random();
            do {
                type = types[random.nextInt(types.length)];
            } while (Arrays.asList(typesUsed).contains(type));

            if (problem.angle.equals("-1000")) {
                choices[i] = new Solution("1", "1");
            } else if (problem.angle.equals("π/6")) {
                choices[i] = SolutionMap.solutionMap.get(new ProblemKey(type, "30"));
            } else if (problem.angle.equals("π/4")) {
                choices[i] = SolutionMap.solutionMap.get(new ProblemKey(type, "45"));
            } else if (problem.angle.equals("π/3")) {
                choices[i] = SolutionMap.solutionMap.get(new ProblemKey(type, "60"));
            } else if (problem.angle.equals("π/2")) {
                choices[i] = SolutionMap.solutionMap.get(new ProblemKey(type, "90"));
            } else {
                choices[i] = SolutionMap.solutionMap.get(new ProblemKey(type, problem.angle));
            }

        }

        return choices;
    }

    Solution[] solutionChoicesShuffled() {
        Solution[] solutions = solutionChoices();
        Random random = new Random();

        for (int i = solutions.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            Solution sol = solutions[index];
            solutions[index] = solutions[i];
            solutions[i] = sol;
        }

        return solutions;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Problem)) return false;

        Problem otherKey = (Problem) other;
        return problem.equals(otherKey.problem);
    }
}
