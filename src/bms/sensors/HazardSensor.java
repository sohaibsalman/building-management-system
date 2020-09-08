package bms.sensors;

/**
 * An interface that will evaluate
 * the level of hazard present in a location 
 * monitored by the sensor.
 */

public interface HazardSensor extends Sensor
{
	int getHazardLevel();		//abstract method to be implemented
}
