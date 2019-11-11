package life.majiang.community.exception;

public class CustmizeException  extends  RuntimeException{
    private  String message;

    public CustmizeException(String message)
    {
        this.message=message;
    }

    public CustmizeException(ICustomizeErrorcodde errorcodde)
    {
        this.message=errorcodde.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
