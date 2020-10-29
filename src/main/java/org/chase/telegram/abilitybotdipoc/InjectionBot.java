package org.chase.telegram.abilitybotdipoc;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.util.AbilityExtension;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InjectionBot extends AbilityBot {
    private final int creatorId;

    protected InjectionBot(
            List<AbilityExtension> extensions,
            @Value("${telegram.bot.token}") String botToken,
            @Value("${telegram.bot.username}") String botUsername,
            @Value("${telegram.bot.creator-id}") int creatorId
    ) {
        super(botToken, botUsername);
        this.creatorId = creatorId;
        this.addExtensions(extensions);
    }

    @Override
    public int creatorId() {
        return creatorId;
    }

    @PostConstruct
    public void register() {
        try {
            new TelegramBotsApi().registerBot(this);
        } catch (TelegramApiRequestException e) {
            LoggerFactory.getLogger(InjectionBot.class).error("Error initializing bot", e);
        }
    }
}
