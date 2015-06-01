package com.bergerkiller.bukkit.common.reflection.classes;

import io.netty.channel.Channel;

import java.util.Queue;

import com.bergerkiller.bukkit.common.reflection.ClassTemplate;
import com.bergerkiller.bukkit.common.reflection.FieldAccessor;
import com.bergerkiller.bukkit.common.reflection.MethodAccessor;
import com.bergerkiller.bukkit.common.reflection.NMSClassTemplate;

public class NetworkManagerRef {
	public static final ClassTemplate<?> TEMPLATE = NMSClassTemplate.create("NetworkManager");
	public static final FieldAccessor<Queue<Object>> queue = TEMPLATE.getField("i");
//	public static final FieldAccessor<Queue<Object>> highPriorityQueue = TEMPLATE.getField("l");
	public static final FieldAccessor<Channel> channel = TEMPLATE.getField("channel");
	public static final MethodAccessor<Boolean> getIsOpen = TEMPLATE.getMethod("g");
}
