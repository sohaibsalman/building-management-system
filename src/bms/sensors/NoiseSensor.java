package bms.sensors;

public class NoiseSensor extends TimedSensor implements HazardSensor
{	
	/* Constructor */
	public NoiseSensor(int[] sensorReadings, int updateFrequency)
	{
		super(sensorReadings, updateFrequency);		//call constructor of parent class
	}
	
	/* Method that will calculate and return relative loudness */
	public double calculateRelativeLoudness()
	{	
		/* Get current reading from the sensor */
		int reading = super.getCurrentReading();
		
		/* Calculate the relative loudness */
		
		/* Formula to calculate relative loudness is:
		 * 2^((measured_volume - 70.0)/10.0) */
		
		double power = (reading - 70.0) / 10.0;
		
		/* Return the calculated relative loudness */
		return Math.pow(2, power);
	}

	@Override
	public int getHazardLevel()
	{
		/* Get relative loudness value */
		double loudness = calculateRelativeLoudness() * 100.0;
		
		/* Round down the relative loudness value */
		int roundedVale = (int) Math.floor(loudness);	
		
		/* when the loudness is less than 100, the relative loudness
		 * value should be returned */
		if(loudness < 100)
			return roundedVale;		//return the relative loudness value
			
		/* Otherwise, the hazard level will be 100 */
		return 100;		//return 100 as hazard level
	}
	
	/* Method toString() that will return 
	 * the information of class in human readable form */
	@Override
	public String toString()
	{
		return super.toString() + " type=" + this.getClass().getSimpleName();
	}
}
