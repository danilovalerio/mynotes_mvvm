package projetos.danilo.mynotesmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import projetos.danilo.mynotesmvvm.data.repository.COLUMN_ID
import projetos.danilo.mynotesmvvm.data.repository.TABLE_NOTA

@Entity(tableName = TABLE_NOTA)
data class Nota (
    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = COLUMN_ID)
    var id:Long = 0,
    var titulo: String = "",
    var descricao: String = "",
    var comentario: String? = "-"
)