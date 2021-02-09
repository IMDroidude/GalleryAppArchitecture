package gallery.app.architecture.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import gallery.app.architecture.R
import gallery.app.architecture.databinding.ActivityPhotoListBinding
import gallery.app.common.BaseActivity

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity(R.layout.activity_photo_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///setContentView(R.layout.activity_photo_list)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
        //return super.onSupportNavigateUp()
    }
}
/*
class PhotoActivity : BaseActivity<ActivityPhotoListBinding,PhotoViewModel>(R.layout.activity_photo_list) {
    override val mViewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///setContentView(R.layout.activity_photo_list)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
        //return super.onSupportNavigateUp()
    }
}*/
