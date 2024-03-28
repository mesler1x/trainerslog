package ru.npcric.asparagus.trainerslog.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@Table(name = "user_info")

public class UserEntity implements UserDetails {
}
