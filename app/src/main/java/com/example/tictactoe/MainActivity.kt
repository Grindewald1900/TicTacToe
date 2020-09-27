package com.example.tictactoe

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var btnReset: ImageButton
    lateinit var btnClear: ImageButton
    lateinit var tvScore: TextView
    lateinit var ivBackground: ImageView

    lateinit var imageSwitcher : ArrayList<ImageSwitcher>
    lateinit var imBackgrounds: ArrayList<ImageView>
    lateinit var claimedArray: ArrayList<Int>
    lateinit var xArray: ArrayList<Int>
    lateinit var oArray: ArrayList<Int>
    lateinit var canvas: Canvas
    lateinit var paint: Paint
    var xScore: Int = 0
    var oScore: Int = 0
    var isCross: Boolean = true
    var isFinish: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        initView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        Log.i("main","onSaveInstanceState")
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("claimedArray",claimedArray)
        outState.putIntegerArrayList("xArray",xArray)
        outState.putIntegerArrayList("oArray",oArray)
        outState.putInt("xScore",xScore)
        outState.putInt("oScore",oScore)
        outState.putBoolean("isCross",isCross)
        outState.putBoolean("isFinish",isFinish)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        Log.i("main","onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        claimedArray = savedInstanceState.getIntegerArrayList("claimedArray") as ArrayList<Int>
        xArray = savedInstanceState.getIntegerArrayList("xArray") as ArrayList<Int>
        oArray = savedInstanceState.getIntegerArrayList("oArray") as ArrayList<Int>
        xScore = savedInstanceState.getInt("xScore")
        oScore = savedInstanceState.getInt("oScore")
        isCross = savedInstanceState.getBoolean("isCross")
        isFinish = savedInstanceState.getBoolean("isFinish")
        recoverView()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView(){
        var bitmap: Bitmap = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888)
        btnReset = findViewById(R.id.ib_main_reset)
        btnClear = findViewById(R.id.ib_main_clear)
        tvScore = findViewById(R.id.tv_main_score)
        ivBackground = findViewById(R.id.iv_main_background)
        ivBackground.bringToFront()
//        canvas = Canvas(bitmap)
//        canvas.drawColor(Color.BLUE)
//        paint = Paint()
//        paint.setColor(Color.BLACK)
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 80f

        initImageSwitcher()
        btnReset.setOnClickListener {
            resetGame()
        }
        btnClear.setOnClickListener {
            resetGame()
            xScore = 0
            oScore = 0
            editScore()
        }
        editScore()

        if (!getSharedPreferences("data", Context.MODE_PRIVATE).contains("AutoReset"))
            getSharedPreferences("data",Context.MODE_PRIVATE).edit().putBoolean("AutoReset", false).apply()
    }

    private fun recoverView(){
        for (i in 1..9){
            if (xArray.contains(i))
                imageSwitcher[i - 1].setImageResource(R.drawable.button_cross)
            if (oArray.contains(i))
                imageSwitcher[i - 1].setImageResource(R.drawable.button_circle)
        }
        editScore()
    }

    private fun editScore(){
        val share: SharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE)
        share.edit().putInt("xScore", xScore).apply()
        share.edit().putInt("oScore", oScore).apply()
        tvScore.setText("$xScore --- $oScore")
    }

    private fun isAutoReset() : Boolean{
        val share: SharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE)
        return share.getBoolean("AutoReset",false)
    }

    private fun initImageSwitcher(){
        imageSwitcher = ArrayList()
        claimedArray = ArrayList()
        xArray = ArrayList()
        oArray = ArrayList()
        imBackgrounds = ArrayList()
        imBackgrounds.add(findViewById(R.id.iv_main_background))

        for (i in 1..9){
//            Log.i("main",i.toString())
            imageSwitcher.add(findViewById(getViewIdByString("is_main_$i")))
            imageSwitcher[i - 1].setFactory {
                val imgView = ImageView(applicationContext)
                imgView.scaleType = ImageView.ScaleType.FIT_CENTER
                imgView.setPadding(8, 8, 8, 8)
                imgView
            }

            imageSwitcher[i - 1].setOnClickListener {
                if(isFinish) {
                    Toast.makeText(applicationContext, resources.getString(R.string.toast_reset), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Log.i("main", "$i Clicked")
                if (!claimedArray.contains(i)){
                    claimedArray.add(i)
                    isCross = if (isCross){
                        xArray.add(i)
                        imageSwitcher[i - 1].setImageResource(R.drawable.button_cross)
                        false
                    } else{
                        oArray.add(i)
                        imageSwitcher[i - 1].setImageResource(R.drawable.button_circle)
                        true
                    }
                }
                else{
                    Toast.makeText(applicationContext, resources.getString(R.string.toast_claimed), Toast.LENGTH_SHORT).show()
                }
                when {
                    Utils.ifWin(xArray) -> {
                        isFinish = true
                        xScore ++
                        drawLine(xArray)
                        Toast.makeText(applicationContext, resources.getString(R.string.toast_x_win), Toast.LENGTH_SHORT).show()
                    }
                    Utils.ifWin(oArray) -> {
                        isFinish = true
                        oScore ++
                        drawLine(oArray)
                        Toast.makeText(applicationContext, resources.getString(R.string.toast_o_win), Toast.LENGTH_SHORT).show()
                    }
                    claimedArray.size == 9 -> {
                        isFinish = true
                        Toast.makeText(applicationContext, resources.getString(R.string.toast_tie), Toast.LENGTH_SHORT).show()
                    }
                }
                if (isFinish && isAutoReset())
                    resetGame()
                editScore()
            }
        }

        for (i in 1..6){
            imBackgrounds.add(findViewById(getViewIdByString("iv_main_background$i")))
        }
    }

    private fun resetGame(){
        isFinish = false;
        claimedArray.clear()
        xArray.clear()
        oArray.clear()
        isCross = true
        for (i in 1..9){
            imageSwitcher[i - 1].setImageResource(R.drawable.button_empty)
        }
        for (i in 1..7){
            imBackgrounds[i - 1].setImageResource(R.drawable.empty)
        }
    }


    private fun getViewIdByString(str: String): Int{
        return resources.getIdentifier(str,"id",packageName)
    }

    private fun drawLine(array: ArrayList<Int>){
//        var spots: ArrayList<Int> = Utils.getLineSpots(array)
//        var startX: Float = (imageSwitcher[spots.get(0) - 1].right + imageSwitcher[spots.get(0) - 1].left) / 2f
//        var startY: Float = (imageSwitcher[spots.get(0) - 1].top + imageSwitcher[spots.get(0) - 1].bottom) / 2f
//        var endX: Float = (imageSwitcher[spots.get(1) - 1].right + imageSwitcher[spots.get(1) - 1].left) / 2f
//        var endY: Float = (imageSwitcher[spots.get(1) - 1].top + imageSwitcher[spots.get(1) - 1].bottom) / 2f
//        Log.i("main","points:"+ startX.toString()+ " " +startY.toString() + " "+endX.toString()+" "+endY.toString())
//        canvas.drawLine(startX, startY, endX, endY, paint)


        when (Utils.getLineSpots(array)){
            1 -> imBackgrounds.get(1).setImageResource(R.drawable.horizontal_line)
            2 -> imBackgrounds.get(2).setImageResource(R.drawable.horizontal_line)
            3 -> imBackgrounds.get(3).setImageResource(R.drawable.horizontal_line)
            4 -> imBackgrounds.get(4).setImageResource(R.drawable.vertical_line)
            5 -> imBackgrounds.get(5).setImageResource(R.drawable.vertical_line)
            6 -> imBackgrounds.get(6).setImageResource(R.drawable.vertical_line)
            7 -> imBackgrounds.get(0).setImageResource(R.drawable.diagonal_line1)
            8 -> imBackgrounds.get(0).setImageResource(R.drawable.diagonal_line2)
        }
    }

}