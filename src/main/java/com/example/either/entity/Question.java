package com.example.either.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 200)
    private String optionA;
    @Column(nullable = false, length = 200)
    private String optionB;

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true // 이걸 하게 되면 Post에서 Comment를 제거할 때 자동으로 삭제됨
    )
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer){
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer){
        answers.remove(answer);
        answer.setQuestion(null);
    }


    public Question(String title, String optionA, String optionB) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
    }
}