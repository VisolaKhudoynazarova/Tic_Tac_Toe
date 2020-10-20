package idrok.visola_khudoynazarova.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import idrok.visola_khudoynazarova.tictactoe.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private var lastBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)

        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHost.navController

        toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START, true)
        }

        binding.navView.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.share -> {
                }
                R.id.settings -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                    navController.navigate(R.id.settingFragment)
                }
                R.id.info -> {

                    binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                    navController.navigate(R.id.infoFragment)
                }

            }
            true
        }
    }

    override fun onBackPressed() {

            if (navController.currentDestination?.id == R.id.mainFragment) {
                if (lastBackPressed + 2000 >= System.currentTimeMillis()) {
                    finishAffinity()
                } else {
                    lastBackPressed = System.currentTimeMillis()
                    Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                super.onBackPressed()
            }


    }
}