package com.maidandhelper.www.maidandhelper.VideoCallModule.Activites

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.maidandhelper.www.maidandhelper.R
import kotlinx.android.synthetic.main.activity_comming_video_call.*

class CommingVideoCall : Activity()
{



    lateinit var mp: MediaPlayer
    lateinit var vibratorService: Vibrator

    var played : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comming_video_call)
        Glide.with(this)
            .load("https://media.licdn.com/dms/image/C4D03AQEu6S-tmHzDHg/profile-displayphoto-shrink_200_200/0?e=1550102400&v=beta&t=BfPaHFxXpNCh4fjFSdyfjDQ20gQsdy9VDSmrasYT7DE")
            .error(R.drawable.avatar).thumbnail(.2f).into(callerPhoto)
        mp = MediaPlayer.create(this , R.raw.riningtone)
        mp.start()

        vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val pattern = longArrayOf(0, 3000, 1000)
        vibratorService.vibrate(pattern ,0)

        mp.setOnCompletionListener(object : MediaPlayer.OnCompletionListener
        {
            override fun onCompletion(mp: MediaPlayer?)
            {
                if(played)
                {
                    vibratorService.cancel()
                    Toast.makeText(this@CommingVideoCall , "Call Will Finish" , Toast.LENGTH_LONG).show()
                }
                else
                {
                    mp?.start()
                    played = true
                }
            }

        })
    }


    fun answer (view : View)
    {
        Toast.makeText(applicationContext , "Answer" , Toast.LENGTH_LONG).show()
    }


    fun cancel(view: View)
    {
        mp.stop()
        vibratorService.cancel()
        Toast.makeText(applicationContext , "Cancel" , Toast.LENGTH_LONG).show()

    }




}