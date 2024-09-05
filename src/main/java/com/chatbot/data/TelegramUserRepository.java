package com.chatbot.data;

import org.springframework.data.repository.CrudRepository;

public interface TelegramUserRepository extends CrudRepository<TelegramUser,Long> {
    TelegramUser findByUserName(String userName);
}
