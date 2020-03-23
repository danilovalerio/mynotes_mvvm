package projetos.danilo.mynotesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.include_toolbar.*
import projetos.danilo.mynotesmvvm.base.BaseActivity

class NotasActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        configurarToolbar(toolbarPrincipal, R.string.titulo_minhas_notas)
    }
}
