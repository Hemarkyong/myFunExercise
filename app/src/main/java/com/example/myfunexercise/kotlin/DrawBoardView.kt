package com.example.myfunexercise.kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.myfunexercise.R
/**
 * ABCgggg
 * */
class DrawBoardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var defaultPath: Path
    private var defaultPaint: Paint
    private var textPaint : TextPaint
    private val res : Resources
    private var typeface:Typeface? = null
    private var wenzi : String
    private var textSize : Float;
    private var viewWidth :Float;
    private var viewHeight :Float;
    init {
        textSize = 500f
        viewWidth = 0f
        viewHeight = 0f
        res = context.resources;
        typeface = Typeface.createFromAsset(getResources().getAssets(), "夏行楷.ttf");
        //初始化defaultPath
        defaultPath = Path()
        defaultPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        defaultPaint.color = Color.DKGRAY
        defaultPaint.style = Paint.Style.STROKE
        defaultPaint.strokeWidth = 3f

        //初始化textPaint
        textPaint = TextPaint()
        textPaint.isAntiAlias = true //打开抗锯齿功能
        textPaint.isFakeBoldText = false //是否使用伪粗体。
        textPaint.color = res.getColor(R.color.color1) //字体颜色
        textPaint.textSize = textSize  //字体大小
        textPaint.typeface = typeface
        textPaint.strokeWidth = 1f
        wenzi = ""
    }

    fun setWenzi(w : String?){
        if (w != null) {
            wenzi = w
            clear()
        };
    }

    private fun getSize(defaultSize: Int, measureSpec: Int): Int {
        var mySize = defaultSize
        //取测量模式
        val mode = MeasureSpec.getMode(measureSpec)
        //取测量长度
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.UNSPECIFIED -> mySize = defaultSize
            MeasureSpec.AT_MOST -> mySize = size
            MeasureSpec.EXACTLY -> mySize = size
        }
        return mySize
    }

    //获取字符串的宽度：
    fun GetTextWidth(text: String?, Size: Float): Float { // 第一个参数是要计算的字符串，第二个参数是字提大小
        val FontPaint = TextPaint()
        FontPaint.textSize = Size
        return FontPaint.measureText(text)
    }

    /**
     * 测量自己宽度和高度
     * */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize( widthMeasureSpec).toFloat()
        viewHeight = MeasureSpec.getSize( heightMeasureSpec).toFloat()

       /* if(wenzi!="") {
            while (true) {
                var textWidth = GetTextWidth(wenzi, textSize);
                if (textWidth < viewWidth) {
                    var percent = textWidth % viewWidth * 100
                    if (percent > 80) { //计算字体宽度在view80%内
                        break
                    }
                }
                textSize++
            }
        }*/

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(defaultPath, defaultPaint)

        //var textWidth = GetTextWidth(wenzi, textSize);
        //var x = viewWidth/2-textWidth/2
        canvas.drawText(wenzi, 0f, 400f, textPaint);
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> defaultPath.moveTo(x, y)
            MotionEvent.ACTION_MOVE -> defaultPath.lineTo(x, y)
            MotionEvent.ACTION_UP -> defaultPath.lineTo(x, y)
        }
        invalidate()
        return true
    }

    fun clear(){
        defaultPath.reset()
        invalidate()
    }
}