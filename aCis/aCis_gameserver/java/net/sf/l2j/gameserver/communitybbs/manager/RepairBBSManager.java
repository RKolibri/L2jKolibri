package net.sf.l2j.gameserver.communitybbs.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import net.sf.l2j.commons.pool.ConnectionPool;
import net.sf.l2j.gameserver.data.cache.HtmCache;
import net.sf.l2j.gameserver.model.actor.Player;

public class RepairBBSManager extends BaseBBSManager {
	private static final Logger _log = Logger.getLogger(RepairBBSManager.class.getName());

	public static RepairBBSManager getInstance() {
		return SingletonHolder._instance;
	}

	@Override
	public void parseCmd(String command, Player activeChar) {
		if (command.equals("_bbsShowRepair")) {
			showRepairWindow(activeChar);
		} else if (command.startsWith("_bbsRepair")) {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();
			String repairChar = st.nextToken();
			if (repairChar == null) {
				activeChar.sendMessage("Please first select character to be repaired.");
				return;
			}
			if (checkAcc(activeChar, repairChar)) {
				if (checkChar(activeChar, repairChar)) {
					activeChar.sendMessage("You cannot repair your self.");
					return;
				}
				if (checkJail(activeChar, repairChar)) {
					activeChar.sendMessage(
							"The character that you are attempting to repair is in jail and this function cannot be used to help him.");
					return;
				}
				repairBadCharacter(repairChar);
				separateAndSend(HtmCache.getInstance().getHtm("data/html/CommunityBoard/top/repaired.htm"), activeChar);
			} else {
				activeChar.sendMessage("Something went wrong. Please contact with the server's administrator.");
				return;
			}
		} else {
			super.parseCmd(command, activeChar);
		}
	}

	private static void showRepairWindow(Player activeChar) {
		String content = HtmCache.getInstance().getHtm("data/html/CommunityBoard/top/repair.htm");

		content = content.replaceAll("%acc_chars%", getCharList(activeChar));
		separateAndSend(content, activeChar);
	}

	private static String getCharList(Player activeChar) {
		String result = "";
		String repCharAcc = activeChar.getAccountName();
		Connection con = null;
		try {
			con = ConnectionPool.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT char_name FROM characters WHERE account_name=?");
			statement.setString(1, repCharAcc);
			ResultSet rset = statement.executeQuery();
			while (rset.next()) {
				if (activeChar.getName().compareTo(rset.getString(1)) != 0) {
					result = result + rset.getString(1) + ";";
				}
			}
			rset.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	@SuppressWarnings("null")
	private static boolean checkAcc(Player activeChar, String repairChar) {
		boolean result = false;
		String repCharAcc = "";
		Connection con = null;
		try {
			con = ConnectionPool.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT account_name FROM characters WHERE char_name=?");
			statement.setString(1, repairChar);
			ResultSet rset = statement.executeQuery();
			if (rset.next()) {
				repCharAcc = rset.getString(1);
			}
			rset.close();
			statement.close();
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (activeChar.getAccountName().compareTo(repCharAcc) != 0) {
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = true;
		return result;
	}

	@SuppressWarnings("null")
	private static boolean checkJail(Player activeChar, String repairChar) {
		boolean result = false;
		int repCharJail = 0;
		Connection con = null;
		try {
			con = ConnectionPool.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT punish_level FROM characters WHERE char_name=?");
			statement.setString(1, repairChar);
			ResultSet rset = statement.executeQuery();
			if (rset.next()) {
				repCharJail = rset.getInt(1);
			}
			rset.close();
			statement.close();
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (repCharJail <= 1) {
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = true;
		return result;
	}

	private static boolean checkChar(Player activeChar, String repairChar) {
		boolean result = false;
		if (activeChar.getName().compareTo(repairChar) == 0) {
			result = true;
		}
		return result;
	}

	private static void repairBadCharacter(String charName) {
		Connection con = null;
		try {
			con = ConnectionPool.getConnection();

			PreparedStatement statement = con.prepareStatement("SELECT obj_Id FROM characters WHERE char_name=?");
			statement.setString(1, charName);
			ResultSet rset = statement.executeQuery();

			int objId = 0;
			if (rset.next()) {
				objId = rset.getInt(1);
			}
			rset.close();
			statement.close();
			if (objId == 0) {
				con.close();
				return;
			}
			statement = con.prepareStatement("UPDATE characters SET x=17867, y=170259, z=-3503 WHERE obj_Id=?");
			statement.setInt(1, objId);
			statement.execute();
			statement.close();
			statement = con.prepareStatement("DELETE FROM character_shortcuts WHERE char_obj_id=?");
			statement.setInt(1, objId);
			statement.execute();
			statement.close();
			statement = con
					.prepareStatement("UPDATE items SET loc=\"INVENTORY\" WHERE owner_id=? AND loc=\"PAPERDOLL\"");
			statement.setInt(1, objId);
			statement.execute();
			statement.close();
			return;
		} catch (Exception e) {
			_log.warning("GameServer: could not repair character:" + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static class SingletonHolder {
		protected static final RepairBBSManager _instance = new RepairBBSManager();
	}
}