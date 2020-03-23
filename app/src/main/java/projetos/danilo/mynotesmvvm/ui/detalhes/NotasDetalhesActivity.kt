package projetos.danilo.mynotesmvvm.ui.detalhes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notas_details.*
import kotlinx.android.synthetic.main.include_toolbar.*
import projetos.danilo.mynotesmvvm.R
import projetos.danilo.mynotesmvvm.ui.base.BaseActivity

class NotasDetalhesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas_details)

        configurarToolbar(toolbarPrincipal, R.string.titulo_detalhe_notas, true)

        /**valores que vem da intent*/
        tv_notaDetalheTitulo.text = intent.getStringExtra(EXTRA_TITULO)
        tv_notaDetalheDescricao.text = intent.getStringExtra(EXTRA_DESCRICAO)
        tv_notaComentario.text = intent.getStringExtra(EXTRA_COMENTARIO)
    }

    /** objeto Nota para essa activity*/
    companion object{
        private const val EXTRA_TITULO = "EXTRA_TITULO"
        private const val EXTRA_DESCRICAO = "EXTRA_DESCRICAO"
        private const val EXTRA_COMENTARIO = "EXTRA_COMENTARIO"

        fun getStartIntent(ctx: Context, titulo: String, descricao: String, comentario: String): Intent {
            /**Intent(activity de origem, activity de destino*/
            val intent = Intent(ctx, NotasDetalhesActivity::class.java)
            intent.putExtra(EXTRA_TITULO, titulo)
            intent.putExtra(EXTRA_DESCRICAO, descricao)
            intent.putExtra(EXTRA_COMENTARIO, comentario)

            return intent
        }

    }
}