package com.patika.user_service.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * Aktif/Pasif
     *
     * Kayıt silinmişse false değilse true;
     */

    @Builder.Default
    @Column(columnDefinition = "boolean not null default true")
    private boolean active = true;


    private LocalDateTime createdDateTime;

}
