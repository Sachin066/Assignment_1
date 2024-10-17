package com.example.assignment_1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnCloseListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var nameInput: EditText
    private lateinit var dobInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.name)
        dobInput = findViewById(R.id.dob)
        emailInput = findViewById(R.id.email)
        submitButton = findViewById(R.id.btn)

        // Calendar instance for DatePicker
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

        // DatePickerDialog listener
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Adjust month (because month index starts from 0)
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dobInput.setText(dateFormat.format(calendar.time))
        }

        // Open DatePickerDialog when DOB field is clicked
        dobInput.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Submit button logic to send data to another activity
        submitButton.setOnClickListener {
            val name = nameInput.text.toString()
            val dob = dobInput.text.toString()
            val email = emailInput.text.toString()

            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("userName", name)
            intent.putExtra("userDOB", dob)
            intent.putExtra("userEmail", email)
            startActivity(intent)
        }
    }
}

