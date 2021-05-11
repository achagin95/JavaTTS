package net.thumbtack.school.elections;

import com.google.gson.Gson;

import net.thumbtack.school.elections.model.Status;
import net.thumbtack.school.elections.model.Voter;

import net.thumbtack.school.elections.dao.SessionDao;
import net.thumbtack.school.elections.dao.SessionDaoImpl;
import net.thumbtack.school.elections.database.DataBase;

import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.exceptions.RegErrorCode;
import net.thumbtack.school.elections.exceptions.RegException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWorkServer {


    @BeforeAll
    static void testWorkDataBeforeAll_Register() throws IOException {

        Gson gson = new Gson();
        Voter voter1 = new Voter("Иванов Иван Иванович", "Иванов", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter2 = new Voter("Петров", "Иван", "Федорович", "Петрович", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter3 = new Voter("QWertyuy", "QWeert", "Петрович", "qwe", "10 Лет Октября д 15 кв 6");
        Voter voter4 = new Voter("QWertyuy", "QWeert", "Петрович", "qweqweqwe", "10 Лет Октября д 15 кв 6");
        Voter voter5 = new Voter("QWertyuy", "QWeert", "Йцукен", "qweqweqwe", "10 Лет Октября д 15 кв 6");

        Server server = new Server();
        server.startServer("test");
        assertEquals(gson.toJson("Register success"),server.registerVoter(voter1.voterToJson()));
        assertEquals(gson.toJson("Register success"),server.registerVoter(voter2.voterToJson()));
        assertEquals(gson.toJson(RegErrorCode.REGISTER_WRONG_PASSWORD),server.registerVoter(voter3.voterToJson()));
        assertEquals(gson.toJson(RegErrorCode.DUPLICATE_VOTER),server.registerVoter(voter2.voterToJson()));
        assertEquals(gson.toJson(RegErrorCode.DUPLICATE_VOTER),server.registerVoter(voter2.voterToJson()));
        assertEquals(gson.toJson(RegErrorCode.DUPLICATE_VOTER),server.registerVoter(voter4.voterToJson()));
        assertEquals(gson.toJson("Register success"),server.registerVoter(voter5.voterToJson()));


        assertEquals(3, DataBase.getInstance().getDataVoterSet().size());
        assertEquals(3, DataBase.getInstance().getDataVoterSet().size());


        server.stopServer("test");
        Server server2 = new Server();
        server2.startServer("test");
        Voter voter6 = new Voter("Фамилия6 Имя6 Отчество6", "Master", "qwertyu", "10 Лет Октября д 15 кв 6");
        assertEquals(gson.toJson("Register success"),server2.registerVoter(voter6.voterToJson()));
        assertEquals(4, DataBase.getInstance().getDataVoterSet().size());
        assertTrue(DataBase.getInstance().getDataVoterSet().contains(voter1));
        assertTrue(DataBase.getInstance().getDataVoterSet().contains(voter6));

        server2.stopServer("test");
    }

    @BeforeEach
    public void testWorkData1Before_LogIn() throws IOException {
        Voter voter1 = new Voter("Иванов Иван Иванович", "Иванов", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter2 = new Voter("Петров", "Иван", "Федорович", "Петрович", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter5 = new Voter("QWertyuy", "QWeert", "Йцукен", "qweqweqwe", "10 Лет Октября д 15 кв 6");
        Voter voter6 = new Voter("Фамилия6 Имя6 Отчество6", "Master", "qwertyu", "10 Лет Октября д 15 кв 6");
        Gson gson = new Gson();
        Server server = new Server();
        server.startServer("test");
        LoginDto loginpassword1 = new LoginDto(voter1.getLogin(), voter1.getPassword());
        server.logInVoter(gson.toJson(loginpassword1));

        LoginDto loginpassword2 = new LoginDto(voter2.getLogin(), voter2.getPassword());
        server.logInVoter(gson.toJson(loginpassword2));

        LoginDto loginpassword5 = new LoginDto(voter5.getLogin(), voter5.getPassword());
        server.logInVoter(gson.toJson(loginpassword5));

        LoginDto loginpassword6 = new LoginDto(voter6.getLogin(), voter6.getPassword());
        server.logInVoter(gson.toJson(loginpassword6));

    }

    @Test
    public void testWorkData2_LogIn_LogOut() throws IOException, RegException {
        Gson gson = new Gson();
        Server server2 = new Server();
        SessionDao asd = new SessionDaoImpl();

        Voter voter1 = asd.getVoterByLogin("Иванов");

        Voter voter6 = asd.getVoterByLogin("Master");


        String wronglogin = "ИвановMaster";
        String wrongpassword = "qwertyuqwertyu";


        LoginDto stForJsonWrong1 = new LoginDto(wronglogin, voter1.getPassword());
        assertEquals(gson.toJson(RegErrorCode.WRONG_LOGIN), server2.logInVoter(gson.toJson(stForJsonWrong1)));

        LoginDto stForJsonWrong2 = new LoginDto(voter1.getLogin(), wrongpassword);
        assertEquals(gson.toJson(RegErrorCode.WRONG_PASSWORD), server2.logInVoter(gson.toJson(stForJsonWrong2)));

        assertTrue(DataBase.getInstance().getDataVoterSet().contains(voter1));
        assertTrue(DataBase.getInstance().getDataVoterSet().contains(voter6));

        assertEquals(4, DataBase.getInstance().getUserOnlineMap().size());
        LoginDto loginpassword1 = new LoginDto(voter1.getLogin(), voter1.getPassword());
        assertEquals(gson.toJson(RegErrorCode.ERROR),server2.logInVoter(gson.toJson(loginpassword1)));

        UuidDto uuidVoter1 = new UuidDto(asd.getUUID(voter1));

        assertEquals(gson.toJson("logOut sucsess"),server2.logOutVoter(gson.toJson(uuidVoter1)));
        assertEquals(3, DataBase.getInstance().getUserOnlineMap().size());
        assertEquals(gson.toJson(RegErrorCode.ERROR),server2.logOutVoter(gson.toJson(uuidVoter1)));
        assertEquals(4, DataBase.getInstance().getDataVoterSet().size());
        assertFalse(DataBase.getInstance().getUserOnlineMap().containsValue(voter1));

        UuidDto uuidVoter6 = new UuidDto(asd.getUUID(voter6));
        server2.logOutVoter(gson.toJson(uuidVoter6));
        assertEquals(2, DataBase.getInstance().getUserOnlineMap().size());

        assertEquals(gson.toJson(RegErrorCode.ERROR), server2.logOutVoter(gson.toJson(uuidVoter6)));
        assertEquals(4, DataBase.getInstance().getDataVoterSet().size());
        //предполагается, что файл записан из предыдущих тестов.
        server2.stopServer("test");

    }

    @Test
    public void testWorkData3AddCandidate_AcceptCancelStatus_GetCandidates() throws IOException, RegException {
        Gson gson = new Gson();
        Server server = new Server();
        SessionDao asd = new SessionDaoImpl();
        Voter voter1 = asd.getVoterByLogin("Иванов");
        Voter voter6 = asd.getVoterByLogin("Master");
        Voter voter2 = asd.getVoterByLogin("Петрович");

        Voter voter7 = new Voter("firstNameNotFound", "lastNameNotFound", "LoginNotFound", "qweqweqwe", "10 Лет Октября д 15 кв 6");

        String uuidVoter1 = asd.getUUID(voter1);
        AddCandidateDto stTest1 = new AddCandidateDto(uuidVoter1, voter2);
        assertEquals(gson.toJson(""), server.addCandidate(gson.toJson(stTest1)));
        //assertEquals(1, DataBase.getInstance().getDataCandidate().size());
        assertEquals(0, DataBase.getInstance().getDataCandidate().size());
        assertEquals(Status.CANDIDATE_EXPECTATION, asd.getVoterByLogin(voter2.getLogin()).getStatus());
        String uuidVoter2 = asd.getUUID(voter2);
        UuidDto testAcceptStatusCandidate = new UuidDto(uuidVoter2);
        assertEquals(gson.toJson(""), server.cancelStatusCandidate(gson.toJson(testAcceptStatusCandidate)));
        assertEquals(0, DataBase.getInstance().getDataCandidate().size());
        assertEquals(Status.VOTER, asd.getVoterByLogin(voter2.getLogin()).getStatus());

        assertEquals(gson.toJson(""), server.addCandidate(gson.toJson(stTest1)));
        //assertEquals(1, DataBase.getInstance().getDataCandidate().size());
        assertEquals(0, DataBase.getInstance().getDataCandidate().size());
        assertEquals(Status.CANDIDATE_EXPECTATION, asd.getVoterByLogin(voter2.getLogin()).getStatus());
        //String uuidVoter2 = asd.getUUID(voter2);
        //UuidDto testAcceptStatusCandidate=new UuidDto(uuidVoter2);
        assertEquals(gson.toJson(""), server.acceptStatusCandidate(gson.toJson(testAcceptStatusCandidate)));
        assertEquals(1, DataBase.getInstance().getDataCandidate().size());

        AddCandidateDto stTest2 = new AddCandidateDto(uuidVoter1, voter7);
        assertEquals(gson.toJson(RegErrorCode.VOTER_NOT_FOUND), server.addCandidate(gson.toJson(stTest2)));

        UuidDto uuidVoter6 = new UuidDto(asd.getUUID(voter6));
        server.getCandidates(gson.toJson(uuidVoter6));

        AddCandidateDto stTest3 = new AddCandidateDto(uuidVoter6.getUuid(), voter6);
        assertEquals(gson.toJson(""), server.addCandidate(gson.toJson(stTest3)));
        assertEquals(2, DataBase.getInstance().getDataCandidate().size());
        assertTrue(DataBase.getInstance().getDataCandidate().contains(voter6));

        server.getCandidates(gson.toJson(uuidVoter6));
        server.stopServer("test");

        Server server2=new Server();
        server2.startServer("test");
        LoginDto stTest1Login = new LoginDto(voter1.getLogin(), voter1.getPassword());
        server2.logInVoter(gson.toJson(stTest1Login));
        System.out.println(server2.getCandidates(gson.toJson(new UuidDto(asd.getUUID(voter1)))));
        server2.stopServer("test");


    }

    @Test
    public void testWorkData4_addAdvice_AddScoreToAdvice_GetAdvices_ChangeAdvice_DeleteAdvice() throws IOException, RegException {

        Gson gson = new Gson();
        Server server = new Server();
        SessionDao asd = new SessionDaoImpl();
        Voter voter1 = asd.getVoterByLogin("Иванов");
        Voter voter6 = asd.getVoterByLogin("Master");
        Voter voter2 = asd.getVoterByLogin("Петрович");


        AddAdviceDto stTest1AddAdvice1 = new AddAdviceDto(asd.getUUID(voter1), "Починить дорогу на Wall Street");
        System.out.println(server.addAdvice(gson.toJson(stTest1AddAdvice1)));

        GetAdvicesDto stTest1GetAdvice1 = new GetAdvicesDto(asd.getUUID(voter2), voter1);
        System.out.println(server.getAdvices(gson.toJson(stTest1GetAdvice1)));

        AddChangeScoreToAdviceDto stTestAddScore = new AddChangeScoreToAdviceDto(asd.getUUID(voter2), asd.getUUID(voter1), 3, 1);

        System.out.println(server.addScoreToAdvice(gson.toJson(stTestAddScore)));
        assertEquals(gson.toJson(RegErrorCode.ERROR), server.addScoreToAdvice(gson.toJson(stTestAddScore)));

        AddChangeScoreToAdviceDto stTestChangeScore = new AddChangeScoreToAdviceDto(asd.getUUID(voter2), asd.getUUID(voter1), 5, 1);
        //System.out.println(server.changeScoreToAdvice(gson.toJson(stTestChangeScore)));
        assertEquals(gson.toJson("score was changed"), server.changeScoreToAdvice(gson.toJson(stTestChangeScore)));
        DeleteScoreToAdviceDto testDeleteScore=new DeleteScoreToAdviceDto(asd.getUUID(voter2), asd.getUUID(voter1),  1);
        assertEquals(gson.toJson("score was deleted"), server.deleteScoreToAdvice(gson.toJson(testDeleteScore)));
        System.out.println(server.addScoreToAdvice(gson.toJson(stTestAddScore)));


        GetAdvicesDto stTest1GetAdvice2 = new GetAdvicesDto(asd.getUUID(voter2), voter1);

        System.out.println(server.getAdvices(gson.toJson(stTest1GetAdvice2)));

        AddAdviceDto stTest1AddAdvice2 = new AddAdviceDto(asd.getUUID(voter2), "Предвыборные обещания кандидата Петрова");
        AddAdviceDto stTest1AddAdvice3 = new AddAdviceDto(asd.getUUID(voter6), "Предвыборные обещания кандидата Иванова под логином Мастер (т.к. Ивановых МНОГО)");

        System.out.println(server.addAdvice(gson.toJson(stTest1AddAdvice2)));

        System.out.println(server.addAdvice(gson.toJson(stTest1AddAdvice3)));

        server.stopServer("test");

    }

    @AfterAll
    static void testWorkElections_StartStopElections_GetCandidates_GetCandidateListOnElections_AddScoreOnElections() throws IOException, RegException {

        Voter voter1 = new Voter("Иванов Иван Иванович", "Иванов", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter2 = new Voter("Петров", "Иван", "Федорович", "Петрович", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter5 = new Voter("QWertyuy", "QWeert", "Йцукен", "qweqweqwe", "10 Лет Октября д 15 кв 6");
        Voter voter6 = new Voter("Фамилия6 Имя6 Отчество6", "Master", "qwertyu", "10 Лет Октября д 15 кв 6");
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer("test");

        LoginDto loginpassword1 = new LoginDto(voter1.getLogin(), voter1.getPassword());
        server.logInVoter(gson.toJson(loginpassword1));

        LoginDto loginpassword2 = new LoginDto(voter2.getLogin(), voter2.getPassword());
        server.logInVoter(gson.toJson(loginpassword2));

        LoginDto loginpassword5 = new LoginDto(voter5.getLogin(), voter5.getPassword());
        server.logInVoter(gson.toJson(loginpassword5));

        LoginDto loginpassword6 = new LoginDto(voter6.getLogin(), voter6.getPassword());
        server.logInVoter(gson.toJson(loginpassword6));

        SessionDaoImpl asd = new SessionDaoImpl();

        Voter voter7 = new Voter("Иванов1 Иван1 Иванович1", "Voter7", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter8 = new Voter("Иванов2 Иван1 Иванович1", "Voter8", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter9 = new Voter("Иванов1 Иван1 Иванович3", "Voter9", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter10 = new Voter("Иванов1 Иван2 Иванович1", "Voter10", "qwertyu", "10 Лет Октября д 15 кв 6");
        Voter voter11 = new Voter("Иванов5 Иван5 Иванович5", "Candidate3", "qwertyu", "10 Лет Октября д 15 кв 6");
        //в листе кандидатов заложена проверка на полную идентичность ФИО (в списке избирателей такой проверки нет)
        Voter voter12 = new Voter("Сидоров Сидр Сидорович", "Candidate4", "qwertyu", "10 Лет Октября д 15 кв 6");

        server.registerVoter(voter7.voterToJson());
        server.registerVoter(voter8.voterToJson());
        server.registerVoter(voter9.voterToJson());
        server.registerVoter(voter10.voterToJson());
        server.registerVoter(voter11.voterToJson());
        server.registerVoter(voter12.voterToJson());
        assertEquals(10, DataBase.getInstance().getDataVoterSet().size());

        System.out.println(server.getCandidates(gson.toJson(new UuidDto(asd.getUUID(voter1)))));

        LoginDto loginpassword7 = new LoginDto(voter7.getLogin(), voter7.getPassword());
        server.logInVoter(gson.toJson(loginpassword7));

        LoginDto loginpassword8 = new LoginDto(voter8.getLogin(), voter8.getPassword());
        server.logInVoter(gson.toJson(loginpassword8));

        LoginDto loginpassword9 = new LoginDto(voter9.getLogin(), voter9.getPassword());
        server.logInVoter(gson.toJson(loginpassword9));

        LoginDto loginpassword10 = new LoginDto(voter10.getLogin(), voter10.getPassword());
        server.logInVoter(gson.toJson(loginpassword10));

        LoginDto loginpassword11 = new LoginDto(voter11.getLogin(), voter11.getPassword());
        server.logInVoter(gson.toJson(loginpassword11));

        LoginDto loginpassword12 = new LoginDto(voter12.getLogin(), voter12.getPassword());
        server.logInVoter(gson.toJson(loginpassword12));
        assertEquals(10, DataBase.getInstance().getUserOnlineMap().size());

        AddCandidateDto stAddCandidateError = new AddCandidateDto(asd.getUUID(asd.getVoterByLogin(voter11.getLogin())), voter11);
        //assertEquals(gson.toJson(RegErrorCode.ERROR), server.addCandidate(gson.toJson(stAddCandidateError)));// Была проверка на идентичность кандидатов по ФИО, Сейчас ФИО везде не ижентично, смысл проверки отпал

        AddCandidateDto stAddCandidate = new AddCandidateDto(asd.getUUID(asd.getVoterByLogin(voter12.getLogin())), voter12);
        server.addCandidate(gson.toJson(stAddCandidate));

        assertEquals(3, DataBase.getInstance().getDataCandidate().size());

        System.out.println(server.getCandidates(gson.toJson(new UuidDto(asd.getUUID(voter1)))));
        server.startElection();
        System.out.println(server.getCandidates(gson.toJson(new UuidDto(asd.getUUID(voter1)))));

        assertEquals(3, DataBase.getInstance().getListCandidateForElection().size());
        assertFalse(DataBase.getInstance().getListCandidateForElection().contains(voter11));


        System.out.println(server.getCandidateListOnElections(gson.toJson(new UuidDto(asd.getUUID(asd.getVoterByLogin(voter1.getLogin()))))));


        AddScoreToCandidateDto addscore1 = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter1.getLogin())), 1);
        server.addScoreToCandidate(gson.toJson(addscore1));

        AddScoreToCandidateDto addscore1Error = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter1.getLogin())), 2);
        assertEquals(gson.toJson(RegErrorCode.ERROR), server.addScoreToCandidate(gson.toJson(addscore1Error)));

        AddScoreToCandidateDto addscore2ErrorScoreForeHimself = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter2.getLogin())), 1);
        assertEquals(gson.toJson(RegErrorCode.ERROR), server.addScoreToCandidate(gson.toJson(addscore2ErrorScoreForeHimself)));

        AddScoreToCandidateDto addscore2 = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter2.getLogin())), 3);//3
        assertEquals(gson.toJson("success"), server.addScoreToCandidate(gson.toJson(addscore2)));

        AddScoreToCandidateDto addscore5 = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter5.getLogin())), 1);
        server.addScoreToCandidate(gson.toJson(addscore5));

        AddScoreToCandidateDto addscore6 = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter6.getLogin())), 1);
        server.addScoreToCandidate(gson.toJson(addscore6));

        AddScoreToCandidateDto addscore7 = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter7.getLogin())), 1);
        server.addScoreToCandidate(gson.toJson(addscore7));

        AddScoreToCandidateDto addscore8 = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter8.getLogin())), 2);
        server.addScoreToCandidate(gson.toJson(addscore8));

        AddScoreToCandidateDto addscore2Error = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter9.getLogin())), 4);
        AddScoreToCandidateDto addscore3Error = new AddScoreToCandidateDto(asd.getUUID(asd.getVoterByLogin(voter10.getLogin())), 0);
        assertEquals(gson.toJson(RegErrorCode.ERROR), server.addScoreToCandidate(gson.toJson(addscore2Error)));
        assertEquals(gson.toJson(RegErrorCode.ERROR), server.addScoreToCandidate(gson.toJson(addscore3Error)));

        server.stopElection();
        //assertEquals(gson.toJson(RegErrorCode.OPPOSITE_ALL_WIN), server.stopElection());
        server.stopServer("test");

        Server server3 = new Server();
        server3.startServer("test");
        List<Voter> test = new ArrayList<>(DataBase.getInstance().getDataCandidate());
        for (Voter voter : test) {
            System.out.println(voter.getLogin() + ": Score " + voter.getScore());
        }
        server3.stopServer("test");
        File file = new File("test");
        file.delete();

    }


}
