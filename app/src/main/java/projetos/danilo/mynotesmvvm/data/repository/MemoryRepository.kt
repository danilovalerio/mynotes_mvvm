package projetos.danilo.mynotesmvvm.data.repository

import projetos.danilo.mynotesmvvm.data.NotaRepository
import projetos.danilo.mynotesmvvm.data.model.Nota

object MemoryRepository: NotaRepository {

    override fun insert(nota: Nota) {
        TODO("Not yet implemented")
    }

    override fun remove(vararg notas: Nota) {
        TODO("Not yet implemented")
    }

    override fun hotelById(id: Long, callback: (Nota?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun search(term: String, callback: (List<Nota>) -> Unit) {
        TODO("Not yet implemented")
    }

}