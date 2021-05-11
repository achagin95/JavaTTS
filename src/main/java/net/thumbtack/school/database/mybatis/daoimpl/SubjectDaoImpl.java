package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.mybatis.dao.SubjectDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubjectDaoImpl extends DaoImplBase implements SubjectDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoImpl.class);

    @Override
    public Subject insert(Subject subject) {
        // вставляет Subject в базу данных.
        LOGGER.debug("DAO insert subject {} ", subject);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).insert(subject);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert subject {} {}", subject, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return subject;
    }

    @Override
    public Subject getById(int id) {
        // получает Subject по его ID. Если такого ID нет, метод возвращает null
        LOGGER.debug("DAO get subject by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getSubjectMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get subject {}", id, ex);
            throw ex;
        }
    }

    @Override
    public List<Subject> getAll() {
        // получает список всех Subject. Если БД не содержит ни одного Subject, метод возвращает пустой список
        LOGGER.debug("DAO get all subjects");
        try (SqlSession sqlSession=getSession()) {
            return getSubjectMapper(sqlSession).getAll();

        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all subjects");
            throw ex;
        }
    }

    @Override
    public Subject update(Subject subject) {
        // изменяет Subject  в базе данных
        LOGGER.debug("DAO update subject {}", subject);
        try (SqlSession sqlSession=getSession()) {
            try {
                getSubjectMapper(sqlSession).update(subject);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update subject {}",subject, ex);
                throw ex;
            }
            sqlSession.commit();
        }
        return subject;
    }

    @Override
    public void delete(Subject subject) {
        LOGGER.debug("DAO delete subject {}", subject);
        try (SqlSession sqlSession=getSession()) {
            try {
                getSubjectMapper(sqlSession).delete(subject);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete subject {}",subject, ex);
                throw ex;
            }
            sqlSession.commit();
        }

    }

    @Override
    public void deleteAll() {
        // удаляет все Subject из базы данных
        LOGGER.debug("DAO delete all subjects");
        try (SqlSession sqlSession=getSession()) {
            try {
                getSubjectMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all subjects", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();

        }

    }
}
