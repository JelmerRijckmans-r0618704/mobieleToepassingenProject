package example.com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

import example.com.domain.Category;
import example.com.domain.MultipleChoice;
import example.com.domain.Question;
import example.com.domain.Service;

public class MainActivity extends AppCompatActivity
{
    private Service service = new Service();
    private int amountQuestion = 0;
    private int currentQuestionIndex = 0;
    private String questionAnswer;
    private Question actualQuestion;
    private Category currentCategory;

    private TextView mScoreView;
    private TextView mQuestionView;
    private RequestQueue mQueue;
    private ImageView mImage;


    private int mScore = 0;
    private String chosenCategory;
    private String playerName = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreView = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);
        mQueue = Volley.newRequestQueue(this);

        chosenCategory = getIntent().getExtras().getString("chosenCategory");
        currentCategory = service.getCategory(chosenCategory);
        amountQuestion = currentCategory.getQuestionArrayList().size();

        updateQuestion();
    }

    private void updateQuestion()
    {
        if(currentQuestionIndex >= amountQuestion)
        {
            final Intent intent  = new Intent(MainActivity.this, HighScoreActivity.class);
            intent.putExtra("category", currentCategory.getName());
            intent.putExtra("score", mScore);
            intent.putExtra("player", "anon");

            if(isNetworkAvailable())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Please put in your username");

                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(15)});
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playerName = input.getText().toString();
                        if(!(playerName.trim().isEmpty()))
                        {
                            postRequest();
                            Toast.makeText(MainActivity.this, "Score has been sent", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(MainActivity.this, "Score not sent", Toast.LENGTH_SHORT).show();

                        intent.putExtra("player", playerName);
                        startActivity(intent);
                    }

                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        resetScore();
                        Toast.makeText(MainActivity.this, "Score not sent", Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    }
                });

                builder.show();
            }
            else
            {
                resetScore();
                Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }
        else
        {
            actualQuestion = currentCategory.getQuestionArrayList().get(currentQuestionIndex);
            String title = currentCategory.getName() + " : Question " + (currentQuestionIndex+1);
            getSupportActionBar().setTitle(title);
            currentQuestionIndex++;
            questionAnswer = actualQuestion.getAnswer();

            switch(actualQuestion.getType())
            {
                case MULTIPLE_CHOICE:
                    createMultipleChoice();
                    break;
                case TEXT_INPUT:
                    createTextInput();
                    break;
            }

            mQuestionView.setText(actualQuestion.getQuestion());


            //create quit button programmatically, there might be another better way
            LinearLayout layout = findViewById(R.id.questionChoices);
            Button quitBtn = new Button(this);
            quitBtn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            quitBtn.setText("Back to Main Menu");
            quitBtn.setId(View.NO_ID);
            quitBtn.setBackgroundColor(Color.rgb(183,28,28));
            quitBtn.setTextColor(Color.WHITE);
            quitBtn.setPadding(8,8,8,8);
            quitBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            layout.addView(quitBtn);
            quitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for button quit goes here
                    askForUserToExitToMenu();
                }
            });
        }
    }


    private void createMultipleChoice()
    {
        for (final String choice:  ((MultipleChoice) actualQuestion).getChoicesInList())
        {
            final LinearLayout layout = findViewById(R.id.questionChoices);

            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btnTag.setText(choice);
            btnTag.setId(View.NO_ID);
            btnTag.setBackgroundColor(Color.rgb(0,145,234));
            btnTag.setTextColor(Color.WHITE);
            btnTag.setPadding(8,8,8,8);
            btnTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view)
                {
                    layout.removeAllViews();
                    if(choice.equals(questionAnswer))
                    {
                        mScore += 1;
                        updateScore(mScore);

                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                    updateQuestion();
                }
            });

            layout.addView(btnTag);
            layout.addView(new TextView(this)); // no way to change margin so I use this lol
        }
    }


    private void postRequest() {
        String url = "http://188.166.68.90:5000/api/v1/highscores";
        JSONObject jsonBody = new JSONObject();
        try
        {
            jsonBody.put("playername", playerName);
            jsonBody.put("categoryPlayed", chosenCategory);
            jsonBody.put("highscore", mScore);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("request done");
                            Toast.makeText(MainActivity.this, "Score sent", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(MainActivity.this, "Score not sent", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }


    private void createTextInput()
    {
        final LinearLayout layout = findViewById(R.id.questionChoices);

        createImage(layout);

        final EditText inputUser = new EditText(this);
        inputUser.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        inputUser.setFilters(new InputFilter[] {new InputFilter.LengthFilter(15)});
        inputUser.setSingleLine(true);

        Button btnTag = new Button(this);
        btnTag.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnTag.setText("Confirm answer");
        btnTag.setId(View.NO_ID);
        btnTag.setBackgroundColor(Color.rgb(0,145,234));
        btnTag.setTextColor(Color.WHITE);
        btnTag.setPadding(8,8,8,8);
        btnTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view)
            {
                layout.removeAllViews();
                if(inputUser.getText().toString().toLowerCase().equals(actualQuestion.getAnswer().toLowerCase()))
                {
                    mScore += 1;
                    updateScore(mScore);

                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                updateQuestion();
            }
        });

        layout.addView(inputUser);
        layout.addView(new TextView(this));
        layout.addView(btnTag);
        layout.addView(new TextView(this));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void askForUserToExitToMenu(){
        AlertDialog.Builder builder =   new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to go back to the menu?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetScore();
                Intent intent  = new Intent(MainActivity.this, CategoryActivity.class);
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


    private void createImage(final ViewGroup view)
    {
        if(!(actualQuestion.getResource() == null))
        {
            final ImageView src = new ImageView(this);
            Picasso.with(view.getContext())
                    .load(actualQuestion.getResource())
                    .into(src, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            view.addView(src);
                        }

                        @Override
                        public void onError() {
                            Toast.makeText(MainActivity.this, "Could not retrieve image", Toast.LENGTH_SHORT).show();
                            updateQuestion();
                        }
                    });
        }
    }

    private void resetScore() {
        this.mScore = 0;
    }

    private void updateScore (int point){
        mScoreView.setText("" + mScore);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

