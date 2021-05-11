package net.thumbtack.school.elections.database;

//import net.thumbtack.school.elections.UserType.Candidate;
import net.thumbtack.school.elections.model.Voter;

        import java.util.*;

public class DataBase {
    private static DataBase instance;

    private Set<Voter> dataVoter;

    private Map<String, Voter> userOnlineMap;

    private Set<Voter> dataCandidate;

    private List<String> checkElections;

    private List<Voter> listCandidateForElection;


    public DataBase() {
    }


    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public Set<Voter> getDataVoterSet() {
        if (DataBase.getInstance().dataVoter == null) {
            DataBase.getInstance().dataVoter = new TreeSet<>((v1, v2) -> {
                return v1.getLogin().compareTo(v2.getLogin());
            });
        }
        return DataBase.getInstance().dataVoter;
    }

    public Set<Voter> getDataCandidate() {
        if (DataBase.getInstance().dataCandidate == null) {
            DataBase.getInstance().dataCandidate = new TreeSet<>((c1, c2) -> {
                return c1.getFullName().compareTo(c2.getFullName());
            });

        }
        return DataBase.getInstance().dataCandidate;
    }

    public List<String> getCheckElections() {
        if (DataBase.getInstance().checkElections == null) {
            DataBase.getInstance().checkElections = new ArrayList<>();
        }
        return DataBase.getInstance().checkElections;
    }

    public List<Voter> getListCandidateForElection() {
        if (DataBase.getInstance().listCandidateForElection == null) {
            DataBase.getInstance().listCandidateForElection = new ArrayList<>(DataBase.getInstance().getDataCandidate());
        }
        return DataBase.getInstance().listCandidateForElection;
    }


    public Map<String, Voter> getUserOnlineMap() {
        if (DataBase.getInstance().userOnlineMap == null) {
            DataBase.getInstance().userOnlineMap = new HashMap<>();
        }
        return DataBase.getInstance().userOnlineMap;
    }


}
