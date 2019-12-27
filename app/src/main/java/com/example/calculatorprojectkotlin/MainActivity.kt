package com.example.calculatorprojectkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myPlus.setOnClickListener{interactExpression("+", false)}
        myMinus.setOnClickListener{interactExpression("-", false)}
        myOpen.setOnClickListener{interactExpression("(", false)}
        myClose.setOnClickListener{interactExpression(")", false)}
        myMul.setOnClickListener{interactExpression("*", false)}
        myDivide.setOnClickListener{interactExpression("/", false)}



        myOne.setOnClickListener{interactExpression("1", true)}
        myTwo.setOnClickListener{interactExpression("2", true)}
        myThree.setOnClickListener{interactExpression("3", true)}
        myFour.setOnClickListener{interactExpression("4", true)}
        myFive.setOnClickListener{interactExpression("5", true)}
        mySix.setOnClickListener{interactExpression("6", true)}
        mySeven.setOnClickListener{interactExpression("7", true)}
        myEight.setOnClickListener{interactExpression("8", true)}
        myNine.setOnClickListener{interactExpression("9", true)}
        myDot.setOnClickListener{interactExpression(".", true)}


        myCE.setOnClickListener{
            myExpression.text = ""
            myResult.text = ""
        }
        myBack.setOnClickListener {
            val myText = myExpression.text.toString()
            if(myText.isNotEmpty()){
                myExpression.text = myText.substring(0, myText.length-1)
            }
            myResult.text = ""
        }
        myEquals.setOnClickListener{
            try {
               val expression = ExpressionBuilder(myExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()) {
                    myResult.text =  longResult.toString()   }
                else{
                    myResult.text = result.toString()
                }
            }
            catch (e: Exception){
                   Log.d("Exception", "Something went wrong!")
            }
        }

    }

    fun interactExpression(string: String, canClear: Boolean){
        if(myResult.text.isNotEmpty()){
            myExpression.text = ""
            
        }
        if(canClear){
            myResult.text = ""
            myExpression.append(string)

        } else{
            myExpression.append(myResult.text)
            myExpression.append(string)
            myResult.text = ""
        }


    }
}
