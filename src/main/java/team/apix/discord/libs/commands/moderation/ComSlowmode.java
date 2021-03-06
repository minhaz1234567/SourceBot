package team.apix.discord.libs.commands.moderation;

import team.apix.discord.utils.BotAPI;
import team.apix.discord.utils.api.EmbedMessageManager;
import team.apix.discord.utils.api.PermissionManager;
import team.apix.discord.utils.vars.Lists;
import team.apix.discord.utils.vars.entites.enums.SimpleRank;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

/**
 * SourceBot (2017) was created by ApixTeam (C) 2016-2018
 * in association with TheSourceCode (C) 2016-2018
 */
public class ComSlowmode implements CommandExecutor {
    @Command(aliases = {"slowmode", "slowdown"}, privateMessages = false)
    public void onCommand(Guild guild, User user, MessageChannel messageChannel, Object[] objects){
        BotAPI botAPI = new BotAPI();
        PermissionManager pm = botAPI.getPermissionManager();
        EmbedMessageManager embedManager = botAPI.getEmbedMessageManager();

        if(!pm.userRoleAtLeast(guild.getMember(user), SimpleRank.MOD)) {
            botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getNoComPermission());
            return;
        }

        if(objects.length != 1){
            botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getUsage("!slowmode {TIME_IN_SECONDS|OFF}"));
            return;
        }

        if(botAPI.getExtraUtils().isInteger(objects[0].toString())){
            if(Lists.getSlowmodeChannelCooldown().containsKey(messageChannel.getIdLong())){
                botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getAsDescription("This channel is already in slowmode"));
                return;
            }

            Lists.getSlowmodeChannelCooldown().put(messageChannel.getIdLong(), Integer.valueOf(objects[0].toString()));
            botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getAsDescription("This channel is in slowmode now :upside_down:"));
        }else if(objects[0].toString().equalsIgnoreCase("off")){
            if(!Lists.getSlowmodeChannelCooldown().containsKey(messageChannel.getIdLong())){
                botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getAsDescription("This channel isn't in slowmode"));
                return;
            }

            Lists.getSlowmodeChannelCooldown().remove(messageChannel.getIdLong());
            botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getAsDescription("This channel is not in slowmode anymore"));
        }else
            botAPI.getMessageManager().sendMessage(messageChannel, embedManager.getUsage("!slowmode {TIME_IN_SECONDS|OFF}"));
    }
}
