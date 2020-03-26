package projetos.danilo.mynotesmvvm.ui.notas

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.sqlite.SQLiteRepository
import projetos.danilo.mynotesmvvm.domain.NotasUseCase

class NotasViewModel : ViewModel() {

    val notasUseCase = NotasUseCase()
    val notasLiveData: MutableLiveData<List<Nota>> = MutableLiveData()

    fun initDatabase(ctx: Context){
        notasUseCase.initDatabase(ctx)
    }

    //Podemos usar couroutines para solicitar informação assíncrona (Async)
    fun getListaNotas(){
        setListaNotas(notasUseCase.obterListaDeNotas())
    }

    fun setListaNotas(listaNotas:List<Nota>){
        notasLiveData.value = listaNotas
    }

    fun adicionarNota(nota: Nota){
        notasUseCase.adicionarNota(nota)
        getListaNotas()
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