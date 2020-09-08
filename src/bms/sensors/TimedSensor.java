package bms.sensors;

import bms.util.TimedItem;
import bms.util.TimedItemManager;

public abstract class TimedSensor extends Object implements TimedItem, Sensor
{
	private int [] sensorReadings;		//array to store the readings of sensor
	private int updateFrequency;		//to store average update time interval
	
	private int currentReading;		//to store the current reading by sensor
	private int currentIndex;		//to store the position of current reading in array
	private int timeElapsed;		//to store the the passed since the sensor was start
	
	
	/* Constructor */
	public TimedSensor(int[] sensorReadings, int updateFrequency) throws IllegalArgumentException
	{
		/* Throw an exception if updated frequency is less than 1 or greater than 5 */
		if(updateFrequency < 1 && updateFrequency > 5)
			throw new IllegalArgumentException();

		/* Throw an exception if sensorReading array is null */
		if(sensorReadings == null)
			throw new IllegalArgumentException();

		/* Throw an exception if sensorReading array does not have any element */
		if(sensorReadings.length <= 0)
			throw new IllegalArgumentException();

		/* Throw an exception if sensorReading array contains element having value less than zero */
		for (int i = 0; i < sensorReadings.length; i++)
		{
			if(sensorReadings[i] < 0)
				throw new IllegalArgumentException();
		}
		 
		/* No exception, initialize the private data members */
		this.sensorReadings = sensorReadings;
		this.updateFrequency = updateFrequency;
		
		/* Get instance of TimeItemManager and register this item to the manager */
		TimedItemManager manager = TimedItemManager.getInstance();
		manager.registerTimedItem(this);
		
		/* Initialize private data members of the class */
		timeElapsed = 0;
		currentIndex = 0;
		currentReading = sensorReadings[currentIndex];
	}

	/* Method that will return current reading
	 * on the sensor */
	@Override
	public int getCurrentReading()
	{
		return currentReading;
	}

	/* Method that will return the total time 
	 * elapsed since the sensor was started */
	public int getTimeElapsed()
	{
		return this.timeElapsed;
	}

	/* Method that will return the update time 
	 * frequency of the sensor */
	public int getUpdateFrequency()
	{
		return this.updateFrequency;
	}

	@Override
	public void elapseOneMinute()
	{
		/* Increment the total time passed since the sensor was started */
		this.timeElapsed++;
		
		/* Check if the sensor reading need to be updated */
		if((getTimeElapsed() % getUpdateFrequency()) == 0)
		{
			currentIndex++;		//update the sensor reading
			
			/* Check if the end of array is reached, 
			 * if yes then start from beginning of 
			 * the array*/
			if(currentIndex > sensorReadings.length)
				currentIndex = 0;
			
			/* Update the current reading of sensor */
			currentReading = sensorReadings[currentIndex];
		}
	}
	
	/* 
	 * Overridden toString() method that will return 
	 * information of the class in human readable form
	 *  */
	@Override
	public String toString()
	{
		String str =  "TimedSensor: freq=" + updateFrequency + ", readings=";
		
		for (int i = 0; i < sensorReadings.length; i++)
		{
			str += sensorReadings[i] + ",";
		}
		return str;
	}
	
}
