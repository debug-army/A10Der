package com.debug_army.a10der.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.debug_army.a10der.R
import com.debug_army.a10der.databinding.FragmentSwipeBinding
import com.lorentzos.flingswipe.SwipeFlingAdapterView


class SwipeFragment : Fragment() {

    lateinit var binding: FragmentSwipeBinding
    private var userList = ArrayList<String>()
    private var arrayAdapter: ArrayAdapter<String>? = null
//    private var i = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSwipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList.add("a")
        userList.add("b")
        userList.add("c")
        userList.add("d")

        //choose your favorite adapter
        arrayAdapter =
            activity?.let { ArrayAdapter<String>(it, R.layout.item_view, R.id.helloText, userList) }

        //set the listener and the adapter
        binding.frame.adapter = arrayAdapter
        binding.frame.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun onScroll(p0: Float) {

            }

            override fun removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!")
                userList.removeAt(0)
                arrayAdapter?.notifyDataSetChanged()
            }

            override fun onLeftCardExit(dataObject: Any) {
                //Do something on the left!
                //You userListso have access to the originuserList object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(activity, "left", Toast.LENGTH_SHORT).show()
            }

            override fun onRightCardExit(dataObject: Any) {
                Toast.makeText(activity, "Right!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                //for unlimited data
//                userList.add("XML $i")
//                arrayAdapter?.notifyDataSetChanged()
//                Log.d("LIST", "notified")
//                i++
                Toast.makeText(activity, "its over", Toast.LENGTH_SHORT).show()
            }
        })
    }

}