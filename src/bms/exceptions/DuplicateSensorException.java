package bms.exceptions;

public class DuplicateSensorException extends Exception
{
	public DuplicateSensorException()
	{
		//normal DuplicateSensorException with no error message or cause.
	}
	
	public DuplicateSensorException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
