package mrzeeshandigital.outdoor.mrzeeshantrail.data.model

import androidx.annotation.StringRes
import mrzeeshandigital.outdoor.mrzeeshantrail.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    BIKES(R.string.wqlno_category_bikes),
    FITNESS(R.string.wqlno_category_fitness),
    CAMPING(R.string.wqlno_category_camping),
    BACKPACKS(R.string.wqlno_category_backpacks),
    TRAINING(R.string.wqlno_category_training),
}
