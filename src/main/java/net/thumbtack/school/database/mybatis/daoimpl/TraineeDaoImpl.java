package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.TraineeDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TraineeDaoImpl extends DaoImplBase implements TraineeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraineeDaoImpl.class);

    @Override
    public Trainee insert(Group group, Trainee trainee) {
        // вставляет Trainee в базу данных. Если group не null,  Trainee после вставки принадлежит этой группе
        // в противном случае Trainee не принадлежит никакой группе
        //LOGGER.debug("DAO insert trainee {} groups {}", trainee, group);
        LOGGER.debug("DAO insert trainee {} ", trainee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).insert(trainee);
                if (group!=null) {
                    getTraineeMapper(sqlSession).isnertTraineeGroups(trainee,group);
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert trainee {} groups {} {}", trainee, group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        //if (group != null)
        //    group.getTrainees().add(trainee);
        return trainee;

    }

    @Override
    public Trainee getById(int id) {
        // получает Trainee по его ID. Если такого ID нет, метод возвращает null
        LOGGER.debug("DAO get trainee by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getTraineeMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get trainee {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Trainee> getAll() {
        // получает список всех Trainee, независимо от их Group. Если БД не содержит ни одного Trainee, метод возвращает пустой список
        LOGGER.debug("DAO get all ");
        try (SqlSession sqlSession = getSession()) {
            return getTraineeMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get All {}", ex);
            throw ex;
        }
    }

    @Override
    public Trainee update(Trainee trainee) {
        // изменяет Trainee в базе данных. Метод не изменяет принадлежности Trainee к Group
        LOGGER.debug("DAO update trainee {} ", trainee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).update(trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update trainee {}", ex);
                throw ex;
            }
            sqlSession.commit();
        }
        return trainee;


    }

    @Override
    public List<Trainee> getAllWithParams(String firstName, String lastName, Integer rating) {
        // получает список всех Trainee при условиях
        // если firstName не равен null - только имеющих это имя
        // если lastName не равен null - только имеющих эту фамилию
        // если rating не равен null - только имеющих эту оценку
        LOGGER.debug("DAO get all with params {} {} {}", firstName,lastName,rating);
        try (SqlSession sqlSession=getSession()) {
            return getTraineeMapper(sqlSession).getAllWithParams(firstName,lastName,rating);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all with params", ex);
            throw ex;
        }


    }

    @Override
    public void batchInsert(List<Trainee> trainees) {
        // вставляет список из Trainee в базу данных. Вставленные Trainee не принадлежат никакой группе
        LOGGER.debug("DAO batch insert {}",trainees);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).batchInsert(trainees);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't batch insert trainees {} {} ", trainees, ex);
                sqlSession.rollback();
                throw  ex;
            }
            sqlSession.commit();
        }

    }

    @Override
    public void delete(Trainee trainee) {
        // удаляет Trainee из базы данных
        LOGGER.debug("DAO delete trainee {}",trainee);
        try (SqlSession sqlSession=getSession()) {
            try {
                getTraineeMapper(sqlSession).delete(trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete trainee {} {} ", trainee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }

    }

    @Override
    public void deleteAll() {
// удаляет все Trainee из базы данных
        LOGGER.debug("DAO delete all trainees");
        try (SqlSession sqlSession=getSession()) {
            try {
                getTraineeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all trainees",ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }

    }


}
