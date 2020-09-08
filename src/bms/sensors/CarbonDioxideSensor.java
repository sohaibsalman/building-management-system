package bms.sensors;

public class CarbonDioxideSensor extends TimedSensor implements HazardSensor
{
	private int idealValue;		//to store the ideal value of 
	private int variationLimit;		//to store the variation limit
		
	/* Constructor */
	public CarbonDioxideSensor(int[] sensorReadings, 
			int updateFrequency, 
			int idealValue, 
			int variationLimit) throws IllegalArgumentException
	{
		super(sensorReadings, updateFrequency);	//call parent class constructor

		/* Check for the exceptions */
		if(idealValue <= 0)		//ideal value cannot be 0 or negative
			throw new IllegalArgumentException();	//throw exception
		 
		if(variationLimit <= 0)		//variation limit cannot be 0 or negative
			throw new IllegalArgumentException();		//throw exception
		 
		if((idealValue - variationLimit) < 0)	//difference of ideal & variation cannot be negative
			throw new IllegalArgumentException();		//throw exception

		/* No exception occurred, update the values of the date members */
		this.idealValue = idealValue;
		this.variationLimit = variationLimit;
	}
	
	/* Method that will return variation limit */
	public int getVariationLimit()
	{
		return this.variationLimit;
	}
	
	/* Method that will return ideal value */
	public int getIdealValue()
	{
		return this.idealValue;
	}
	
	/* Method that will return hazard level */
	@Override
	public int getHazardLevel()
	{
		/* Get current reading from the sensor */
		int reading = super.getCurrentReading();
		
		if(reading >= 0 && reading <= 999)
			return 0;	//return 0 as hazard level
		else if(reading >= 1000 && reading <= 1999)
			return 25;		//return 25 as hazard level
		else if(reading >= 2000 && reading <= 4999)
			return 50;		//return 50 as hazard level
		else 
			return 100;		//return 100 as hazard level
	}
	
	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return super.toString() + " type=," + this.getClass().getSimpleName() + ", idealPPM=" + idealValue + 
				", varLimit=" + variationLimit;
	}
}
