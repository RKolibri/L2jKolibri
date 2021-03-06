package net.sf.l2j.gameserver.model.location;

import net.sf.l2j.commons.data.StatSet;

/**
 * A datatype extending {@link Location}, used to retain a single Gatekeeper
 * teleport location.
 */
public class TeleportLocation extends Location {
	public TeleportLocation(StatSet set) {
		super(set.getInteger("x"), set.getInteger("y"), set.getInteger("z"));

		_price = set.getInteger("price");
		_isNoble = set.getBool("isNoble");
	}

	private final int _price;
	private final boolean _isNoble;

	public int getPrice() {
		return _price;
	}

	public boolean isNoble() {
		return _isNoble;
	}
}