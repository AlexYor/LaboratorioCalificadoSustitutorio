package com.flores.yorsh.laboratoriocalificadosustitutorio.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flores.yorsh.laboratoriocalificadosustitutorio.databinding.ItemPostBinding
import com.flores.yorsh.laboratoriocalificadosustitutorio.model.Post
import com.flores.yorsh.laboratoriocalificadosustitutorio.utils.EmailUtils

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var posts = listOf<Post>()

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body

            // Click simple para enviar SMS
            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:+51925137361")
                    putExtra("sms_body", post.title)
                }
                binding.root.context.startActivity(intent)
            }

            // Click largo para enviar email
            binding.root.setOnLongClickListener {
                EmailUtils.sendEmail(
                    context = binding.root.context,
                    emailAddress = "victor.saico@tecsup.edu.pe",
                    subject = "Post Body",
                    body = post.body
                )
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun updatePosts(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}