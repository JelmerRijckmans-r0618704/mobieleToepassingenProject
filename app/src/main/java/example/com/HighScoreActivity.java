package example.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import example.com.domain.Highscore;
import example.com.domain.Player;


public class HighScoreActivity extends AppCompatActivity {

    private int score = 0;
    private TextView highscoreview_text, currentScore;
    private String category;
    private String nameCurrentPlayer;
    private String highscoresTextList = "";
    private RequestQueue mQueue;
    private Button mQuit;
    private ArrayList<Highscore> highscores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Highscores");
        setContentView(R.layout.activity_highscore);
        highscoreview_text = (TextView) findViewById(R.id.highscoreview_text);
        currentScore = (TextView) findViewById(R.id.currentScore);
        highscoreview_text.setMovementMethod(new ScrollingMovementMethod());

        score = getIntent().getExtras().getInt("score");
        category = getIntent().getExtras().getString("category");
        nameCurrentPlayer = getIntent().getExtras().getString("player");

        if("anon".equals(nameCurrentPlayer)) currentScore.setText("Congrats anon! \n Your score is: " + score);
        else currentScore.setText("Congrats " + nameCurrentPlayer + "! \nYour score is: " + score);
        
        mQuit = (Button)findViewById(R.id.quit);
        mQueue = Volley.newRequestQueue(this);

        jsonParse();
        //Start of Button Listener for quit
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button quit goes here
                askForUserInputToQuit();
            }
        });
    }



    private void jsonParse()
    {
        String url = "http://188.166.68.90:5000/api/v1/highscores";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("highscores");
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if(category.equals(jsonObject.getString("categoryPlayed")))
                                {
                                    highscores.add(new Highscore(jsonObject.getString("playername"), category, jsonObject.getInt("highscore")));
                                }
                            }
                            Collections.sort(highscores);
                            int listL = 1;
                            for (Highscore highscore: highscores)
                            {
                                highscoresTextList += listL + ". " + highscore.toString() + "\n";
                                listL++ ;
                            }
                            highscoreview_text.setText(highscoresTextList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
             }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        highscoreview_text.setText("Could not retrieve highscores.");
                        error.printStackTrace();
                    }
        });

        mQueue.add(request);
    }



    //Dialog asks user to quit
    private void askForUserInputToQuit(){
        AlertDialog.Builder builder =   new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to return to the menu?");

        //sets the yes and no button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Return to menu
                Intent intent  = new Intent(HighScoreActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Does nothing
            }
        });
        builder.show();
    }

    @Override
    //Back button on app leads user to home
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
