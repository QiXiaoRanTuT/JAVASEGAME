package gok.model;

public class Game {
    private Integer gameid;
    private Integer id;
    private String username;
    private Integer score;
    private String gametime;

    @Override
    public String toString() {
        return "Game{" +
                "gameid=" + gameid +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", gametime='" + gametime + '\'' +
                '}';
    }

    public Game() {
    }

    public Game(Integer gameid, Integer id, String username, Integer score, String gametime) {
        this.gameid = gameid;
        this.id = id;
        this.username = username;
        this.score = score;
        this.gametime = gametime;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGametime() {
        return gametime;
    }

    public void setGametime(String gametime) {
        this.gametime = gametime;
    }
}
