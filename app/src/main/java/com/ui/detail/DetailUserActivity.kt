package com.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityDetailUserBinding
import com.example.githubuser.databinding.ItemUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
         val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)




        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(username = String())
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        })


        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }}