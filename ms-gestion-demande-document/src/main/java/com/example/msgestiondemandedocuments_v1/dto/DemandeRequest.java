package com.example.msgestiondemandedocuments_v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeRequest {
    private Long id;
    private String DemandeNum;
}
