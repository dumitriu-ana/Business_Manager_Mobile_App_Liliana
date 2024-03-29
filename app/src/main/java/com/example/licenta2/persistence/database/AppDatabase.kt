package com.example.licenta2.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.licenta2.persistence.dao.ClientDao
import com.example.licenta2.persistence.dao.FacturaDao
import com.example.licenta2.persistence.dao.IncasareDao
import com.example.licenta2.persistence.dao.ProdusDao
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.Incasare
import com.example.licenta2.persistence.entities.Produs
import com.example.licenta2.ui.produse.adaugare_produse.AdaugaProdus


@Database(entities = [Produs::class, Client::class, Factura::class, Incasare::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun clientDao():ClientDao
    abstract fun produsDao(): ProdusDao
    abstract fun facturaDao():FacturaDao
    abstract fun incasareDao():IncasareDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
