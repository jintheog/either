package com.example.either.controller;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.service.AnswerService;
import com.example.either.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String eitherHome(Model model) {
        List<Question> questions = questionService.findAllQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("title", "A VS B Home");
        return "either/home";
    }

    @GetMapping("/new")
    public String newQuestion(Model model) {
        model.addAttribute("title", "New Question");
        return "either/new";
    }

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question question) {
        questionService.createQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String questionDetail(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        List<Answer> answers = answerService.getAnswersByQuestionId(id);
        int countTotal = answers.size();
        int countOptionA = 0;
        double percentA = 0;
        double percentB = 0;
        if(countTotal != 0){
            countOptionA = answers.stream()
                    .mapToInt(answer -> answer.getAnswerText().equals("A") ? 1 : 0)
                    .sum();
            percentA = ((double) countOptionA / countTotal) * 100;
            percentB = (double) 100 - percentA;
        } else{
            percentA = 50;
            percentB = 50;
        }
        model.addAttribute("percentA",  percentA);
        model.addAttribute("percentB", percentB);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        model.addAttribute("title", question.getTitle());
        return "either/detail";
    }

    @PostMapping("/{questionId}/answer/create")
    public String createAnswer(@PathVariable Long questionId, @ModelAttribute Answer answerForm) {
        answerService.saveAnswer(questionId, answerForm);
        return "redirect:/questions/" + questionId;
    }
}
