package com.example.fitx

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure your XML file is named 'activity_home.xml'
        setContentView(R.layout.dashboard)

        setupCharacterVideo()
        setupDashboardMetrics()
        setupHeaderInteractions()
        setupBottomNavigation()
    }

    private fun setupCharacterVideo() {
        val videoView = findViewById<VideoView>(R.id.character_video)

        // NOTE: This assumes you have a video file named 'character_idle_anim' in 'res/raw/'
        // If you don't have a video yet, this block is safe to keep but won't play anything
        // until the resource exists.
        try {
            // Construct the path to the raw resource
            // resourceId would normally be R.raw.character_idle_anim
            // using a placeholder ID or checking existence logic is recommended here
            val resourceId = resources.getIdentifier("character_idle_anim", "raw", packageName)

            if (resourceId != 0) {
                val uri = Uri.parse("android.resource://$packageName/$resourceId")
                videoView.setVideoURI(uri)

                // Loop the video for the character animation effect
                videoView.setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                    // Ensure the video scales correctly
                    mediaPlayer.setVideoScalingMode(android.media.MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT)
                    // Make background transparent if needed once video starts
                    videoView.background = null
                }

                videoView.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback: The XML 'android:background' will show the static image
        }
    }

    private fun setupDashboardMetrics() {
        // 1. Steps Card
        findViewById<TextView>(R.id.steps_value).text = "8,240 / 10,000"

        // 2. Calories Card
        findViewById<TextView>(R.id.calories_value).text = "640 kcal"

        // 3. Active Minutes Card
        findViewById<TextView>(R.id.minutes_value).text = "52 / 60 mins"

        // 4. Water Intake Card
        findViewById<TextView>(R.id.water_value).text = "1.5 / 2.5 L"

        // Optional: Add click listeners to cards for detailed views
        findViewById<MaterialCardView>(R.id.steps_card).setOnClickListener {
            Toast.makeText(this, "Opening Steps Details...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupHeaderInteractions() {
        findViewById<ImageView>(R.id.notification_button).setOnClickListener {
            Toast.makeText(this, "No new notifications", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.streak_icon).setOnClickListener {
            Toast.makeText(this, "You are on a 5-day streak! Keep it up!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // Assuming ids in your 'bottom_nav_menu.xml' are like below:
                // R.id.nav_home, R.id.nav_workout, R.id.nav_stats, R.id.nav_profile

                // Replace these IDs with your actual menu IDs
                resources.getIdentifier("nav_home", "id", packageName) -> {
                    // Already on Home
                    true
                }
                resources.getIdentifier("nav_workout", "id", packageName) -> {
                    Toast.makeText(this, "Workouts", Toast.LENGTH_SHORT).show()
                    true
                }
                resources.getIdentifier("nav_stats", "id", packageName) -> {
                    Toast.makeText(this, "Statistics", Toast.LENGTH_SHORT).show()
                    true
                }
                resources.getIdentifier("nav_profile", "id", packageName) -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}