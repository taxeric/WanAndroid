package com.eric.wanandroid.base.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.eric.wanandroid.R
import com.eric.wanandroid.utils.DpPxUtils
import com.eric.wanandroid.utils.LogUtils


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
        val strokeWidth = typeArray.getFloat(R.styleable.StrokeTextView_strokeWidthX, 1f)
        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = DpPxUtils.dp2px(strokeWidth).toFloat()
//        LogUtils.i("strokeWidth = $strokeWidth & ${DpPxUtils.dp2px(strokeWidth.toFloat()).toFloat()}")
        typeArray.recycle()
    }
}