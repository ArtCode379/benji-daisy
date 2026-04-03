package com.benjianddaisy.store.data.repository

import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.model.Product
import com.benjianddaisy.store.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Persian Heritage Rug",
            description = "Hand-knotted wool rug with a traditional medallion design. Rich jewel tones and intricate border detail, 200x300cm. A statement piece that anchors any living space.",
            category = ProductCategory.RUGS,
            price = 899.00,
            imageRes = R.drawable.product_1,
        ),
        Product(
            id = 2,
            title = "Saxony Plush Carpet",
            description = "Deep-pile saxony carpet in warm ivory. Luxuriously soft underfoot, this carpet brings warmth and comfort to bedrooms and reception rooms. Priced per square metre.",
            category = ProductCategory.CARPETS,
            price = 34.99,
            imageRes = R.drawable.product_2,
        ),
        Product(
            id = 3,
            title = "Oak Engineered Plank",
            description = "Natural oak engineered hardwood in a 190mm wide plank. Brushed and lightly oiled finish reveals the wood's natural grain. Suitable for underfloor heating.",
            category = ProductCategory.HARDWOOD,
            price = 52.99,
            imageRes = R.drawable.product_3,
        ),
        Product(
            id = 4,
            title = "Luxury Vinyl Tile",
            description = "Stone-effect luxury vinyl tile with rigid core construction. Fully waterproof and ideal for kitchens and bathrooms. Click-lock installation, 4.5mm total thickness.",
            category = ProductCategory.VINYL_FLOORING,
            price = 29.99,
            imageRes = R.drawable.product_4,
        ),
        Product(
            id = 5,
            title = "Berber Loop Carpet",
            description = "Textured loop pile carpet in heather grey. Stain-resistant fibre treatment makes this an excellent choice for busy hallways and family rooms. Priced per square metre.",
            category = ProductCategory.CARPETS,
            price = 24.99,
            imageRes = R.drawable.product_5,
        ),
        Product(
            id = 6,
            title = "Moroccan Trellis Rug",
            description = "Geometric trellis pattern in cream and warm gold. Flatweave construction with cotton backing, 160x230cm. Adds elegance to dining rooms and open-plan spaces.",
            category = ProductCategory.RUGS,
            price = 249.00,
            imageRes = R.drawable.product_6,
        ),
        Product(
            id = 7,
            title = "Herringbone Laminate",
            description = "Oak-effect herringbone laminate flooring. AC4 wear rating suitable for heavy domestic use. 12mm thickness with integrated underlay. Easy click-lock fitting.",
            category = ProductCategory.LAMINATE,
            price = 19.99,
            imageRes = R.drawable.product_7,
        ),
        Product(
            id = 8,
            title = "Sisal Natural Runner",
            description = "Natural sisal hallway runner with bound edges. Earthy texture complements both traditional and contemporary interiors. Size 80x300cm, available in custom lengths.",
            category = ProductCategory.RUGS,
            price = 89.00,
            imageRes = R.drawable.product_8,
        ),
        Product(
            id = 9,
            title = "Grasscloth Wallcovering",
            description = "Woven grasscloth wallcovering in sage green. Natural texture adds warmth and depth to walls. Each roll covers approximately 5.5 square metres.",
            category = ProductCategory.WALL_COVERINGS,
            price = 65.00,
            imageRes = R.drawable.product_9,
        ),
        Product(
            id = 10,
            title = "Underlay Premium",
            description = "12mm luxury carpet underlay for enhanced comfort and insulation. Improves the feel and longevity of your carpet investment. Priced per square metre.",
            category = ProductCategory.ACCESSORIES,
            price = 8.99,
            imageRes = R.drawable.product_10,
        ),
        Product(
            id = 11,
            title = "Shaggy Sheepskin Rug",
            description = "Genuine sheepskin rug in natural white. Deep, luxurious pile creates an inviting focal point in bedrooms and living rooms. Size 70x110cm.",
            category = ProductCategory.RUGS,
            price = 129.00,
            imageRes = R.drawable.product_11,
        ),
        Product(
            id = 12,
            title = "Twist Pile Carpet",
            description = "Heavy domestic twist pile carpet in charcoal. Tightly twisted fibres offer excellent durability and a smart, contemporary finish. Priced per square metre.",
            category = ProductCategory.CARPETS,
            price = 28.99,
            imageRes = R.drawable.product_12,
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}
