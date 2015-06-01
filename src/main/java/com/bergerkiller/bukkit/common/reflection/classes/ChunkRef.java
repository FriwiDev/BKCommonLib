package com.bergerkiller.bukkit.common.reflection.classes;

import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.EnumSkyBlock;
import net.minecraft.server.v1_8_R3.IBlockData;

import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;

import com.bergerkiller.bukkit.common.reflection.ClassTemplate;
import com.bergerkiller.bukkit.common.reflection.FieldAccessor;
import com.bergerkiller.bukkit.common.reflection.MethodAccessor;
import com.bergerkiller.bukkit.common.reflection.NMSClassTemplate;
import com.bergerkiller.bukkit.common.reflection.SafeField;
import com.bergerkiller.bukkit.common.utils.CommonUtil;

public class ChunkRef {
	private static final Class<?> icp = CommonUtil.getNMSClass("IChunkProvider");
	public static final int XZ_MASK = 0xf;
	public static final int Y_MASK = 0xff;
	public static final ClassTemplate<Object> TEMPLATE = new NMSClassTemplate("Chunk");
	public static final FieldAccessor<Integer> x = TEMPLATE.getField("locX");
	public static final FieldAccessor<Integer> z = TEMPLATE.getField("locZ");
	public static final MethodAccessor<byte[]> biomeData = TEMPLATE.getMethod("e");
	public static final MethodAccessor<Object[]> sections = TEMPLATE.getMethod("getSections");
	public static final FieldAccessor<Boolean> seenByPlayer = TEMPLATE.getField("t");
	private static final MethodAccessor<Void> addEntities = TEMPLATE.getMethod("addEntities");
	private static final MethodAccessor<Void> loadNeighbours = TEMPLATE.getMethod("loadNearby", icp, icp, int.class, int.class);
	private static final MethodAccessor<Boolean> needsSaving = TEMPLATE.getMethod("a", boolean.class);
	public static final FieldAccessor<Object> world = TEMPLATE.getField("world");
	public static final FieldAccessor<Map<?, ?>> tileEntities = TEMPLATE.getField("tileEntities");
	public static final FieldAccessor<List<Object>[]> entitySlices = TEMPLATE.getField("entitySlices");
	public static final FieldAccessor<Object> worldProvider = new SafeField<Object>(CommonUtil.getNMSClass("World"), "worldProvider");
	public static final FieldAccessor<Boolean> hasSkyLight = new SafeField<Boolean>(CommonUtil.getNMSClass("WorldProvider"), "e");

	public static void loadNeighbours(Object chunkHandle, Object chunkProvider1, Object chunkProvider2, int x, int z) {
		loadNeighbours.invoke(chunkHandle, chunkProvider1, chunkProvider2, x, z);
	}

	public static void addEntities(Object chunkHandle) {
		addEntities.invoke(chunkHandle);
	}

	/**
	 * Whether saving is needed for a chunk
	 * 
	 * @param chunkHandle to check
	 * @return True if the chunk needs saving, False if not
	 */
	public static boolean needsSaving(Object chunkHandle) {
		return needsSaving.invoke(chunkHandle, false);
	}

	/**
	 * Gets all chunk sections contained in a chunk
	 */
	public static Object[] getSections(Object chunkHandle) {
		return sections.invoke(chunkHandle);
	}

	/**
	 * Gets the y-coordinate of the highest chunk section
	 * 
	 * @param chunkHandle to get it from
	 * @return chunk section highest y-position
	 */
	public static int getTopSectionY(Object chunkHandle) {
		return ((Chunk) chunkHandle).g();
	}

	public static int getHeight(Object chunkHandle, int x, int z) {
		return ((Chunk) chunkHandle).b(x & XZ_MASK, z & XZ_MASK);
	}

	public static int getBlockLight(Object chunkHandle, int x, int y, int z) {
		return getBrightness(((Chunk) chunkHandle), x, y, z, EnumSkyBlock.BLOCK);
	}

	public static int getSkyLight(Object chunkHandle, int x, int y, int z) {
		return getBrightness(((Chunk) chunkHandle), x, y, z, EnumSkyBlock.SKY);
	}

	private static int getBrightness(Object chunkHandle, int x, int y, int z, EnumSkyBlock mode) {
		if (y < 0) {
			return 0;
		} else if (y >= ((Chunk) chunkHandle).world.getWorld().getMaxHeight()) {
			return mode.c;
		}
		return ((Chunk) chunkHandle).getBrightness(mode, new BlockPosition(x & XZ_MASK, y, z & XZ_MASK));
	}

	public static boolean setBlock(Object chunkHandle, int x, int y, int z, Object type, int data) {
		return ((Chunk) chunkHandle).a(new BlockPosition(x & XZ_MASK, y, z & XZ_MASK), ((Block) type).fromLegacyData(data))!=null;
	}

	@Deprecated
	public static boolean setBlock(Object chunkHandle, int x, int y, int z, int typeId, int data) {
		return setBlock(chunkHandle, x, y, z, CraftMagicNumbers.getBlock(typeId), data);
	}

	public static int getData(Object chunkHandle, int x, int y, int z) {
		BlockPosition pos = new BlockPosition(x & XZ_MASK, y, z & XZ_MASK);
		return ((Chunk) chunkHandle).getType(pos).toLegacyData(((Chunk) chunkHandle).getBlockData(pos));
	}

	public static Object getType(Object chunkHandle, int x, int y, int z) {
		return ((Chunk) chunkHandle).getType(new BlockPosition(x & XZ_MASK, y, z & XZ_MASK));
	}

	@Deprecated
	public static int getTypeId(Object chunkHandle, int x, int y, int z) {
		return CraftMagicNumbers.getId((Block) getType(chunkHandle, x, y, z));
	}
}
