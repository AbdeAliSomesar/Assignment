package com.example.koo.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koo.R
import com.example.koo.databinding.ActivityMainBinding
import com.example.koo.domain.models.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainAdapter.PostClickListener, MainAdapter.LoadNextPosts {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding
  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)

//    val navController = findNavController(R.id.nav_host_fragment_content_main)
//    appBarConfiguration = AppBarConfiguration(navController.graph)
//    setupActionBarWithNavController(navController, appBarConfiguration)
//    viewModel.getNextPosts()
    setup()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }

  private fun setup(){

    val adapter = MainAdapter(this@MainActivity,this@MainActivity)
    binding.apply {
      recyclerView.adapter = adapter
      recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
      val itemDecoration = DividerItemDecoration(
        this@MainActivity,
        LinearLayoutManager.VERTICAL
      )
      recyclerView.addItemDecoration(itemDecoration)
    }
    viewModel.posts.observe(this){
      if(it.isNotEmpty())
        adapter.submitList(it as MutableList<Post>)
    }

  }

  override fun onClick(postId: Int) {
   // TODO("Not yet implemented")
  }

  override fun loadPosts() {
    viewModel.getNextPosts()
  }
}
