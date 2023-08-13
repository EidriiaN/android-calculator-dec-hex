package com.example.tema3dam

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tema3dam.databinding.FragmentHistoryBinding
import com.example.tema3dam.databinding.FragmentLogsBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Incepe binding ceva
    private lateinit var binding: FragmentLogsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogsBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var sharedPreferences = requireContext().getSharedPreferences("MyLogs", Context.MODE_PRIVATE)

        var logsText = sharedPreferences.getString("logsText", "")

        var listText = binding.logsText

        listText.setText(listText.text.toString() + "\n" + logsText.toString())

//        //Calendar, get format
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
//        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
//
//        //Get curent date and time
//        val currentDate = dateFormat.format(calendar.time)
//        val currentTime = timeFormat.format(calendar.time)
//
//        //String builder to build the string with the data
//        val stringBuilder = StringBuilder()
//        stringBuilder.append(currentDate.toString(), " ", currentTime.toString())


        //Volley

//        // Creați o nouă coadă de cereri Volley
//        val requestQueue = Volley.newRequestQueue(requireContext())
//
//        // Definiți URL-ul pentru cererea HTTP
//        var base = "dec"
//        var op1 = "2"
//        var operation = "%2B"
//        var op2 = "3"
//        val url = "http://idp.upg-ploiesti.ro/z/zdemo9/calc?q=user36%20${base}%20${op1}%20${operation}%20${op2}"
//
//        // Creați o cerere de tip StringRequest
//        val stringRequest = StringRequest(
//            Request.Method.GET,
//            url,
//            { response ->
//                // Răspunsul este primit cu succes
//                // Utilizați răspunsul pentru a actualiza interfața de utilizator sau pentru a efectua alte acțiuni
//                stringBuilder.append(" Connect server: success", "\n",  response.toString().replace(".",""))
//                //Show the result
//                val logsText = binding.logsText
//                logsText.setText(stringBuilder.toString())
//
//            },
//            { error ->
//                // A apărut o eroare în timpul cererii
//                // Tratați eroarea sau afișați un mesaj de eroare
//
//                stringBuilder.append(" Connect server: failed " ,"\n" , error.message.toString())
//                //Show the result
//                val logsText = binding.logsText
//                logsText.setText(stringBuilder.toString())
//            }
//        )
//
//        // Adăugați cererea în coada de cereri Volley
//        requestQueue.add(stringRequest)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}