package com.nettakrim.client_execution;

import net.fabricmc.api.ClientModInitializer;

public class ClientExecutionClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientExecutionNetwork.init();
	}
}