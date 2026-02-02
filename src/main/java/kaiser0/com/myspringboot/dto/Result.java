package kaiser0.com.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, message, data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(false, message, null);
    }
}