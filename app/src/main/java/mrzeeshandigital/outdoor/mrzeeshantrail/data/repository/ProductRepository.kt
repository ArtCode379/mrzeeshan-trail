package mrzeeshandigital.outdoor.mrzeeshantrail.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import mrzeeshandigital.outdoor.mrzeeshantrail.data.model.Product
import mrzeeshandigital.outdoor.mrzeeshantrail.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Summit Trail Bike", "A lightweight 18-speed hardtail with responsive disc brakes for woodland paths.",
            ProductCategory.BIKES, 649.0, "https://images.unsplash.com/photo-1576435728678-68d0fbf94e91?w=1200",
        ),
        Product(
            2, "Ridge Carbon Helmet", "Ventilated trail protection with an adjustable fit cradle and comfort lining.",
            ProductCategory.BIKES, 74.0, "https://images.unsplash.com/photo-1557803175-2c1a2e8c4e34?w=1200",
        ),
        Product(
            3, "Northstar 3 Tent", "Weather-ready three-person tent with a quick-pitch frame and taped seams.",
            ProductCategory.CAMPING, 189.0, "https://images.unsplash.com/photo-1504280390367-361c6d9f38f4?w=1200",
        ),
        Product(
            4, "Alpine Sleep System", "Warm, packable sleeping bag rated for cool spring and autumn nights.",
            ProductCategory.CAMPING, 96.0, "https://images.unsplash.com/photo-1504851149312-7a075b496cc7?w=1200",
        ),
        Product(
            5, "Traverse 45 Backpack", "Balanced hiking pack with breathable back support and rain cover.",
            ProductCategory.BACKPACKS, 119.0, "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=1200",
        ),
        Product(
            6, "Daybreak 22 Pack", "Compact daypack with hydration sleeve and easy-access trail pockets.",
            ProductCategory.BACKPACKS, 62.0, "https://images.unsplash.com/photo-1622260614153-03223fb72052?w=1200",
        ),
        Product(
            7, "FlexPro Dumbbell Set", "Space-saving adjustable dumbbells for strength sessions at home.",
            ProductCategory.FITNESS, 149.0, "https://images.unsplash.com/photo-1638536532686-d610adfc8e5c?w=1200",
        ),
        Product(
            8, "Balance Cork Mat", "Supportive natural-cork exercise mat with a grippy, easy-clean surface.",
            ProductCategory.FITNESS, 42.0, "https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f?w=1200",
        ),
        Product(
            9, "Stride Indoor Trainer", "Quiet magnetic resistance trainer with eight levels for year-round cycling.",
            ProductCategory.TRAINING, 229.0, "https://images.unsplash.com/photo-1591741535018-d042766c62eb?w=1200",
        ),
        Product(
            10, "PowerLoop Bands", "Five durable resistance bands for mobility and progressive workouts.",
            ProductCategory.TRAINING, 28.0, "https://images.unsplash.com/photo-1598289431512-b97b0917affc?w=1200",
        ),
        Product(
            11, "Ember Camp Stove", "Compact efficient burner with stable pot supports and precise flame control.",
            ProductCategory.CAMPING, 54.0, "https://images.unsplash.com/photo-1523987355523-c7b5b0dd90a7?w=1200",
        ),
        Product(
            12, "Trail Steel Bottle", "Insulated one-litre bottle that keeps drinks cold throughout a long hike.",
            ProductCategory.BACKPACKS, 31.0, "https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
