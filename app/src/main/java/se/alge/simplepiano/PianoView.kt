package se.alge.simplepiano

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.graphics.RectF


class PianoView : View{

    var white_keys: MutableList<Key> = ArrayList()
    var black_keys: MutableList<Key> = ArrayList()
    val num_white: Int = 14

    var key_with: Float = 0.0F

    val start_octave: Int = 3
    val start_key: Int = 0

    // Specify a few colors
    var black = Paint()

    var white = Paint()

    var yellow = Paint()

    var key_width: Float = 0f

    private var soundPlayer: AudioSoundPlayer

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

        // Specify a few colors
        black.color = Color.BLACK

        white.color = Color.WHITE
        white.style = Paint.Style.FILL

        yellow.color = Color.YELLOW
        yellow.style = Paint.Style.FILL

        this.soundPlayer = AudioSoundPlayer()
        Log.d("PianoView", "Init done")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("PianoView", "size changed" )
        super.onSizeChanged(w, h, oldw, oldh)

        key_width = w / num_white.toFloat()
        var height = h
        var count = 15

        for (i in 0 until num_white) {
            Log.d("PianoView","Generating keys iteration: " + i.toString())
            val left = i * key_width
            var right = left + key_width

            if (i == num_white - 1) {
                right = w.toFloat()
            }

            var rect = RectF(left.toFloat(), 0f, right.toFloat(), h.toFloat())


            white_keys.add(Key(i+1, rect))
            Log.d("PianoView", "Created white key: " + rect.toString())

            if (i != 0 && i != 3 && i != 7 && i != 10) {
                rect = RectF(
                    (i - 1).toFloat() * key_width + 0.5f * key_width + 0.25f * key_width, 0f,
                    i.toFloat() * key_width + 0.25f * key_width, 0.67f * height
                )
                black_keys.add(Key(1, rect))
                count++
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("PianoView", "drawing the canvas")
        super.onDraw(canvas)
        for (k in white_keys) {
            if (canvas != null) {
                canvas.drawRect(k.rect, if (k.down) yellow else white)
            }
        }

        for (i in 1 until num_white) {
            if (canvas != null) {
                canvas.drawLine(i * key_width, 0f, i * key_width, height.toFloat(), black)
            }
        }

        for (k in black_keys) {
            if (canvas != null) {
                canvas.drawRect(k.rect, if (k.down) yellow else black)
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("PianoView", "TouchEvent: " + event.toString())
        return super.onTouchEvent(event)
    }

    private fun keyForCoords(x: Float, y: Float): Key? {
        for (k in black_keys) {
            if (k.rect.contains(x, y)) {
                return k
            }
        }

        for (k in white_keys) {
            if (k.rect.contains(x, y)) {
                return k
            }
        }

        return null
    }

}