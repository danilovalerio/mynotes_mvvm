package projetos.danilo.mynotesmvvm.ui.notas

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

fun provideNotasViewModel(activity: AppCompatActivity) : NotasViewModel {
    return ViewModelProviders.of(
        activity
    ).get(NotasViewModel::class.java)
}