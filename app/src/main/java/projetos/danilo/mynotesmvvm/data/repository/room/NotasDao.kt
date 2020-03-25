package projetos.danilo.mynotesmvvm.data.repository.room

import androidx.room.*
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.TABLE_NOTA

@Dao
interface NotasDao {

    @Query("SELECT * FROM $TABLE_NOTA")
    fun getAllNotas(): List<Nota>

    /** Inserção de multiplas notas */
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertNotas(vararg notas: Nota)

    @Insert
    fun insertNota(nota: Nota)

    @Update
    fun updateNota(nota: Nota)

    @Delete
    fun deleteNota(nota: Nota)
}