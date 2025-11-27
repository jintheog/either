package com.example.either.service;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.repository.AnswerRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public long getAnswerCount(Long questionId, String answerText) {
        return answerRepository.countByQuestionIdAndAnswerText(questionId, answerText);
    }

    @Transactional
    public void saveAnswer(Long question_id, Answer answer)
    {
        Question question = questionService.getQuestionById(question_id);
        question.addAnswer(answer);
        answerRepository.save(answer);
    }
}
