package example.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CategoryLibrary mCategoryLibrary = new CategoryLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mQuit;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private int mQuestions = 0;
    private String chosenCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mQuit = (Button)findViewById(R.id.quit);
        chosenCategory = getIntent().getExtras().getString("chosenCategory");
        mQuestions = mCategoryLibrary.getNumberOfQuestionsOfCategory(chosenCategory);

        updateQuestion();
            //Start of Button Listener for Button1
            mButtonChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for button goes here
                    if (mButtonChoice1.getText() == mAnswer) {
                        mScore = mScore + 1;
                        updateScore(mScore);
                        updateQuestion();

                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            });
            //End of Button Listener for Button1

            //Start of Button Listener for Button2
            mButtonChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for button goes here
                    if (mButtonChoice2.getText() == mAnswer) {
                        mScore = mScore + 1;
                        updateScore(mScore);
                        updateQuestion();

                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            });
            //End of Button Listener for Button2

            //Start of Button Listener for Button3
            mButtonChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for button goes here
                    if (mButtonChoice3.getText() == mAnswer) {
                        mScore = mScore + 1;
                        updateScore(mScore);
                        updateQuestion();

                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            });
            //End of Button Listener for Button3

            //Start of Button Listener for quit
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button quit goes here
                askForUserToExitToMenu();
            }
        });
        //End of Button Listener for quit
    }
    private void updateQuestion(){

        if(mQuestionNumber >= mQuestions){
            //At the end of the quiz, shows a new screen with highscores
            Intent intent  = new Intent(MainActivity.this, HighScoreActivity.class);
            intent.putExtra("score", mScore);
            startActivity(intent);
        }
        else {
            mQuestionView.setText(mCategoryLibrary.getQuestionOfCategoryWithIndex(mQuestionNumber, chosenCategory));
            mButtonChoice1.setText(mCategoryLibrary.getChoice1OfCategoryWithIndex(mQuestionNumber, chosenCategory));
            mButtonChoice2.setText(mCategoryLibrary.getChoice2OfCategoryWithIndex(mQuestionNumber, chosenCategory));
            mButtonChoice3.setText(mCategoryLibrary.getChoice3OfCategoryWithIndex(mQuestionNumber, chosenCategory));

            mAnswer = mCategoryLibrary.getCorrectAnswerOfCategoryWithIndex(mQuestionNumber, chosenCategory);
            mQuestionNumber++;
        }
    }

    @Override
    //back button on app leads user to home
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Dialog asks user to quit
    private void askForUserToExitToMenu(){
        AlertDialog.Builder builder =   new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to go back to the menu?");

        //sets the yes and no button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetScore();
                Intent intent  = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
               //Exits the app

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

    private void resetScore() {
        this.mScore = 0;
    }

    private void updateScore (int point){
        mScoreView.setText("" + mScore);
    }
}
