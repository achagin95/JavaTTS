package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupMapper {

    @Select("SELECT id, groupName as name, room FROM groups WHERE schoolId IN (#{school.id})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY))})
    List<Group> getBySchool(@Param("school") School school);

    @Insert("INSERT INTO groups (groupName, room, schoolId) VALUES"
            + "( #{group.name},#{group.room}, #{school.id})")
    @Options(useGeneratedKeys = true,keyProperty = "group.id", keyColumn = "id")
    Integer insert(@Param("group") Group group, @Param("school") School school);

    /*@Insert("INSERT INTO school_groups (schoolid, groupsid) VALUES"
            + " (#{school.id},#{group.id})")
    void insertGroupSchool(@Param("group") Group group, @Param("school") School school);

     */


    @Update("UPDATE groups SET groupName =#{name}, room=#{room} WHERE id = #{id}")
    void update(Group group);

    @Select("SELECT id, groupName as name, room FROM groups")
    List<Group> getAll();

    @Delete("DELETE FROM groups WHERE id=#{gr.id}")
    void delete(@Param("gr") Group group);

    @Insert("INSERT INTO trainee_groups (traineeId, groupsid) VALUES"
            + "( #{tr.id},#{gr.id})")
    void moveTraineeToGroup(@Param("gr") Group group, @Param("tr") Trainee trainee);

    @Update("UPDATE trainee_groups SET groupsid =#{gr.id} WHERE traineeId = #{tr.id}")
    void move2TraineeToGroup(@Param("gr") Group group, @Param("tr") Trainee trainee);

    @Delete("DELETE FROM trainee_groups WHERE traineeId = #{tr.id}")
    void deleteTraineeFromGroup(@Param("tr") Trainee trainee);

    @Insert("INSERT INTO subjects_groups (subjectsId, groupsid) VALUES"
            + "( #{subj.id}, #{gr.id})")
    void addSubjectToGroup(@Param("gr") Group group,@Param("subj") Subject subject);

    @Delete("DELETE FROM groups")
    void deleteALL();
}
