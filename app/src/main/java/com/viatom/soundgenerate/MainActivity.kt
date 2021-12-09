package com.viatom.soundgenerate

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import me.tankery.lib.circularseekbar.CircularSeekBar
import java.lang.Math.sin
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var text: TextView
    lateinit var fuck: CircularSeekBar
    lateinit var mAudioTrack: AudioTrack

    lateinit var shortArray: ShortArray

    inner class Fuckx: TimerTask() {
        override fun run() {
            shortArray= ShortArray(1600){
                ((sin(it.toDouble()/10*2*Math.PI)+1.1f)*5000).toInt().toShort()
            }
           mAudioTrack.write(shortArray,0,1600)
        }

    }

    val fuckx=Fuckx()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text=findViewById(R.id.baba)
        fuck=findViewById(R.id.ga)
        fuck.setOnSeekBarChangeListener(object:CircularSeekBar.OnCircularSeekBarChangeListener{
            override fun onProgressChanged(
                circularSeekBar: CircularSeekBar?,
                progress: Float,
                fromUser: Boolean
            ) {
                text.text=(200+progress.toInt()).toString()
            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {

            }

            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {

            }

        })

        mAudioTrack = AudioTrack(
            AudioManager.STREAM_VOICE_CALL,
            16000, AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT, 16000, AudioTrack.MODE_STREAM
        )
        mAudioTrack.play()

        Timer().schedule(fuckx, Date(), 100)


    }
}