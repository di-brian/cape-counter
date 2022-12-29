package com.CapeCounterPlugin;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.PlayerChanged;
import net.runelite.api.events.PlayerDespawned;
import net.runelite.api.events.PlayerSpawned;
import net.runelite.api.kit.KitType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Cape Counter"
)
public class CapeCounterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private CapeCounterConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CapeCounterOverlay capeCounterOverlay;

	@Getter(AccessLevel.PACKAGE)
	public final Map<Player, Integer> playerCapes = new HashMap<>();

	protected void startUp() {
		this.overlayManager.add(this.capeCounterOverlay);
	}

	protected void shutDown() {
		this.overlayManager.remove(this.capeCounterOverlay);
	}

	@Provides
	CapeCounterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CapeCounterConfig.class);
	}

	@Subscribe
	public void onPlayerSpawned(PlayerSpawned event)
	{
		final Player player = event.getPlayer();
		playerCapes.put(player, player.getPlayerComposition().getEquipmentId(KitType.CAPE));
	}

	@Subscribe
	public void onPlayerDespawned(PlayerDespawned playerDespawned)
	{
		final Player player = playerDespawned.getPlayer();
		playerCapes.remove(player);
	}

}
