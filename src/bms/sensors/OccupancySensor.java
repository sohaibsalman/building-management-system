package bms.sensors;

public class OccupancySensor extends TimedSensor implements HazardSensor
{
	private int capacity;		//to store the total capacity of a room 
	
	/* Constructor */
	public OccupancySensor(int[] sensorReadings, int updateFrequency, int capacity)
	{
		super(sensorReadings, updateFrequency);		//call constructor of parent class
		
		/* Capacity cannot be less than 0, if 
		 * it is then throw an exception */
		if(capacity < 0)
			throw new IllegalArgumentException();		//throw exception
		
		/* Update the capacity */
		this.capacity = capacity;
	}
	
	/* Method that will return the total capacity */
	public int getCapacity()
	{
		return this.capacity;
	}
	
	/* Method that will return Hazard Level */
	@Override
	public int getHazardLevel()
	{
		/* Get reading from the sensor */
		int reading = super.getCurrentReading();
		
		/* if the reading of the sensor results the value greater
		 * than the total capacity, it means hazard level is 100 */
		if(reading >= this.capacity)
			return 100;		//return 100 as hazard level
		
		/* Otherwise, calculate the occupied percentage */
		float occupancyPercent = (reading / this.capacity) * 100;

		/* Return the calculated percentage as hazard level */
		return Math.round(occupancyPercent);		
	}

	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return super.toString() + " type=" + this.getClass().getSimpleName() + ", capacity=" + this.capacity;
	}
}
