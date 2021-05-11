package net.thumbtack.school.database.mybatis.daoimpl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.GroupDao;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GroupDaoImpl extends DaoImplBase implements GroupDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);

    @Override
    public Group insert(School school, Group group) {
        LOGGER.debug("DAO insert group {} school {}", group, school);
        try (SqlSession sqlSession = getSession()) {
            try {
                if (school.getId()==0) {
                    throw new RuntimeException();
                }
                getGroupMapper(sqlSession).insert(group,school);
                //getGroupMapper(sqlSession).insertGroupSchool(group, school);
            } catch (RuntimeException ex) {
                LOGGER.info("Cant insert group {} school {}", group, school, ex);
                throw ex;
            }
            sqlSession.commit();
            //school.getGroups().add(group);
        }
        return group;
    }

    @Override
    public Group update(Group group) {
        LOGGER.debug("DAO update group {}", group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).update(group);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update group {}", group, ex);
                throw ex;
            }
            sqlSession.commit();
        }
        return group;
    }

    @Override
    public List<Group> getAll() {
        // получает список всех Group, независимо от School
        LOGGER.debug("DAO get all groups");
        try (SqlSession sqlSession = getSession()) {
            return getGroupMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all groups");
            throw ex;
        }
    }

    @Override
    public void delete(Group group) {
        LOGGER.debug("DAO delete group {}", group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).delete(group);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete group {}", group, ex);
                throw ex;
            }
            sqlSession.commit();
        }

    }

    @Override
    public Trainee moveTraineeToGroup(Group group, Trainee trainee) {
        // переводит Trainee в Group. Если Trainee не принадлежал никакой Group, добавляет его в Group
        LOGGER.debug("DAO move Trainee {} to Group {}", trainee, group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).moveTraineeToGroup(group, trainee);
            } catch (PersistenceException ex) {
                getGroupMapper(sqlSession).move2TraineeToGroup(group,trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't move Trainee {} to Group {}", trainee, group);
                throw ex;
            }
            sqlSession.commit();
        }
        return trainee;
    }

    @Override
    public void deleteTraineeFromGroup(Trainee trainee) {
        LOGGER.debug("DAO delete Trainee {} from group", trainee);
        try (SqlSession sqlSession=getSession()) {
            try {
                getGroupMapper(sqlSession).deleteTraineeFromGroup(trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Trainee {} from group",trainee,ex);
                throw ex;
            }
            sqlSession.commit();
        }

    }

    @Override
    public void addSubjectToGroup(Group group, Subject subject) {
        LOGGER.debug("DAO add Subject {} to Group {}",subject,group);
        try (SqlSession sqlSession=getSession()) {
            try {
                getGroupMapper(sqlSession).addSubjectToGroup(group,subject);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't add Subject {} to Group {}",subject,group,ex);
                throw ex;
            }
            sqlSession.commit();
        }

    }
}
