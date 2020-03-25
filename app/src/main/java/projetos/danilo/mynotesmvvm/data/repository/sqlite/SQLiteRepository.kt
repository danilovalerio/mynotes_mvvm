package projetos.danilo.mynotesmvvm.data.repository.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import projetos.danilo.mynotesmvvm.data.NotaRepository
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.*

class SQLiteRepository(ctx: Context) : NotaRepository {
    private val helper = NotaSqlHelper(ctx)
    var notasMutableList: MutableList<Nota> = mutableListOf()
    lateinit var notaLiveData: MutableLiveData<Nota>
    lateinit var notasLiveData: MutableLiveData<List<Nota>>

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

    override fun notaById(id: Long): LiveData<Nota> {
        val sql = "SELECT * FROM $TABLE_NOTA WHERE $COLUMN_ID = ?"
        val db = helper.readableDatabase

        val cursor = db.rawQuery(sql, arrayOf(id.toString()))
        val hotel = if(cursor.moveToNext()) notaFromCursor(cursor) else null

        notaLiveData.value = hotel

        return notaLiveData
    }

    override fun search(term: String): LiveData<List<Nota>> {
        var sql = "SELECT * FROM $TABLE_NOTA"
        var args: Array<String>? = null
        if(term.isNotEmpty()){
            sql += "WHERE $COLUMN_TITULO LIKE ?"
            args = arrayOf("%$term%")
        }
        sql += "ORDER BY $COLUMN_TITULO"
        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)
        val notas = ArrayList<Nota>()

        while (cursor.moveToNext()){
            val nota = notaFromCursor(cursor)
            notas.add(nota)
        }

        cursor.close()
        db.close()
        notasLiveData.value = notas
        return notasLiveData
    }

    fun getAllNotas(): MutableList<Nota> {
        if(notasMutableList.size > 0) notasMutableList.clear()

        val sql = "SELECT * FROM $TABLE_NOTA"
        val db = helper.readableDatabase

        val cursor = db.rawQuery(sql, null)

        while (cursor.moveToNext()){
            val nota = notaFromCursor(cursor)
            notasMutableList.add(nota)
        }

        cursor.close()
        db.close()

        return notasMutableList
    }

    private fun notaFromCursor(cursor: Cursor): Nota {
        val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
        val titulo = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO))
        val descricao = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO))
        val comentario = cursor.getString(cursor.getColumnIndex(COLUMN_COMENTARIO))

        return Nota(id, titulo, descricao, comentario)
    }
}