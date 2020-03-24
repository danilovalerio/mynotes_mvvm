package projetos.danilo.mynotesmvvm.data

import projetos.danilo.mynotesmvvm.data.model.Nota

interface NotaRepository {
    fun save(nota:Nota)
    fun remove(vararg notas: Nota)
    fun hotelById(id: Long, callback: (Nota?) -> Unit)
    fun search(term: String, callback: (List<Nota>) -> Unit)
}