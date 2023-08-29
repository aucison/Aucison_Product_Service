package com.example.Aucsion_Product_Service.time;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;



//클래스를 만들어 생성과 수정 시간이 필요한 모든 요소의 상위 클래스로 지정하여, Entity들의 createdDate, modifiedDate를 자동으로 관리

@Getter
@MappedSuperclass // BaseEntity를 상속한 엔티티들은 아래 필드들을 컬럼으로 인식하게 된다.
@EntityListeners(AuditingEntityListener.class)  // Auditing(자동으로 값 매핑) 기능 추가
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdTime;

    @LastModifiedDate // 마지막으로 수정된 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedTime;

    //JPA auditing 기능
    // - 데이터베이스에서 누가, 언제하였는지 기록을 잘 남겨놓아야 함
    // - JPA에서는 Audit이라는 기능을 제공
    // - Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
    // - audit을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됨
}