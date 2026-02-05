package kaiser0.com.myspringboot.controller;

import kaiser0.com.myspringboot.dto.Result;
import kaiser0.com.myspringboot.entity.User;
import kaiser0.com.myspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    /**
     * Kullanıcıyı soft delete yap (sadece işaretle)
     * Kimlik doğrulama yapan kullanıcı adını deletedBy olarak kaydeder
     */
    @DeleteMapping("/{userId}/soft")
    public ResponseEntity<Result<String>> softDeleteUser(
            @PathVariable Long userId,
            Authentication authentication) {

        String deletedBy = authentication.getName(); // Giriş yapmış kullanıcının adı
        return ResponseEntity.ok(userService.softDeleteUser(userId, deletedBy));
    }

    /**
     * Kullanıcıyı kalıcı olarak sil (hard delete)
     * DİKKAT: Bu işlem geri alınamaz!
     */
    @DeleteMapping("/{userId}/hard")
    public ResponseEntity<Result<String>> hardDeleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.hardDeleteUser(userId));
    }

    /**
     * Soft delete yapılmış kullanıcıyı geri yükle
     */
    @PatchMapping("/{userId}/restore")
    public ResponseEntity<Result<String>> restoreUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.restoreUser(userId));
    }

    /**
     * Tüm aktif kullanıcıları listele
     */
    @GetMapping("/active")
    public ResponseEntity<Result<List<User>>> getAllActiveUsers() {
        return ResponseEntity.ok(userService.getAllActiveUsers());
    }

    /**
     * Tüm silinmiş kullanıcıları listele
     */
    @GetMapping("/deleted")
    public ResponseEntity<Result<List<User>>> getAllDeletedUsers() {
        return ResponseEntity.ok(userService.getAllDeletedUsers());
    }

    /**
     * Kullanıcı detaylarını getir
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Result<User>> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}