package com.chatbot.service;

import com.chatbot.data.TelegramUserRepository;
import com.chatbot.mapping.UserMapper;
import com.chatbot.transfer.TelegramUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TelegramUserService implements TelegramUserInterface {
    private final TelegramUserRepository rep;
    private final UserMapper mapper;

    @Autowired
    public TelegramUserService(TelegramUserRepository rep, UserMapper mapper) {
        this.rep = rep;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TelegramUser save(TelegramUser user) {
        //com.chatbot.data.TelegramUser us=mapper.fromTransfer(user);
        //com.chatbot.data.TelegramUser use = rep.save(us);
        //return mapper.fromEntity(use);
        return mapper.fromEntity(rep.save(mapper.fromTransfer(user)));
    }

    @Override
    @Transactional
    public TelegramUser update(TelegramUser update) {
        com.chatbot.data.TelegramUser user = rep.findById(update.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setUserName(update.getUserName());
        user.setChatId(update.getChatId());
        rep.save(user);
        return mapper.fromEntity(user);
    }

    @Override
    @Transactional
    public TelegramUser getUser(Long id) {
        return mapper.fromEntity(rep.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found ")));
    }

    @Override
    @Transactional
    public TelegramUser findUser(String userName) {
        return mapper.fromEntity(rep.findByUserName(userName));
    }

    @Override
    @Transactional

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
