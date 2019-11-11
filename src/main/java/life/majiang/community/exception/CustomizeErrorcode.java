package life.majiang.community.exception;

public enum CustomizeErrorcode implements  ICustomizeErrorcodde{
    QUESTION_NOT("问题没有没有找到，换个问题试试");
    private String message;
    @Override
    public String getMessage() {
        return message;
    }
    CustomizeErrorcode(String message)
    {
        this.message=message;
    }
}
