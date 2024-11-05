package com.tuit.diplomish.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    @Transient
    private static final String sequenceName = "user_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName,sequenceName = sequenceName,allocationSize = 1)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long userId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<QuestionsEntity> questionsEntity;


}
