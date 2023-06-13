package enums;

public enum Title {
    LECTURER("lecturer"),
    STUDENT("student"),
    APPLICANT("applicant"),
    GRADUATE("graduate");


    private final String title;

    Title(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
