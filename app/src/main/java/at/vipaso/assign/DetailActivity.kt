package at.vipaso.assign

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import at.vipaso.assign.databinding.ActivityDetailBinding
import at.vipaso.assign.model.DetailViewModel
import at.vipaso.assign.response.UserDetailResponse

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    companion object {
        const val LOGIN_KEY = "LOGIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        setContentView(binding.root)

        val username = intent.getStringExtra(LOGIN_KEY)

        /**
        viewModel.setLoadingDetail.observe(this, Observer { data ->
            setLoading(data)
        })
        **/

        viewModel.userDetailResponse.observe(this, Observer { data ->
            setUserDetail(data)
        })

        viewModel.errUserDetailMsg.observe(this, Observer { data ->
            data.getContentIfNotHandled()?.let {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })


        viewModel.getUserDetail(username!!)

    }

    @SuppressLint("SetTextI18n")
    private fun setUserDetail(data: UserDetailResponse) {

        Glide.with(this).load(data.avatarUrl).circleCrop().into(binding.itemDetailImage)
        binding.itemDetailName.text = data.name?.toString() ?: ""
        binding.itemDetailUsername.text = data.login?.toString() ?: ""
        binding.itemDetailFollowersCount.text = data.followers?.toString() ?: ""
        binding.itemDetailFollowingCount.text = data.following?.toString() ?: ""
        binding.itemDetailHtmlUrl.text = data.htmlUrl?.toString() ?: ""
        binding.itemDetailCompany.text = data.company?.toString() ?: ""
        binding.itemDetailBlog.text = data.blog?.toString() ?: ""
        binding.itemDetailLocation.text = data.location?.toString() ?: ""
        binding.itemDetailEmail.text = data.email?.toString() ?: ""
        binding.itemDetailHireable.text = data.hireable?.toString() ?: ""
        binding.itemDetailBio.text = data.bio?.toString() ?: ""
        binding.itemDetailTwitterUsername.text = data.twitterUsername?.toString() ?: ""
        binding.itemDetailPublicRepos.text = data.publicRepos?.toString() ?: ""
        binding.itemDetailPublicGists.text = data.publicGists?.toString() ?: ""
        binding.itemDetailCreatedAt.text = data.createdAt?.toString() ?: ""
        binding.itemDetailUpdatedAt.text = data.updatedAt?.toString() ?: ""

    }

}