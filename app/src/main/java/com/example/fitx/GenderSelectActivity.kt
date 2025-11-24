package com.example.fitx

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class GenderSelectActivity : AppCompatActivity() {

    private var selectedGender: String? = null

    // We use MaterialCardView now, not FrameLayout
    private lateinit var cardMale: MaterialCardView
    private lateinit var cardFemale: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure this matches your XML filename (activity_gender_selection.xml)
        setContentView(R.layout.gender_select)

        // Find Views
        cardMale = findViewById(R.id.cardMale)
        cardFemale = findViewById(R.id.cardFemale)
        val backButton = findViewById<MaterialButton>(R.id.back_button)
        val nextButton = findViewById<MaterialButton>(R.id.next_button)

        // Gender selection click listeners
        cardMale.setOnClickListener { selectGender("Male") }
        cardFemale.setOnClickListener { selectGender("Female") }

        // Back button
        backButton.setOnClickListener { onBackPressed() }

        // Next button → move to GoalSelectionActivity
        nextButton.setOnClickListener {
            if (selectedGender == null) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, GoalSelectionActivity::class.java)

                // Important: I changed the key to "USER_GENDER" to match your other activities
                intent.putExtra("USER_GENDER", selectedGender)
                startActivity(intent)
            }
        }
    }

    private fun selectGender(gender: String) {
        selectedGender = gender

        // Define Fit X Colors
        val colorSelected = Color.parseColor("#B4F656") // Lime Green
        val colorDefault = Color.parseColor("#333333")  // Dark Grey

        if (gender == "Male") {
            // Highlight Male
            cardMale.strokeColor = colorSelected
            cardMale.strokeWidth = 6 // Thicker border

            // Reset Female
            cardFemale.strokeColor = colorDefault
            cardFemale.strokeWidth = 2 // Thin border
        } else {
            // Highlight Female
            cardFemale.strokeColor = colorSelected
            cardFemale.strokeWidth = 6

            // Reset Male
            cardMale.strokeColor = colorDefault
            cardMale.strokeWidth = 2
        }
    }
}