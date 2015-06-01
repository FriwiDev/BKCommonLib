package net.friwi.reflection;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.WorldManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import com.bergerkiller.bukkit.common.internal.CommonWorldListener;

public class WorldManagerReflector {
	public static WorldServer get(CommonWorldListener l){
		try{
			Field f = WorldManager.class.getDeclaredField("world");
			f.setAccessible(true);
			return (WorldServer) f.get(l);
		}catch(Exception e){
			System.out.println("Could not get WorldServer object from CommonWorldListener");
			return null;
		}
	}
}
