package at.vipaso.assign

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.vipaso.assign.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Check for internet connection
        if (isNetworkAvailable()) {
            startPulsatingAnimation()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000) // 3 seconds delay before going to MainActivity
        } else {
            // Show a long toast warning and close the app
            Toast.makeText(this, "No internet connection. Please check your connection.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun startPulsatingAnimation() {

        val pulseIn = ObjectAnimator.ofFloat(binding.splashImage, "scaleX", 1f, 1.2f)
        pulseIn.duration = 1000 // Pulse in duration

        val pulseOut = ObjectAnimator.ofFloat(binding.splashImage, "scaleX", 1.2f, 1f)
        pulseOut.duration = 1000 // Pulse out duration

        val pulseInY = ObjectAnimator.ofFloat(binding.splashImage, "scaleY", 1f, 1.2f)
        pulseInY.duration = 1000 // Pulse in duration

        val pulseOutY = ObjectAnimator.ofFloat(binding.splashImage, "scaleY", 1.2f, 1f)
        pulseOutY.duration = 1000 // Pulse out duration

        val pulse = AnimatorSet()
        pulse.play(pulseIn).before(pulseOut)
        pulse.play(pulseInY).before(pulseOutY)
        pulse.interpolator = AccelerateDecelerateInterpolator()

        pulse.start()
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
