package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.GroupDao;
import net.thumbtack.school.database.mybatis.dao.SchoolDao;
import net.thumbtack.school.database.mybatis.dao.SubjectDao;
import net.thumbtack.school.database.mybatis.dao.TraineeDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SchoolDaoImpl extends DaoImplBase implements SchoolDao {
    //позже исправить ХМЛ файл МАППЕРА

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoImpl.class);

    @Override
    public School insert(School school) {
        // вставляет School в базу данных.
        LOGGER.debug("DAO insert school {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).insert(school);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert school {}", school, ex);
                throw ex;
            }
            sqlSession.commit();
        }
        return school;
    }

    @Override
    public School getById(int id) {
        // получает School по его ID. Если такого ID нет, метод должен возвращать null
        LOGGER.debug("DAO get School by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSchoolMapper(sqlSession).getById(id);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't get school by id {}", id, ex);
                throw ex;
            }
        }
    }

    @Override
    public List<School> getAllLazy() {
        // получает список всех School вместе с Group, Subject, и Trainee, используя LAZY подход.
        // Если БД не содержит ни одной School, метод должен возвращать пустой список
        LOGGER.debug("DAO get All Lazy");
        try (SqlSession sqlSession = getSession()) {
            return getSchoolMapper(sqlSession).getAllLazy();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all lazy", ex);
            throw ex;
        }
    }

    @Override
    public List<School> getAllUsingJoin() {
        // получает список всех School вместе с Group, Subject, и Trainee, используя JOIN подход.
        // Если БД не содержит ни одной School, метод должен возвращать пустой список
        LOGGER.debug("DAO get All using JOIN");
        try (SqlSession sqlSession = getSession()) {
            return sqlSession.selectList("net.thumbtack.school.database.mybatis.mappers.SchoolMapper.getAllUsingJoin");
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all using JOIN");
            throw ex;
        }
        //сделно коряво, для теста, разобраться с иф елсе в sql т.к. при полном запросе выводит пустой запрос при отсутствии трайни
        /*SELECT
        school.id as id,schoolName, year,
        groups.id as group_id, groupName, room,
        trainee.id as trainee_id, firstname, lastname, raiting,
        subjects.id as subject_id, subjname
        FROM school
        join school_groups on school_groups.schoolid = school.id
        join groups on groups.id = school_groups.groupsid
        join trainee_groups on trainee_groups.groupsid=groups.id
        join trainee on trainee.id=trainee_groups.traineeId
        join subjects_groups on subjects_groups.groupsid=groups.id
        join subjects on subjects.id=subjects_groups.subjectsId

         */


    }

    @Override
    public void update(School school) {
        // изменяет School  в базе данных
        LOGGER.debug("DAO update school {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).update(school);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update school {}", school, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }

    }

    @Override
    public void delete(School school) {
        // удаляет School  в базе данных, при этом удаляются все Group
        LOGGER.debug("DAO delete school {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).delete(school);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete school {}", school, ex);
                sqlSession.rollback();
                throw ex;

            }
            sqlSession.commit();
        }

    }

    @Override
    public void deleteAll() {
        // удаляет все School  в базе данных, при этом удаляются все Group для каждой School
        LOGGER.debug("DAO delete all");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).deleteAll();
                getGroupMapper(sqlSession).deleteALL();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete All", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }


    }

    @Override
    public School insertSchoolTransactional(School school2018) {
        // трансакционно вставляет School вместе с ее Group, со всеми Trainee этих Group, и привязывает все Subject для этих Group
        // предполагается, что сами Subject были вставлены раньше

        LOGGER.debug("DAO insert School {} transactional", school2018);
        List<Group> groups = school2018.getGroups();

        GroupDao groupDao = new GroupDaoImpl();
        TraineeDao traineeDao = new TraineeDaoImpl();

        try (SqlSession sqlSession = getSession()) {
            try {
                insert(school2018);
                if (groups != null && groups.size() != 0) {
                    for (Group group : groups) {
                        groupDao.insert(school2018, group);
                        if (group.getTrainees() != null && group.getTrainees().size() != 0) {
                            for (Trainee trainee:group.getTrainees()) {
                                traineeDao.insert(group, trainee);
                            }
                        }
                        if (group.getSubjects() != null && group.getSubjects().size() != 0) {
                            for (Subject subject: group.getSubjects()) {
                                groupDao.addSubjectToGroup(group, subject);
                            }
                        }
                    }
                }

            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert School {} transactional",school2018,ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }

        return school2018;
    }
}
