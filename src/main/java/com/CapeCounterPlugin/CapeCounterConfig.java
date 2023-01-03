package com.CapeCounterPlugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("capeCounter")
public interface CapeCounterConfig extends Config {
	@Range(
			min = 1
	)
	@ConfigItem(
			keyName = "minimumCapeCount",
			name = "Minimum Cape Count",
			description = "Configures the minimum number of capes which must be present before being displayed.",
			position = 1
	)
	default int getMinimumCapeCount() {
		return 1;
	}
}
