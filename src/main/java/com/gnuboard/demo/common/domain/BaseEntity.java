package com.gnuboard.demo.common.domain;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modifier_at")
    private LocalDateTime modifierAt;

    @Column(name = "modified_by")
    private Long modifiedBy;

}
