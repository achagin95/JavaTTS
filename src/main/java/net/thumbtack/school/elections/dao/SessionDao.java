package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.*;

public interface SessionDao {

    void insertStartServer(Set<Voter> startSet);
    int getIndexVoterByLogin(String login);
    boolean checkPasswordByLogin(String login, String password);
    void insert(Voter voter);
    void insertMap(Voter voter);
    void insertCandidate(Voter candidate);
    String getUUID(Voter voter);
    Voter getVoterByUUID(String uuid)throws RegException;
    Voter getVoterByLogin(String login);
    void logOut(Voter voter);
    void checkOnline(String StringUUID) throws RegException;
    void checkInDataVoter(Voter voter) throws RegException ;
    void checkScore(int score) throws RegException;
    void checkEqualsUUID(String uuidUser, String uuidByAdvice) throws RegException;
    void checkElectionsListContains(String uuidUser) throws RegException;
    void checkElectionsNomberOfCandidate(int numberOfCandidateInList) throws RegException;

    public  void checkEqualsVoterAndCandidateOnElections(String uuidUser, int numberOfCandidateInList) throws RegException;
    void checkStopElections() throws RegException;
}
