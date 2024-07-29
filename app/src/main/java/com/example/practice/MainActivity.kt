package com.example.practice

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.util.Random
import java.util.Timer
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main(4)
    }

    var pointList = mutableListOf<Float>()

    private fun main(player: Int) {
        var playerCount = 1
        val maxPlayer = player

        setContentView(R.layout.activity_main)

        var timerTask: Timer? = null
        var stage = 1
        var sec = 0f
        val timer: TextView = findViewById(R.id.tv_random)
        val btn : Button = findViewById(R.id.btn_main)
        val goalTime : TextView = findViewById(R.id.tv_timer)
        val score: TextView = findViewById(R.id.score)
        val random = Random()
        val randomNum = (random.nextInt(1001))
        var currentPlayer : TextView = findViewById(R.id.currentPlayer)


        goalTime.text = ((randomNum.toFloat())/100).toString()

        btn.setOnClickListener {
            when (stage) {
                1 -> {
                    btn.text = "정지"
                    timerTask = kotlin.concurrent.timer(period = 10) {
                        sec++
                        runOnUiThread {
                            timer.text = (sec/100).toString()
                        }

                    }
                    stage++
                }
                2 -> {
                    btn.text = "리셋"
                    timerTask?.cancel()
                    val point = abs(sec - randomNum) / 100
                    pointList.add(point)
                    score.text = point.toString()
                    stage ++
                }
                3 -> {
                    if (playerCount < maxPlayer) {
                        btn.text = "시작"
                        score.text = ""
                        timer.text = "0.00"
                        sec = 0f
                        stage = 1
                        playerCount++
                        currentPlayer.text = getString(R.string.currentPlayer, playerCount)
                    } else {
                        btn.text = "게임종료"
                        currentPlayer.text = pointList.toString()
                    }
                }
            }

        }
    }


}