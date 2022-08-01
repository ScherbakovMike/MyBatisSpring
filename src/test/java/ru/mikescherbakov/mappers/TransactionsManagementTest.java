package ru.mikescherbakov.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.mikescherbakov.configuration.MyBatisConfig;
import ru.mikescherbakov.models.Question;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@SpringBootTest
@ContextConfiguration(classes = MyBatisConfig.class)
class TransactionsManagementTest {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @BeforeEach
    void setUp() {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
    }

    @Test
    @Transactional(propagation = NOT_SUPPORTED, timeout = 30, rollbackFor = DataIntegrityViolationException.class)
    void onCommitTransaction_SaveEntity() {
        assertThrows(DataIntegrityViolationException.class,
                () -> transactionTemplate.execute(status -> {
                    String testTitle = null;
                    var testContent = "test content";

                    var question = new Question();
                    question.setTitle(testTitle);
                    question.setContent(testContent);

                    try {
                        questionMapper.addQuestion(question);
                    } catch (Exception e) {
                        status.setRollbackOnly();
                        throw e;
                    }
                    return questionMapper.getQuestionByTitle(testTitle).getId();
                }));
    }
}