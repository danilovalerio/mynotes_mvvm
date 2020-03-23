package projetos.danilo.mynotesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_notas.*
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.include_toolbar.toolbarPrincipal
import projetos.danilo.mynotesmvvm.base.BaseActivity

class NotasActivity : BaseActivity() {
    private val viewModel by lazy { provideNotasViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        configurarToolbar(toolbarPrincipal, R.string.titulo_minhas_notas)

        viewModel.notasLiveData.observe(this, Observer {
            it?.let { notas ->
                with(recyclerNotas) {
                    layoutManager = LinearLayoutManager(
                        this@NotasActivity, //chama o contexto de NotasActicity
                        RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)


                }
            }
        })

        Log.i("TESTE 1", viewModel.getAllNotas().toString())
    }
}
