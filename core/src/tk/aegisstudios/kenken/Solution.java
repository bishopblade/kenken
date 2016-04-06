package tk.aegisstudios.kenken;

class Solution {
    String numerator;
    String denominator;

    Solution(String numerator, String denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Solution)) return false;

        Solution otherSolution = (Solution) other;
        return numerator.equals(otherSolution.numerator) && denominator.equals(otherSolution.denominator);
    }

    public String toString() {
        if (denominator.equals("")) {
            return numerator;
        } else {
            return numerator + "/" + denominator;
        }
    }
}
