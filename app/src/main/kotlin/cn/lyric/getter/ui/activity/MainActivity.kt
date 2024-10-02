package cn.lyric.getter.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import cn.lyric.getter.R
import cn.lyric.getter.databinding.ActivityMainBinding
import cn.lyric.getter.tool.ActivityTools
import cn.lyric.getter.tool.ActivityTools.checkUpdate
import cn.lyric.getter.tool.ActivityTools.updateAppRules
import cn.lyric.getter.tool.Tools.xpActivation
import cn.lyric.getter.ui.viewmodel.ShareViewModel
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private val shareViewModel: ShareViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityTools.context = this
        shareViewModel.activated = xpActivation
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            if (!shareViewModel.activated) {
                nav.visibility = View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        (binding.nav as NavigationBarView).setupWithNavController(findNavController(R.id.nav_host_fragment))
        checkUpdate()
        updateAppRules()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}