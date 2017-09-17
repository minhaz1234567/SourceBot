package bid.ApixTeam.bot;

import bid.ApixTeam.bot.libs.Listener;
import bid.ApixTeam.bot.utils.tasks.ScheduledTask1s;
import bid.ApixTeam.bot.utils.vars.Levels;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.Timer;

/**
 * TSC was created by ApixTeam (C) 2017
 * in association with TheSourceCode (C) 2017
 */
public class TSC {
    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException {
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("MzU2MTI1ODc2Njc4NTU3NzI3.DJdAcg._67wYa-oyF7LGUtsW_hwI32Yenk")
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setGame(Game.of("!help for commands."))
                .buildBlocking();

        new Levels();
        new Listener(jda);
        Timer timer = new Timer();
        ScheduledTask1s scheduledTask1s = new ScheduledTask1s();
        timer.schedule(scheduledTask1s, 0, 1000);
    }
}
