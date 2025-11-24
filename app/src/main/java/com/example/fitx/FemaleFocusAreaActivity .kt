package com.example.fitx

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FemaleFocusAreaActivity : AppCompatActivity() {

    private lateinit var focusButtons: List<Button>
    private val selectedFocusAreas = mutableSetOf<String>() // Store multiple selections

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.focus_area) // Assumes the XML layout file is focus_area.xml

        // Initialize buttons using findViewById
        val btnBicep = findViewById<Button>(R.id.btnBicep)
        val btnTricep = findViewById<Button>(R.id.btnTricep)
        val btnAbs = findViewById<Button>(R.id.btnAbs)
        val btnLeg = findViewById<Button>(R.id.btnLeg)
        val btnFullBody = findViewById<Button>(R.id.btnFullBody)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // List now includes Bicep and Tricep buttons
        focusButtons = listOf(btnBicep, btnTricep, btnAbs, btnLeg, btnFullBody)

        // Set click listeners for focus buttons
        focusButtons.forEach { button ->
            button.setOnClickListener {
                toggleFocus(button)
            }
        }

        // Back button
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Next button
        btnNext.setOnClickListener {
            if (selectedFocusAreas.isEmpty()) {
                Toast.makeText(this, "Please select at least one focus area", Toast.LENGTH_SHORT).show()
            } else {
                // Pass the list of selected focus areas to the next activity
                val intent = Intent(this, MetricsInputActivity::class.java)
                intent.putStringArrayListExtra("FOCUS_AREAS", ArrayList(selectedFocusAreas))
                startActivity(intent)
            }
        }
    }

    /**
     * Toggles the selection state (color and background) of a focus button
     * and updates the selectedFocusAreas set.
     */
    private fun toggleFocus(button: Button) {
        val focus = button.text.toString()
        if (selectedFocusAreas.contains(focus)) {
            // Deselect: remove from set and reset colors
            selectedFocusAreas.remove(focus)
            // Default colors (Note: Uses hardcoded colors as provided in your input)
            button.setBackgroundColor(Color.parseColor("#F0F0F0"))
            button.setTextColor(Color.parseColor("#222222"))
        } else {
            // Select: add to set and apply selected colors
            selectedFocusAreas.add(focus)
            button.setBackgroundColor(Color.parseColor("#3B82F6")) // Blue background
            button.setTextColor(Color.WHITE)
        }
    }
}
