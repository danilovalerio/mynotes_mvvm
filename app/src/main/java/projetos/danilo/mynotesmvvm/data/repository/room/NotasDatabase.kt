package projetos.danilo.mynotesmvvm.data.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import projetos.danilo.mynotesmvvm.data.model.Nota
import projetos.danilo.mynotesmvvm.data.repository.DATABASE_NAME
import projetos.danilo.mynotesmvvm.data.repository.DATABASE_VERSION

@Database(entities = arrayOf(Nota::class), version = DATABASE_VERSION)
abstract class NotasDatabase: RoomDatabase(){

    abstract fun notasDao(): NotasDao

    companion object{
        private var INSTANCE: NotasDatabase? = null

        fun getInstance(ctx: Context): NotasDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    ctx.applicationContext,
                    NotasDatabase::class.java,
                    DATABASE_NAME)
                    .build()
            }
            return INSTANCE as NotasDatabase
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}