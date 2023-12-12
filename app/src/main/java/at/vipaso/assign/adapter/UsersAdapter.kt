package at.vipaso.assign.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import at.vipaso.assign.response.ItemsItem
import at.vipaso.assign.R
import at.vipaso.assign.databinding.UserItemBinding
import com.bumptech.glide.Glide

class UsersAdapter(
    private val usersList: ArrayList<ItemsItem>,
    private val onClick: (ItemsItem) -> Unit,
    private val colorList: List<Int>

) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemUsername.text = usersList[position].login
        holder.binding.itemUserid.text = String.format(
            holder.itemView.context.resources.getString(R.string.user_id),
            usersList[position].id
        )
        Glide.with(holder.itemView.context).load(usersList[position].avatarUrl)
            .circleCrop().into(holder.binding.itemImage)

        holder.binding.itemContainer.setOnClickListener {
            onClick(usersList[holder.adapterPosition])
        }

        // Set a random backgroundTint for each item
        val randomColorIndex = (colorList.indices).random()
        holder.binding.itemCardview.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, colorList[randomColorIndex]))

    }

    override fun getItemCount(): Int = usersList.size

}