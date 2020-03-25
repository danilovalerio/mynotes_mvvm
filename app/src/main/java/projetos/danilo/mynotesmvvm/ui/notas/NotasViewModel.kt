package projetos.danilo.mynotesmvvm.ui.notas

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.sqlite.SQLiteRepository

class NotasViewModel : ViewModel() {

    lateinit var database: SQLiteRepository

//    var notas: MutableList<Nota> = mutableListOf()
    val notasLiveData: MutableLiveData<List<Nota>> = MutableLiveData()

    fun initDatabase(ctx: Context){
        database = SQLiteRepository(ctx)
    }

    fun getAllNotas() {
        notasLiveData.value = database.getAllNotas()
    }

    //todo: Adicionar nota a partir do que foi informado pelo usuário
    fun addNota(nota: Nota) {
        database.save(nota)
        notasLiveData.value = database.getAllNotas()
    }


    //todo: Remover quando concluir
//    fun createFakeNotas() {
//        notas.add(Nota(1, "Nota 1", "Comprar item 1", null))
//        notas.add(Nota(2, "Nota 2", "Comprar item 2", "Com comentário de teste 2"))
//        notas.add(Nota(3, "Nota 3", "Comprar item 3", null))
//        notas.add(Nota(4, "Nota 4", "Comprar item 4", "Com comentário de teste 4"))
//        notas.add(Nota(5, "Nota 5", "Comprar item 5", null))
//        notas.add(Nota(6, "Nota 6", "Comprar item 6", "Com comentário de teste 6"))
//    }

}