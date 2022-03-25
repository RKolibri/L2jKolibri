package net.sf.l2j.gameserver.model.entity.instance;

import java.util.ArrayList;
import java.util.List;

import net.sf.l2j.gameserver.model.actor.instance.Door;

/**
 * @author Anarchy
 */
public class Instance
{
	private int id;
	private List<Door> doors;
	
	public Instance(int id)
	{
		this.id = id;
		doors = new ArrayList<>();
	}
	
	public void openDoors()
	{
		for (Door door : doors)
			door.openMe();
	}
	
	public void closeDoors()
	{
		for (Door door : doors)
			door.closeMe();
	}
	
	public void addDoor(Door door)
	{
		doors.add(door);
	}
	
	public List<Door> getDoors()
	{
		return doors;
	}
	
	public int getId()
	{
		return id;
	}
}