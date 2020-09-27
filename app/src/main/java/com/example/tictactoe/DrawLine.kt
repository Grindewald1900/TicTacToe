package com.example.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawLine @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val mContext: Context = context
    companion object{
        var paint: Paint = Paint()
        var startX: Float = 0f
        var startY: Float = 0f
        var endX: Float = 100f
        var endY: Float = 100f
        fun DrawView(context: Context, startX: Float, startY: Float, endX: Float, endY: Float){
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            paint.setColor(Color.BLACK);
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(startX, startY, endX, endY, paint)
    }



}