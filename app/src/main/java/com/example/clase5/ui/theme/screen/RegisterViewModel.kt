package com.example.clase5.ui.theme.screen

import androidx.lifecycle.ViewModel
import com.example.clase5.data.dao.MensajeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: MensajeDao): ViewModel(){
    //YA PUEDO USAR EL OBJETO DAO QUE INYECTE

}