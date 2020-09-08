package bms.util;

import bms.exceptions.FireDrillException;
import bms.room.RoomType;


/**
 * Interface for fire drill
 */
public interface FireDrill
{
	void fireDrill(RoomType roomType) throws FireDrillException;		//abstract function
}
