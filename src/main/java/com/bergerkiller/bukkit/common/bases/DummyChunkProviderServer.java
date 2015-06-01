package com.bergerkiller.bukkit.common.bases;

import net.minecraft.server.v1_8_R3.Chunk;

import com.bergerkiller.bukkit.common.conversion.Conversion;
import com.bergerkiller.bukkit.common.proxies.ChunkProviderServerProxy;
import com.bergerkiller.bukkit.common.reflection.classes.WorldServerRef;

/**
 * This class is mainly used by NoLagg chunks - for compatibilities' sake, it is ported to here.
 */
public class DummyChunkProviderServer extends ChunkProviderServerProxy {

	DummyChunkProviderServer(Object worldHandle) {
		super(worldHandle, null, null, null);
	}

	public void setBase(org.bukkit.World world) {
		setProxyBase(WorldServerRef.chunkProviderServer.get(Conversion.toWorldHandle.convert(world)));
	}

	@Override
	public Chunk getChunkAt(int x, int z, Runnable task) {
		return null;
	}
}
