package com.CapeCounterPlugin;

import lombok.Getter;

import java.util.stream.Stream;

public enum Capes {
	RED_CAPE(1007),
	BLACK_CAPE(1019),
	BLUE_CAPE(1021),
	YELLOW_CAPE(1023),
	GREEN_CAPE(1027),
	PURPLE_CAPE(1029),
	ORANGE_CAPE(1031),
	BLACK_CLAN_CLOAK(25712),
	ORANGE_CLAN_CLOAK(25714),
	BLUE_CLAN_CLOAK(25715),
	RED_CLAN_CLOAK(25716),
	GREEN_CLAN_CLOAK(25717),
	YELLOW_CLAN_CLOAK(25718),
	LIGHT_BLUE_CLAN_CLOAK(25719),
	PURPLE_CLAN_CLOAK(25720);

	@Getter
	private int itemId;

	Capes(int itemId) {
		this.itemId = itemId;
	}
}
