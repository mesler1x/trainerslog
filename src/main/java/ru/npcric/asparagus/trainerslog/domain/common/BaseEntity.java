package ru.npcric.asparagus.trainerslog.domain.common;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.Objects;

import static jakarta.persistence.GenerationType.*;

@Getter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
@FieldNameConstants(innerTypeName = "BaseFields")
public abstract class BaseEntity {
    @Id
    @SequenceGenerator(name = "MY_SEQ", sequenceName = "MY_SEQ",
            allocationSize = 1, initialValue = 50)
    @GeneratedValue(strategy = SEQUENCE, generator = "MY_SEQ")
    Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
