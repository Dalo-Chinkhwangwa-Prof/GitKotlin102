package com.britishbroadcast.gitkotlinapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.britishbroadcast.gitkotlinapplication.databinding.GitItemLayoutBinding
import com.britishbroadcast.gitkotlinapplication.model.data.GitResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GitRepositoryAdapter(var gitList: List<GitResponseItem>): RecyclerView.Adapter<GitRepositoryAdapter.GitViewHolder>() {

    inner class GitViewHolder(val binding: GitItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    fun updateGitRepositories(gitList: List<GitResponseItem>){
        this.gitList = gitList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val binding = GitItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GitViewHolder(binding)
    }

    override fun getItemCount(): Int = gitList.size

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {

        gitList[position].apply {
            holder.binding.apply {
                Glide.with(holder.itemView.context)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(owner.avatar_url)
                    .into(avatarImageview)
                repoNameTextview.text = name
            }
        }
    }

}