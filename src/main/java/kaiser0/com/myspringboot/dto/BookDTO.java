package kaiser0.com.myspringboot.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private int pageCount;

}
