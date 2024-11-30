package com.flores.yorsh.laboratoriocalificadosustitutorio

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.flores.yorsh.laboratoriocalificadosustitutorio.adapter.PostAdapter
import com.flores.yorsh.laboratoriocalificadosustitutorio.api.RetrofitInstance
import com.flores.yorsh.laboratoriocalificadosustitutorio.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchPosts()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = postAdapter
    }

    private fun fetchPosts() {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val posts = RetrofitInstance.postApiService.getPosts()
                postAdapter.updatePosts(posts)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}