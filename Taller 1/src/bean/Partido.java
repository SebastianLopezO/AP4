package bean;

public class Partido {
    private String TeamLocal;
    private String TeamVisit;
    private int GoalsLocal;
    private int GoalsVisit;

    public Partido(String teamLocal, String teamVisit, int goalsLocal, int goalsVisit) {
        TeamLocal = teamLocal;
        TeamVisit = teamVisit;
        GoalsLocal = goalsLocal;
        GoalsVisit = goalsVisit;
    }

    public String getTeamLocal() {
        return TeamLocal;
    }

    public void setTeamLocal(String teamLocal) {
        TeamLocal = teamLocal;
    }

    public String getTeamVisit() {
        return TeamVisit;
    }

    public void setTeamVisit(String teamVisit) {
        TeamVisit = teamVisit;
    }

    public int getGoalsLocal() {
        return GoalsLocal;
    }

    public void setGoalsLocal(int goalsLocal) {
        GoalsLocal = goalsLocal;
    }

    public int getGoalsVisit() {
        return GoalsVisit;
    }

    public void setGoalsVisit(int goalsVisit) {
        GoalsVisit = goalsVisit;
    }
}
