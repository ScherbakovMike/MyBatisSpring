package ru.mikescherbakov.mappers;

import org.apache.ibatis.annotations.Mapper;
import ru.mikescherbakov.models.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper {
    @Select("SELECT * FROM QUESTIONS WHERE id = #{id}")
    Question getQuestion(@Param("id") Integer id);
}
