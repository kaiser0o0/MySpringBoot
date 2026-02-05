package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.Result;
import kaiser0.com.myspringboot.entity.User;
import kaiser0.com.myspringboot.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    @Transactional
    public Result<String> softDeleteUser(Long userId, String deletedBy) {
        User user = userRepository.findByIdIncludingDeleted(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı! ID: " + userId));

        if (user.isDeleted()) {
            return Result.error("Bu kullanıcı zaten silinmiş durumda.");
        }

        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
        user.setDeletedBy(deletedBy);

        userRepository.save(user);

        return Result.success("Kullanıcı başarıyla soft delete yapıldı.",
                "Kullanıcı: " + user.getUsername());
    }

    @Override
    @Transactional
    public Result<String> hardDeleteUser(Long userId) {
        User user = userRepository.findByIdIncludingDeleted(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı! ID: " + userId));

        String username = user.getUsername();
        userRepository.delete(user);

        return Result.success("Kullanıcı kalıcı olarak silindi.",
                "Silinen kullanıcı: " + username);
    }

    @Override
    @Transactional
    public Result<String> restoreUser(Long userId) {
        User user = userRepository.findByIdIncludingDeleted(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı! ID: " + userId));

        if (!user.isDeleted()) {
            return Result.error("Bu kullanıcı zaten aktif durumda.");
        }

        user.setDeleted(false);
        user.setDeletedAt(null);
        user.setDeletedBy(null);

        userRepository.save(user);

        return Result.success("Kullanıcı başarıyla geri yüklendi.",
                "Kullanıcı: " + user.getUsername());
    }

    @Override
    public Result<List<User>> getAllActiveUsers() {
        List<User> activeUsers = userRepository.findAllActiveUsers();
        return Result.success(activeUsers, "Aktif kullanıcılar listelendi. Toplam: " + activeUsers.size());
    }

    @Override
    public Result<List<User>> getAllDeletedUsers() {
        List<User> deletedUsers = userRepository.findAllDeletedUsers();
        return Result.success(deletedUsers, "Silinmiş kullanıcılar listelendi. Toplam: " + deletedUsers.size());
    }

    @Override
    public Result<User> getUserById(Long userId) {
        User user = userRepository.findActiveById(userId)
                .orElseThrow(() -> new RuntimeException("Aktif kullanıcı bulunamadı! ID: " + userId));

        return Result.success(user, "Kullanıcı bilgileri getirildi.");
    }
}