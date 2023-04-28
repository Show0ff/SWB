package com.khlopin.BearSWB.entity;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageMessage extends Message{
    private String text;
    private User ownerOfMessage;
    private String receiverOfMessage;
}
