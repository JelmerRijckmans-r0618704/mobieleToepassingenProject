package example.com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
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
        highscoreview_text = findViewById(R.id.highscoreview_text);
        currentScore = findViewById(R.id.currentScore);
        highscoreview_text.setMovementMethod(new ScrollingMovementMethod());

        score = getIntent().getExtras().getInt("score");
        category = getIntent().getExtras().getString("category");
        nameCurrentPlayer = getIntent().getExtras().getString("player");

        if("anon".equals(nameCurrentPlayer)) currentScore.setText("Congrats anon! \n Your score is: " + score);
        else currentScore.setText("Congrats " + nameCurrentPlayer + "! \nYour score is: " + score);
        
        mQuit = findViewById(R.id.quit);
        mQueue = Volley.newRequestQueue(this);
        if(isNetworkAvailable()) jsonParse();
        else highscoreview_text.setText("No internet connection");
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            if(highscores.size() != 0)
                            {
                                Collections.sort(highscores);
                                int listL = 1;
                                for (Highscore highscore: highscores)
                                {
                                    highscoresTextList += listL + ". " + highscore.toString() + "\n";
                                    listL++ ;
                                }
                            }
                            else highscoresTextList = "No scores for this category yet!";
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

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent  = new Intent(HighScoreActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
