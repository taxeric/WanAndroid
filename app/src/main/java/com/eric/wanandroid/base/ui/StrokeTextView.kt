package com.eric.wanandroid.base.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.eric.wanandroid.R


/**
 * Created by eric on 20-9-22
 */
class StrokeTextView: AppCompatTextView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs){
        init(context, attrs!!)
    }

    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val width = getWidth()
        val height = getHeight()
        canvas!!.drawRect(0.1f, 0.1f, width.toFloat(), height.toFloat(), paint)
    }

    private fun init(context: Context, attrs: AttributeSet){
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView)
        val strokeColor = typeArray.getColor(R.styleable.StrokeTextView_strokeColor, Color.RED)
        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1f
        typeArray.recycle()
    }
}