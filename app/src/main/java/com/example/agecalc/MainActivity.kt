package com.example.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView?=null
    private var tvminutes :TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn : Button=findViewById(R.id.button)
        tvSelectedDate = findViewById(R.id.selectedDate)
        tvminutes=findViewById(R.id.minutes)
        btn.setOnClickListener {
            DateButton()
        }
    }
    fun DateButton(){
        val myCalander= Calendar.getInstance()
        val day=myCalander.get(Calendar.DAY_OF_MONTH)
        val mth=myCalander.get(Calendar.MONTH)
        val yr=myCalander.get(Calendar.YEAR)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,yr,mth,day->
                Toast.makeText(this,"$day / $mth / $yr",Toast.LENGTH_LONG).show()
                val selectedDate="$day/${mth+1}/$yr"
                tvSelectedDate?.text = selectedDate
                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                val selectedDateInMinutes=theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes=currentDate.time/60000
                val ans=currentDateInMinutes-selectedDateInMinutes
                tvminutes?.text= ans.toString()
            },
            yr,mth,day
        ).show()
    }
}