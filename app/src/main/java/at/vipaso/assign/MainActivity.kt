package at.vipaso.assign

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import at.vipaso.assign.adapter.UsersAdapter
import at.vipaso.assign.databinding.ActivityMainBinding
import at.vipaso.assign.model.MainViewModel
import at.vipaso.assign.response.ItemsItem
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val searchHandler = Handler()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(binding.root)

        binding.usersList.layoutManager = LinearLayoutManager(this)

        viewModel.itemsItem.observe(this, Observer { data ->
            setUserList(data)
        })


        viewModel.setLoading.observe(this, Observer { data ->
            setLoading(data)
        })


        viewModel.resultFound.observe(this, Observer { data ->
            setResultFound(data)
        })

        viewModel.errorMsg.observe(this, Observer { data ->
            data.getContentIfNotHandled()?.let {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })


        binding.editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                //viewModel.searchUser(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {
                //binding.searchBtn.isEnabled = editable.length >= 2
            }
        })

        binding.searchBtn.setOnClickListener {

            val searchText = binding.editSearch.text.toString()

            // Check if the length of the entered text is less than 2 characters
            if (searchText.length < 2) {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter at least 2 characters",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            searchHandler.removeCallbacksAndMessages(null)

            // Start loading state
            setLoading(true)


            // Schedule the API request after a 1-second delay
            searchHandler.postDelayed({


                //viewModel.searchUser(editable.toString())
                viewModel.searchUser(binding.editSearch.text.toString())

                viewModel.setLoading.observe(this@MainActivity, Observer { data ->
                    // Set loading state based on the received data
                    setLoading(data)
                })

                // Observe for general errors 4xx, 5xx, errors...
                viewModel.errorMsg.observe(this@MainActivity, Observer { errorEvent ->
                    errorEvent.getContentIfNotHandled()?.let { errorMessage ->
                        // Handle general errors
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                })


            }, 1000)
        }

    }





    private fun setUserList(data: List<ItemsItem>) {
        val listUsers = ArrayList<ItemsItem>()
        for (user in data) {
            listUsers.add(user)
        }

        val colorList = listOf(
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7,
            R.color.color8,
            R.color.color9,
        )

        /**
        binding.usersList.adapter = UsersAdapter(listUsers) { data ->
            val i = Intent(this@MainActivity, DetailActivity::class.java)
            i.putExtra(DetailActivity.LOGIN_KEY, data.login)

            val connection = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

            if(connection.activeNetworkInfo?.isConnected == true){
                startActivity(i)
            }else{
                Snackbar.make(
                    binding.root,
                    "No internet connection",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        **/

        val adapter = UsersAdapter(listUsers, { selectedUser ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.LOGIN_KEY, selectedUser.login)

            val connection = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

            if (connection.activeNetworkInfo?.isConnected == true) {
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "No internet connection",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }, colorList)

        // Set the adapter to the RecyclerView
        binding.usersList.adapter = adapter
    }


    private fun setLoading(value: Boolean) {
        if (value) {
            binding.progressView.visibility = View.VISIBLE
        } else {
            binding.progressView.visibility = View.GONE
        }
    }

    private fun setResultFound(data: Int) {
        binding.searchResult.text = String.format(resources.getString(R.string.search_status), data)
        binding.searchResult.visibility = View.VISIBLE
        setLoading(false)
    }
}