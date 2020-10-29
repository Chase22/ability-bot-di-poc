package org.chase.telegram.abilitybotdipoc;

import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.util.AbilityExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class TestExtension implements AbilityExtension {
    public Ability injectedAbility() {
        return Ability.builder()
                .name("injected")
                .info("This ability is injected")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx ->
                        ctx.bot().silent().execute(new SendMessage(ctx.chatId(), "This ability was injected"))
                )
                .build();
    }
}
