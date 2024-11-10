package com.tuit.diplomish.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "answers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerToEntity {
    @Transient
    private static final String sequenceName = "questions_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName,sequenceName = sequenceName,allocationSize = 1)
    private Long id;

    private String answer;

    private Boolean correctAnswer;

    @ManyToOne
    private QuestionsEntity questionsEntity;

}
