package example.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    private CategoryLibrary mCategoryLibrary = new CategoryLibrary();

    private TextView mCategoryView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mExit;
    private String chosenCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mCategoryView = (TextView) findViewById(R.id.category);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mExit = (Button)findViewById(R.id.exit);

        updateCategories();
        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button goes here
                startIntent(CategoryEnum.Flowers.toString());
            }
        });
        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button goes here
                startIntent(CategoryEnum.Pokemon.toString());
            }
        });
        //End of Button Listener for Button2

        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button goes here
                startIntent(CategoryEnum.Yugioh.toString());
            }
        });
        //End of Button Listener for Button3

        //Start of Button Listener for quit
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button quit goes here
                askForUserToExit();
            }
        });
        //End of Button Listener for quit
    }

    private void startIntent(String chosenCategory) {
        this.chosenCategory = chosenCategory;
        Intent intent  = new Intent(CategoryActivity.this, MainActivity.class);
        intent.putExtra("chosenCategory", this.chosenCategory);
        startActivity(intent);
    }

    private void updateCategories(){
            mCategoryView.setText("Choose a category: ");
            mButtonChoice1.setText(CategoryEnum.Flowers.toString());
            mButtonChoice2.setText(CategoryEnum.Pokemon.toString());
            mButtonChoice3.setText(CategoryEnum.Yugioh.toString());
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
    private void askForUserToExit(){
        AlertDialog.Builder builder =   new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to exit the quiz?");

        //sets the yes and no button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Exits the app
                finish();
                moveTaskToBack(true);
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
}

