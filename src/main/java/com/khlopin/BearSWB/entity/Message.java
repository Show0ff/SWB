package com.khlopin.BearSWB.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message", schema = "public")
public class Message {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@ManyToOne
@JoinColumn(name = "user_id")
    private User ownerOfMessage;
@Column(name = "text")
    private String text;


}
