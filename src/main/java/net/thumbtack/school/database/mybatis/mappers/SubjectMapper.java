package net.thumbtack.school.database.mybatis.mappers;


import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {

    @Insert("INSERT INTO subjects (subjname) VALUES "
            + "( #{name} )")
    @Options(useGeneratedKeys = true)
    Integer insert(Subject subject);

    @Select("SELECT id, subjname as name FROM subjects WHERE id = #{id}")
    /*@Results({
            @Result(property = "id", column = "id"),
            @Result(property = "addresses", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.mybatis.mappers.AddressMapper.getByAuthor", fetchType = FetchType.LAZY)),
            @Result(property = "books", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.mybatis.mappers.BookMapper.getByAuthor", fetchType = FetchType.LAZY))
    })

     */
    Subject getById(int id);

    @Select("SELECT id, subjname as name FROM subjects")
    List<Subject> getAll();

    @Update("UPDATE subjects SET subjname = #{name} WHERE id = #{id}")
    void update(Subject subject);

    @Delete("DELETE FROM subjects WHERE id=#{subject.id}")
    void delete(@Param("subject") Subject subject);

    @Delete("DELETE FROM subjects")
    void deleteAll();

    @Select("SELECT id, subjname as name FROM subjects WHERE id IN (SELECT subjectsId FROM subjects_groups WHERE groupsid = #{group.id})")
    List<Subject>getByGroup(@Param("group") Group group);
}
