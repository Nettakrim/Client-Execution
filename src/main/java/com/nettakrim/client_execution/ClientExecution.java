package com.nettakrim.client_execution;

import com.nettakrim.client_execution.payloads.CommandPayload;
import com.nettakrim.client_execution.payloads.LockPayload;
import com.nettakrim.client_execution.payloads.UnlockPayload;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientExecution implements ModInitializer {
	public static final String modid = "client_execution";
    public static final Logger LOGGER = LoggerFactory.getLogger(modid);

	@Override
	public void onInitialize() {
		CommandPayload.register();
		LockPayload.register();
		UnlockPayload.register();

		Commands.init();
	}
}