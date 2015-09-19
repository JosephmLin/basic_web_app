package model.event;

public class EventBatch {

	
	//Each batch will be a size of 25.
	public static int default_size = 25;
	
	private int startIndex;
	boolean isLoaded = false;
	private Event[] events_saved = new Event[EventBatch.default_size];
	String[] colnames;
	//This will also 
	public EventBatch()
	{
		
	}
	
	public EventBatch(int batchNumber)
	{
		this.startIndex = batchNumber*EventBatch.default_size;
		for (int i = 0; i < EventBatch.default_size; i++)
		{
			//populate Events
			events_saved[i] = new Event(startIndex + i);
		}
	}

	public boolean isLoaded()
	{
		return isLoaded;
	}
	
	public void setValue(Object[] eventData, String[] columnNames)
	{
		isLoaded = true;
		int eventDataIter = 0;
		colnames = columnNames;
		int number_of_events;
		if (eventData.length / columnNames.length < EventBatch.default_size)
			number_of_events = eventData.length/columnNames.length;
		else
			number_of_events = EventBatch.default_size;
		for (int i = 0; i < number_of_events; i++)
		{
			for (int j = 0; j < columnNames.length; j++)
			{
				events_saved[i].addEntry(columnNames[j], eventData[eventDataIter]);
				eventDataIter++;
			}
		}
	}
	
	public String toString()
	{
		String sb = "";
		sb += "<tr>";
		for (int i = 0; i < colnames.length; i++)
		{
			sb += "<th>" + colnames[i] + "</th>";
		}
		sb+="</tr>";
		for (int i = 0; i < events_saved.length ; i++)
		{
			sb += events_saved[i].get(colnames);
		}
		return sb;
	}
	
	public void setLoaded(boolean isLoaded)
	{
		this.isLoaded = isLoaded;
	}
	
	public int getStartIndex()
	{
		return startIndex;
	}
	
	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
		for (int i = 0; i < EventBatch.default_size; i++)
		{
			//populate Events
			events_saved[i] = new Event(startIndex + i);
		}
	}
	public Event[] getEventsSaved()
	{
		return events_saved;
	}
	public String[] getColNames()
	{
		return colnames;
	}
}
