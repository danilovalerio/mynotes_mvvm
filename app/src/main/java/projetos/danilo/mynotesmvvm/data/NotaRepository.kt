package projetos.danilo.mynotesmvvm.data

import androidx.lifecycle.LiveData
import projetos.danilo.mynotesmvvm.data.model.Nota

interface NotaRepository {
    fun save(nota:Nota)
    fun remove(vararg notas: Nota)
    fun notaById(id: Long) : LiveData<Nota>
    fun search(term: String): LiveData <List<Nota>>
}