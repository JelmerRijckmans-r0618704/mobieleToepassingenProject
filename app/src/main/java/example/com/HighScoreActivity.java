package example.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class HighScoreActivity extends AppCompatActivity {

    private TextView mScoreView;
    private int score = 0;
    private TextView highscoreview_text;
    private HashMap<String, Integer> highScoreList = new HashMap<>();
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        highscoreview_text = (TextView) findViewById(R.id.highscoreview_text);
        score = getIntent().getExtras().getInt("score");
        setScore();
        printHighScores();
    }

    //puts some players in the highscore list and adds the current player
    private void setScore(){
        mScoreView = (TextView) findViewById(R.id.score);
        mScoreView.setText("" + score);
        highScoreList.put("Bert",1);
        highScoreList.put("Anna", 2);
        highScoreList.put("current player", score);
    }

    //sorts highscore, highest first
    public void sortHighScores() {
        Object[] a = this.highScoreList.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        for (Object e : a) {
           String key = ((Map.Entry<String, Integer>) e).getKey();
           int value = ((Map.Entry<String, Integer>) e).getValue();
          this.text += "\n"+(((Map.Entry<String, Integer>) e).getKey() + " : "
                   + ((Map.Entry<String, Integer>) e).getValue()) +"\n";
        }
    }

    //prints out the name and score of the players
    public void printHighScores() {
       sortHighScores();
        highscoreview_text.setText(text);
    }


}
