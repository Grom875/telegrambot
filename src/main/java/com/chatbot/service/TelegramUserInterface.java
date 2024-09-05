package com.chatbot.service;

import com.chatbot.transfer.TelegramUser;

public interface TelegramUserInterface {
    TelegramUser save(TelegramUser user);
    TelegramUser update (TelegramUser update);
    TelegramUser getUser(Long id);
    TelegramUser findUser(String userName);
    void delete (Long id);
}

