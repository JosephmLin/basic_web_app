package controller;
import java.util.ArrayList;

import model.event.EventBatch;
import model.event.EventDisplay;
import dbsetup.EventJDBCConnection;
public class ObjectController {
	//This object controller will be involved with Passing the data to the Event. I will need to do some research into how Java connects to mysql databases.
	EventJDBCConnection conn = new EventJDBCConnection();
	//Each entry into storedEvents acts as a page that can be clicked.
	ArrayList<EventBatch> storedEvents = new ArrayList<EventBatch>();
	//Page begins at 0.
	String[] columnNames;
	
	public static int default_display_size = 25;
	
	//TODO: Will implement this later to make the appropriate number of indicides for frontend. For now, I'm going to try and pass information to the frontend. 
	int totalEvents;
	public ObjectController()
	{
		columnNames = getColumnNames();
		
	}
	
	public EventDisplay getEventDisplay(int page, int display_size)
	{
		Integer startIndex = page * display_size;
		Integer lastIndex = (page+1)*display_size;
		//While lastIndex is Greater than Last index of last element in storedEvents
		while (lastIndex > storedEvents.size() * EventBatch.default_size)
		{
			storedEvents.add(new EventBatch(storedEvents.size()));
		}		
		
		Integer fromIndex = startIndex/EventBatch.default_size;
		
		Double toIndexDouble = Math.ceil((lastIndex.doubleValue()/EventBatch.default_size));
		Integer toIndex = toIndexDouble.intValue();
		if (fromIndex == toIndex)
			toIndex++;
		//This populates all of the event batches involved in the event display.
		//I.e. Say we are querying from indecies 99-130. It will query for 75-100, 100-125, 125-150
		for (int i = fromIndex; i < toIndex; i++)
			storedEvents.get(i).setValue(queryPage(i), columnNames);
//		System.out.println(storedEvents.size() + " , " + fromIndex + " - " + toIndex);
		EventDisplay ed = new EventDisplay(startIndex, new ArrayList<EventBatch>(storedEvents.subList(fromIndex, toIndex)), display_size);
		return ed;
	}
	
	public static void setDisplaySize(Integer display_size)
	{
		if (display_size > 1000)
		ObjectController.default_display_size = display_size;
	}
	private Object[] queryPage(int page)
	{
		conn.executeAllEntries(page);
		return conn.getValues();
	}
	private String[] getColumnNames()
	{
		return conn.getColumnNames();
	}
	public int getCount(int display_size)
	{
		int count = conn.getCount();
		if (count%display_size == 0)
			return (count/display_size) - 1;
		return count/display_size;
	}
}
