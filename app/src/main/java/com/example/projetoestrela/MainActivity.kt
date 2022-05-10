package com.example.projetoestrela

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.layout_dialog.view.*
import kotlinx.android.synthetic.main.layout_opniao.*
import kotlinx.android.synthetic.main.layout_opniao.view.*

class MainActivity : AppCompatActivity() {
    val api by lazy {
        AppRetrofit()
    }
    lateinit var fechadialog: AlertDialog // fecha a caixa de dialogo anterior
    lateinit var view: View //pegar texto da opiniao
    var i: Int = 0 // armazena o contador da vez de
    var k: Int = 0 // armazena o valor de onde o usuario parou o voto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showDialog(viewBtn: View) {
        when (viewBtn.id) {//case para o Id do botões
            R.id.btnOk -> {
                abrirConfirmacao().show()
            }
            R.id.btnSim -> {
                fechadialog.cancel()
                abrirDialog().show()
            }
            R.id.btnPergunta -> {
                i++
                fechadialog.cancel()
                abrirDialog().show()
            }
            R.id.btnNao -> {
                fechadialog.cancel()
            }
            R.id.btnOpniao -> {
                fechadialog.cancel()
                salvaOpniao()
            }
        }
    }

    private fun salvaOpniao() {
        val test = view.findViewById<TextInputEditText>(R.id.textInputEditText)
        Toast.makeText(this, "sua resposta foi: ${test.text}", Toast.LENGTH_SHORT).show()
        i = 0
    }

    @SuppressLint("SetTextI18n")
    fun abrirDialog(): AlertDialog {
        val alertDialogPerguntas = AlertDialog.Builder(this)
        val inflater = layoutInflater
        view = inflater.inflate(R.layout.layout_dialog, null)
        alertDialogPerguntas.setView(view)
        Network.services(api, 449400) {
            val pergunta = it.dados
            view.textViewPergunta.text = pergunta[i].DS_PERGUNTA
            if (i != it.qtdPerguntas) {
                view.ratingBarStar.setOnRatingBarChangeListener { ratingBar, valorRating, b ->
                    view.textViewStar.text = "Seu voto foi " + valorRating.toInt()
                    k = valorRating.toInt()// em "K" vai ser armazendado o valor das estrelas
                }
            }
            if (i == it.qtdPerguntas - 1) {
                fechadialog.cancel()
                abrirOpiniao().show()
            }
        }
        fechadialog = alertDialogPerguntas.create()
        return fechadialog
    }

    fun abrirConfirmacao(): AlertDialog {
        val alertDialogConfirmation = AlertDialog.Builder(this)
        val inflater = layoutInflater
        view = inflater.inflate(R.layout.layout_confirmacao, null)
        alertDialogConfirmation.setView(view)
        fechadialog = alertDialogConfirmation.create()
        return fechadialog
    }


    fun abrirOpiniao(): AlertDialog {
        val alertDialogOpiniao = AlertDialog.Builder(this)
        val inflater = layoutInflater
        view = inflater.inflate(R.layout.layout_opniao, null)
        alertDialogOpiniao.setView(view)
        Network.services(api, 449400) {
            val pergunta = it.dados
            view.textViewOpniao.text = pergunta[i].DS_PERGUNTA
        }
        fechadialog = alertDialogOpiniao.create()
        return fechadialog
    }

}