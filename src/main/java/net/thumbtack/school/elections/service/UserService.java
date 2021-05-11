package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.model.Status;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.dao.*;
import net.thumbtack.school.elections.model.Advice;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.exceptions.RegErrorCode;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final VoterDao voterDao = new VoterDaoImpl();
    private final SessionDao sessionDao = new SessionDaoImpl();
    private final CandidateDao candidateDao = new CandidateDaoImpl();
    private final Gson gson = new Gson();

    public String registerVoter(String requestJsonString) {

        RegisterVoterDtoRequest registerVoterDtoReques = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        Voter newVoter = new Voter(registerVoterDtoReques);

        try {
            registerVoterDtoReques.valuable();
            return gson.toJson(voterDao.insert(newVoter));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }


    public String logOut(String uuidJson) {

        String uuid = gson.fromJson(uuidJson, UuidDto.class).getUuid();

        try {
            return gson.toJson(voterDao.logOut(uuid));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }

    public String login(String loginPasswordJson) {

        LoginDto loginDto = gson.fromJson(loginPasswordJson, LoginDto.class);

        String login = loginDto.getLogin();
        String password = loginDto.getPassword();

        try {
            voterDao.checkLoginAndPassword(login, password);
            return gson.toJson(voterDao.login(login));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }

    public String getCandidates(String requestJsonStringUUID) {


        String uuid = gson.fromJson(requestJsonStringUUID, UuidDto.class).getUuid();
        GetCandidatesDtoResponse getCandidatesDtoResponse = new GetCandidatesDtoResponse();

        try {
            sessionDao.checkOnline(uuid);
            ArrayList<Voter> list = candidateDao.getCandidates();
            for (int i = 0; i < list.size(); i++) {
                CandidatesDto getCandidatesDto = new CandidatesDto(i + 1, list.get(i));
                getCandidatesDtoResponse.getCandidates().add(getCandidatesDto);
            }

            return gson.toJson(getCandidatesDtoResponse.getCandidates());
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }

    public String getCandidatesOnElection(String requestJsonStringUUID) {
        String uuid = gson.fromJson(requestJsonStringUUID, UuidDto.class).getUuid();
        GetCandidatesDtoResponse getCandidatesDtoResponse = new GetCandidatesDtoResponse();
        try {
            sessionDao.checkOnline(uuid);
            List<Voter> list = candidateDao.getCandidateListOnElections();
            for (int i = 0; i < list.size(); i++) {
                CandidatesDto getCandidatesDto = new CandidatesDto(i + 1, list.get(i));
                getCandidatesDtoResponse.getCandidates().add(getCandidatesDto);
            }

            return gson.toJson(getCandidatesDtoResponse.getCandidates());
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
    }

    public String addCandidate(String uuidAndrequestJsonString) {//qwe

        AddCandidateDto addCandidateDto = gson.fromJson(uuidAndrequestJsonString, AddCandidateDto.class);

        String uuid = addCandidateDto.getUuid();

        Voter voter = addCandidateDto.getVoter();
        Voter voterByUUID;


        try {
            sessionDao.checkOnline(uuid);
            voterByUUID = sessionDao.getVoterByUUID(uuid);//если чеконлайн не пйомал ошибку, то и это метод не поймает
            sessionDao.checkInDataVoter(voter);
            if (voterByUUID.getLogin().equals(voter.getLogin()))
                return gson.toJson(candidateDao.insertSelf(voter));
            else return gson.toJson(candidateDao.insert(voter));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }


    }

    public String addAdvice(String uuidAdviceJson) {

        AddAdviceDto addAdviceDto = gson.fromJson(uuidAdviceJson, AddAdviceDto.class);
        String uuid = addAdviceDto.getUuid();
        String advice = addAdviceDto.getAdvice();
        try {
            return gson.toJson(voterDao.addAdvice(uuid, advice));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
    }

    public String addScoreToAdvice(String uuidUser_uuidByAdvice_score_numberJson) {


        AddChangeScoreToAdviceDto addChangeScoreToAdviceDto = gson.fromJson(uuidUser_uuidByAdvice_score_numberJson, AddChangeScoreToAdviceDto.class);
        String uuidUser = addChangeScoreToAdviceDto.getUuidUser();
        String uuidByAdvice = addChangeScoreToAdviceDto.getUuidByAdvice();
        int score = addChangeScoreToAdviceDto.getScore();
        int number = addChangeScoreToAdviceDto.getNumberAdvice();
        try {
            sessionDao.checkScore(score);
            sessionDao.checkEqualsUUID(uuidUser, uuidByAdvice);
            return gson.toJson(voterDao.addScoreToAdvice(uuidUser, uuidByAdvice, score, number));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }


    }

    public String changeScoreToAdvice(String uuidUser_uuidByAdvice_score_numberJson) {


        AddChangeScoreToAdviceDto addChangeScoreToAdviceDto = gson.fromJson(uuidUser_uuidByAdvice_score_numberJson, AddChangeScoreToAdviceDto.class);
        String uuidUser = addChangeScoreToAdviceDto.getUuidUser();
        String uuidByAdvice = addChangeScoreToAdviceDto.getUuidByAdvice();
        int score = addChangeScoreToAdviceDto.getScore();
        int number = addChangeScoreToAdviceDto.getNumberAdvice();
        try {
            sessionDao.checkScore(score);
            sessionDao.checkEqualsUUID(uuidUser, uuidByAdvice);
            return gson.toJson(voterDao.changeScoreToAdvice(uuidUser, uuidByAdvice, score, number));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
    }

    public String deleteScoreToAdvice(String uuidUser_uuidByAdvice_numberJson) {

        DeleteScoreToAdviceDto deleteScoreToAdviceDto = gson.fromJson(uuidUser_uuidByAdvice_numberJson, DeleteScoreToAdviceDto.class);
        String uuidUser = deleteScoreToAdviceDto.getUuidUser();
        String uuidByAdvice = deleteScoreToAdviceDto.getUuidByAdvice();
        int number = deleteScoreToAdviceDto.getNumberAdvice();
        try {
            sessionDao.checkEqualsUUID(uuidUser, uuidByAdvice);
            return gson.toJson(voterDao.deleteScoreToAdvice(uuidUser, uuidByAdvice, number));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
    }

    public String getAdvices(String uuidUser_VoterByAdviceJson) {

        GetAdvicesDto getAdvicesDto = gson.fromJson(uuidUser_VoterByAdviceJson, GetAdvicesDto.class);
        String uuidUser = getAdvicesDto.getUuidUser();
        Voter voterByAdvice = getAdvicesDto.getVoterByAdvice();
        GetAdviceDtoResponse getAdviceDtoResponse = new GetAdviceDtoResponse();

        try {
            sessionDao.checkOnline(uuidUser);
            sessionDao.checkInDataVoter(voterByAdvice);
            ArrayList<Advice> tmp = voterDao.getAdvices(voterByAdvice);
            if (tmp.size() == 0)
                throw new RegException(RegErrorCode.ERROR);
            for (Advice advice : tmp) {
                AdviceDto adviceDto = new AdviceDto(advice.getAdvice(), advice.getScore(), advice.getAverage());
                getAdviceDtoResponse.getGetAdviceDtoSet().add(adviceDto);
            }
            return gson.toJson(getAdviceDtoResponse.getGetAdviceDtoSet());
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }

    public String addScoreToCandidate(String uuidUser_NumberOfCandidateInListJson) {


        AddScoreToCandidateDto addScoreToCandidateDto = gson.fromJson(uuidUser_NumberOfCandidateInListJson, AddScoreToCandidateDto.class);
        String uuidUser = addScoreToCandidateDto.getUuidUser();
        int numberOfCandidateInList = addScoreToCandidateDto.getNumberOfCandidate() - 1;
        try {
            sessionDao.checkElectionsListContains(uuidUser);
            sessionDao.checkElectionsNomberOfCandidate(numberOfCandidateInList);
            sessionDao.checkEqualsVoterAndCandidateOnElections(uuidUser, numberOfCandidateInList);
            return gson.toJson(candidateDao.addScoreToCandidate(uuidUser, numberOfCandidateInList));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }

    public String startElection() throws RegException {

        return candidateDao.StartElections();
    }

    public String stopElection() {

        int size = candidateDao.getSizeListCandidateForEnection();
        try {
            sessionDao.checkStopElections();
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
        GetResultsStopElectionDtoResponse response = new GetResultsStopElectionDtoResponse();
        for (int i = 0; i < size; i++) {
            Voter tmp = candidateDao.stopElections(i);
            CandidatesResultDto candidatesResultDto = new CandidatesResultDto(tmp.getFullName(), i + 1, tmp.getScore());
            response.getSetCandidates().add(candidatesResultDto);
        }
        response.setVotersVoted(candidateDao.getSizeVotersVoted());
        response.setVotersAll(candidateDao.getSizeVotersAll());
        return gson.toJson(response);
    }

    public String getCandidateListOnElections(String uuidJson) {
        String uuid = gson.fromJson(uuidJson, UuidDto.class).getUuid();

        try {
            sessionDao.checkOnline(uuid);
            return gson.toJson(candidateDao.getCandidateListOnElectionsPrint());
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }

    }

    public String acceptStatusCandidate(String uuidJsonString) {//qwe
        String uuid=gson.fromJson(uuidJsonString, UuidDto.class).getUuid();
        try {
            Voter voterByUUID = sessionDao.getVoterByUUID(uuid);
            if (voterByUUID.getStatus()== Status.CANDIDATE_ACCEPTED)
                return gson.toJson(RegErrorCode.ERROR);
            return gson.toJson(candidateDao.insertSelf(voterByUUID));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
    }

    public String cancelStatusCandidate(String uuidJsonString) {//qwe
        String uuid=gson.fromJson(uuidJsonString, UuidDto.class).getUuid();
        try {
            Voter voterByUUID = sessionDao.getVoterByUUID(uuid);
            if (voterByUUID.getStatus()== Status.VOTER)
                return gson.toJson(RegErrorCode.ERROR);
            return gson.toJson(candidateDao.cancelStatusCandidate(voterByUUID));
        } catch (RegException e) {
            return gson.toJson(e.getErrorCode());
        }
    }
}
