package enums;

public enum Qualification {
    ENGINEER(1),
    CHEMIST(2),
    SOFTWARE_ENGINEER(3);

    private final Integer qualification;

    Qualification(Integer degree) {
        this.qualification = degree;
    }

    public Integer getQualification() {
        return qualification;
    }
}
