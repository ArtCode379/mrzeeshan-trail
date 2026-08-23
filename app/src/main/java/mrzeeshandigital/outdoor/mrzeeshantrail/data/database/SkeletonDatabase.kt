package mrzeeshandigital.outdoor.mrzeeshantrail.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mrzeeshandigital.outdoor.mrzeeshantrail.data.dao.CartItemDao
import mrzeeshandigital.outdoor.mrzeeshantrail.data.dao.OrderDao
import mrzeeshandigital.outdoor.mrzeeshantrail.data.database.converter.Converters
import mrzeeshandigital.outdoor.mrzeeshantrail.data.entity.CartItemEntity
import mrzeeshandigital.outdoor.mrzeeshantrail.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WQLNODatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}