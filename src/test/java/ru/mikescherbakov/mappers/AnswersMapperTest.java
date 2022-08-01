package ru.mikescherbakov.mappers;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.mikescherbakov.configuration.MyBatisConfig;
import ru.mikescherbakov.models.Question;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = MyBatisConfig.class)
class AnswersMapperTest {
    @Autowired
    AnswerStatisticMapper answerStatisticMapper;

    @Autowired
    SqlSessionFactory sessionFactory;

    @Test
    void onDatabaseNotEmpty_ReturnsRecords() {
        var result = answerStatisticMapper.getAnswersCountStat();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}