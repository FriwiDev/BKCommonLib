package com.bergerkiller.bukkit.common.reflection.gen;

import java.util.Map;

import com.bergerkiller.bukkit.common.sf.cglib.core.Signature;
import com.bergerkiller.bukkit.common.sf.cglib.proxy.MethodProxy;

public class SuperCallbackSignature implements CallbackSignature {
	private final Signature superSignature;
	private CallbackMethod callbackInstance;

	public SuperCallbackSignature(Signature superSignature) {
		this.superSignature = superSignature;
	}

	@Override
	public CallbackMethod createCallback(Object instance, Map<Class<?>, Object> callbackInstances) {
		if (callbackInstance == null) {
			callbackInstance = new SuperCallbackMethod(MethodProxy.find(instance.getClass(), superSignature));
		}
		return callbackInstance;
	}
}
