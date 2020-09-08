package bms.exceptions;

public class InsufficientSpaceException extends Exception
{
	public InsufficientSpaceException()
	{
		//normal InsufficientSpaceException with no error message or cause.
	}
	public InsufficientSpaceException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
