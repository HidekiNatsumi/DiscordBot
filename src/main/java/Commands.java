import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Commands extends ListenerAdapter {
    public String prefix = "!";
    boolean exists = false;
    int MemberCount;
    boolean shutdown=false;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel textChannel = event.getGuild().getTextChannelsByName("bot-test", true).get(0);//sends the messages on this particular channel
        Message message = event.getMessage();
        String[] command = message.getContentRaw().split(" ");
        User author = event.getAuthor();
        String id = author.getId();
        Guild guild = event.getGuild();

        int MemberCount = guild.getMemberCount();
        if (id.equalsIgnoreCase("949676546468225074")) {    //to not respond to itself
            return;
        }
        for (String s : command) {
            System.out.println(s);//checks the items in the array
        }

        if (command[0].equalsIgnoreCase(prefix + "spam")) { // checks the prefix and the key to send the messages
            int count = 1;
         
           int temp = Integer.parseInt(command[2]);
            while (count <= temp) {
                textChannel.sendMessage(command[1]).queue();
                count++;
            }
        }
        if (command[0].equalsIgnoreCase(prefix + "commands")) {
            textChannel.sendMessage("!spam -> to spam some random words\n!mot -> best of wishes\n!shutup + (mentioning a person) ->shutup @personMentioned").queue();
        }
        if (command[0].equalsIgnoreCase(prefix + "mot")) {
            textChannel.sendMessage("<@" + id + ">" + " best of wishes!").queue();//motivational
        }
        if (command[0].equalsIgnoreCase(prefix + "shutup")) {
            String convert = command[1];
            convert = convert.replace("<", "");
            convert = convert.replace(">", "");
            convert = convert.replace("@", "");
            // Task<List<Member>> members= event.getGuild().retrieveMembersByIds(convert);
            for (int i = 0; i < guild.getMemberCount(); i++) {
                exists = (event.getGuild().getMembers().contains(convert));
                // System.out.println(temp);
                if (!exists) {
                    exists = true;
                    textChannel.sendMessage("shut up " + "<@" + convert + ">").queue();
                    break;
                }
            }
            if (!exists) {
                textChannel.sendMessage("User <@" + convert + "> doesn't exists").queue();
            }
        }
    }
}
