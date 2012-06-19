package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class GuessNumberActivity extends Activity
{
    EditText input;
    EditText result;
    ArrayList<String> randomNumbers;
    ArrayList<String> enterNumbers = new ArrayList<String>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.main);
        input = (EditText)findViewById(R.id.input);
        result = (EditText)findViewById(R.id.result);
        randomNumbers = getRandomNumbers();

    }

    private ArrayList<String> getRandomNumbers() {
        ArrayList numbers = new ArrayList();
        for(int i =0; i<10; i++) {
            numbers.add(i);
        }
        ArrayList randomNum = new ArrayList();
        int count=10;
        for(int j=0; j<4; j++) {
            Random r = new Random();
            int num = r.nextInt(count);
            randomNum.add(numbers.get(num).toString());
            numbers.remove(num);
            count--;
        }
        System.out.println(randomNum.toString());
        return randomNum;
    }

    public void guess(View v) {
        int countA = 0;
        int countB = 0;
        for(int i=0; i< enterNumbers.size(); i++) {
            String number = enterNumbers.get(i);
            if(number.equals(randomNumbers.get(i))) {
                countA++;
                System.out.println(countA);
            } else if(randomNumbers.contains(number)) {
                countB++;
            }
        }
        String checkResult = input.getText() + ":" + countA + "A" + countB + "B";
        if(checkResult.endsWith("4A0B")) {
            checkResult = checkResult + " Congratulation!";
        }
        result.append("\n" + checkResult);
        input.setText("");
        enterNumbers.clear();
    }

    public void enterNumber(View v) {
        String number = ((Button)v).getText().toString();
        if(enterNumbers.size() < 4 && !enterNumbers.contains(number)) {
            input.append(number);
            enterNumbers.add(number);
        }
    }

    public void delete(View v) {
        if(enterNumbers.size() > 0) {
            enterNumbers.remove(enterNumbers.size()-1);
            String text = input.getEditableText().toString();
            input.setText("");
            input.append(text.substring(0, text.length() - 1));
        }
    }
}