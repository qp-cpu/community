package life.majiang.community.enums;

public enum NtificationEnums {
  REPY_QUESTION(1,"回复了问题"),
  REPY_COMMENT(2,"回复了评论")
    ;
    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    NtificationEnums(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
