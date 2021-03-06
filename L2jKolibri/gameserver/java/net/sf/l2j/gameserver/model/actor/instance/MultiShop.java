package net.sf.l2j.gameserver.model.actor.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

import net.sf.l2j.Config;
import net.sf.l2j.commons.crypt.BCrypt;
import net.sf.l2j.commons.pool.ConnectionPool;
import net.sf.l2j.commons.pool.ThreadPool;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.data.manager.CastleManager;
import net.sf.l2j.gameserver.data.sql.PlayerInfoTable;
import net.sf.l2j.gameserver.enums.Paperdoll;
import net.sf.l2j.gameserver.enums.actors.Sex;
import net.sf.l2j.gameserver.model.Augmentation;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.model.olympiad.OlympiadManager;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.InventoryUpdate;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
import net.sf.l2j.gameserver.skills.L2Skill;
import net.sf.l2j.mods.Tournament.enums.TournamentFightType;

public class MultiShop extends Folk {

	public MultiShop(int objectId, NpcTemplate template) {
		super(objectId, template);
	}

	@Override

	public void onBypassFeedback(Player player, String command) {
		if (player == null)
			return;

		if (command.startsWith("donate")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();
			try {
				String type = st.nextToken();
				switch (type) {
				case "Noblesse":
					Nobless(player);
					break;
				case "ChangeSex":
					Sex(player);
					break;
				case "CleanPk":
					CleanPk(player);
					break;
				case "FullRec":
					Rec(player);
					break;
				case "ChangeClass":
					final NpcHtmlMessage html = new NpcHtmlMessage(0);
					html.setFile("data/html/mods/donateNpc/50091-2.htm");
					player.sendPacket(html);

					break;
				}
			} catch (Exception e) {
			}
		} else if (command.startsWith("clan")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();
			try {
				String type = st.nextToken();
				switch (type) {
				case "ClanLevel":
					Clanlvl(player);
					break;
				case "ClanRep_20k":
					ClanRep(player);
					break;
				case "ClanSkills":
					ClanSkill(player);
					break;
				}
			} catch (Exception e) {
			}
		} else if (command.startsWith("vip")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();
			try {
				String type = st.nextToken();
				switch (type) {
				case "Vip7Days":
					Vip7(player);
					break;
				case "Vip15Days":
					Vip15(player);
					break;
				case "Vip30Days":
					Vip30(player);
					break;
				}
			} catch (Exception e) {
			}
		} else if (command.startsWith("active")) {
			StringTokenizer st = new StringTokenizer(command);

			st.nextToken();
			try {
				String type = st.nextToken();
				switch (type) {
				case "Might":
					augments(player, 1062079106, 3132, 10);
					break;
				case "Empower":
					augments(player, 1061423766, 3133, 10);
					break;
				case "DuelMight":
					augments(player, 1062406807, 3134, 10);
					break;
				case "Shield":
					augments(player, 968884225, 3135, 10);
					break;
				case "MagicBarrier":
					augments(player, 956760065, 3136, 10);
					break;
				case "WildMagic":
					augments(player, 1067850844, 3142, 10);
					break;
				case "Focus":
					augments(player, 1067523168, 3141, 10);
					break;
				case "BattleRoad":
					augments(player, 968228865, 3125, 10);
					break;
				case "BlessedBody":
					augments(player, 991625216, 3124, 10);
					break;
				case "Agility":
					augments(player, 1060444351, 3139, 10);
					break;
				case "Heal":
					augments(player, 1061361888, 3123, 10);
					break;
				case "HydroBlast":
					augments(player, 1063590051, 3167, 10);
					break;
				case "AuraFlare":
					augments(player, 1063455338, 3172, 10);
					break;
				case "Hurricane":
					augments(player, 1064108032, 3168, 10);
					break;
				case "ReflectDamage":
					augments(player, 1067588698, 3204, 3);
					break;
				case "Celestial":
					augments(player, 974454785, 3158, 1);
					break;
				case "Stone":
					augments(player, 1060640984, 3169, 10);
					break;
				case "HealEmpower":
					augments(player, 1061230760, 3138, 10);
					break;
				case "ShadowFlare":
					augments(player, 1063520931, 3171, 10);
					break;
				case "Prominence":
					augments(player, 1063327898, 3165, 10);
					break;
				}
			} catch (Exception e) {
				player.sendMessage("Usage : Bar>");
			}
		} else if (command.startsWith("passive")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();
			try {
				String type = st.nextToken();
				switch (type) {
				case "DuelMight":
					augments(player, 1067260101, 3243, 10);
					break;
				case "Might":
					augments(player, 1067125363, 3240, 10);
					break;
				case "Shield":
					augments(player, 1067194549, 3244, 10);
					break;
				case "MagicBarrier":
					augments(player, 962068481, 3245, 10);
					break;
				case "Empower":
					augments(player, 1066994296, 3241, 10);
					break;
				case "Agility":
					augments(player, 965279745, 3247, 10);
					break;
				case "Guidance":
					augments(player, 1070537767, 3248, 10);
					break;
				case "Focus":
					augments(player, 1070406728, 3249, 10);
					break;
				case "WildMagic":
					augments(player, 1070599653, 3250, 10);
					break;
				case "ReflectDamage":
					augments(player, 1070472227, 3259, 3);
					break;
				case "HealEmpower":
					augments(player, 1066866909, 3246, 10);
					break;
				case "Prayer":
					augments(player, 1066932422, 3238, 10);
					break;

				}
			} catch (Exception e) {
				player.sendMessage("Usage : Bar>");
			}
		} else if (command.startsWith("name")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();

			String newName = "";
			try {
				if (st.hasMoreTokens()) {
					newName = st.nextToken();
				}
			} catch (Exception e) {
			}
			if (!conditionsname(newName, player))
				return;
			player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.NAME_ITEM_COUNT, player, true);
			player.setName(newName);
			player.store();
			player.sendMessage("Your new character name is " + newName);
			player.broadcastUserInfo();
		} else if (command.startsWith("password")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();

			String newPass = "";
			String repeatNewPass = "";

			try {
				if (st.hasMoreTokens()) {
					newPass = st.nextToken();
					repeatNewPass = st.nextToken();
				}
			} catch (Exception e) {
				player.sendMessage("Please fill all the blanks before requesting for a password change.");
				return;
			}

			if (!conditions(newPass, repeatNewPass, player))
				return;
			changePassword(newPass, repeatNewPass, player);

		}

