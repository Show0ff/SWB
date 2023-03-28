package com.khlopin.BearSWB.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    private Long id = 1L;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private List<User> usersOfChat;

}
