package se.alge.simplepiano

import android.content.res.AssetManager
import android.media.AudioTrack
import android.util.Log
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager

class PlayThread : Thread {


    var audioTrack: AudioTrack
    var note: String


    constructor(note: String, context: Context) {
        this.note = note

        val path = "C2.wav"

        var assetManager: AssetManager = context.assets
        val ad = assetManager.openFd(path)
        val fileSize = ad.length
        val bufferSize = 4096
        var buffer = ByteArray(bufferSize)


        this.audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            11025,
            AudioFormat.CHANNEL_CONFIGURATION_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize,
            AudioTrack.MODE_STREAM
        )




    }

    override fun run() {
        Log.d("PlayThread", "Running thread")
        super.run()
    }
}