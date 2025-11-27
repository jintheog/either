package com.example.either.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "answer")
@NoArgsConstructor
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

    public Answer(String answerText, String content, Question question){
        this.answerText = answerText;
        this.content = content;
        if(question != null){
            question.addAnswer(this);
        }
    }
}