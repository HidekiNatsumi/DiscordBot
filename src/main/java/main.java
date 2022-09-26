import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class main {
    public static String token = "Input your discord bot token in here";

    public static void main(String[] args) throws LoginException {

        JDABuilder jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS);
        jda.setActivity(Activity.playing("League of Legends"));// sets bot activity
        jda.addEventListeners(new Commands());
        jda.build();
    }
}
/*
 */