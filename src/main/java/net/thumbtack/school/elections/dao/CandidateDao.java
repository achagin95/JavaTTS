package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.ArrayList;
import java.util.List;

public interface CandidateDao {

    String insert(Voter voter);
    String insertSelf(Voter voter) throws RegException;
    String addScoreToCandidate(String uuidUser, int numberOfCandidateInList) throws RegException;
    ArrayList<Voter> getCandidates() throws RegException;
    int getSizeListCandidateForEnection();
    int getSizeVotersVoted();
    int getSizeVotersAll();
    Voter stopElections(int i);
    String getCandidateListOnElectionsPrint();
    List<Voter> getCandidateListOnElections();
    String StartElections() throws RegException;

    String cancelStatusCandidate(Voter voterByUUID);


}
