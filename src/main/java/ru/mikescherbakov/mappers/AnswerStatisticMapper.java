package ru.mikescherbakov.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnswerStatisticMapper {
    @Select("SELECT SESSIONS.STUDENT, " +
            "COUNT(ANSWERS.ID)" +
            "FROM SESSIONS AS SESSIONS " +
            "LEFT JOIN ANSWERS AS ANSWERS " +
            "ON SESSIONS.QUESTION=ANSWERS.ID " +
            "GROUP BY SESSIONS.STUDENT")
    List<Map<String, Object>> getAnswersCountStat();
}
