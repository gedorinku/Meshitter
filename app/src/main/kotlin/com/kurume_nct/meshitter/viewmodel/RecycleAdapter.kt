package com.kurume_nct.meshitter.viewmodel

import android.content.Context
import android.graphics.drawable.AnimatedStateListDrawable
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import com.kurume_nct.meshitter.R

/**
 * Created by hanah on 7/28/2017.
 */
class PictureListAdapter(private val context : Context) : BaseAdapter(){

    //Intからuriから変えれる型にする
    var picture : List<Uri> = emptyList()

    override fun getCount(): Int = picture.size

    override fun getItem(position: Int): Any? = picture[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?) =
            (convertView as? PictureView )?: PictureView(context).apply  {
                setImageView(picture[position])
            }
}

class PictureView : FrameLayout{

    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs : AttributeSet?) : super(context,attrs)

    /*constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAtter : Int) : super(context,attrs,defStyleAtter)*/

    val pictureView : ImageView by lazy {
        findViewById(R.id.item_imageView) as ImageView
    }
    init {
        LayoutInflater.from(context).inflate(R.layout.activity_post,this)
    }

    fun setImageView(imageUri: Uri){
        this.pictureView.setImageURI(imageUri)
    }
}