package bms.exceptions;

public class NoFloorBelowException extends Exception
{
	public NoFloorBelowException()
	{
		//normal DuplicateFloorException with no error message or cause.
	}
	public NoFloorBelowException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
