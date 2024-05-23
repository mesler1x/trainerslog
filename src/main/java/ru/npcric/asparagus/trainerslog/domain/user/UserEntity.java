package ru.npcric.asparagus.trainerslog.domain.user;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user_info")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserEntity extends BaseEntity implements UserDetails {
    String username;
    String password;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Set<UserRole> authorities;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean enabled;
    boolean accountNonLocked;

    public static UserEntity from(Context context) {
        UserEntity user = new UserEntity();
        user.username = context.username();
        user.password = context.password;
        Set<UserRole> defaultRole = new HashSet<>();
        defaultRole.add(UserRole.DEFAULT);
        user.authorities = defaultRole;
        user.accountNonExpired = true;
        user.credentialsNonExpired = true;
        user.enabled = true;
        user.accountNonLocked = true;
        return user;
    }

    public record Context(String username,
                          String password) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return id != null && id.equals(user.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
