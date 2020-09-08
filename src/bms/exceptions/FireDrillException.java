package bms.exceptions;

public class FireDrillException extends Exception
{
	public FireDrillException()
	{
		//normal FireDrillException with no error message or cause.
	}
	
	public FireDrillException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
