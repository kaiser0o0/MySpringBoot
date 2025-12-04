package kaiser0.com.myspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "Kitap başlığı boş olamaz")
    @Size(min = 2, max = 100, message = "Kitap başlığı 2 ile 100 karakter arasında olmalıdır")
    private String title;

    @NotBlank(message = "Yazar ismi boş olamaz")
    private String author;

    @Min(value = 1, message = "Sayfa sayısı en az 1 olmalıdır")
    private int pageCount;
}