package com.example.tema3dam

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import com.example.tema3dam.databinding.FragmentEmailBinding
import com.example.tema3dam.databinding.FragmentHistoryBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EmailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmailFragment : Fragment() {
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

    //Binding
    private lateinit var binding: FragmentEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var MyDBHelper = MyDBHelper(this)
        var itemList = MyDBHelper.readData()

        var array = mutableListOf<String>()


        val adapter = ArrayAdapter(context as Context, android.R.layout.simple_list_item_1, array)

        val listView = binding.emailListView

        adapter.addAll(itemList)
        adapter.notifyDataSetChanged()

        listView.setAdapter(adapter)

        var sendButton = binding.send

        sendButton.setOnClickListener {

            var user = binding.email.text.toString()
            var subiect = binding.subject.text.toString()
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"


            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,subiect)
            emailIntent.putExtra(Intent.EXTRA_TEXT,array.toString())
            try {
                startActivity(Intent.createChooser(emailIntent, "Send email..."))
            }catch (e: Exception){
                Log.d("Email", "Nu s-a putut trimite")
            }

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RewardsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}