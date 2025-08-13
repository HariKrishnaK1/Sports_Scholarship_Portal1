package com.example.sportsscholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {
    private Long scholarshipId;
    private String documentsUrls;
}
