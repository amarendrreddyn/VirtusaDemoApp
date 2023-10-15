package com.ajs.virtusademoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajs.virtusademoapp.R
import com.ajs.virtusademoapp.databinding.UserDetailsLayoutBinding
import com.ajs.virtusademoapp.data.UserData

class UserDetailAdapter(var usersList: List<UserData>): RecyclerView.Adapter<UserDetailAdapter.UserDetailViewHolder>() {

    private lateinit var binding: UserDetailsLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailViewHolder {
        var binding= UserDetailsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return UserDetailViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
        var userDataObj: UserData = usersList[position]
        holder.firstName.text = holder.itemView.context.getString(R.string.first_name_prefix) + userDataObj.first_name
        holder.lastName.text =  holder.itemView.context.getString(R.string.last_name_prefix) + userDataObj.last_name
        holder.mailId.text =  holder.itemView.context.getString(R.string.email_prefix) + userDataObj.email

    }

    override fun getItemCount(): Int {
        return usersList.size
    }
    fun updateData(newData: List<UserData>) {
        usersList = newData
        notifyDataSetChanged()
    }


    class UserDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val lastName: TextView = itemView.findViewById(R.id.userLastNameTextview)
            val mailId: TextView = itemView.findViewById(R.id.user_mailId_textview)
            val firstName: TextView = itemView.findViewById(R.id.userFirstNameTextview)

    }
}