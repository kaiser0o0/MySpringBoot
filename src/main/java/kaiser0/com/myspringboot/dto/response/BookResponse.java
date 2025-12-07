package kaiser0.com.myspringboot.dto.response;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private int pageCount;
}