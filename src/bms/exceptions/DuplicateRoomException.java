package bms.exceptions;

public class DuplicateRoomException extends Exception
{
	public DuplicateRoomException()
	{
		//normal DuplicateRoomException with no error message or cause.
	}
	
	public DuplicateRoomException(String message)
	{
		System.out.println(message);
		getCause();
	}
}
