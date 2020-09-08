package bms.sensors;

/**
 *	Implementation of a sensor that
 *	measures the temperature of the room
 */

public class TemperatureSensor extends TimedSensor implements HazardSensor
{

	/* Constructor */
	public TemperatureSensor(int[] sensorReadings)
	{
		/* All temperature sensors have an update frequency of 1 minute */
		super(sensorReadings, 1);		//call the constructor of the base class
	}
	
	/* Method that will return the Hazard level */
	@Override
	public int getHazardLevel()
	{
		/* Get current reading of the sensor */
		int reading = super.getCurrentReading();
		
		/* if reading is greater than 68, that means hazard level is 100 */
		if(reading >= 68)
			return 100;		//return 100 as hazard level
		
		/* if reading is less than 68, that means hazard level is 0 */
		return 0;	//return 0 as hazard level
	}
	
	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return super.toString() + " type=" + this.getClass().getSimpleName();
	}
}
