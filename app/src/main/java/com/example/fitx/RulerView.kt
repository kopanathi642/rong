package com.example.fitx

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class RulerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        strokeWidth = 4f
        textSize = 40f
    }

    var maxScale = 300   // e.g. 300 lbs or 300 cm
    var unitStep = 1     // each tick = 1 unit
    var spacing = 20     // pixels between ticks

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0..maxScale) {
            val x = i * spacing.toFloat()

            // Major tick every 10 units
            if (i % 10 == 0) {
                canvas.drawLine(x, 0f, x, 100f, paint)
                canvas.drawText(i.toString(), x - 20f, 150f, paint)
            }
            // Minor tick
            else {
                canvas.drawLine(x, 0f, x, 60f, paint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = maxScale * spacing
        setMeasuredDimension(width, 200) // 200px tall ruler
    }
}
