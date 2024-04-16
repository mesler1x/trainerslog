package ru.npcric.asparagus.trainerslog.domain.user;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "user_info")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    List<UserRole> authorities;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean enabled;
    boolean accountNonLocked;

    //нужно спросить на счет связи потому что user не получится удалить
    public static UserEntity from(Context context) {
        UserEntity user = new UserEntity();
        user.username = context.username();
        user.password = context.password;
        List<UserRole> defaultRole = new ArrayList<>();
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
