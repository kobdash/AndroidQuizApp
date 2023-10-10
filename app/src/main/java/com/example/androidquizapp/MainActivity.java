package com.example.androidquizapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private int currentQuestion = 1;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);

        // Load the first question layout
        loadQuestionLayout(R.layout.question1);
    }

    private void loadQuestionLayout(int layoutResId) {
        frameLayout.removeAllViews();
        getLayoutInflater().inflate(layoutResId, frameLayout, true);

        if (currentQuestion == 5) {
            // This is the last question, show "Submit" button
            Button submitButton = findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Calculate and show the score
                    calculateAndShowScore();
                }
            });
        } else {
            // This is not the last question, show "Next" button
            Button nextButton = findViewById(R.id.nextButton);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the answer and load the next question
                    handleAnswer();
                }
            });
        }
    }

    private void handleAnswer() {
        // Depending on the current question, handle the answer.
        // You should check user selections and update the score accordingly.

        switch (currentQuestion) {
            case 1:
                // Handle the answer for question 1 with checkboxes
                CheckBox optionA = findViewById(R.id.optionA);
                CheckBox optionB = findViewById(R.id.optionB);
                CheckBox optionC = findViewById(R.id.optionC);

                boolean isOptionASelected = optionA.isChecked();
                boolean isOptionBSelected = optionB.isChecked();
                boolean isOptionCSelected = optionC.isChecked();

                // Check if Option A and Option B are selected (correct answer)
                if (isOptionASelected && isOptionBSelected && !isOptionCSelected) {
                    score += 4;
                }
                break;

            case 2:
                // Handle the answer for question 2 with radio buttons
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

                if (selectedRadioButton != null) {
                    String selectedOption = selectedRadioButton.getText().toString();
                    if (selectedOption.equals("True")) {
                        score += 4;
                    }
                }
                break;

            // Add cases for questions 3, 4, and any additional questions.

            default:
                break;
        }

        // Load the next question
        currentQuestion++;
        String nextQuestionLayout = "question" + currentQuestion;
        int nextLayoutResId = getResources().getIdentifier(nextQuestionLayout, "layout", getPackageName());
        loadQuestionLayout(nextLayoutResId);
    }

    private void calculateAndShowScore() {
        // Calculate the total score and show it in a Toast message
        String message = "Your score: " + score + " out of 20";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}