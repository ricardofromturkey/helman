
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
//use with jda 4.4
public class VideoUploaderBot {
    public static void main(String[] args) {
        String token = "ur token"; // Replace with your bot token
        String channelId = "channled id"; // Replace with the ID of the channel where you want to send the video

        try {
            JDABuilder builder = JDABuilder.createDefault(token);
            builder.addEventListeners(new MyListener(channelId));
            builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyListener extends ListenerAdapter {
        private final String channelId;

        public MyListener(String channelId) {
            this.channelId = channelId;
        }

        @Override
        public void onReady(ReadyEvent event) {
            try {
                TextChannel channel = event.getJDA().getTextChannelById(channelId);
                if (channel != null) {
                    File videoFile = new File("path/to/your/video.mp4"); // Replace with the path to your video file
                    channel.sendFile(videoFile).queue();
                } else {
                    System.out.println("Invalid channel ID!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
