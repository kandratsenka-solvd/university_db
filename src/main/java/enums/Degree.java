package enums;

public enum Degree {
    BACHELOR(1),
    MASTER(2),
    DOCTOR(3),
    CUSTOM(4);

    private final Integer degree;

    Degree(Integer degree) {
        this.degree = degree;
    }

    public Integer getDegree() {
        return degree;
    }
}
