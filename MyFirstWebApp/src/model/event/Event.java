package model.event;

import java.util.HashMap;

public class Event {
	private HashMap<String, Object> hm;
	
	//We will want the number that this is saved within the database. 
	private int index;
	boolean isLoaded = false;
	public Event(int index)
	{
		hm = new HashMap<String, Object>();
		this.index = index;
	}
	public boolean addEntry(String key, Object value)
	{
		isLoaded = true;
		Object a = hm.put(key, value);
		return true;
	}
	
	public HashMap<String, Object> getHm() {
		return hm;
	}
	public int getIndex() {
		return index;
	}
	
	public String toString()
	{
		String sb = "";

		for (String key: hm.keySet())
		{
			sb += key + ", " + hm.get(key)+ "<br/>";
		}
		
		return sb;
	}
	//If there are no values within a key, then its null. Therefore do not display.
	public String get(String[] key)
	{
		boolean notnull = false;
		String sb = "";
		
		sb += "<tr>";
		for (int i = 0; i < key.length; i++)
		{
			sb += "<td>";
			if (hm.containsKey(key[i]) || notnull)
			{
				notnull = true;
				sb += hm.get(key[i]);
			}
			sb += "</td>";
		}

		sb += "</tr>";
		if(!notnull)
			return "";
		return sb;
	}

}
