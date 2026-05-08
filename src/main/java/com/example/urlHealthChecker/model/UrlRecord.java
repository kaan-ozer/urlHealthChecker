package com.example.urlHealthChecker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.time.LocalDateTime;

@Data // Getter, Setter, toString vb  metodları otomatik oluştursun diye ekledim.
@NoArgsConstructor // Parametresiz boş bir constructor oluşturur (Hibernate/Redis için).
@AllArgsConstructor // Tüm alanları içeren bir constructor için ekledim.
@RedisHash("UrlCheck") // Bu sınıfın Redis'te "UrlCheck" adında bir hash yapısında saklanması için.
public class UrlRecord {

    @Id
    private String id;
    private String url;
    private Integer lastStatusCode;
    private Long lastResponseTime;
    private LocalDateTime lastCheckTime;
}