package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.Server;
import net.thumbtack.school.elections.model.Status;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.database.DataBase;
import net.thumbtack.school.elections.exceptions.RegErrorCode;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.*;

public class SessionDaoImpl implements SessionDao{

    public void insertStartServer(Set<Voter> startSet) {
        //старт сервера и заполнение Сетов
        if (!Server.checkWorkStatus()) {
            if (startSet.size() != 0) {
                DataBase.getInstance().getDataVoterSet().addAll(startSet);
                List<Voter> tmp=new ArrayList<>(DataBase.getInstance().getDataVoterSet());
                for (Voter voter : tmp) {
                    if (voter.getStatus() == Status.CANDIDATE_ACCEPTED) {
                        DataBase.getInstance().getDataCandidate().add(voter);
                    }
                }
            }
        }
    }

    public int getIndexVoterByLogin(String login) {
        List<Voter> tmp = new ArrayList<>(DataBase.getInstance().getDataVoterSet());
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getLogin().equals(login)) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkPasswordByLogin(String login, String password) {
        int index = getIndexVoterByLogin(login);
        //проверку не вводил, т.к. проверка была в Классе Сервис
        List<Voter> tmp = new ArrayList<>(DataBase.getInstance().getDataVoterSet());
        return tmp.get(index).getPassword().equals(password);
    }

    public void insert(Voter voter) {
        DataBase.getInstance().getDataVoterSet().add(voter);
    }

    public void insertMap(Voter voter) {
        String s = UUID.randomUUID().toString();

        if (!DataBase.getInstance().getUserOnlineMap().containsValue(voter))
            DataBase.getInstance().getUserOnlineMap().put(s, voter);

    }

    public void insertCandidate(Voter candidate) {

        DataBase.getInstance().getDataCandidate().add(candidate);


    }


    public String getUUID(Voter voter) {

        for (Map.Entry<String, Voter> p : DataBase.getInstance().getUserOnlineMap().entrySet()) {
            if (voter.getLogin().equals(p.getValue().getLogin())) {
                return p.getKey();// нашли наше значение и возвращаем  ключ
            }
        }
        return null;

    }


    public Voter getVoterByUUID(String uuid)throws RegException {

        if (!DataBase.getInstance().getUserOnlineMap().containsKey(uuid))
            throw new RegException(RegErrorCode.ERROR);
        return DataBase.getInstance().getUserOnlineMap().get(uuid);
    }


    public Voter getVoterByLogin(String login) {
        int index = getIndexVoterByLogin(login);
        List<Voter> tmp = new ArrayList<>(DataBase.getInstance().getDataVoterSet());
        return tmp.get(index);
    }


    public void logOut(Voter voter) {
        String uuid=getUUID(voter);
        DataBase.getInstance().getUserOnlineMap().remove(uuid);
    }
    public void checkOnline(String StringUUID) throws RegException {
        if (!DataBase.getInstance().getUserOnlineMap().containsKey(StringUUID))
            throw new RegException(RegErrorCode.UUID_NOT_FOUND);

    }

    public void checkInDataVoter(Voter voter) throws RegException {
        if (!DataBase.getInstance().getDataVoterSet().contains(voter))
            throw new RegException(RegErrorCode.VOTER_NOT_FOUND);
    }


    public void checkScore(int score) throws RegException {
        if (score<1 || score>5)
            throw new RegException(RegErrorCode.ERROR);
    }

    public void checkEqualsUUID(String uuidUser, String uuidByAdvice) throws RegException {
        if (uuidUser.equals(uuidByAdvice))
            throw new RegException(RegErrorCode.ERROR);
    }

    public void checkElectionsListContains(String uuidUser) throws RegException {
        if (DataBase.getInstance().getCheckElections().contains(uuidUser))
            throw new RegException(RegErrorCode.ERROR);
    }
    public void checkElectionsNomberOfCandidate(int numberOfCandidateInList) throws RegException {
        if (numberOfCandidateInList < 0 || numberOfCandidateInList > DataBase.getInstance().getListCandidateForElection().size() - 1)
            throw new RegException(RegErrorCode.ERROR);
    }
    public  void checkEqualsVoterAndCandidateOnElections(String uuidUser, int numberOfCandidateInList) throws RegException{
        Voter voter=getVoterByUUID(uuidUser);
        Voter candidate=DataBase.getInstance().getListCandidateForElection().get(numberOfCandidateInList);
        if (voter.getLogin().equals(candidate.getLogin()))
            throw new RegException(RegErrorCode.ERROR);

    }
    public void checkStopElections() throws RegException {
        int size=DataBase.getInstance().getListCandidateForElection().size();
        int scoreMax=0;
        int scoreOppositeAll=DataBase.getInstance().getListCandidateForElection().get(size-1).getScore();
        for (int i=0; i<DataBase.getInstance().getListCandidateForElection().size()-1; i++) {
            if (DataBase.getInstance().getListCandidateForElection().get(i).getScore()>scoreMax)
                scoreMax=DataBase.getInstance().getListCandidateForElection().get(i).getScore();
        }
        Voter voterOppositeAll=DataBase.getInstance().getListCandidateForElection().get(DataBase.getInstance().getListCandidateForElection().size()-1);
        DataBase.getInstance().getDataVoterSet().add(voterOppositeAll);//добавление для сохранения очков против всех
        if (scoreMax<=scoreOppositeAll)
            throw new RegException(RegErrorCode.OPPOSITE_ALL_WIN);

    }

}
