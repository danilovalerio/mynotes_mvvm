package projetos.danilo.mynotesmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import projetos.danilo.mynotesmvvm.model.Nota

class NotasAdapter(
    val notas: List<Nota>,
    val onItemClickListener: (nota: Nota) -> Unit
)
/**lâmbda ao inves da interface*/
    :
    RecyclerView.Adapter<NotasViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotasViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotasViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return notas.count()
    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        holder.bindView(notas[position])
    }

}