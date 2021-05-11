package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.mybatis.dao.CommonDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonDaoImpl extends DaoImplBase implements CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoImpl.class);

    @Override
    public void clear() {
        LOGGER.debug("DAO clear");
        try (SqlSession sqlSession=getSession()){
            try {
                getTraineeMapper(sqlSession).deleteAll();
                getSubjectMapper(sqlSession).deleteAll();
                getSchoolMapper(sqlSession).deleteAll();
                //deleteALL for GROUPS
            } catch (RuntimeException ex) {
                LOGGER.info("Can't clear");
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
