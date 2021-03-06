package net.sf.l2j.mods.Tournament.Commands;

import java.util.StringTokenizer;

import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.mods.Tournament.TournamentManager;
import net.sf.l2j.mods.Tournament.enums.TournamentFightType;
import net.sf.l2j.mods.Tournament.model.TournamentTeam;

/**
 * @author Rouxy
 */
public class VoiceTournament implements IVoicedCommandHandler
{
	
	@Override
	public boolean useVoicedCommand(String command, Player player, String params)
	{
		StringTokenizer st = new StringTokenizer(command, " ");
		st.nextToken();
		TournamentTeam team = player.getTournamentTeam();
		if (command.startsWith("mytour"))
		{
			TournamentManager.getInstance().showHtml(player, "myTour", TournamentFightType.NONE);
		}
		if (command.startsWith("tournamentinvite"))
		{
			if (!TournamentManager.getInstance().isRunning())
			{
				player.sendMessage("Tournament isn't Running!");
				return false;
			}
			if (TournamentManager.getInstance().isTournamentTeleporting())
			{
				player.sendMessage("Tournament is teleportind players, wait 30 seconds to invite someone.");
				return false;
			}
			String nextMemberName = st.nextToken();
			Player nextMember = World.getInstance().getPlayer(nextMemberName);
			if (nextMember == player)
			{
				player.sendMessage("You can't invite yourself!");
				return false;
			}
			
			if (nextMember != null)
			{
				if (nextMember.isInTournamentTeam())
				{
					player.sendMessage("This player already in Tournament Team.");
					return false;
				}
				if (nextMember.isInParty())
				{
					player.sendMessage("You can't players in party. Don't worry, party will be automatically created!");
					return false;
				}
				if (team != null)
				{
					if (team.getLeader() != player)
					{
						player.sendMessage("Only Leaders can invite players.");
						return false;
					}
				}
				TournamentManager.getInstance().askJoinTeam(player, nextMember);
			}
			else
			{
				player.sendMessage("Player " + nextMemberName + " doesn't exists or is not online!");
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String[] getVoicedCommandList()
	{
		
		return new String[]
		{
			"mytour",
			"tournamentinvite"
		};
	}
	
}
