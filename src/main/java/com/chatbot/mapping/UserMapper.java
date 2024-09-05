package com.chatbot.mapping;

import com.chatbot.data.TelegramUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target="id",ignore = true)
    TelegramUser fromTransfer(com.chatbot.transfer.TelegramUser user);

    com.chatbot.transfer.TelegramUser fromEntity (TelegramUser user);
}
