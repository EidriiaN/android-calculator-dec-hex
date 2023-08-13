package com.example.tema3dam

import android.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tema3dam.databinding.FragmentHistoryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    //Incepe binding ceva
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.ImageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

    //termin binding ceva

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var MyDBHelper = MyDBHelper(this)
        var itemList = MyDBHelper.readData()

        var array = mutableListOf<String>()

        val adapter = ArrayAdapter(context as Context, android.R.layout.simple_list_item_1, array)

        val listView = binding.listView

        adapter.addAll(itemList)
        adapter.notifyDataSetChanged()

        listView.setAdapter(adapter)

        listView.onItemClickListener =
            AdapterView.OnItemClickListener{parent, view, position, id ->
                val itemValue = listView.getItemAtPosition(position) as String

                Toast.makeText(context, "Position :$position\nItem Value : $itemValue", Toast.LENGTH_SHORT).show()
            }


        //Clear data base

        var clearButton = binding.clearDataBase

        clearButton.setOnClickListener {

            MyDBHelper.clearDatabase()

            adapter.clear()
//            adapter.addAll()

            adapter.notifyDataSetChanged()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}