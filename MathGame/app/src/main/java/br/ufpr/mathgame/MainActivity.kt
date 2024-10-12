package br.ufpr.mathgame


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var nivelAtual = 1
    private val nivelMax = 5
    private var pontos = 0
    private var notaFinal = 0
    private var respCorreta = 0
    private lateinit var questionLayout: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        questionLayout = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        geraNum()
        findViewById<Button>(R.id.botaoProx).setOnClickListener {
            proximo()
        }
        findViewById<Button>(R.id.botaoEnviaRes).setOnClickListener {
            correcao()
        }
        findViewById<Button>(R.id.botaoFinalizar).setOnClickListener {
            finalizar()
        }
    }

    private fun geraNum() {
        val num1 = (0..99).random()
        val num2 = (0..99).random()
        respCorreta = num1 + num2

        findViewById<TextView>(R.id.idNum1).text = num1.toString()
        findViewById<TextView>(R.id.idNum2).text = num2.toString()

        findViewById<Button>(R.id.botaoEnviaRes).visibility = View.VISIBLE

        findViewById<EditText>(R.id.idNumRes).text.clear()
        findViewById<Button>(R.id.botaoProx).visibility = View.GONE
        findViewById<Button>(R.id.botaoFinalizar).visibility = View.GONE
        findViewById<TextView>(R.id.idTextNotaFinal).visibility = View.GONE
        findViewById<TextView>(R.id.idNotaFinal).visibility = View.GONE
        findViewById<TextView>(R.id.idResCorreta).visibility = View.GONE
        questionLayout.setBackgroundColor(Color.parseColor("#B0C4DE"))
    }

    private fun proximo() {
            nivelAtual++
            geraNum()
    }

    fun correcao() {

        val respUsuario = findViewById<EditText>(R.id.idNumRes)
        if(respUsuario.length()==0){
            Toast.makeText(this,"Informe a Resposta", Toast.LENGTH_SHORT).show()
        } else{

        val resultado = findViewById<TextView>(R.id.idResCorreta)
        var result: String

        val respostaUsu = respUsuario.text.toString().toIntOrNull()

        findViewById<Button>(R.id.botaoEnviaRes).visibility = View.GONE

        if (respostaUsu == respCorreta) {
            questionLayout.setBackgroundColor(Color.GREEN)
            result = "Acertou!"
            resultado.text = result
            pontos += 20
        } else {
            questionLayout.setBackgroundColor(Color.RED)
            result = "Errou! \nA resposta correta é $respCorreta."
            resultado.text = result
        }
        resultado.visibility = View.VISIBLE

        if (nivelAtual < nivelMax)
            findViewById<Button>(R.id.botaoProx).visibility = View.VISIBLE
        else {
            findViewById<Button>(R.id.botaoFinalizar).visibility = View.VISIBLE
            findViewById<Button>(R.id.botaoProx).visibility = View.GONE
        }
        }

    }

    private fun finalizar() {

        notaFinal = pontos
        questionLayout.setBackgroundColor(Color.parseColor("#B0C4DE"))

        findViewById<TextView>(R.id.idTextResolvaAconta).visibility = View.GONE
        findViewById<TextView>(R.id.idTextMais).visibility = View.GONE
        findViewById<TextView>(R.id.idTextIgual).visibility = View.GONE
        findViewById<TextView>(R.id.idNum1).visibility = View.GONE
        findViewById<TextView>(R.id.idNum2).visibility = View.GONE
        findViewById<TextView>(R.id.idNumRes).visibility = View.GONE
        findViewById<Button>(R.id.botaoEnviaRes).visibility = View.GONE
        findViewById<Button>(R.id.botaoProx).visibility = View.GONE
        findViewById<Button>(R.id.botaoFinalizar).visibility = View.GONE
        findViewById<TextView>(R.id.idResCorreta).visibility = View.GONE
        findViewById<TextView>(R.id.idTextNotaFinal).visibility = View.VISIBLE
        findViewById<TextView>(R.id.idNotaFinal).visibility = View.VISIBLE
        findViewById<TextView>(R.id.idNotaFinal).text = notaFinal.toString()

    }

}