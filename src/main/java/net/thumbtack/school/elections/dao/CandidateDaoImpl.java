package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Status;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.database.DataBase;
import net.thumbtack.school.elections.exceptions.RegErrorCode;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.ArrayList;
import java.util.List;

public class CandidateDaoImpl implements CandidateDao {

    public String insert(Voter voter) {
        SessionDaoImpl asd = new SessionDaoImpl();
        Voter newCandidate = asd.getVoterByLogin(voter.getLogin());
        newCandidate.setStatus(Status.CANDIDATE_EXPECTATION);
        return "";
    }

    public String insertSelf(Voter voter) throws RegException {

        SessionDaoImpl asd = new SessionDaoImpl();
        int sizeBefore = DataBase.getInstance().getDataCandidate().size();
        Voter newCandidate = asd.getVoterByLogin(voter.getLogin());
        asd.insertCandidate(newCandidate);

        if (DataBase.getInstance().getDataCandidate().size() == sizeBefore)
            throw new RegException(RegErrorCode.ERROR);
        newCandidate.setStatus(Status.CANDIDATE_ACCEPTED);
        return "";

    }
    public String cancelStatusCandidate(Voter voterByUUID){
        voterByUUID.setStatus(Status.VOTER);//т.к. нужный класс уже достали в сервисе, просто присваиваем статус
        return "";
    }

    public String addScoreToCandidate(String uuidUser, int numberOfCandidateInList) throws RegException {
        Voter candidate = DataBase.getInstance().getListCandidateForElection().get(numberOfCandidateInList);
        int scoreBefore = candidate.getScore();
        int sizeBefore = DataBase.getInstance().getCheckElections().size();
        DataBase.getInstance().getCheckElections().add(uuidUser);
        if (sizeBefore == DataBase.getInstance().getCheckElections().size())
            throw new RegException(RegErrorCode.ERROR);
        candidate.addScore();
        if (scoreBefore == candidate.getScore())
            throw new RegException(RegErrorCode.ERROR);
        return "success";
    }

    public ArrayList<Voter> getCandidates() throws RegException {

        if (DataBase.getInstance().getDataCandidate().size() > 0) {
            return new ArrayList<>(DataBase.getInstance().getDataCandidate());

        } else {
            throw new RegException(RegErrorCode.EMPTY_CANDIDATE_LIST);
        }

    }

    public int getSizeListCandidateForEnection() {
        return DataBase.getInstance().getListCandidateForElection().size();
    }
    public int getSizeVotersVoted() {
        return DataBase.getInstance().getCheckElections().size();
    }
    public int getSizeVotersAll(){
        return DataBase.getInstance().getUserOnlineMap().size();
    }

    public Voter stopElections(int i) {
        return DataBase.getInstance().getListCandidateForElection().get(i);

    }


    public String getCandidateListOnElectionsPrint() {

        for (int i = 0; i < DataBase.getInstance().getListCandidateForElection().size(); i++) {
            System.out.println("Кандидат №" + (i + 1) + ": " + DataBase.getInstance().getListCandidateForElection().get(i).getFullName());
        }

        return "";
    }
    public List<Voter> getCandidateListOnElections(){
        return DataBase.getInstance().getListCandidateForElection();
    }

    public String StartElections() throws RegException {

        int delete = 0;
        for (int i = 0; i < DataBase.getInstance().getListCandidateForElection().size(); i++) {
            if (DataBase.getInstance().getListCandidateForElection().get(i).getSetAdvice() == null || DataBase.getInstance().getListCandidateForElection().get(i).getSetAdvice().size() == 0) {
                DataBase.getInstance().getListCandidateForElection().remove(i);
                i--;
                delete++;

            }
        }
        if (delete>0)
            System.out.println("Количество кандидатов, не допущенных к выборам: "+delete);
        if (DataBase.getInstance().getListCandidateForElection().size()==0)
            throw new RegException(RegErrorCode.EMPTY_CANDIDATE_LIST);
        Voter voterOppositeAll=new Voter("Point", "AgainstAll", "AgainstALL", "AgainstALL", "123");
        voterOppositeAll.setStatus(Status.CANDIDATE_ACCEPTED);
        DataBase.getInstance().getListCandidateForElection().add(voterOppositeAll);

        return "";

    }
}
