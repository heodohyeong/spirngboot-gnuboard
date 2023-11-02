package com.gnuboard.demo.common.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    //@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @CreatedDate
    @Column(name = "created_at" , updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by" , updatable = false)
    private Long createdBy;

    //@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @Column(name = "modifier_at")
    private LocalDateTime modifierAt;

    @Column(name = "modified_by")
    @LastModifiedBy
    private Long modifiedBy;

}
