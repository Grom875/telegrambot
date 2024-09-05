package com.chatbot.data;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="TELEGRAM_USER")
public class  TelegramUser implements Serializable {
    private Long id;
    private Long chatId;
    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CHAT_ID")
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
