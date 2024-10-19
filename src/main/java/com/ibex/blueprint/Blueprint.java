package com.ibex.blueprint;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blueprint implements ModInitializer {
	@Override
	public void onInitialize() {
		new BlueprintLibrary();
	}
}