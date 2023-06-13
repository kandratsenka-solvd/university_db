package enums;

public enum Degree {
    BACHELOR("bachelor"),
    MASTER("master"),
    DOCTOR("doctor");

    private final String degree;

    Degree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }
}
