package com.CapeCounterPlugin;

import com.google.inject.Inject;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.*;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.ImageComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class CapeCounterOverlay extends OverlayPanel{

	private final CapeCounterPlugin plugin;
	private final CapeCounterConfig config;
	private final ItemManager manager;

	@Inject
	private CapeCounterOverlay(CapeCounterPlugin plugin, CapeCounterConfig config, ItemManager manager)
	{
		super(plugin);
		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(OverlayPriority.LOW);
		this.plugin = plugin;
		this.config = config;
		this.manager = manager;
		panelComponent.setWrap(true);
		panelComponent.setOrientation(ComponentOrientation.HORIZONTAL);
		getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Cape Counter overlay"));
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{

		for (Capes cape : Capes.values()) {

			int count = Collections.frequency(plugin.playerCapes.values(),cape.getItemId());
			if(count >= config.getMinimumCapeCount()) {
				panelComponent.getChildren().add(new ImageComponent(manager.getImage(cape.getItemId(), count, true)));
			}
		}

		return super.render(graphics);
	}

}
