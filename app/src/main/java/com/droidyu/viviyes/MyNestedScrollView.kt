package com.droidyu.viviyes

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView
import kotlin.math.abs

class MyNestedScrollView(context: Context, attributes: AttributeSet) :
    NestedScrollView(context, attributes) {
    var lastInterceptX = 0
    var lastInterceptY = 0
    var lastX = 0
    var lastY = 0
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var intercept = false
        val x = ev.x
        val y = ev.y
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> intercept = false
            MotionEvent.ACTION_MOVE -> {
                val dX = x - lastInterceptX
                val dY = y - lastInterceptY
                intercept = abs(dX) > abs(dY)
            }
            MotionEvent.ACTION_UP -> intercept = false
        }
        lastX = x.toInt()
        lastY = y.toInt()
        lastInterceptX = x.toInt()
        lastInterceptY = y.toInt()
        Log.d("touch", intercept.toString())
        return intercept
    }
}