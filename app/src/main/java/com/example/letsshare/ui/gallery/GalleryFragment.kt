package com.example.letsshare.ui.gallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.letsshare.R
import com.example.letsshare.books
import com.example.letsshare.notesend
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_gallery.*
import java.util.*
import kotlin.math.log

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    var imageurl: String = ""
    val adminref = FirebaseDatabase.getInstance().getReference()
    val aid = FirebaseAuth.getInstance().uid
    var pname: String = ""
    val storeref=FirebaseStorage.getInstance().getReference("/notes/")
    var fileurl:String=""
    val dataref=FirebaseDatabase.getInstance().getReference()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)


        select_frag.setOnClickListener {
            Log.d("gallary fragment", "clicked hai")
            selectPicture()
        }
        post_frag.setOnClickListener {
            postUploadDatabase()
        }
        selectpdf_frag.setOnClickListener{
            selectpdf()
        }
        postpdf_frag.setOnClickListener {
            postpdf()
        }

        return root
    }
    private fun selectpdf(){
        val selectPdf=Intent(Intent.ACTION_PICK)
        selectPdf.type="application/pdf"
        startActivityForResult(selectPdf, 1)
    }


    private fun selectPicture() {
        val selectPic = Intent(Intent.ACTION_PICK)
        selectPic.type = "image/*"
        startActivityForResult(selectPic, 0)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("admin", "photowas selected")
            var selectedPhotouri = data.data
            if (selectedPhotouri != null) {
                val fileid = UUID.randomUUID().toString()
                val imageref = FirebaseStorage.getInstance().getReference("/images/$fileid")
                imageref.putFile(selectedPhotouri)
                    .addOnSuccessListener {
                        Log.d("admin", "Uploaded image")
                        imageref.downloadUrl.addOnSuccessListener {

                            Log.d("admin", "urlis${it.toString()}")
                            imageurl = it.toString()
                        }
                    }
            }
            else if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
                    Log.d("note", "File was selected")
                    var selectedFileuri = data.data
                    if (selectedFileuri != null) {
                        val fileid = UUID.randomUUID().toString()
                        storeref.child("/$fileid").putFile(selectedFileuri)
                            .addOnSuccessListener {
                                storeref.downloadUrl.addOnSuccessListener {

                                    Log.d("admin", "urlis${it.toString()}")
                                    fileurl = it.toString()
                                    Log.d("note","the url is:$fileurl")
                                }
                            }
                    }
                }
            }
            }

    private fun postpdf() {
        val filename = titlepdf_frag.text.toString()
        Log.d("note", "the url is::::$fileurl")
        val semester1 = semesterpdf_frag.text.toString()
        if (filename.isEmpty() || semester1.isEmpty()) {
            Toast.makeText(context, "Please Fill all the information", Toast.LENGTH_SHORT).show()
            return
        } else {
            val notesen = notesend(filename, fileurl)
            dataref.child("/Notes/$semester1").setValue(notesen)
                .addOnCompleteListener {
                }
            Toast.makeText(context, "PDF Posted", Toast.LENGTH_SHORT).show()


        }
    }






private fun postUploadDatabase() {

        val Title = title_frag.text.toString()
        val Semester = semester_frag.text.toString()
        val Price = price_frag.text.toString()


        if (Title.isEmpty() || Semester.isEmpty() || Price.isEmpty()) {
            Toast.makeText(context, "Please Fill all the information", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            val adminnotice = adminref.child("/books/${Semester}")


            adminref.child("/user/$aid").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        pname = p0.child("names").getValue().toString()
                        Log.d("admin", "output name:$pname")
                        val noticepublish = books(Title, Price,Semester, imageurl, pname)
                        Log.d("admin", "output name:$pname")
                        adminnotice.push().setValue(noticepublish)
                            .addOnCompleteListener {
                                Log.d("admin", "Successfully published Notice")
                            }
                    }
                })
                Toast.makeText(context, "Notice Posted", Toast.LENGTH_SHORT).show()


            }}

    }




