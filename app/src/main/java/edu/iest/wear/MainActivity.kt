package edu.iest.wear

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edu.iest.wear.databinding.ActivitymainBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivitymainBinding
    private  var textoSeleccionado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitymainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSaludo.text = "Hola Amiguito"
        binding.bnCambio.text = "Cambiar"


        // Es para poner la infromación con una vista base en el spinner
        // context es la vista
        // el segundo son lñas opciones
        val adaptador = ArrayAdapter.createFromResource(this, R.array.misopciones, android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item)


        binding.spOpciones.adapter = adaptador
        binding.spOpciones.onItemSelectedListener = this

        binding.bnCambio.setOnClickListener {
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atencion")
                .setMessage("Quiere enviar el saludo")
                .setCancelable(false)
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener{dialogInterface, i ->
                    //Codigo
                    binding.tvSaludo.text  = textoSeleccionado
                })
                .setNegativeButton("ño", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                    Toast.makeText(this, "una lastima :'(", Toast.LENGTH_LONG).show()
                }).show()
        }


    }

    //La vista del adaptador
    //La que se presenta y la que se vista por ocion, posicion y id
    override fun onItemSelected(vistaPadre: AdapterView<*>?, vistaRow: View?, posicion: Int, id: Long) {
        textoSeleccionado = vistaPadre?.getItemAtPosition(posicion).toString() // Para sacar el texto
        Toast.makeText(this, "Elegiste $textoSeleccionado", Toast.LENGTH_LONG).show()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}