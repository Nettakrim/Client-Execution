package com.nettakrim.client_execution;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientExecution implements ModInitializer {
	public static final String modid = "client_execution";
    public static final Logger LOGGER = LoggerFactory.getLogger(modid);

	@Override
	public void onInitialize() {
		CommandPayload.register();
		Commands.init();
	}
}