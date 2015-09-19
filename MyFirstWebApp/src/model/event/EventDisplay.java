package model.event;

import java.util.ArrayList;

import controller.ObjectController;

public class EventDisplay{
	Event[] event_set;
	String[] colnames;
	public EventDisplay(int startIndex, ArrayList<EventBatch> eventBatchesToDisplay, int display_size)
	{
		event_set = new Event[display_size];
		
		//How this works: It takes in a list of event batches which are ordered according to their start indexes. From here, it takes each event until it populates event_set
		//by going into an internal list within each object, then iterating through the list of objects. Essentially iterating through a 2d array.
		int ebtdIndex = 0;
		int startEventBatchIndex = startIndex - eventBatchesToDisplay.get(ebtdIndex).getStartIndex();
		colnames = eventBatchesToDisplay.get(ebtdIndex).getColNames();
		Event[] start = eventBatchesToDisplay.get(ebtdIndex).getEventsSaved();
		for(int i = 0; i < display_size; i++)
		{
			if (startEventBatchIndex >= start.length)
			{
				//This moves the large array counter.
				ebtdIndex++;
				start = eventBatchesToDisplay.get(ebtdIndex).getEventsSaved();
				startEventBatchIndex = 0;
			}
			event_set[i] = start[startEventBatchIndex];
			startEventBatchIndex++;
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
		for (int i = 0; i < event_set.length ; i++)
		{
			sb += event_set[i].get(colnames);
		}
		return sb;
	}
}
