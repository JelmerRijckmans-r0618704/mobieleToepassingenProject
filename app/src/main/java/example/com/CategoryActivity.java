package example.com;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.com.domain.Service;

public class CategoryActivity extends AppCompatActivity {

    private Service service = new Service();
    private String chosenCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Categories");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        updateCategories();
    }

    private void startIntent(String chosenCategory) {
        this.chosenCategory = chosenCategory;
        Intent intent  = new Intent(CategoryActivity.this, MainActivity.class);
        intent.putExtra("chosenCategory", this.chosenCategory);
        startActivity(intent);
    }

    private void updateCategories()
    {
        for (final String categoryName: service.getAllCategoryNames())
        {
            LinearLayout layout = findViewById(R.id.categoryList);

            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            btnTag.setText(categoryName);
            btnTag.setId(View.NO_ID);
            btnTag.setBackgroundColor(Color.rgb(0,145,234));
            btnTag.setTextColor(Color.WHITE);
            btnTag.setPadding(8,8,8,8);
            btnTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startIntent(categoryName);
                }
            });

            layout.addView(btnTag);
            layout.addView(new TextView(this)); // no way to change margin so I use this lol
        }
    }
}

