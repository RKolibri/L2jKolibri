package net.sf.l2j.gameserver.handler;

import java.util.HashMap;
import java.util.Map;

import net.sf.l2j.gameserver.handler.itemhandlers.BeastSoulShots;
import net.sf.l2j.gameserver.handler.itemhandlers.BeastSpices;
import net.sf.l2j.gameserver.handler.itemhandlers.BeastSpiritShots;
import net.sf.l2j.gameserver.handler.itemhandlers.BlessedSpiritShots;
import net.sf.l2j.gameserver.handler.itemhandlers.Bog;
import net.sf.l2j.gameserver.handler.itemhandlers.Books;
import net.sf.l2j.gameserver.handler.itemhandlers.Calculators;
import net.sf.l2j.gameserver.handler.itemhandlers.Elixirs;
import net.sf.l2j.gameserver.handler.itemhandlers.EnchantScrolls;
import net.sf.l2j.gameserver.handler.itemhandlers.FastAugmentHighGrade;
import net.sf.l2j.gameserver.handler.itemhandlers.FastAugmentMidGrade;
import net.sf.l2j.gameserver.handler.itemhandlers.FastAugmentNoGrade;
import net.sf.l2j.gameserver.handler.itemhandlers.FastAugmentTopGrade;
import net.sf.l2j.gameserver.handler.itemhandlers.FishShots;
import net.sf.l2j.gameserver.handler.itemhandlers.Harvesters;
import net.sf.l2j.gameserver.handler.itemhandlers.ItemSkills;
import net.sf.l2j.gameserver.handler.itemhandlers.Keys;
import net.sf.l2j.gameserver.handler.itemhandlers.Maps;
import net.sf.l2j.gameserver.handler.itemhandlers.MercenaryTickets;
import net.sf.l2j.gameserver.handler.itemhandlers.PaganKeys;
import net.sf.l2j.gameserver.handler.itemhandlers.PetFoods;
import net.sf.l2j.gameserver.handler.itemhandlers.Recipes;
import net.sf.l2j.gameserver.handler.itemhandlers.RollingDices;
import net.sf.l2j.gameserver.handler.itemhandlers.ScrollsOfResurrection;
import net.sf.l2j.gameserver.handler.itemhandlers.Seeds;
import net.sf.l2j.gameserver.handler.itemhandlers.SevenSignsRecords;
import net.sf.l2j.gameserver.handler.itemhandlers.SoulCrystals;
import net.sf.l2j.gameserver.handler.itemhandlers.SoulShots;
import net.sf.l2j.gameserver.handler.itemhandlers.SpecialXMas;
import net.sf.l2j.gameserver.handler.itemhandlers.SpiritShots;
import net.sf.l2j.gameserver.handler.itemhandlers.SummonItems;
import net.sf.l2j.gameserver.model.item.kind.EtcItem;
import net.sf.l2j.mods.skins.itemhandlers.Skin1;
import net.sf.l2j.mods.skins.itemhandlers.Skin10;
import net.sf.l2j.mods.skins.itemhandlers.Skin11;
import net.sf.l2j.mods.skins.itemhandlers.Skin12;
import net.sf.l2j.mods.skins.itemhandlers.Skin13;
import net.sf.l2j.mods.skins.itemhandlers.Skin14;
import net.sf.l2j.mods.skins.itemhandlers.Skin15;
import net.sf.l2j.mods.skins.itemhandlers.Skin2;
import net.sf.l2j.mods.skins.itemhandlers.Skin3;
import net.sf.l2j.mods.skins.itemhandlers.Skin4;
import net.sf.l2j.mods.skins.itemhandlers.Skin5;
import net.sf.l2j.mods.skins.itemhandlers.Skin6;
import net.sf.l2j.mods.skins.itemhandlers.Skin7;
import net.sf.l2j.mods.skins.itemhandlers.Skin8;
import net.sf.l2j.mods.skins.itemhandlers.Skin9;
import net.sf.l2j.mods.skins.visual.Visual;
import net.sf.l2j.mods.vip.itemhandlers.VipCoin;

public class ItemHandler {
	private final Map<Integer, IItemHandler> _entries = new HashMap<>();

	protected ItemHandler() {
		registerHandler(new BeastSoulShots());
		registerHandler(new BeastSpices());
		registerHandler(new BeastSpiritShots());
		registerHandler(new BlessedSpiritShots());
		registerHandler(new Books());
		registerHandler(new Calculators());
		registerHandler(new Elixirs());
		registerHandler(new EnchantScrolls());
		registerHandler(new FishShots());
		registerHandler(new Harvesters());
		registerHandler(new ItemSkills());
		registerHandler(new Keys());
		registerHandler(new Maps());
		registerHandler(new MercenaryTickets());
		registerHandler(new PaganKeys());
		registerHandler(new PetFoods());
		registerHandler(new Recipes());
		registerHandler(new RollingDices());
		registerHandler(new ScrollsOfResurrection());
		registerHandler(new Seeds());
		registerHandler(new SevenSignsRecords());
		registerHandler(new SoulShots());
		registerHandler(new SpecialXMas());
		registerHandler(new SoulCrystals());
		registerHandler(new SpiritShots());
		registerHandler(new SummonItems());
		registerHandler(new Skin1());
		registerHandler(new Skin2());
		registerHandler(new Skin3());
		registerHandler(new Skin4());
		registerHandler(new Skin5());
		registerHandler(new Skin6());
		registerHandler(new Skin7());
		registerHandler(new Skin8());
		registerHandler(new Skin9());
		registerHandler(new Skin10());
		registerHandler(new Skin11());
		registerHandler(new Skin12());
		registerHandler(new Skin13());
		registerHandler(new Skin14());
		registerHandler(new Skin15());
		registerHandler(new VipCoin());
		registerHandler(new Visual());
		registerHandler(new FastAugmentTopGrade());
		registerHandler(new FastAugmentHighGrade());
		registerHandler(new FastAugmentMidGrade());
		registerHandler(new FastAugmentNoGrade());
		registerHandler(new Bog());
	}

	private void registerHandler(IItemHandler handler) {
		_entries.put(handler.getClass().getSimpleName().intern().hashCode(), handler);
	}

	public IItemHandler getHandler(EtcItem item) {
		if (item == null || item.getHandlerName() == null)
			return null;

		return _entries.get(item.getHandlerName().hashCode());
	}

	public int size() {
		return _entries.size();
	}

	public static ItemHandler getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final ItemHandler INSTANCE = new ItemHandler();
	}
}