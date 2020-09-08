package bms.util;

/**
 * 
 * Singleton class ensuring only one object instance
 * 
 * */

public class TimedItemManager extends Object implements TimedItem
{
	private static TimedItemManager instance = null;	//one instance of object
	
	/* private constructor */
	private TimedItemManager()
	{
		/* The constructor is private to implement
		 * singleton design pattern */
	}
	
	@Override
	public void elapseOneMinute()
	{
		/* Call the same function recursively to elapse the time */
		elapseOneMinute();
	}
	
	/* static method that will return an instance of this class */
	public static TimedItemManager getInstance()
	{
		/* If instance is not created before, create instance by calling private constructor */
		if(instance == null)
			instance = new TimedItemManager();
		
		/* Return the instance of the object */
		return instance;
	}
	
	
	/* Method that will register an item with the manager */
	public void registerTimedItem(TimedItem timedItem)
	{
		timedItem.elapseOneMinute();
	}
}
