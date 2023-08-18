package com.example.votingiitj

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class cat1RA(val context: Context,var list: HashMap<String,Map<String,String>>,var uid: MutableList<String>,val cati: Int):RecyclerView.Adapter<cat1RA.view_holder>() {

    class view_holder(itemview: View):RecyclerView.ViewHolder(itemview){
        var x: TextView = itemview.findViewById(R.id.name)
        var y: TextView = itemview.findViewById(R.id.branch)
        var z: TextView = itemview.findViewById(R.id.Cate)
        var w =itemview.findViewById<Button>(R.id.getprof)
        var e = itemview.findViewById<Button>(R.id.dovote)
        var image: ImageView =itemview.findViewById(R.id.profile_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cat1RA.view_holder {

        var itemview:View=LayoutInflater.from(parent.context).inflate(R.layout.cardfrocatview,parent,false)
        return view_holder(itemview)

    }

    override fun onBindViewHolder(holder: cat1RA.view_holder, position: Int) {
        holder.x.text="Name - "+list[(uid.get(position))]?.get("Name")
        holder.y.text="Branch - "+list[(uid.get(position))]?.get("Branch")
        holder.z.text="Category - "+list[(uid.get(position))]?.get("Category")
        Glide.with(context).load(list[uid.get(position)]?.get("Image URL")).circleCrop().into(holder.image)
        holder.w.setOnClickListener {
            val intent= Intent(it.context,full_profile::class.java)
            intent.putExtra("uid",uid.get(position))
            it.context.startActivity(intent)
        }
        holder.e.setOnClickListener {
            val db=Firebase.firestore
            db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("Category "+cati.toString(),uid.get(position))

            db.collection("Constestants").document(uid.get(position)).get().addOnSuccessListener {
                var x = it.data?.get("votes").toString()
                x=x+"1"
                Log.d(TAG,"${x}")
                db.collection("Constestants").document(uid.get(position)).update("votes",x)

            }
            val intent=Intent(context,voting_activity_category::class.java)
            context.startActivity(intent)
            (context as Activity).finish()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}