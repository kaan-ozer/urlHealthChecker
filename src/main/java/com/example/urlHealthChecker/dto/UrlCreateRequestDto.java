package com.example.urlHealthChecker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import lombok.Data;

@Data
public class UrlCreateRequestDto {

    @NotBlank(message = "URL bos olamaz") // Boş gelirse hata fırlatması için
    @URL(message = "Gecerli bir URL giriniz") // Format hatalıysa yakalanması için
    @Schema(example = "https://www.google.com", description = "Takip edilecek tam URL adresi")
    private String url;
}
