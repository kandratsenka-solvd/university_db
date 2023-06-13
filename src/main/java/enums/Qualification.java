package enums;

public enum Qualification {
    ENGINEER("engineer"),
    CHEMIST("chemist"),
    SOFTWARE_ENGINEER("software engineer");

    private final String qualification;

    Qualification(String degree) {
        this.qualification = degree;
    }

    public String getQualification() {
        return qualification;
    }
}
