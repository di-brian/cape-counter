package com.CapeCounterPlugin;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.PlayerChanged;
import net.runelite.api.events.PlayerDespawned;
import net.runelite.api.events.PlayerSpawned;
import net.runelite.api.kit.KitType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@PluginDescriptor(
		name = "Cape Counter"
)
public class CapeCounterPlugin extends Plugin {
	@Getter(AccessLevel.PACKAGE)
	private final Map<Player, Integer> playerCapes = new HashMap<>();
	@Inject
	private Client client;
	@Inject
	private CapeCounterConfig config;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private CapeCounterOverlay capeCounterOverlay;

	protected void startUp() {
		this.overlayManager.add(this.capeCounterOverlay);
	}

	protected void shutDown() {
		this.overlayManager.remove(this.capeCounterOverlay);
	}

	@Provides
	CapeCounterConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(CapeCounterConfig.class);
	}

	@Subscribe
	public void onPlayerSpawned(PlayerSpawned event) {
		final Player player = event.getPlayer();
		if (doesPlayerHaveCape(player)) {
			playerCapes.put(player, player.getPlayerComposition().getEquipmentId(KitType.CAPE));
		}
	}

	@Subscribe
	public void onPlayerDespawned(PlayerDespawned playerDespawned) {
		final Player player = playerDespawned.getPlayer();
		playerCapes.remove(player);
	}

	@Subscribe
	public void onPlayerChanged(PlayerChanged playerChanged) {
		playerCapes.remove(playerChanged.getPlayer());
		if (doesPlayerHaveCape(playerChanged.getPlayer())) {
			playerCapes.put(playerChanged.getPlayer(), playerChanged.getPlayer().getPlayerComposition().getEquipmentId(KitType.CAPE));
		}
	}

	private boolean doesPlayerHaveCape(Player player) {
		if (player.getPlayerComposition().getEquipmentId(KitType.CAPE) == -1) {
			return false;
		}
		for (Capes cape : Capes.values()) {
			if (player.getPlayerComposition().getEquipmentId(KitType.CAPE) == cape.getItemId()) {
				return true;
			}
		}
		return false;
	}

}
