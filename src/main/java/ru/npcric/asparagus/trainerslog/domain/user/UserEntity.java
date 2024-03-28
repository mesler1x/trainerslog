package ru.npcric.asparagus.trainerslog.domain.user;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user_info")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    List<UserRole> authorities;
    boolean isAccountNonExpired;
    boolean isCredentialsNonExpired;
    boolean isEnabled;
    boolean isAccountNonLocked;

    public static UserEntity from(Context context) {
        UserEntity user = new UserEntity();
        user.username = context.username();
        user.password = context.password;
        List<UserRole> defaultRole = new ArrayList<>();
        defaultRole.add(UserRole.DEFAULT);
        user.authorities = defaultRole;
        user.isAccountNonExpired = true;
        user.isCredentialsNonExpired = true;
        user.isEnabled = true;
        user.isAccountNonLocked = true;
        return user;
    }

    public record Context(String username,
                          String password){

    }
}
