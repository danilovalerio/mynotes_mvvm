package projetos.danilo.mynotesmvvm

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_nota.view.*
import projetos.danilo.mynotesmvvm.model.Nota

class NotasViewHolder(
    itemView: View,
    private val onItemClickListener:((nota: Nota) -> Unit)
) : RecyclerView.ViewHolder(itemView) {
    val titulo = itemView.tv_tituloNota
    val descricao = itemView.tv_descricaoNota
    val comentarios = itemView.tv_comentarioNota

    fun bindView(nota: Nota){
        titulo.text = nota.titulo
        descricao.text = nota.descricao
        comentarios.text = nota.comentario
    }
}