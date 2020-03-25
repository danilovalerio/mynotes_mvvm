package projetos.danilo.mynotesmvvm.data.model

data class Nota (
    var id:Long = 0,
    var titulo: String = "",
    var descricao: String = "",
    var comentario: String? = "-"
)