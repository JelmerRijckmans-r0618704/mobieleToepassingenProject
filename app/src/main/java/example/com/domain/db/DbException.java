package example.com.domain.db;
public class DbException extends RuntimeException
{
    public DbException()
    {
        super();
    }

    public DbException(String message)
    {
        super(message);
    }

    public DbException(Throwable exception)
    {
        super(exception);
    }

    public DbException(String message, Throwable exception)
    {
        super(message, exception);
    }

}