package ru.mikescherbakov.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.mikescherbakov.configuration.MyBatisConfig;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = MyBatisConfig.class)
class QuestionMapperTest {
    @Autowired
    QuestionMapper questionMapper;

    @Test
    void whenRecordsInDatabase_recordFieldsAreCorrect() {
        var question = questionMapper.getQuestion(1);
        assertNotNull(question);
        assertFalse(question.getTitle().isBlank());
    }
}