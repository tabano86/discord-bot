package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

@Slf4j
@Configuration
public class BotConfig extends BotUtils {
    @Value("${discord-token}")
    private String token;

    @Bean
    public IDiscordClient config() {
        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        IDiscordClient client = new ClientBuilder()
                .withToken(token)
                .withRecommendedShardCount()
                .build();

        client.getDispatcher().registerListener(new CommandHandler());
        // Only login after all events are registered otherwise some may be missed.
        client.login();
        return client;
    }
}
