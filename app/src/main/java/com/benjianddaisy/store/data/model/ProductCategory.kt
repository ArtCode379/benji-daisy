package com.benjianddaisy.store.data.model

import androidx.annotation.StringRes
import com.benjianddaisy.store.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    CARPETS(R.string.category_carpets),
    RUGS(R.string.category_rugs),
    VINYL_FLOORING(R.string.category_vinyl_flooring),
    LAMINATE(R.string.category_laminate),
    HARDWOOD(R.string.category_hardwood),
    WALL_COVERINGS(R.string.category_wall_coverings),
    ACCESSORIES(R.string.category_accessories),
}
