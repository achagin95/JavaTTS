package net.thumbtack.school.elections.dao;

import com.google.gson.Gson;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.model.Advice;
import net.thumbtack.school.elections.database.DataBase;
import net.thumbtack.school.elections.exceptions.RegErrorCode;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.ArrayList;
import java.util.List;

public class VoterDaoImpl implements VoterDao {

    private final SessionDao asd=new SessionDaoImpl();

    public String insert(Voter voter) throws RegException {

        int sizeBefore = DataBase.getInstance().getDataVoterSet().size();
        asd.insert(voter);

        if (DataBase.getInstance().getDataVoterSet().size() == sizeBefore) {
            //return "error, пользователь с данным логином уже существует.";
            throw new RegException(RegErrorCode.DUPLICATE_VOTER);
        }
        return "Register success";

    }

    public String logOut(String uuid) throws RegException {

        Voter voterByUUID = asd.getVoterByUUID(uuid);
        if (voterByUUID == null)
            throw new RegException(RegErrorCode.ERROR);
        int sizeBefore = DataBase.getInstance().getUserOnlineMap().size();
        asd.logOut(voterByUUID);
        if (DataBase.getInstance().getUserOnlineMap().size() == sizeBefore)
            throw new RegException(RegErrorCode.ERROR);
        return "logOut sucsess";

    }

    public String login(String login/*, String password*/) throws RegException {

        Gson gson = new Gson();
        Voter voterByLogin = asd.getVoterByLogin(login);
        int sizeBefore = DataBase.getInstance().getUserOnlineMap().size();
        asd.insertMap(voterByLogin);
        if (sizeBefore == DataBase.getInstance().getUserOnlineMap().size())
            throw new RegException(RegErrorCode.ERROR);
        return gson.toJson(asd.getUUID(voterByLogin));


    }

    public String addAdvice(String uuid, String advice) throws RegException {

        Voter voter = asd.getVoterByUUID(uuid);
        if (voter == null)
            throw new RegException(RegErrorCode.UUID_NOT_FOUND);

        Advice newAdvice = new Advice();
        int numberAdvice = voter.getSetAdvice().size() + 1;
        newAdvice.setNumber(numberAdvice);
        newAdvice.setAdvice(advice);
        newAdvice.getScore().put(voter.getLogin(), 5);
        int sizeBefore = voter.getSetAdvice().size();
        voter.getSetAdvice().add(newAdvice);
        if (sizeBefore == voter.getSetAdvice().size())
            throw new RegException(RegErrorCode.ERROR);

        return "advice was added";

    }

    public String addScoreToAdvice(String uuidUser, String uuidByAdvice, int score, int number) throws RegException {

        Voter voterWhoseAdvice = asd.getVoterByUUID(uuidByAdvice);
        Voter user=asd.getVoterByUUID(uuidUser);
        if (user == null || voterWhoseAdvice == null)
            throw new RegException(RegErrorCode.ERROR);

        List<Advice> tmp = new ArrayList<>(voterWhoseAdvice.getSetAdvice());
        if (number <= 0 || number > tmp.size())
            throw new RegException(RegErrorCode.ERROR);

        for (Advice advice : tmp) {
            if (advice.getNumber() == number) {
                if (advice.getScore().containsKey(user.getLogin()))
                    throw new RegException(RegErrorCode.ERROR);
                advice.getScore().put(user.getLogin(), score);
                break;
            }
        }
        return "score was added";


    }

    public String changeScoreToAdvice(String uuidUser, String uuidByAdvice, int score, int number) throws RegException {

        Voter voterWhoseAdvice = asd.getVoterByUUID(uuidByAdvice);
        Voter user=asd.getVoterByUUID(uuidUser);
        if (user == null || voterWhoseAdvice == null)
            throw new RegException(RegErrorCode.ERROR);

        List<Advice> tmp = new ArrayList<>(voterWhoseAdvice.getSetAdvice());
        if (number <= 0 || number > tmp.size())
            throw new RegException(RegErrorCode.ERROR);

        for (Advice advice : tmp) {
            if (advice.getNumber() == number) {
                if (advice.getScore().containsKey(user.getLogin())) {
                    advice.getScore().put(user.getLogin(), score);
                    break;
                } else throw new RegException(RegErrorCode.ERROR);

            }
        }
        return "score was changed";
    }

    public String deleteScoreToAdvice(String uuidUser, String uuidByAdvice, int number) throws RegException {

        Voter voterWhoseAdvice = asd.getVoterByUUID(uuidByAdvice);
        Voter user=asd.getVoterByUUID(uuidUser);
        if (user == null || voterWhoseAdvice == null)
            throw new RegException(RegErrorCode.ERROR);

        List<Advice> tmp = new ArrayList<>(voterWhoseAdvice.getSetAdvice());
        if (number <= 0 || number > tmp.size())
            throw new RegException(RegErrorCode.ERROR);

        for (Advice advice : tmp) {
            if (advice.getNumber() == number) {
                if (advice.getScore().containsKey(user.getLogin())) {
                    advice.getScore().remove(user.getLogin());
                    break;
                } else throw new RegException(RegErrorCode.ERROR);

            }
        }
        return "score was deleted";
    }


    public void checkLoginAndPassword(String login, String password) throws RegException {

        if (asd.getIndexVoterByLogin(login) < 0) {
            throw new RegException(RegErrorCode.WRONG_LOGIN);
        }
        if (!asd.checkPasswordByLogin(login, password)) {
            throw new RegException(RegErrorCode.WRONG_PASSWORD);
        }

    }

    public ArrayList<Advice> getAdvices(Voter voter) {

        Voter tmp = asd.getVoterByLogin(voter.getLogin());
        return new ArrayList<>(tmp.getSetAdvice());

    }


}
