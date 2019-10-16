package se.alge.simplepiano

import android.graphics.RectF

class Key{

    var down: Boolean = false
    var sound: Int
    var rect: RectF

    constructor(sound: Int, rect: RectF) {
        this.sound = sound
        this.rect = rect
    }


}
