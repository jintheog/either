package com.example.either;

import com.example.either.entity.Question;
import com.example.either.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EitherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EitherApplication.class, args);
    }

    // 샘플 데이터 생성
    @Bean
    public CommandLineRunner initData(QuestionRepository qr) {
        return args -> {
            qr.save(new Question("짜장면 VS 짬뽕?", "짜장면", "짬뽕"));
            qr.save(new Question("여름 VS 겨울?", "여름", "겨울"));
            qr.save(new Question("아침형 VS 저녁형?", "아침형", "저녁형"));
            qr.save(new Question("갤럭시 VS 아이폰?", "갤럭시", "아이폰"));

            System.out.println("샘플 데이터 " + qr.count() + "개 생성 완료");
        };
    }
}
