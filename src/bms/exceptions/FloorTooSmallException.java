package bms.exceptions;

public class FloorTooSmallException extends Exception
{
	public FloorTooSmallException()
	{
		//normal FloorBelowTooSmallException with no error message or cause.
	}
	
	public FloorTooSmallException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
