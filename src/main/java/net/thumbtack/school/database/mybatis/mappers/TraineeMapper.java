package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface TraineeMapper {

    @Insert("INSERT INTO trainee ( firstname, lastname, raiting) VALUES "
            + "( #{firstName}, #{lastName}, #{raiting} )")
    @Options(useGeneratedKeys = true)
    Integer insert(Trainee trainee);

    @Select("SELECT id, firstname, lastname, raiting FROM trainee WHERE id = #{id}")
    /*@Results({
            @Result(property = "id", column = "id"),
            @Result(property = "addresses", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.mybatis.mappers.AddressMapper.getByAuthor", fetchType = FetchType.LAZY)),
            @Result(property = "books", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.mybatis.mappers.BookMapper.getByAuthor", fetchType = FetchType.LAZY))
    })

     */
    Trainee getById(int id);


    @Select("SELECT id, firstname, lastname, raiting from trainee")
    List<Trainee> getAll();

    @Update("UPDATE trainee SET firstname =#{firstName}, lastname=#{lastName}, raiting=#{raiting} WHERE id = #{id}")
    Integer update(Trainee trainee);


    @Insert({"<script>",
            "insert into trainee (firstname, lastname, raiting) values",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstName}, #{item.lastName}, #{item.raiting} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(List<Trainee> trainees);

    @Delete("DELETE FROM trainee WHERE id=#{trainee.id}")
    void delete(@Param("trainee") Trainee trainee);

    @Delete("DELETE FROM trainee")
    void deleteAll();

    @Select({"<script>",
            "SELECT id, firstname, lastname, raiting FROM trainee",
            "<where>" +
                    "<if test='firstName != null'> firstname=#{firstName}",
            "</if>",
            "<if test='lastName != null'> AND lastname like #{lastName}",
            "</if>",
            "<if test='raiting != null'> AND raiting =#{raiting}",
            "</if>",
            "</where>" +
                    "</script>"})
    List<Trainee> getAllWithParams(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("raiting") Integer rating);


    @Select("SELECT id, firstname as firstName, lastname as lastName, raiting FROM trainee WHERE id IN (SELECT traineeId FROM trainee_groups WHERE groupsid = #{group.id})")
    List<Trainee> getByGroup(@Param("group") Group group);

    @Insert("INSERT INTO trainee_groups ( traineeId, groupsid) VALUES "
            + "( #{tr.id}, #{gr.id} )")
    void isnertTraineeGroups(@Param("tr") Trainee trainee, @Param("gr") Group group);
}
