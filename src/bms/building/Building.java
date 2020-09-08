package bms.building;

import java.util.ArrayList;
import java.util.List;

import bms.exceptions.DuplicateFloorException;
import bms.exceptions.FireDrillException;
import bms.exceptions.FloorTooSmallException;
import bms.exceptions.NoFloorBelowException;
import bms.floor.Floor;
import bms.room.Room;
import bms.room.RoomType;
import bms.util.FireDrill;

public class Building extends Object implements FireDrill
{
	private String name;		//to store name of the building	
	private List<Floor> floorList;		//to store floors in a building
	
	/* Constructor */
	public Building(String name)
	{
		/* Initialize the data members */
		this.name = name;
		this.floorList = new ArrayList<Floor>();
	}
	
	/* Method that will return the name of the building */
	public String getName()
	{
		return this.name;
	}
	
	/* Method that will return list of floors in a building */
	public List<Floor> getFloors()
	{
		List<Floor> newList = new ArrayList<>(this.floorList);	//create copy of list
		return newList;	//return the copy
	}

	/* Method that will return a specific floor by its number */
	public Floor getFloorByNumber(int floorNumber)
	{
		/* Iterate over the list of floors to find the floor */
		for (Floor floor : floorList)
		{
			/* Check if this is the required floor */
			if(floor.getFloorNumber() == floorNumber)
				return floor;	//floor is found, return it
		}
		
		/* Floor was not found */
		return null;
	}
	
	public void addFloor(Floor newFloor) throws IllegalArgumentException, DuplicateFloorException,
												NoFloorBelowException, FloorTooSmallException
	{
		/* Throw exception if floor number is invalid,
		 * or width of floor is invalid, 
		 * or the length of the floor is invalid */
		if(newFloor.getFloorNumber() < 0 || newFloor.getWidth() < Floor.getMinWidth() || newFloor.getLength() < Floor.getMinLength())
			throw new IllegalArgumentException();
		
		/* Throw an exception if the floor already exisits */
		for (Floor floor : floorList)
		{
			if(floor.getFloorNumber() == newFloor.getFloorNumber())
				throw new DuplicateFloorException();
		}
		
		if(newFloor.getFloorNumber() >= 2)
		{
			/* Throw exception if there is no floor below */
			boolean hasFloorBelow = false;
			for (Floor floor : floorList)
			{
				//get the floor below
				if((newFloor.getFloorNumber() - floor.getFloorNumber()) == 1)
				{
					hasFloorBelow = true;
					break;
				}
			}
			if(!hasFloorBelow)
				throw new NoFloorBelowException();
			
			/* Throw exception if the below floor is too small */
			for (Floor floor : floorList)
			{
				//first get the floor below
				if((newFloor.getFloorNumber() - floor.getFloorNumber()) == 1)
				{
					if((newFloor.calculateArea()) > floor.calculateArea())
						throw new FloorTooSmallException();
				}
			}
		}
		
		/* No exceptions occurred, add the floor to the building */
		this.floorList.add(newFloor);
	}
	
	/* Method that will set fire drill for some specific rooms */
	@Override
	public void fireDrill(RoomType roomType) throws FireDrillException
	{
		/* if the room type is null, set fire drill in all rooms to true */
		if(roomType == null)
		{
			/* Get all floors in the building */
			for (Floor floor : floorList)
			{
				/* Get all rooms in the floor */
				for (Room room : floor.getRooms())
				{
						room.setFireDrill(true);	//set fire drill to true
				}
			}
		}
		/* Otherwise, set fire drill for some specific rooms */
		else
		{
			/* get all floors in the building */
			for (Floor floor : floorList)
			{
				/* get all rooms in a floor */
				for (Room room : floor.getRooms())
				{
					if(room.getType() == roomType)	//room type matches
						room.setFireDrill(true);	//set fire drill to true
				}
			}
		}	
	}
	
	/* Method that will cancel fire drills in all rooms */
	public void cancelFireDrill()
	{
		/* Get all the floors in building */
		for (Floor floor : floorList)
		{
			/* Get all the rooms in a floor */
			for (Room room : floor.getRooms())
			{
				/* Set fire drill to false */
				room.setFireDrill(false);
			}
		}
	}
	
	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return "Building: name=" + this.name + ", floors=" + this.floorList.size();
	}
}
