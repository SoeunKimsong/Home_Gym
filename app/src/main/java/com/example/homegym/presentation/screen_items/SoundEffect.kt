package com.example.homegym.presentation.screen_items

import android.content.Context
import android.media.MediaPlayer

class SoundEffectPlayer(context: Context, soundEffect: Int) {
    private val mediaPlayer = MediaPlayer.create(context, soundEffect)

    fun playSound() {
        mediaPlayer.start()
    }

    fun stopSound() {
        mediaPlayer.pause()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}