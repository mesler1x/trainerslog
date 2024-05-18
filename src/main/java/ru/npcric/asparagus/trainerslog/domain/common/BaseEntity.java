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
    @SequenceGenerator(name = HibernateSequence.NAME, sequenceName = HibernateSequence.NAME,
            allocationSize = HibernateSequence.ALLOCATION_SIZE, initialValue = 20)
    @GeneratedValue(strategy = SEQUENCE, generator = HibernateSequence.NAME)
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
