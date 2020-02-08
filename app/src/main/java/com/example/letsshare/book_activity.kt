package com.example.letsshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recycbar.view.*

class book_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        val semester=intent.getStringExtra("sem")
        supportActionBar?.title="Digital Art"
        val noticemanager= androidx.recyclerview.widget.LinearLayoutManager(this)
        noticemanager.reverseLayout=true
        noticemanager.stackFromEnd=true
        book_recycler.layoutManager=noticemanager//can use this statement in xml also
        fetchdatas(semester)
//        val dataRef=FirebaseDatabase.getInstance().getReference("books/${semester}")
//        dataRef.keepSynced(true)
//
//        dataRef.addListenerForSingleValueEvent(object :ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                p0.children.forEach {
//                    val data=it.getValue(books::class.java)
//                    if (data!=null){
//                        val uname=data.uname
//                        val price=data.price
//                        val bname=data.bookname
//
//                    }
//
//                    Log.d("bookactivity","msg ${data?.uname}")
//                }
//
//            }
//
//        })
    }

    private fun fetchdatas(semes:String){
        val auth= FirebaseAuth.getInstance().uid
        val Nref=FirebaseDatabase.getInstance().getReference().child("books/${semes}")

        Nref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                val adapter= GroupAdapter<GroupieViewHolder>()
                p0.children.forEach {

                    val noticet=it.getValue(books::class.java)
                    Log.d("notice","price is:${noticet?.price}")
                    if (noticet!=null)
                    {
                        adapter.add(dataitem(noticet))
                    }
                }
                book_recycler.adapter=adapter
            }
        })  }
}

class dataitem(val noticetitle: books): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.price_view.text="Price:"+noticetitle.price+" RS."
        viewHolder.itemView.Book_title.text="Book Name:"+noticetitle.bookname
        viewHolder.itemView.poster_name.text="Uploader:"+noticetitle.uname
        Log.d("notice",noticetitle.bookname)
        viewHolder.itemView.book_view.setImageDrawable(null)
        if (noticetitle.fileurl!=""){
            Log.d("datas",noticetitle.fileurl)
            Picasso.get().load(noticetitle.fileurl).fit().into(viewHolder.itemView.book_view)

        }
        else Log.d("sffgh","failed")
    }
    override fun getLayout(): Int {
        return R.layout.recycbar
    }
}