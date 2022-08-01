package ru.mikescherbakov.mappers;

import org.apache.ibatis.annotations.*;
import ru.mikescherbakov.models.Question;

@Mapper
public interface QuestionMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "image", column = "image")
    })

    @Select("SELECT * FROM QUESTIONS WHERE id = #{id}")
    Question getQuestionById(@Param("id") Integer id);

    @Select("SELECT * FROM QUESTIONS WHERE title = #{title}")
    Question getQuestionByTitle(@Param("title") String title);


    @Insert("INSERT INTO QUESTIONS (TITLE, CONTENT) VALUES (#{title}, #{content})")
    void addQuestion(Question question);

    @Delete("DELETE FROM QUESTIONS WHERE title= #{title}")
    void removeQuestion(@Param("title") String title);

}
