package bms.room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bms.exceptions.DuplicateSensorException;
import bms.sensors.Sensor;

public class Room extends Object
{
	private int roomNumber;		//to store unique room number
	private RoomType type;		//to store room type
	private double area;		//to store room area
	
	private boolean FireDrill;		//to keep track of fire drill
	private List<Sensor> sensorsList;		//to store all the sensors in the room
	
	/* Constructor */
	public Room(int roomNumber, RoomType type, double area)
	{
		/* Initialize the values of data members */
		this.roomNumber = roomNumber;
		this.type = type;
		this.area = area;
		
		/* Initialize the array list */
		sensorsList = new ArrayList<Sensor>();
	}
	
	/* Method that will add a sensor in the room */
	public void addSensor(Sensor sensor) throws DuplicateSensorException
	{
		/* Get the name/type of the sensor to be added */
		String sensorType = sensor.getClass().getSimpleName();
		
		/* Flag variable to check if the sensor will same type is already added */
		boolean isAdded = false;
		
		/* Iterate over the list of sensors to check if the sensor is already added */
		for (Sensor temp : sensorsList)
		{
			/* Compare each sensor type */
			if(temp.getClass().getSimpleName().equals(sensorType))
				isAdded = true;		//sensor of this type is already added
		}
		
		/* Check if the type of sensor was found already */
		if(isAdded)
			throw new DuplicateSensorException();	//if found, throw exception
		
		/* Otherwise, add it to sensors list */
		this.sensorsList.add(sensor);
		
		/* Sort the sensors list */
		Collections.sort(sensorsList, new Comparator<Sensor>() {
		    public int compare(Sensor v1, Sensor v2) {
		        return v1.getClass().getSimpleName().compareTo(v2.getClass().getSimpleName());
		    }
		});	
	}
	
	/* Method that will return unique room number */
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
	
	/* Method that will return area of the room */
	public double getArea()
	{
		return this.area;
	}
	
	/* Method that will return minimum area required for a room */
	public static int getMinArea()
	{
		return 5;		//5 is the minimum area required for a room
	}
	
	/* Method that will return type of the room */
	public RoomType getType()
	{
		return this.type;
	}
	
	/* Method that will return a particular sensor from the room */
	public Sensor getSensor(String sensorType)
	{
		/* Iterate over the sensors list to find the sensor */
		for (Sensor sensor : sensorsList)
		{
			/* Check for every sensor that is it the required sensor */
			if(sensor.getClass().getSimpleName().equals(sensorType))
				return sensor;		//required sensor found, return it
		}
		
		/* The required sensor was not found */
		return null;
	}
	
	/* Method that will return list of all sensors in a room */
	public List<Sensor> getSensors()
	{
		List<Sensor> newlist = new ArrayList<Sensor>(sensorsList);
		return newlist;
	}
	
	/* Method that will set fireDrill in a room */
	public void setFireDrill(boolean fireDrill)
	{
		this.FireDrill = fireDrill;
	}
	
	/* Method that will return the status of fire drill of a room */
	public boolean fireDrillOngoing()
	{
		return this.FireDrill;
	}

	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return "Room # " + this.roomNumber + ": type=" + this.type + ", area=" + this.area + "m^2" 
				+ ", sensors="+ this.sensorsList.size();
	}
}
