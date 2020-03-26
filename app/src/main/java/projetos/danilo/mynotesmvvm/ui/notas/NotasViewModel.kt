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
}