package com.example.votingiitj

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class recycleadapter(val context: Context,var list:HashMap<String,Map<String,String>>,var uid:MutableList<String>) :RecyclerView.Adapter<recycleadapter.view_holder>() {

    class view_holder(itemview: View):RecyclerView.ViewHolder(itemview){
        var x: TextView = itemview.findViewById(R.id.name)
        var y: TextView = itemview.findViewById(R.id.branch)
        var z: TextView = itemview.findViewById(R.id.Cate)
        var w =itemview.findViewById<TableRow>(R.id.tablerow)
        var image:ImageView =itemview.findViewById(R.id.profile_image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemview: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardforrecyclerview,parent,false)
        return view_holder(itemview)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
//        holder.x.text=urilist["bdM5FCzBf0MxqmSHREqfXXsuTDo2"].toString()
        holder.x.text="Name - "+list[(uid.get(position))]?.get("Name")
        holder.y.text="Branch - "+list[(uid.get(position))]?.get("Branch")
        holder.z.text="Category - "+list[(uid.get(position))]?.get("Category")
        Glide.with(context).load(list[uid.get(position)]?.get("Image URL")).circleCrop().into(holder.image)
        holder.w.setOnClickListener {
            val intent=Intent(it.context,full_profile::class.java)
            intent.putExtra("uid",uid.get(position))
            it.context.startActivity(intent)
        }


//            Toast.makeText(this@all_candidates,"Final Data Retrieved",Toast.LENGTH_SHORT).show()
        }



    }

