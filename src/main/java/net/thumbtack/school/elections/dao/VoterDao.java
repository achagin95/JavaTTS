package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.model.Advice;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.ArrayList;

public interface VoterDao {
    String insert(Voter voter)throws RegException;
    String logOut(String uuid) throws RegException;
    String login(String login) throws RegException;
    String addAdvice(String uuid, String advice) throws RegException;
    String addScoreToAdvice(String uuidUser, String uuidByAdvice, int score, int number) throws RegException;
    String changeScoreToAdvice(String uuidUser, String uuidByAdvice, int score, int number) throws RegException;
    String deleteScoreToAdvice(String uuidUser, String uuidByAdvice, int number) throws RegException;
    public void checkLoginAndPassword(String login, String password) throws RegException;
    ArrayList<Advice> getAdvices(Voter voter);
}
