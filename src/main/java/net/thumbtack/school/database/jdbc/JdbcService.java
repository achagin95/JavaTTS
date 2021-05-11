package net.thumbtack.school.database.jdbc;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {


    public static void insertTrainee(Trainee trainee) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();

        String insertQuery = "insert into trainee values(?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, trainee.getFirstName());
            stmt.setString(3, trainee.getLastName());
            stmt.setInt(4, trainee.getRating());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trainee.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Creating user failed, no ID obtained.");
            }


        }
    }


    private int getLastId()  throws SQLException{
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();

        int id = -1;
        String findID = "select*from trainee where id=(select max(id) from trainee)";
        try (PreparedStatement stmt = con.prepareStatement(findID)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        }
        return id;
    }

    public static void updateTrainee(Trainee trainee)throws SQLException{

        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        JdbcService js = new JdbcService();
        int id = js.getLastId();

        String updateQuery = "update trainee set firstname = ?, lastname=?, raiting=? where id=?";
        try (PreparedStatement stmt = con.prepareStatement(updateQuery)) {
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }

    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {

        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();

        String exequteQuery = "select * from trainee where id = ?";

        try (PreparedStatement stmt = con.prepareStatement(exequteQuery)) {
            stmt.setInt(1, traineeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int raiting = rs.getInt("raiting");
                return new Trainee(id, firstName, lastName, raiting);
            }

        }
        return null;

    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();


        String exequteQuery = "select * from trainee where id = ?";
        try (PreparedStatement stmt = con.prepareStatement(exequteQuery)) {
            stmt.setInt(1, traineeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int raiting = rs.getInt(4);
                return new Trainee(id, firstName, lastName, raiting);
            }

        }
        return null;

    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String exequteQuery = "select * from trainee";

        try (PreparedStatement stmt = con.prepareStatement(exequteQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int raiting = rs.getInt("raiting");
                Trainee trainee = new Trainee(id, firstName, lastName, raiting);
                trainees.add(trainee);
            }
        }
        return trainees;

    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String exequteQuery = "select * from trainee";

        try (PreparedStatement stmt = con.prepareStatement(exequteQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int raiting = rs.getInt(4);
                Trainee trainee = new Trainee(id, firstName, lastName, raiting);
                trainees.add(trainee);
            }
        }
        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {

        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String exequteUpdate = "delete from trainee where firstname=? and lastname=? and raiting=?";
        try (PreparedStatement stmt = con.prepareStatement(exequteUpdate)) {
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.executeUpdate();
        }

    }

    public static void deleteTrainees() throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String exequteUpdate = "DELETE FROM trainee";
        try (PreparedStatement stmt = con.prepareStatement(exequteUpdate)) {
            stmt.executeUpdate();
        }

    }

    public static void insertSubject(Subject subject) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();

        String insertQuery = "insert into subjects values(?,?)";
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, subject.getName());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Creating subject failed, no ID obtained.");
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String qwe = "select * from subjects where id = ?";

        try (PreparedStatement stmt = con.prepareStatement(qwe)) {
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("subjname");

                return new Subject(id, name);
            }
        }
        return null;

    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String qwe = "select * from subjects where id = ?";

        try (PreparedStatement stmt = con.prepareStatement(qwe)) {
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);

                return new Subject(id, name);
            }
        }
        return null;
    }


    public static void deleteSubjects() throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String exequteUpdate = "DELETE FROM subjects";
        try (PreparedStatement stmt = con.prepareStatement(exequteUpdate)) {
            stmt.executeUpdate();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String insertQuery = "insert into school values(?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, school.getName());
            stmt.setInt(3, school.getYear());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    school.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Creating school failed, no ID obtained.");
            }
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String qwe = "select * from school where id = ?";

        try (PreparedStatement stmt = con.prepareStatement(qwe)) {
            stmt.setInt(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //позже поменять на иф
                int id = rs.getInt("id");
                String name = rs.getString("schoolName");
                int year = rs.getInt("year");

                return new School(id, name, year);
            }
        }
        return null;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String qwe = "select * from school where id = ?";

        try (PreparedStatement stmt = con.prepareStatement(qwe)) {
            stmt.setInt(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                int year = rs.getInt(3);

                return new School(id, name, year);
            }
        }
        return null;
    }


    public static void deleteSchools() throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String exequteUpdate = "DELETE FROM school";
        try (PreparedStatement stmt = con.prepareStatement(exequteUpdate)) {
            stmt.executeUpdate();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();

        String exequteUpdate = "insert into groups values(?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(exequteUpdate, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, group.getName());
            stmt.setString(3, group.getRoom());
            stmt.setInt(4,school.getId());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    group.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Creating group failed, no ID obtained.");
            }
        }

        /*String exequteUpdateConnectSchoolAndGroup = "insert into school_groups values(?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(exequteUpdateConnectSchoolAndGroup)) {
            stmt.setNull(1, Types.INTEGER);
            stmt.setInt(2, school.getId());
            stmt.setInt(3, group.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw throwables;
        }

         */
        //school.getGroups().add(group);


    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();

        School school = null;

        String executeQuery = "select * from groups join school on schoolId=school.id where school.id=?";

        try (PreparedStatement stmt = con.prepareStatement(executeQuery)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            int check = 0;
            while (rs.next()) {
                if (check == 0) {
                    int idSchool = rs.getInt(5);
                    String nameSchool = rs.getString(6);
                    int yearSchool = rs.getInt(7);
                    school = new School(idSchool, nameSchool, yearSchool);
                    check++;
                }
                int idGroup = rs.getInt(1);
                String nameGroup = rs.getString(2);
                String roomGroup = rs.getString(3);
                Group group = new Group(idGroup, nameGroup, roomGroup);
                school.getGroups().add(group);
            }
        }
        return school;

    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        List<School> schoolList = new ArrayList<>();
        JdbcUtils.createConnection();
        Connection con = JdbcUtils.getConnection();
        String executeQuery = "select * from groups join school on schoolId=school.id order by school.id, groups.id";

        School school = null;
        try (PreparedStatement stmt = con.prepareStatement(executeQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int schoolId = rs.getInt(5);
                if (school == null || schoolId != school.getId()) {
                    if (school != null) {
                        schoolList.add(school);
                    }
                    String nameSchool = rs.getString(6);
                    int yearSchool = rs.getInt(7);
                    school = new School(schoolId, nameSchool, yearSchool);
                }

                int idGroup = rs.getInt(1);
                String nameGroup = rs.getString(2);
                String roomGroup = rs.getString(3);
                Group group = new Group(idGroup, nameGroup, roomGroup);
                school.getGroups().add(group);
            }
        }
        schoolList.add(school);
        return schoolList;

    }


}
