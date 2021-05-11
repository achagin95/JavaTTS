package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SchoolMapper {
//позже исправить ХМЛ файл маппера

    @Insert("INSERT INTO school (schoolName, year) VALUES "
            + "( #{name}, #{year} )")
    @Options(useGeneratedKeys = true)
    Integer insert(School school);

    @Select("SELECT id, schoolName as name, year FROM school WHERE id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchool", fetchType = FetchType.LAZY)),
    })
    School getById(int id);
    //в лейзи методе указан неправильный метод у ГрупМаппера(т.к. еще не добавлен), позже исправить

    @Select("SELECT id, schoolName as name, year FROM school")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchool", fetchType = FetchType.LAZY))})
    List<School> getAllLazy();

    @Update("UPDATE school SET schoolName =#{name}, year=#{year} WHERE id = #{id}")
    void update(School school);

    @Delete("DELETE FROM school WHERE id=#{sc.id}")
    void delete(@Param("sc") School school);

    @Delete("DELETE FROM school")
    void deleteAll();
}
