package enums;

public enum Title {
    LECTURER(1),
    STUDENT(2),
    APPLICANT(3),
    GRADUATE(4);


    private final Integer title;

    Title(Integer title) {
        this.title = title;
    }

    public Integer getTitle() {
        return title;
    }
}
