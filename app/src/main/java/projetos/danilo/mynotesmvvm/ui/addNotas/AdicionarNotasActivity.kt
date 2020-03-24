package projetos.danilo.mynotesmvvm.ui.addNotas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_adicionar_notas.*
import kotlinx.android.synthetic.main.include_toolbar.*
import projetos.danilo.mynotesmvvm.R
import projetos.danilo.mynotesmvvm.model.Nota
import projetos.danilo.mynotesmvvm.ui.base.BaseActivity

class AdicionarNotasActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_notas)

        configurarToolbar(toolbarPrincipal, R.string.titulo_adicao, true)

        btn_adicionar.setOnClickListener {
            Log.i("BOTAO", "clique no botao adicionar")
            val notaNova = Nota(1, et_tituloNota.text.toString(), "nota criada")

            val returnIntent = Intent()
            returnIntent.putExtra(EXTRA_TITULO, et_tituloNota.text.toString())
            setResult(Activity.RESULT_OK, returnIntent)

            finish()
        }

    }

    companion object {
        const val EXTRA_TITULO = "TITULO"
    }


}