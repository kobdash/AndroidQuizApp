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
            // Initialize the Reset button
            Button resetButton = findViewById(R.id.resetButton);
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Reset the quiz entirely
                    resetQuiz();
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
        // Handle answers for each question (cases 1 to 5)
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

            case 3:
                // Handle the answer for question 3 with radio buttons
                RadioGroup radioGroup3 = findViewById(R.id.radioGroup);
                RadioButton selectedRadioButton3 = findViewById(radioGroup3.getCheckedRadioButtonId());

                if (selectedRadioButton3 != null) {
                    String selectedOption = selectedRadioButton3.getText().toString();
                    if (selectedOption.equals("2005")) {
                        score += 4;
                    }
                }
                break;

            case 4:
                // Handle the answer for question 4 with checkboxes
                CheckBox optionA4 = findViewById(R.id.optionA);
                CheckBox optionB4 = findViewById(R.id.optionB);
                CheckBox optionC4 = findViewById(R.id.optionC);

                boolean isOptionASelected4 = optionA4.isChecked();
                boolean isOptionBSelected4 = optionB4.isChecked();
                boolean isOptionCSelected4 = optionC4.isChecked();

                // Check if Option B and Option C are selected (correct answer)
                if (!isOptionASelected4 && isOptionBSelected4 && isOptionCSelected4) {
                    score += 4;
                }
                break;

            case 5:
                // Handle the answer for question 5 with radio buttons
                RadioGroup radioGroup5 = findViewById(R.id.radioGroup);
                RadioButton selectedRadioButton5 = findViewById(radioGroup5.getCheckedRadioButtonId());

                if (selectedRadioButton5 != null) {
                    String selectedOption = selectedRadioButton5.getText().toString();
                    if (selectedOption.equals("4")) {
                        score += 4;
                    }
                }
                break;



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

    private void resetQuiz() {
        // Reset the quiz entirely
        currentQuestion = 1;
        score = 0;

        // Load the first question layout
        loadQuestionLayout(R.layout.question1);
    }
}