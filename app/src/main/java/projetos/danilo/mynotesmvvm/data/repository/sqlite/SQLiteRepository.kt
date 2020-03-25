package projetos.danilo.mynotesmvvm.data.repository.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import projetos.danilo.mynotesmvvm.data.NotaRepository
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.*

class SQLiteRepository(ctx: Context) : NotaRepository {
    private val helper = HotelSqlHelper(ctx)

    private fun insert(nota: Nota){
        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_TITULO, nota.titulo)
            put(COLUMN_DESCRICAO, nota.descricao)
            put(COLUMN_COMENTARIO, nota.comentario)
        }

        val id = db.insert(TABLE_NOTA, null, cv)
        if(id != -1L){
            nota.id = id
        }
        db.close()
    }

    private fun update(nota: Nota){
        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_ID, nota.id)
            put(COLUMN_TITULO, nota.titulo)
            put(COLUMN_DESCRICAO, nota.descricao)
            put(COLUMN_COMENTARIO, nota.comentario)
        }

        db.insertWithOnConflict(
            TABLE_NOTA,
            null,
            cv,
            SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    override fun save(nota: Nota) {
        if (nota.id == 0L){
            insert(nota)
        } else {
            update(nota)
        }
    }

    override fun remove(vararg notas: Nota) {
        val db = helper.writableDatabase
        for (nota in notas){
            db.delete(
                TABLE_NOTA,
                "$COLUMN_ID = ?",
                arrayOf(nota.id.toString())
            )
        }
        db.close()
    }

    override fun hotelById(id: Long): LiveData<Nota> {
        TODO("Not yet implemented")
    }

    override fun search(term: String): LiveData<List<Nota>> {
        TODO("Not yet implemented")
    }
}