package com.nettakrim.client_execution;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import java.util.ArrayList;
import java.util.List;

public class ClientExecutionClient implements ClientModInitializer {
	public static final List<String> commandLocks = new ArrayList<>();

	@Override
	public void onInitializeClient() {
		ClientExecutionNetwork.init();

		ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> commandLocks.clear()));
	}
}