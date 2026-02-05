package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.Result;
import kaiser0.com.myspringboot.entity.User;

import java.util.List;

public interface IUserService {

    // Soft Delete - Kullanıcıyı silinmiş olarak işaretle
    Result<String> softDeleteUser(Long userId, String deletedBy);

    // Hard Delete - Kullanıcıyı kalıcı olarak sil
    Result<String> hardDeleteUser(Long userId);

    // Soft Delete'den geri al
    Result<String> restoreUser(Long userId);

    // Tüm aktif kullanıcıları listele
    Result<List<User>> getAllActiveUsers();

    // Tüm silinmiş kullanıcıları listele
    Result<List<User>> getAllDeletedUsers();

    // Kullanıcı detaylarını getir
    Result<User> getUserById(Long userId);
}