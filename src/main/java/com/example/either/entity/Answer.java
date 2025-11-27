package com.example.either.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT", length = 1)
    private String answerText;
    @Column(nullable = false, columnDefinition = "TEXT", length = 500)
    private String content;
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

}