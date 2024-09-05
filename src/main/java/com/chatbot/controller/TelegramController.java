package com.chatbot.controller;

import com.chatbot.service.TelegramUserInterface;
import com.chatbot.service.TelegramUserService;
import com.chatbot.transfer.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Component
public class TelegramController extends TelegramLongPollingBot {

    private final TelegramUserInterface userservice ;


    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.username}")
    private String userName;

    @Autowired
    public TelegramController(TelegramUserInterface userservice) {
        this.userservice = userservice;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            switch (messageText) {
                case "/start":
                    doStart(message,update);
                    break;
                default:
                    doDefault(message);
                    break;


            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void doDefault(SendMessage message) {
        message.setText("Please enter text");
    }

    private void doStart(SendMessage message , Update update) {
        String username=update.getMessage().getFrom().getUserName();
        saveUsername(username,update.getMessage()
                .getChatId());
        message.setText("Hi ,"+ username);

    }

    private void saveUsername(String username, Long chatId) {
        TelegramUser user=new TelegramUser();
        user.setUserName(username);
        user.setChatId(chatId);
        userservice.save(user);
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}


//ukrchatbotdbot
//7149548435:AAHsU4IPFSQ53jiwhEwNikfCCHD060XpzBo
