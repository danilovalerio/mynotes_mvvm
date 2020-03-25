package projetos.danilo.mynotesmvvm.ui.notas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_notas.*
import kotlinx.android.synthetic.main.include_toolbar.toolbarPrincipal
import projetos.danilo.mynotesmvvm.R
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.sqlite.SQLiteRepository
import projetos.danilo.mynotesmvvm.ui.addNotas.AdicionarNotasActivity
import projetos.danilo.mynotesmvvm.ui.addNotas.AdicionarNotasActivity.Companion.EXTRA_TITULO
import projetos.danilo.mynotesmvvm.ui.base.BaseActivity
import projetos.danilo.mynotesmvvm.ui.detalhes.NotasDetalhesActivity

class NotasActivity : BaseActivity() {
    private val viewModel by lazy {
        provideNotasViewModel(
            this
        )
    }

    var database = SQLiteRepository(this)

    val ACTIVITY_ADICIONAR_NOTA_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        configurarToolbar(
            toolbarPrincipal,
            R.string.titulo_minhas_notas
        )

        viewModel.notasLiveData.observe(this, Observer {
            it?.let { notas ->
                with(recyclerNotas) {
                    layoutManager = LinearLayoutManager(
                        this@NotasActivity, //chama o contexto de NotasActicity
                        RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)

                    adapter =
                        NotasAdapter(notas) { nota ->
                            val intent = NotasDetalhesActivity.getStartIntent(
                                this@NotasActivity,
                                nota.titulo,
                                nota.descricao,
                                nota.comentario ?: " - "
                            )

                            this@NotasActivity.startActivity(intent)
                        }
                }
            }
        })

        //todo: Capturar clique do botão
        btn_adicionar_nota.setOnClickListener {
            val intent = Intent(this, AdicionarNotasActivity::class.java)
            startActivityForResult(intent, ACTIVITY_ADICIONAR_NOTA_REQUEST)
        }

        viewModel.getAllNotas()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_ADICIONAR_NOTA_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                val resultado = data?.getStringExtra(EXTRA_TITULO) ?: "-"
                val notaNova = Nota(1, resultado, "nota criada")

                database.save(notaNova)
//                viewModel.addNota(notaNova)
            }
        }
    }
}
