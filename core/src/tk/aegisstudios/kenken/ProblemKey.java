package tk.aegisstudios.kenken;

public class ProblemKey {
    public String type;
    public String angle;

    public ProblemKey(String type, String angle) {
        this.type = type;
        this.angle = angle;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ProblemKey)) return false;

        ProblemKey otherKey = (ProblemKey) other;
        return type.equals(otherKey.type) && angle.equals(otherKey.angle);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (angle != null ? angle.hashCode() : 0);
        return result;
    }
}
