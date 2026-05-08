package com.example.urlHealthChecker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // Nesne oluştururken "setUrl", "setId" gibi tek tek uğraşmak yerine, daha okunaklı ve güvenli bir zincirleme yapı kurmak için ekledim.
// Ayrıca nesneyi bir kez oluşturduktan sonra üzerinde değişiklik yapılmasını (Immutable) engelleyerek veri tutarlılığını sağlar.
public class UrlResponseDto {
    private String url;
    private Integer lastStatusCode;
    private Long lastResponseTime;
    private String lastCheckTime;
}