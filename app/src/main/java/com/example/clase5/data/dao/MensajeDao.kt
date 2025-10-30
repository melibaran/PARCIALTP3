package com.example.clase5.data.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.clase5.domain.model.Message


//es el ACCESO A LOS DATOS DE LA BDD
@Dao
interface MensajeDao {
    @Query(value = "SELECT * FROM mensaje")
    fun getAll(): List<Message>

    @Query(value = "SELECT * FROM mensaje WHERE uid IN (:userIds)")
   fun loadAllByIds(userIds: IntArray): List<Message>


    @Insert
    fun insertAll(vararg messages: Message)

    @Delete
    fun delete(messages: Message)
}