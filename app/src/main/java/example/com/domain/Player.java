package example.com.domain;

public class Player implements  Comparable<Player> {

    private String name;
    private int score;

    public Player(String name, int score) {
       setScore(score);
       setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return getName() + " Score: " + getScore();
    }

    @Override
    public int compareTo(Player nextPlayer) {
        return nextPlayer.getScore() - this.getScore();
    }
}
