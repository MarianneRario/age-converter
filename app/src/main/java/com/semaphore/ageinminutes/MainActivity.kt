package com.semaphore.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assigning date picker button to a variable button using id
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)


        // .setOnClickListener with a view (button)
        // lister btnDatePicker
        btnDatePicker.setOnClickListener { view -> // already included in .setOnClickListener
            clickDatePicker(view)
        }
    }

    // function to call when btnDatePicker is clicked
    // parameter must be a view
    private fun clickDatePicker(view: View){
        // assigning selected textview to a variable
        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate) //get the ID of the selected date textview
        // sub text view for selected date
        val subTVSelectedDate: TextView = findViewById(R.id.subSelectedDate)
        val myCalendar = Calendar.getInstance() // allows us to create calendar objects
        val year = myCalendar.get(Calendar.YEAR) // gives us the current year
        val month = myCalendar.get(Calendar.MONTH) // gives us the current month
        val day = myCalendar.get(Calendar.DAY_OF_MONTH) // gives us the current month

        // allows us to open up a date picker
        // dialog is a type of popup
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { // set listener when a date is clicked
                    view, selectedYear, selectedMonth, selectedDay ->

                // variable that will hold the date and will be passed to the tvSelectedDate
                val selectedDate = "${selectedMonth+1}/${selectedDay}/${selectedYear}"

                // run code here when "ok" is clicked in the date picker
                subTVSelectedDate.text = "selected date" // the sub textview
                tvSelectedDate.text = selectedDate // the main textview containing date

                // we need to make it a date object that we can use for calculating how much time has passed since
                // variable that will hold SimpleDateFormat
                val sdf = SimpleDateFormat("dd/MM/yyyy") // allows us to define a pattern that we want to use for our date
                // now we need to create an actual date object to hold the "sdf"
                val theDate = sdf.parse(selectedDate) // convert the "selectedDate" string into sdf format

                TODO("Remove the Time part in SimpleDateFormat to be added to 'selected date' text view")
                // Toast.makeText(this, "SDF: $theDate",Toast.LENGTH_LONG).show()

                // variable that will hold the computed date in minutes
                val selectedDateInMinutes = theDate.time / 60000

                // we need to calculate how much time has passed between the selected date and the current date
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                // calculate the current date in minutes
                val currentDateInMinutes = currentDate.time / 60000

                // IN MINUTES TILL DATE
                // Id for tvSelectedDateInMinutes
                val tvSelectedDateInMinutes: TextView = findViewById(R.id.tvSelectedDateInMinutes)
                // sub text view for date in minutes
                val subSelectedDateInMinutes: TextView = findViewById(R.id.subSelectedDateInMinutes)
                // calculate the difference in minutes
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                // textview that will contain the difference in minutes
                tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                subSelectedDateInMinutes.text = "in minutes till date" // the sub textview




            },
            year, month, day
        ).show()
    }

}