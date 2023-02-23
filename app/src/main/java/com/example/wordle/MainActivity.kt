package com.example.wordle

import FourLetterWordList
import FourLetterWordList.getRandomFourLetterWord
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var wordToGuess = getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val guessButton = findViewById<Button>(R.id.guessButton)
        var counter = 1
        var streak = 0
        val resetButton = findViewById<Button>(R.id.resetButton)
        val editText = findViewById<EditText>(R.id.userInput)
        val userGuessSaved1 = findViewById<TextView>(R.id.userGuess1)
        val userGuessSaved2 = findViewById<TextView>(R.id.userGuess2)
        val userGuessSaved3 = findViewById<TextView>(R.id.userGuess3)
        val userCheck1 = findViewById<TextView>(R.id.userGuessChecked1)
        val userCheck2 = findViewById<TextView>(R.id.userGuessChecked2)
        val userCheck3 = findViewById<TextView>(R.id.userGuessChecked3)
        val solution = findViewById<TextView>(R.id.Answer)
        val starImage = findViewById<ImageView>(R.id.imageView2)
        solution.text = wordToGuess.uppercase()
        val winCounter = findViewById<TextView>(R.id.correctCounter)

        Log.d("myDebugtag",wordToGuess)

        resetButton.setOnClickListener{
            counter = 1
            userGuessSaved1.visibility=View.INVISIBLE
            userGuessSaved2.visibility=View.INVISIBLE
            userGuessSaved3.visibility=View.INVISIBLE
            userCheck1.visibility=View.INVISIBLE
            userCheck2.visibility=View.INVISIBLE
            userCheck3.visibility=View.INVISIBLE
            wordToGuess = getRandomFourLetterWord()
            guessButton.isClickable = true
            guessButton.setBackgroundColor(Color.parseColor("#2596be"))
            solution.text = wordToGuess.uppercase()
            solution.visibility = View.INVISIBLE
            starImage.visibility = View.INVISIBLE
        }

        guessButton.setOnClickListener{
            if(counter == 1) {
                var text1 = editText.text.toString().uppercase()
                if(text1.length != 4){
                    Toast.makeText(it.context,"MUST BE 4 CHARACTERS!",Toast.LENGTH_SHORT).show()
                }
                    else{
                userGuessSaved1.text = text1
                userGuessSaved1.visibility = View.VISIBLE

                userCheck1.text=checkGuess(text1)
                userCheck1.visibility = View.VISIBLE
                if(text1 == wordToGuess.uppercase()){
                    guessButton.isClickable = false
                    guessButton.setBackgroundColor(808080)
                    Toast.makeText(it.context,"You have found the right answer!",Toast.LENGTH_LONG).show()
                    solution.visibility = View.VISIBLE
                    starImage.visibility = View.VISIBLE
                    streak++
                    winCounter.text = streak.toString()
                }
                counter++
                editText.text.clear()
                hideSoftKeyboard(editText)
                }}
            else if(counter == 2){
                var text2 = editText.text.toString().uppercase()
                if(text2.length != 4){
                    Toast.makeText(it.context,"MUST BE 4 CHARACTERS!",Toast.LENGTH_SHORT).show()
                }else{
                userGuessSaved2.text = editText.text.toString().uppercase()
                userGuessSaved2.visibility = View.VISIBLE
                userCheck2.text=checkGuess(text2)
                userCheck2.visibility = View.VISIBLE
                if(text2 == wordToGuess.uppercase()){
                guessButton.isClickable = false
                guessButton.setBackgroundColor(808080)
                Toast.makeText(it.context,"You have found the right answer!",Toast.LENGTH_LONG).show()
                solution.visibility = View.VISIBLE
                starImage.visibility = View.VISIBLE
                streak++
                winCounter.text = streak.toString()
                 }
                counter++
                editText.text.clear()
                    hideSoftKeyboard(editText)
                }}
            else if(counter == 3){
                var text3 = editText.text.toString().uppercase()
                if(text3.length != 4){
                    Toast.makeText(it.context,"MUST BE 4 CHARACTERS!",Toast.LENGTH_SHORT).show()
                }else{
                userGuessSaved3.text = text3
                userGuessSaved3.visibility = View.VISIBLE
                userCheck3.text=checkGuess(text3)
                userCheck3.visibility = View.VISIBLE
                guessButton.isClickable = false
                guessButton.setBackgroundColor(808080)
                editText.text.clear()
                solution.visibility = View.VISIBLE
                if(text3 == wordToGuess.uppercase()){
                    Toast.makeText(it.context,"You have found the right answer!",Toast.LENGTH_LONG).show()
                    starImage.visibility = View.VISIBLE
                    streak++
                    winCounter.text = streak.toString()
                }
                    hideSoftKeyboard(editText)
            }}
        }
    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


