package example.com.domain;

public class Highscore implements Comparable<Highscore>
{
    private int id;
    private String playername;
    private String category;
    private int highscore;

    public Highscore(String playername, String category, int highscore)
    {
        setPlayername(playername);
        setCategory(category);
        setHighscore(highscore);
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Highscore  highscore) {
        int compareScore=((Highscore)highscore).getHighscore();
        return compareScore - this.highscore;
    }

    @Override
    public String toString()
    {
        return "Player: " + playername + " - Score: " + highscore;
    }
}
