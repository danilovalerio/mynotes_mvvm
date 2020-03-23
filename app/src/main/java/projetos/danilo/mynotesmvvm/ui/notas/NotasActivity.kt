package projetos.danilo.mynotesmvvm.ui.notas

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_notas.*
import kotlinx.android.synthetic.main.include_toolbar.toolbarPrincipal
import projetos.danilo.mynotesmvvm.R
import projetos.danilo.mynotesmvvm.ui.base.BaseActivity
import projetos.danilo.mynotesmvvm.ui.detalhes.NotasDetalhesActivity

class NotasActivity : BaseActivity() {
    private val viewModel by lazy {
        provideNotasViewModel(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        configurarToolbar(toolbarPrincipal,
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

        viewModel.getAllNotas()
    }
}
