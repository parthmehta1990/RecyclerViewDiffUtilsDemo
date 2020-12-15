package com.motion.recyclerviewdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    internal var dataSource: MutableList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        rec_view.setHasFixedSize(true)
        rec_view.layoutManager= LinearLayoutManager(this)

        var adapter= MyListAdapter(dataSource)
        rec_view.adapter=adapter

        button.setOnClickListener {
            val newData=ArrayList<String>()
            for(i in 0..2)
                newData.add(UUID.randomUUID().toString())
            adapter.insertData(newData)

            //For auto scroll
            rec_view.smoothScrollToPosition(adapter.itemCount-1)
        }

        button2.setOnClickListener {
            val newData=ArrayList<String>()
            for(i in 0..2)
                newData.add(UUID.randomUUID().toString())
            adapter.updateData(newData)


        }

    }

    private fun initData() {
       for (i in 0..1)
           dataSource.add(UUID.randomUUID().toString())
    }
}