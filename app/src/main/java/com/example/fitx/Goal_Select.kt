package com.example.fitx

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import kotlin.collections.ArrayList

class GoalSelectionActivity : AppCompatActivity() {

    // Store selected goals
    private val selectedGoals = mutableSetOf<MaterialButton>()

    // Variable to store the user's gender
    private var userGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.goals_selection)

        // 1. Retrieve the selected gender from the previous activity
        userGender = intent.getStringExtra("USER_GENDER")

        // Find goal buttons
        val goalLoseWeight = findViewById<MaterialButton>(R.id.goal_lose_weight)
        val goalBuildMuscle = findViewById<MaterialButton>(R.id.goal_build_muscle)
        val goalKeepFit = findViewById<MaterialButton>(R.id.goal_keep_fit)
        val goalImproveHealth = findViewById<MaterialButton>(R.id.goal_improve_health)
        val goalImproveFlexibility = findViewById<MaterialButton>(R.id.goal_improve_flexibility)
        val goalToneMuscles = findViewById<MaterialButton>(R.id.goal_tone_muscles)

        val backButton = findViewById<MaterialButton>(R.id.back_button)
        val nextButton = findViewById<MaterialButton>(R.id.next_button)

        // Add all goal buttons into a list
        val goalButtons = listOf(
            goalLoseWeight,
            goalBuildMuscle,
            goalKeepFit,
            goalImproveHealth,
            goalImproveFlexibility,
            goalToneMuscles
        )

        // Set click listener for each goal button
        goalButtons.forEach { button ->
            button.setOnClickListener { handleGoalSelection(button) }
        }

        // Back button
        backButton.setOnClickListener { onBackPressed() }

        // Next button
        nextButton.setOnClickListener {
            if (selectedGoals.isEmpty()) {
                Toast.makeText(this, "Please select at least one goal", Toast.LENGTH_SHORT).show()
            } else {

                // --- UPDATED LOGIC HERE ---
                // We removed the "if female/male" check.
                // Now it goes straight to CurrentBodyActivity.
                val intent = Intent(this, CurrentBodyActivity::class.java)

                // Pass the selected goals
                val selected = selectedGoals.map { it.text.toString() }
                intent.putStringArrayListExtra("SELECTED_GOALS", ArrayList(selected))

                // Pass gender along (CurrentBodyActivity might need it for the image)
                intent.putExtra("USER_GENDER", userGender)

                startActivity(intent)
            }
        }
    }

    // Toggle selection
    private fun handleGoalSelection(button: MaterialButton) {
        if (selectedGoals.contains(button)) {
            // Deselect
            selectedGoals.remove(button)
            // Using generic grey
            button.backgroundTintList =
                ContextCompat.getColorStateList(this, android.R.color.darker_gray)
            button.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        } else {
            // Select
            selectedGoals.add(button)
            // Using your app's primary color (purple_500)
            button.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.purple_500)
            button.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }
    }
}