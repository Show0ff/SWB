package com.khlopin.BearSWB.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat", schema = "public")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "chats", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> usersOfChat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private List<PersonalMessage> messages;

    public Chat(List<User> usersOfChat) {
        this.usersOfChat = usersOfChat;
    }
}


