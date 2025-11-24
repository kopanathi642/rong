package com.example.fitx

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class FocusAreaActivity : AppCompatActivity() {

    private val selectedFocusAreas = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.focus_area)

        val btnBicep = findViewById<MaterialButton>(R.id.btnBicep)
        val btnTricep = findViewById<MaterialButton>(R.id.btnTricep)
        val btnAbs = findViewById<MaterialButton>(R.id.btnAbs)
        val btnLeg = findViewById<MaterialButton>(R.id.btnLeg)
        val btnFullBody = findViewById<MaterialButton>(R.id.btnFullBody)
        val btnBack = findViewById<MaterialButton>(R.id.back_button)
        val btnNext = findViewById<MaterialButton>(R.id.next_button)

        val focusButtons = listOf(btnBicep, btnTricep, btnAbs, btnLeg, btnFullBody)

        focusButtons.forEach { button ->
            button.setOnClickListener {
                toggleSelection(button)
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener {
            if (selectedFocusAreas.isEmpty()) {
                Toast.makeText(this, "Please select at least one focus area", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MetricsInputActivity::class.java)
                intent.putStringArrayListExtra("FOCUS_AREAS", ArrayList(selectedFocusAreas))
                startActivity(intent)
            }
        }
    } // <--- THIS BRACE WAS MISSING! This closes onCreate

    // This function must be OUTSIDE onCreate
    private fun toggleSelection(button: MaterialButton) {
        val focusArea = button.text.toString()

        if (selectedFocusAreas.contains(focusArea)) {
            selectedFocusAreas.remove(focusArea)
            button.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1C1C1E"))
            button.setTextColor(Color.WHITE)
            button.strokeColor = ColorStateList.valueOf(Color.parseColor("#333333"))
        } else {
            selectedFocusAreas.add(focusArea)
            button.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#B4F656"))
            button.setTextColor(Color.BLACK)
            button.strokeColor = ColorStateList.valueOf(Color.parseColor("#B4F656"))
        }
    }
}