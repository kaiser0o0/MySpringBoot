package kaiser0.com.myspringboot.dto.response;

import kaiser0.com.myspringboot.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private Role role;
    private boolean deleted;
    private LocalDateTime deletedAt;
    private String deletedBy;
}