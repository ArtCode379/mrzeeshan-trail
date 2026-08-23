package mrzeeshandigital.outdoor.mrzeeshantrail.di

import androidx.room.Room
import mrzeeshandigital.outdoor.mrzeeshantrail.data.database.WQLNODatabase
import org.koin.dsl.module

private const val DB_NAME = "wqlno_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = WQLNODatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<WQLNODatabase>().cartItemDao() }

    single { get<WQLNODatabase>().orderDao() }
}