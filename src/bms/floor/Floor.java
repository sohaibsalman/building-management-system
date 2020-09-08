package bms.floor;

import java.util.ArrayList;
import java.util.List;

import bms.exceptions.DuplicateRoomException;
import bms.exceptions.InsufficientSpaceException;
import bms.room.Room;
import bms.room.RoomType;
import bms.util.FireDrill;

public class Floor extends Object implements FireDrill
{
	private int floorNumber;		//to store the floor number
	private List<Room> roomsList;	//to store rooms in a floor	
	private double width;		//to store width of the floor
	private double length;		//to store length of the floor
	
	/* Constructor */
	public Floor(int floorNumber, double width, double length)
	{
		/* Initialize the data members */
		this.floorNumber = floorNumber;
		this.length = length;
		this.width = width;
		
		/* Initialize the rooms list */
		roomsList = new ArrayList<Room>();
	}
	
	/* Method that will return the floor number */
	public int getFloorNumber()
	{
		return this.floorNumber;
	}
	
	/* Method that will return minimum width required for a floor */
	public static int getMinWidth()
	{
		return 5;		//5 is the minimum width for a floor
	}
	
	/* Method that will return minimum length required for a floor */
	public static int getMinLength()
	{
		return 5;		//5 is the minimum length for a floor
	}
	
	/* Method that will return list of rooms in a floor */
	public List<Room> getRooms()
	{
		List<Room> newList = new ArrayList<Room>(roomsList);
		return newList;
	}
	
	/* Method that will return the width of floor */
	public double getWidth()
	{
		return this.width;
	}
	
	/* Method that will return the length of the floor */
	public double getLength()
	{
		return this.length;
	}
	
	/* Method that will find and return a room by its number */
	public Room getRoomByNumber(int roomNumber)
	{
		/* Iterate over the list of rooms */
		for (Room room : roomsList)
		{
			/* Check if this is the required room  */
			if(room.getRoomNumber() == roomNumber)
				return room;		//required room found, return it
		}
		
		/* Required room was not found */
		return null;
	}
	
	/* Method that will calculate the area of a floor */
	public double calculateArea()
	{
		return getWidth() * getLength();
	}
	
	/* Method that will return the area occupied in the floor */
	public float occupiedArea()
	{
		float areaOccupied = 0;		//to store the area occupied
		
		/* Iterate over list of rooms to get their area */
		for (Room room : roomsList)
		{
			/* Add each room's area to the occupied area */
			areaOccupied = areaOccupied + (float)room.getArea();
		}
		
		/* Return the area occupied by the rooms in a floor */
		return areaOccupied;
	}
	
	/* Method that will add a room in a floor */
	public void addRoom(Room newRoom) throws DuplicateRoomException, InsufficientSpaceException
	{
		/* Check for the exceptions */
		if(newRoom.getArea() < Room.getMinArea())		//room area is not enough
			throw new IllegalArgumentException();	//throw exception
		/* Iterate over the room list and to search that the room already exists */
		for (Room room : roomsList)
		{
			/* Check each room */
			if(room.getRoomNumber() == newRoom.getRoomNumber())
				throw new DuplicateRoomException();		//duplicate room found, throw exception
		}
		if((calculateArea() - occupiedArea()) < newRoom.getArea())		//floor does not have enough space
			throw new InsufficientSpaceException();		//throw exception
		
		/* No exceptions, add the room to the list */
		this.roomsList.add(newRoom);
	}

	/* Method that will set fire drill of all the rooms that matches the room type */
	@Override
	public void fireDrill(RoomType roomType)
	{
		/* If room type is null, set fire drill for all rooms */
		if(roomType == null)
		{
			for (Room room : roomsList)
			{
				room.setFireDrill(true);		//set fire drill for all rooms
			}
		}
		/* Otherwise, set fire drill for the rooms that matches the room type */
		else 
		{
			for (Room room : roomsList)
			{
				if(room.getType() == roomType)
					room.setFireDrill(true);		//set fire drill form specific rooms
			}
		}
	}
	
	/* Method that will cancel fire drill in all rooms */
	public void cancelFireDrill()
	{
		for (Room room : roomsList)
		{
			room.setFireDrill(false);		//set fire drill false for all rooms
		}
	}
	
	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return "Floor # " + this.floorNumber + ": width=" + this.width
				+ "m, length=" + this.length + "m, rooms=" + roomsList.size();
	}
	
}
