package net.thumbtack.school.elections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.dao.SessionDaoImpl;
import net.thumbtack.school.elections.database.DataBase;
import net.thumbtack.school.elections.exceptions.RegException;
import net.thumbtack.school.elections.service.UserService;

import java.io.*;
import java.util.Set;

public class Server {

    private static boolean work = false;
    private static boolean election = false;
    private final Gson gson=new Gson();
    private final UserService userService=new UserService();



    public void startServer(String savedDataFileName) throws IOException {
        if (!work) {
            try (BufferedReader br = new BufferedReader(new FileReader(savedDataFileName))) {

                SessionDaoImpl sessionDao= new SessionDaoImpl();
                sessionDao.insertStartServer(gson.fromJson(br, new TypeToken<Set<Voter>>() {
                }.getType()));


            } catch (FileNotFoundException | NullPointerException e) {
                DataBase.getInstance().getDataVoterSet();
            }
            work = true;
        }
    }

    public void stopServer(String saveDataFileName) throws IOException {
        if (work) {
            File file = new File(saveDataFileName);
            if (file.createNewFile()) {
                System.out.println("Файл записан");
            } else {
                System.out.println("Файл существует, и будет перезаписан");

            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveDataFileName))) {

                gson.toJson(DataBase.getInstance().getDataVoterSet(), bw);
            }
            work = false;
        }
    }


    public String registerVoter(String requestJsonString) {
        if (!election) {
            if (work) {

                return userService.registerVoter(requestJsonString);
            } else return gson.toJson("error");
        }
        return gson.toJson("error");
    }

    public String logInVoter(/*String login, String password*/String loginPasswordJson) {
        if (!election) {
            if (work) {

                return userService.login(loginPasswordJson);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }

    public String logOutVoter(String uuid) {
        if (!election) {
            if (work) {

                return userService.logOut(uuid);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }

    public String getCandidates(String requestJsonStringUUID) {
        if (!election) {
            if (work) {

                return userService.getCandidates(requestJsonStringUUID);
            }
            return gson.toJson("error. Server offline");
        }

        return userService.getCandidatesOnElection(requestJsonStringUUID);
    }

    public String getCandidateListOnElections(String uuidJson) {
        if (election) {
            if (work) {

                return userService.getCandidateListOnElections(uuidJson);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }

    public String addCandidate(String uuidAndrequestJsonString) {
        if (!election) {
            if (work) {

                return userService.addCandidate(uuidAndrequestJsonString);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }
    public String acceptStatusCandidate(String uuidJsonString) {
        if (!election) {
            if (work) {

                return userService.acceptStatusCandidate(uuidJsonString);
            }
            return gson.toJson("error. Server offline");
        } return gson.toJson("error");
    }
    public String cancelStatusCandidate(String uuidJsonString) {
        if (!election) {
            if (work) {

                return userService.cancelStatusCandidate(uuidJsonString);
            }
            return gson.toJson("error. Server offline");
        } return gson.toJson("error");
    }

    public String addAdvice(String uuidAdviceJson) {
        if (!election) {
            if (work) {

                return userService.addAdvice(uuidAdviceJson);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }

    public String addScoreToAdvice(String uuidUser_UuidByAdvice_Score_Number_Json) {
        if (!election) {
            if (work) {

                return userService.addScoreToAdvice(uuidUser_UuidByAdvice_Score_Number_Json);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }

    public String changeScoreToAdvice(String uuidUser_UuidByAdvice_Score_Number_Json) {
        if (!election) {
            if (work) {

                return userService.changeScoreToAdvice(uuidUser_UuidByAdvice_Score_Number_Json);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }

    public String deleteScoreToAdvice(String uuidUser_UuidByAdvice_Number_Json) {
        if (!election) {
            if (work) {

                return userService.deleteScoreToAdvice(uuidUser_UuidByAdvice_Number_Json);
            }
            return gson.toJson("error. Server offline");
        }
        return gson.toJson("error");
    }


    public String getAdvices(String uuidUser_VoterByAdviceJson) {
        if (work) {

            return userService.getAdvices(uuidUser_VoterByAdviceJson);
        }
        return gson.toJson("error. Server offline");
        //проверка на день выборов не идет, чтоб получить предложения, т.к. запрос предложений идет по конкретному пользователю
    }

    public String addScoreToCandidate(String uuidUser_NumberOfCandidateInListJson) {
        if (work) {
            if (election) {
                return userService.addScoreToCandidate(uuidUser_NumberOfCandidateInListJson);
            }
            return gson.toJson("error");
        }
        return gson.toJson("error");
    }

    public String startElection() {
        if (work) {
            if (!election) {
                election = true;
                DataBase.getInstance().getCheckElections();
                DataBase.getInstance().getListCandidateForElection();
                //сразу создали даты, хотя эти 2 строки можно убрать(ни на что не влияют)

                try {
                    return gson.toJson(userService.startElection());
                } catch (RegException e) {
                    election=false;
                    return gson.toJson("error");
                }
            }
            return gson.toJson("error");
        }
        return gson.toJson("error");
    }

    public String stopElection() {
        if (work) {
            if (election) {
                election = false;

                return userService.stopElection();
            }return gson.toJson("error");
        }
        return gson.toJson("error");
    }

    public static boolean checkWorkStatus() {
        return work;
    }

    /*public static boolean checkElectionStatus() {
        return election;
    }
    public DataBase getDataBase() {
        return dataBase;
    }
     */


}