		else if (command.startsWith("Chat"))

			showChatWindow(player, command);
	}

	public void showHtml(Player player, String page, TournamentFightType type) {
		NpcHtmlMessage htm = new NpcHtmlMessage(0);
		htm.setFile("data/html/mods/donateNpc/50091" + page + ".htm");

		htm.replace("%objectId%", getObjectId());
		htm.replace("%npcname%", getName());

		player.sendPacket(htm);
	}

	public void Nobless(Player player) {
		if (player.isNoble()) {
			player.sendMessage("You Are Already A Noblesse!.");
			return;
		}
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.NOBL_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.NOBL_ITEM_COUNT, player, true);
		player.setNoble(true, true);
		player.sendMessage("You Are Now a Noble,You Are Granted With Noblesse Status , And Noblesse Skills.");
		player.broadcastUserInfo();

	}

	public void Vip7(Player player) {
		if (player.isVip()) {
			player.sendMessage("You are Already A Vip");
			return;
		}
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.VIP7_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.VIP7_ITEM_COUNT, player, true);
		player.setVip(true);
		player.setEndTime("vip", Config.VIP_DAYS_ID2);
		player.sendMessage("You engage VIP Status for " + Config.VIP_DAYS_ID2 + ".");

	}

	public void Vip15(Player player) {
		if (player.isVip()) {
			player.sendMessage("You are Already A Vip");
			return;
		}

		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.VIP15_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.VIP15_ITEM_COUNT, player, true);
		player.setVip(true);
		player.setEndTime("vip", 15);
		player.sendMessage("You engage VIP Status for 15 Days.");

	}

	public void Vip30(Player player) {
		if (player.isVip()) {
			player.sendMessage("Your character has already Vip Status.");
			return;
		}

		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.VIP30_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.VIP30_ITEM_COUNT, player, true);
		player.setVip(true);
		player.setEndTime("vip", Config.VIP_DAYS_ID3);
		player.sendMessage("You engage VIP Status for " + Config.VIP_DAYS_ID3 + " Days.");

	}

	public void Sex(Player player) {
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.SEX_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		player.getAppearance().setSex(player.getAppearance().getSex() == Sex.MALE ? Sex.FEMALE : Sex.MALE);
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.SEX_ITEM_COUNT, player, true);
		player.sendMessage("Your gender has been changed,You will Be Disconected in 3 Seconds!");
		player.broadcastUserInfo();
		player.decayMe();
		player.spawnMe();
		ThreadPool.schedule(() -> player.logout(false), 3000);
	}

	public void Rec(Player player) {
		if (player.getRecomHave() == 255)
			player.sendMessage("You already have full recommends.");
		else if (player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.REC_ITEM_COUNT, player, true)) {
			player.setRecomHave(254);
			player.editRecomHave(255);
			player.broadcastUserInfo();
		} else
			player.sendMessage("You do not have enough Donate Coins.");
	}

	public void CleanPk(Player player) {
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.PK_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		if (player.getPkKills() < Config.PK_CLEAN) {
			player.sendMessage("You do not have enough Pk kills for clean.");
			return;
		}
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.PK_ITEM_COUNT, player, true);
		player.setPkKills(player.getPkKills() - Config.PK_CLEAN);
		player.sendMessage("You have successfully clean " + Config.PK_CLEAN + " pks!");
		player.broadcastUserInfo();
	}

	public void ClanRep(Player player) {
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.CLAN_REP_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		if (player.getClan() == null) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER);
			return;
		}
		if (!player.isClanLeader()) {
			player.sendPacket(SystemMessageId.NOT_AUTHORIZED_TO_BESTOW_RIGHTS);
			return;
		}
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.CLAN_REP_ITEM_COUNT, player, true);
		player.getClan().addReputationScore(Config.CLAN_REPS);
		player.getClan().broadcastClanStatus();
		player.sendMessage("Your clan reputation score has been increased.");
	}

	public void Clanlvl(Player player) {
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.CLAN_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		} else if (player.getClan() == null) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER);
			return;
		}
		if (!player.isClanLeader()) {
			player.sendPacket(SystemMessageId.NOT_AUTHORIZED_TO_BESTOW_RIGHTS);
			return;
		}
		if (player.getClan().getLevel() == 8) {
			player.sendMessage("Your clan is already level 8.");
			return;
		}
		player.getClan().changeLevel(8);
		player.getClan().broadcastClanStatus();
		player.broadcastPacket(new MagicSkillUse(player, player, 5103, 1, 1000, 0));
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.CLAN_ITEM_COUNT, player, true);
	}

	public void augments(Player player, int attributes, int idaugment, int levelaugment) {
		ItemInstance rhand = player.getInventory().getItemFrom(Paperdoll.RHAND);

		if (rhand == null) {
			player.sendMessage(player.getName() + " have to equip a weapon.");
			return;
		} else if (rhand.getItem().getCrystalType().getId() == 0 || rhand.getItem().getCrystalType().getId() == 1
				|| rhand.getItem().getCrystalType().getId() == 2) {
			player.sendMessage("You can't augment under " + rhand.getItem().getCrystalType() + " Grade Weapon!");
			return;
		} else if (rhand.isHeroItem()) {
			player.sendMessage("You Cannot be add Augment On " + rhand.getItemName() + " !");
			return;
		} else if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.AUGM_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		if (!rhand.isAugmented()) {
			player.sendMessage(
					"Successfully To Add " + SkillTable.getInstance().getInfo(idaugment, levelaugment).getName() + ".");
			augmentweapondatabase(player, attributes, idaugment, levelaugment);
		} else {
			player.sendMessage("You Have Augment on weapon!");
			return;
		}
	}

	public void augmentweapondatabase(Player player, int attributes, int id, int level) {
		ItemInstance item = player.getInventory().getItemFrom(Paperdoll.RHAND);
		Augmentation augmentation = new Augmentation(attributes, id, level);
		augmentation.applyBonus(player);
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.AUGM_ITEM_COUNT, player, true);
		item.setAugmentation(augmentation);
		player.disarmWeapon(true);

		try (Connection con = ConnectionPool.getConnection()) {
			@SuppressWarnings("resource")
			PreparedStatement statement = con.prepareStatement("REPLACE INTO augmentations VALUES(?,?,?,?)");
			statement.setInt(1, item.getObjectId());
			statement.setInt(2, attributes);
			statement.setInt(3, id);
			statement.setInt(4, level);
			InventoryUpdate iu = new InventoryUpdate();
			player.sendPacket(iu);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * @param player
	 */

	public void removeAugmentation(Player player) {
		ItemInstance item = player.getInventory().getItemFrom(Paperdoll.RHAND);

		if (item == null) {
			player.sendMessage("You have to equip a weapon first!");
			return;
		}

		if (!item.isAugmented()) {
			player.sendMessage("The weapon is not augmentable.");
			return;
		}

		item.getAugmentation().removeBonus(player);
		item.removeAugmentation(true);
		{
			player.sendMessage("Your augmentation has been removed!");
			// Unequip the weapon
			player.disarmWeapon(true);
		}
	}

	public void ClanSkill(Player player) {

		if (!player.isClanLeader()) {
			player.sendMessage("Only a clan leader can add clan skills.!");
			return;
		}
		if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.CLAN_SKILL_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return;
		}
		player.getClan().addAllClanSkills();
		player.sendMessage("Your clan received all clan skills.");
		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.CLAN_SKILL_ITEM_COUNT, player, true);
	}

	public static boolean conditionsclass(Player player) {
		if (player.isSubClassActive()) {
			player.sendMessage("You cannot change your Main Class while you're with Sub Class.");
			return false;
		} else if (OlympiadManager.getInstance().isRegisteredInComp(player)) {
			player.sendMessage("You cannot change your Main Class while you have been registered for olympiad match.");
			return false;
		} else if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.CLASS_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return false;
		}
		return true;
	}

	public static boolean conditionsname(String newName, Player player) {
		if (!newName.matches("^[a-zA-Z0-9]+$")) {
			player.sendMessage("Incorrect name. Please try again.");
			return false;
		} else if (newName.equals(player.getName())) {
			player.sendMessage("Please, choose a different name.");
			return false;
		} else if (PlayerInfoTable.getInstance().getPlayerObjectId(newName) > 0) {
			player.sendMessage("The name " + newName + " already exists.");
			return false;
		} else if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.NAME_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return false;
		}
		return true;
	}

	public static boolean conditions(String newPass, String repeatNewPass, Player player) {
		if (newPass.length() < 3) {
			player.sendMessage("The new password is too short!");
			return false;
		} else if (newPass.length() > 45) {
			player.sendMessage("The new password is too long!");
			return false;
		} else if (!newPass.equals(repeatNewPass)) {
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.PASSWORD_ENTERED_INCORRECT2));
			return false;
		} else if (player.getInventory().getItemCount(Config.DONATE_ITEM, -1) < Config.PASSWORD_ITEM_COUNT) {
			player.sendMessage("You do not have enough Donate Coins.");
			return false;
		}
		return true;
	}

	public void changePassword(String newPass, String repeatNewPass, Player activeChar) {
		try (Connection con = ConnectionPool.getConnection();
				PreparedStatement ps = con.prepareStatement("UPDATE accounts SET password=? WHERE login=?")) {
			final String newPassword = BCrypt.hashpw(newPass, BCrypt.gensalt());

			ps.setString(1, newPassword);

			ps.setString(2, activeChar.getAccountName());
			ps.executeUpdate();

			activeChar.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.PASSWORD_ITEM_COUNT, activeChar, true);
			activeChar.sendMessage(
					"Congratulations! Your password has been changed. You will now be disconnected for security reasons. Please login again.");
			ThreadPool.schedule(() -> activeChar.logout(false), 3000);
		} catch (Exception e) {

		}
	}

	public void teleportTo(String val, Player activeChar, Player target) {
		if (target.getObjectId() == activeChar.getObjectId())
			activeChar.sendPacket(SystemMessageId.CANNOT_USE_ON_YOURSELF);

		// Check if the attacker is not in the same party
		if (!activeChar.getParty().getMembers().contains(target)) {
			activeChar.sendMessage("Your target Isn't in your party.");
			return;
		}
		// Simple checks to avoid exploits
		if (target.isInJail() || target.isInOlympiadMode() || target.isInDuel() || target.isFestivalParticipant()
				|| (target.isInParty() && target.getParty().isInDimensionalRift()) || target.isInObserverMode()) {
			activeChar.sendMessage("Due to the current friend's status, the teleportation failed.");
			return;
		}
		if (target.getClan() != null && CastleManager.getInstance().getCastleByOwner(target.getClan()) != null
				&& CastleManager.getInstance().getCastleByOwner(target.getClan()).getSiege().isInProgress()) {
			activeChar.sendMessage("As your friend is in siege, you can't go to him/her.");
			return;
		}
		if (activeChar.getPvpFlag() > 0 || activeChar.getKarma() > 0) {
			activeChar.sendMessage("Go away! Flag or Pk player can not be teleported.");
			return;
		}
		int x = target.getX();
		int y = target.getY();
		int z = target.getZ();

		activeChar.broadcastPacketInRadius(new MagicSkillUse(activeChar, activeChar, 2100, 1, 1, 0), 600);

		activeChar.sendPacket(
				new ExShowScreenMessage("You will be teleported to " + target.getName() + " in 5 Seconds!", 5000));
		ThreadPool.schedule(() -> activeChar.teleportTo(x, y, z, 0), 5000);
		activeChar.sendMessage("You have teleported to " + target.getName() + ".");
	}

	public static void Classes(String command, final Player player) {
		if (!conditionsclass(player))
			return;

		player.destroyItemByItemId("Consume", Config.DONATE_ITEM, Config.CLASS_ITEM_COUNT, player, true);
		for (final L2Skill skill : player.getSkills().values())
			player.removeSkill(skill.getId(), true);
		player.sendSkillList();

		String classes = command.substring(command.indexOf("_") + 1);
		switch (classes) {
		case "duelist":
			player.setClassId(88);
			player.setBaseClass(88);
			break;
		case "dreadnought":
			player.setClassId(89);
			player.setBaseClass(89);
			break;
		case "phoenix":
			player.setClassId(90);
			player.setBaseClass(90);
			break;
		case "hell":
			player.setClassId(91);
			player.setBaseClass(91);
			break;
		case "sagittarius":
			player.setClassId(92);
			player.setBaseClass(92);
			break;
		case "adventurer":
			player.setClassId(93);
			player.setBaseClass(93);
			break;
		case "archmage":
			player.setClassId(94);
			player.setBaseClass(94);
			break;
		case "soultaker":
			player.setClassId(95);
			player.setBaseClass(95);
			break;
		case "arcana":
			player.setClassId(96);
			player.setBaseClass(96);
			break;
		case "cardinal":
			player.setClassId(97);
			player.setBaseClass(97);
			break;
		case "hierophant":
			player.setClassId(98);
			player.setBaseClass(98);
			break;
		case "evas":
			player.setClassId(99);
			player.setBaseClass(99);
			break;
		case "muse":
			player.setClassId(100);
			player.setBaseClass(100);
			break;
		case "windrider":
			player.setClassId(101);
			player.setBaseClass(101);
			break;
		case "sentinel":
			player.setClassId(102);
			player.setBaseClass(102);
			break;
		case "mystic":
			player.setClassId(103);
			player.setBaseClass(103);
			break;
		case "elemental":
			player.setClassId(104);
			player.setBaseClass(104);
			break;
		case "saint":
			player.setClassId(105);
			player.setBaseClass(105);
			break;
		case "templar":
			player.setClassId(106);
			player.setBaseClass(106);
			break;
		case "dancer":
			player.setClassId(107);
			player.setBaseClass(107);
			break;
		case "hunter":
			player.setClassId(108);
			player.setBaseClass(108);
			break;
		case "gsentinel":
			player.setClassId(109);
			player.setBaseClass(109);
			break;
		case "screamer":
			player.setClassId(110);
			player.setBaseClass(110);
			break;
		case "master":
			player.setClassId(111);
			player.setBaseClass(111);
			break;
		case "ssaint":
			player.setClassId(112);
			player.setBaseClass(112);
			break;
		case "titan":
			player.setClassId(113);
			player.setBaseClass(113);
			break;
		case "khavatari":
			player.setClassId(114);
			player.setBaseClass(114);
			break;
		case "domi":
			player.setClassId(115);
			player.setBaseClass(115);
			break;
		case "doom":
			player.setClassId(116);
			player.setBaseClass(116);
			break;
		case "fortune":
			player.setClassId(117);
			player.setBaseClass(117);
			break;
		case "maestro":
			player.setClassId(118);
			player.setBaseClass(118);
			break;
		}
		player.store();
		player.broadcastUserInfo();
		player.sendSkillList();
		player.getAllAvailableSkills();
		player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
		ThreadPool.schedule(() -> player.logout(false), 5000);
	}

	@Override
	public String getHtmlPath(int npcId, int val) {
		String filename = "";

		if (val == 0)
			filename = "" + npcId;
		else
			filename = npcId + "-" + val;

		return "data/html/mods/donateNpc/" + filename + ".htm";
	}
}