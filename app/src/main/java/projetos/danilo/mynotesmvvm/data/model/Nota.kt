package projetos.danilo.mynotesmvvm.data.model

data class Nota (
    val id: Long,
    val titulo: String,
    val descricao: String,
    val comentario: String? = "-"
)