package com.bergerkiller.bukkit.common.internal;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

import com.bergerkiller.bukkit.common.reflection.ClassTemplate;

public class CommonDisabledEntity extends Entity {
	public static final CommonDisabledEntity INSTANCE = ClassTemplate.create(CommonDisabledEntity.class).newInstanceNull();

	private CommonDisabledEntity() {
		super(null);
	}

	@Override
	protected void a(NBTTagCompound arg0) {
	}

	@Override
	protected void b(NBTTagCompound arg0) {
	}

	@Override
	protected void h() {
	}
}
