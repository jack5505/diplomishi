package com.tuit.diplomish.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class QuestionsEntity {
    @Transient
    private static final String sequenceName = "questions_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName,sequenceName = sequenceName,allocationSize = 1)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @OneToMany(mappedBy = "questionsEntity",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AnswerToEntity> answer = new LinkedHashSet<>();

}
