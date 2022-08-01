package ru.mikescherbakov.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import ru.mikescherbakov.configuration.MyBatisConfig;
import ru.mikescherbakov.models.Question;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = MyBatisConfig.class)
class QuestionMapperTest {

    @Autowired
    QuestionMapper questionMapper;

    @Test
    void whenRecordsInDatabase_recordFieldsAreCorrect() {
        var question = questionMapper.getQuestionById(1);
        assertNotNull(question);
        assertFalse(question.getTitle().isBlank());
    }

    @Test
    void insertNewQuestion_QuestionAddedRemovedCorrectly() {
        var testTitle = "test title";
        var testContent = "test content";
        var question = questionMapper.getQuestionByTitle(testTitle);
        assertNull(question);

        question = new Question();
        question.setTitle(testTitle);
        question.setContent(testContent);
        questionMapper.addQuestion(question);

        question = questionMapper.getQuestionByTitle(testTitle);
        assertNotNull(question);

        questionMapper.removeQuestion(testTitle);
        question = questionMapper.getQuestionByTitle(testTitle);
        assertNull(question);

    }
}