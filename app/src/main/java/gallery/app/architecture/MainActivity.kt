package gallery.app.architecture

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import gallery.app.architecture.databinding.ActivityMainBinding
import gallery.app.architecture.main.MainViewModel
import gallery.app.common.BaseActivity


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val mViewModel: MainViewModel by viewModels()


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

            navController = navHostFragment.navController

            NavigationUI.setupActionBarWithNavController(this, navController)
            ///setupActionBarWithNavController(navController)
        },1000)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}