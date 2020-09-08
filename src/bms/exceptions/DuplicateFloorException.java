package bms.exceptions;

public class DuplicateFloorException extends Exception
{
	public DuplicateFloorException()
	{
		//Normal DuplicateFloorException with no error message or cause.
	}
	
	public DuplicateFloorException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
