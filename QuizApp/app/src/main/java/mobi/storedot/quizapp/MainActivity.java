package mobi.storedot.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double mScore;
    double correctionScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the Show Score button is clicked.
     */
    public void onclickSubmitResults(View view) {
        // Get answer text to question 4
        EditText q4Field = (EditText) findViewById(R.id.q4a);
        Editable answerEditable = q4Field.getText();
        q4Field.setText("");
        String q4Answer = answerEditable.toString();

        // Figure out if the user entered correct answer to question 4, and add 1 point to quiz initial score.
        if (q4Answer.equalsIgnoreCase("Bern")) { // q4Answer is the string from the edit text, Lucerne is the string
            mScore = mScore + 1;
        } else {
            mScore = mScore + 0;
        }
        // Figure out if the user selected answer 1 to question 2
        CheckBox q2a1 = (CheckBox) findViewById(R.id.q2a1);
        boolean selected_q2a1 = q2a1.isChecked();
        q2a1.setChecked(false);

        // Figure out if the user selected the answer 2 to question 2
        CheckBox q2a2 = (CheckBox) findViewById(R.id.q2a2);
        boolean selected_q2a2 = q2a2.isChecked();
        q2a2.setChecked(false);

        // Figure out if the user selected the answer 3 to question 2
        CheckBox q2a3 = (CheckBox) findViewById(R.id.q2a3);
        boolean selected_q2a3 = q2a3.isChecked();
        q2a3.setChecked(false);

        // Figure out if the user selected answer 1 to question 3
        CheckBox q3a1 = (CheckBox) findViewById(R.id.q3a1);
        boolean selected_q3a1 = q3a1.isChecked();
        q3a1.setChecked(false);

        // Figure out if the user selected the answer 2 to question 3
        CheckBox q3a2 = (CheckBox) findViewById(R.id.q3a2);
        boolean selected_q3a2 = q3a2.isChecked();
        q3a2.setChecked(false);

        // Figure out if the user selected the answer 3 to question 3
        CheckBox q3a3 = (CheckBox) findViewById(R.id.q3a3);
        boolean selected_q3a3 = q3a3.isChecked();
        q3a3.setChecked(false);

        // Calculate the score for correct answers
        double scoreCorrect = calculateScore(selected_q2a1, selected_q2a2, selected_q2a3, selected_q3a1, selected_q3a2, selected_q3a3);

        // Display on the screen the result of the quiz after user selected the answers to quiz.
        String quizResult = quizResult(scoreCorrect);

        /**
         * This method displays in a toast the points scored through answering correctly the quiz.
         */
        Context context = getApplicationContext();
        CharSequence text = quizResult;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * Calculates the Score for each question answered correctly.
     *
     * @param q2a1 is whether or not the app should add 0.5 points if this answer to quiz is selected.
     * @param q2a2 is whether or not the app should add 0.5 points if this answer to quiz is selected.
     * @param q2a3 if this answer is selected app will add 0 points to total score, because this answer is incorrect.
     * @param q3a1 is whether or not the app should add 0.5 points if this answer to quiz is selected.
     * @param q3a2 if this answer is selected app will add 0 points to total score, because this answer is incorrect.
     * @param q3a3 is whether or not the app should add 0.5 points if this answer to quiz is selected.
     * @return total calculated points based on points added by correct answer from check boxes and based on initial score which is dependent on correct answer from question 4 and 1.
     */

    public double calculateScore(boolean q2a1, boolean q2a2, boolean q2a3,
                                 boolean q3a1, boolean q3a2, boolean q3a3) {

        {
            // First calculate the score if all answer were incorrect

            // If the user selects checkbox q2a1, add 1 point
            if (q2a1) {
                correctionScore = correctionScore + 0.5;
            }

            // If the user selects checkbox q2a2, add 1 point
            if (q2a2) {
                correctionScore = correctionScore + 0.5;
            }

            // If the user selects checkbox q2a3, add 0 (Zero) point
            if (q2a3) {
                correctionScore = correctionScore + 0;
            }

            // If the user selects checkbox q3a1, add 1 point
            if (q3a1) {
                correctionScore = correctionScore + 0.5;
            }

            // If the user selects checkbox q3a2, add 0 Zero point
            if (q3a2) {
                correctionScore = correctionScore + 0;
            }

            // If the user selects checkbox q3a3, add 1 Zero point
            if (q3a3) {
                correctionScore = correctionScore + 0.5;
            }
            //user has selected all the correct options as well as not selected any incorrect ones before awarding points
            if (q2a1 && q2a2 && !q2a3) {
                correctionScore++;
            }
            // Calculate the total scored points
            return correctionScore + mScore;

        }
    }

    /**
     * Calculates the Score for true/false question 1/ i.e. adds 1 point for true and 0 points for false answer.
     */

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();
        RadioGroup onRadioButtonClicked = (RadioGroup) findViewById(R.id.rQ1);
        onRadioButtonClicked.setEnabled(false);

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.trueQ1:
                if (checked)
                    mScore++;
                break;

            case R.id.falseQ1:
                if (checked)
                    //false;
                    break;
        }
    }

    /**
     * Create the result of the quiz.
     *
     * @param scoreCorrect show the result after all points are added per checked checkbox.
     * @param "Score" Text describing the total number of points on the display/toast.
     * @return resultAnswer   final total result after all questions are answer.
     */
    private String quizResult(double scoreCorrect) {
        String resultAnswer = "Score" + "" + scoreCorrect;
        return resultAnswer;
    }


}