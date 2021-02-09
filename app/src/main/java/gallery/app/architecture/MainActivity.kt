package gallery.app.architecture

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        navController = navHostFragment.findNavController()
        ///setupActionBarWithNavController(navController)

        /*val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)*/
        /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        navController = navHostFragment.findNavController()
        NavigationUI.setupActionBarWithNavController(this,navController)*/

        /*val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)*/

        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)*/

        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)*/
    }

    override fun onSupportNavigateUp(): Boolean {
        //return navController.navigateUp()
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
        /*val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()*/
        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp()*/
        //return super.onSupportNavigateUp()
    }
}