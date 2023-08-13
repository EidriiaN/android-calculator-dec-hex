package com.example.tema3dam

import android.content.Context
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tema3dam.databinding.FragmentHomeBinding
import java.util.*
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    //
    private lateinit var binding: FragmentHomeBinding
    private var myData: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //binding.IvHome.setOnClickListener {

        //    findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        //}
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DB

        val MyDBHelper = MyDBHelper(this)

        //Layout input tip TextView
        val input1 = binding.input1
        val input2 = binding.input2
        var baseDEC = true
        var operation = 0
        var number1 = 0
        var number2 = 0
        var rezultat = 0


        //Butoane
        val dec_hex = binding.decHex
        val button0 = binding.button0
        val button1 = binding.button4
        val button2 = binding.button5
        val button3 = binding.button6
        val button4 = binding.button7
        val button5 = binding.button8
        val button6 = binding.button9
        val button7 = binding.button10
        val button8 = binding.button11
        val button9 = binding.button12
        val buttonA = binding.button13
        val buttonB = binding.button14
        val buttonC = binding.button15
        val buttonD = binding.button16
        val buttonE = binding.button17
        val buttonF = binding.button18


        //Vector de butoane
        val array = arrayOf(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            buttonA,
            buttonB,
            buttonC,
            buttonD,
            buttonE,
            buttonF
        )


        //Toggle deca - hexa
        var areButtonsEnable = true
        var areButtonsDisable = false

        //Default caractere sunt in baza 10
        initButtonsState(array, areButtonsEnable, areButtonsDisable)

        //La apasarea butonului 10/16 schimbal ce numere folosim
        dec_hex.setOnClickListener {

            initButtonsState(array, !areButtonsEnable, !areButtonsDisable)
            areButtonsEnable = !areButtonsEnable
            areButtonsDisable = !areButtonsDisable
            baseDEC = !baseDEC
        }

        //Verificam daca campul input1 are focus, daca da putem scrie in el
        input1.setOnFocusChangeListener { _, hasFocus ->

            if (hasFocus) {
                //La apasarea butoanelor textul de pe buton se trimite in input
                for (i in array) {

                    i.setOnClickListener {

                        input1.setText(input1.text.toString() + i.text)
                    }
                }
            }
        }

        //Default campul input1 are focus initial
        input1.requestFocus()

        //Verificam in prealabil daca campul input2 are focus
        input2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                //La apasarea butoanelor textul de pe buton se trimite in input
                for (i in array) {

                    i.setOnClickListener {

                        input2.setText(input2.text.toString() + i.text)
                    }
                }
            }
        }

        //Buton plus
        val plu = binding.buttonPLU

        plu.setOnClickListener {

            if (isNumberInInput(input1)){

                Toast.makeText(context,"No number inserted", Toast.LENGTH_SHORT).show()
            }
            else{

                operation = 1
                if (baseDEC){
                    number1 = input1.text.toString().toInt()

                }
                else{
                    number1 = input1.text.toString().toInt(16)
                }
                input1.setText(input1.text.toString() + "+")
                //Schimbam focusul pe campul input2 atunci cand am terminat cu campul input1
                input2.requestFocus()
            }
        }

        val min = binding.buttonMIN

        min.setOnClickListener {

            if (isNumberInInput(input1)){

                Toast.makeText(context,"No number inserted", Toast.LENGTH_SHORT).show()
            }
            else {

                operation = 2
                if (baseDEC){
                    number1 = input1.text.toString().toInt()

                }
                else{
                    number1 = input1.text.toString().toInt(16)
                }
                input1.setText(input1.text.toString() + "-")
                input2.requestFocus()
            }
        }

        val multi = binding.buttonMULTI

        multi.setOnClickListener {

            if (isNumberInInput(input1)){

                Toast.makeText(context,"No number inserted", Toast.LENGTH_SHORT).show()
            }
            else {
                operation = 3
                if (baseDEC){
                    number1 = input1.text.toString().toInt()

                }
                else{
                    number1 = input1.text.toString().toInt(16)
                }
                input1.setText(input1.text.toString() + "*")
                input2.requestFocus()
            }
        }


        //Buton trimitere
        val buttonOK = binding.buttonok


        //La apasarea butonului OK textul introdus se transmite in istoric
        buttonOK.setOnClickListener {

            if (isNumberInInput(input2)){

                Toast.makeText(context,"No number inserted", Toast.LENGTH_SHORT).show()
            }
            else {

                if (baseDEC){
                    number2 = input2.text.toString().toInt()
                }
                else{
                    number2 = input2.text.toString().toInt(16)
                }


                when (operation) {
                    1 -> rezultat = number1 + number2
                    2 -> rezultat = number1 - number2
                    3 -> rezultat = number1 * number2
                }

//            output =
//                output + input1.text.toString() + input2.text.toString() + "\n" + "=" + rezultat.toString() + "\n"

                if (baseDEC){

                    MyDBHelper.insertData(input1.text.toString() + input2.text.toString() + "\n" + "=" + rezultat.toString())
                }
                else{
                    MyDBHelper.insertData(input1.text.toString() + input2.text.toString() + "\n" + "=" + rezultat.toString(16))
                }


                val sharedPreferences = requireContext().getSharedPreferences("MyLogs", Context.MODE_PRIVATE)


                insertInLogs(sharedPreferences,number1,number2, operation, baseDEC)

                input1.setText("")
                input2.setText("")
                input1.requestFocus()
                /*
           if (!input1.text.isEmpty()){

               multiLine.setText(multiLine.text.toString() + input1.text.toString()+"\n")
               multiLine.setSelection(multiLine.length())//placing cursor at the end of the text
               input1.setText("")
           }
           */


                //baza 16
            }
        }

        //Buton stergere ultim caracter
        val buttonDEL = binding.button39

        //La apasarea butonului ultimul caracter se sterge
        buttonDEL.setOnClickListener {

            input1.setText(input1.text.dropLast(1))
        }

        //Buton delete all
        val buttonDELETE = binding.buttonC

        //La apasarea butonului tot ce am introdus se sterge
        buttonDELETE.setOnClickListener {

            input1.setText("")
        }

        //Switch pentru tema de noapte(dark mode)

        val Toggle = binding.switch1
        Toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            //Toast.makeText(context, isChecked.toString(), Toast.LENGTH_LONG).show()
            if (isChecked == true) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Salvarea datelor în Bundle
        outState.putString("my_data", myData)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // Restabilirea datelor salvate

        if (savedInstanceState != null) {
            myData = savedInstanceState.getString("my_data", "")
            // Restabiliți și alte variabile aici, dacă există
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initButtonsState(buttonArray: Array<Button>, enable: Boolean, disable: Boolean) {

        /*for (i in 0..9){

            buttonArray[i].isEnabled = enable
        }
        */
        for (i in 10..15) {
            buttonArray[i].isEnabled = disable
        }
    }

    fun isNumberInInput(input1:TextView): Boolean{

        val numberVerify = input1.text.toString()
        if (numberVerify == ""){

            return true
        }
        return false
    }

    fun insertInLogs(share: SharedPreferences, nr1: Int, nr2: Int, op: Int, baseDEC: Boolean){

        //Calendar, get format
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        //Get curent date and time
        val currentDate = dateFormat.format(calendar.time)
        val currentTime = timeFormat.format(calendar.time)

        //String builder to build the string with the data
        val stringBuilder = StringBuilder()
        stringBuilder.append(currentDate.toString(), " ", currentTime.toString())


        //Volley

        // Creați o nouă coadă de cereri Volley
        val requestQueue = Volley.newRequestQueue(requireContext())

        // Definiți URL-ul pentru cererea HTTP
        var base = when(baseDEC){
            true -> "dec"
            false -> "hex"
            else -> ""
        }
        var op1 = nr1
        var operation = when(op){
            1 -> "%2B"
            2 -> "%2D"
            3 -> "%2A"
            else -> ""
        }
        var op2 = nr2
        val url = "http://idp.upg-ploiesti.ro/z/zdemo9/calc?q=user36%20${base}%20${op1}%20${operation}%20${op2}"

        // Creați o cerere de tip StringRequest
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                // Răspunsul este primit cu succes
                // Utilizați răspunsul pentru a actualiza interfața de utilizator sau pentru a efectua alte acțiuni
                stringBuilder.append(" Connect server: success", "\n",  response.toString().replace(".",""))
                //Show the result
//
                val editor = share.edit()
                editor.putString("logsText", stringBuilder.toString())
                editor.apply()

            },
            { error ->
                // A apărut o eroare în timpul cererii
                // Tratați eroarea sau afișați un mesaj de eroare

                stringBuilder.append(" Connect server: failed " ,"\n" , error.message.toString())
                //Show the result
                val editor = share.edit()
                editor.putString("logsText", stringBuilder.toString())
                editor.apply()
            }
        )

        // Adăugați cererea în coada de cereri Volley
        requestQueue.add(stringRequest)

    }
}