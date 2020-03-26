package projetos.danilo.mynotesmvvm.domain

import android.content.Context
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.sqlite.SQLiteRepository

class NotasUseCase {
    lateinit var database: SQLiteRepository

    fun initDatabase(ctx: Context){
        database = SQLiteRepository(ctx)
    }

    fun obterListaDeNotas() : List<Nota> {
        return database.getAllNotas()
    }

    fun adicionarNota(nota: Nota) {
        database.save(nota)
    }
}