package com.akansh.midterm.thegamesstore

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.view.ViewGroup


class ProductsAdapter(private val context: Context, private val productList: List<Products>) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    private val LAT = "Latitude"
    private val LONG = "Longitude"
    private val SELLER_NAME = "SellerName"
    private val SELLER_LOCATION = "SellerLocation"

    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView
        var productName: TextView
        var productDiscounts: TextView
        var productPrice: TextView
        var productType: TextView
        var sellerName: TextView
        var cardView: CardView

        init {
            productImage = itemView.findViewById(R.id.productImage)
            productName = itemView.findViewById(R.id.productName)
            productDiscounts = itemView.findViewById(R.id.productDiscounts)
            productPrice = itemView.findViewById(R.id.productPrice)
            productType = itemView.findViewById(R.id.productType)
            sellerName = itemView.findViewById(R.id.sellerName)
            cardView = itemView.findViewById(R.id.cardView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.products_card, parent, false)

        return ProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = productList[position]

        Glide
            .with(context)
            .load(product.getProductImagePath())
            .centerCrop()
            .placeholder(R.drawable.ic_important_devices_black_24dp)
            .into(holder.productImage)
        holder.productName.text = product.getProductName()
        holder.productType.text = product.getProductType()
        holder.sellerName.text = product.getSellerName()
        holder.productPrice.text = "$${product.getProductPrice()}"
        if (product.getProductDiscounts() != 0.0) {
            holder.productDiscounts.text = "-$${product.getProductDiscounts()}"
            holder.productDiscounts.visibility = View.VISIBLE
        } else
            holder.productDiscounts.visibility = View.INVISIBLE
        holder.cardView.setOnClickListener {
            val intent = Intent(context, ProductsInfoActivity::class.java)
            intent.putExtra(LAT, product.getSellerLocation().split(" ")[0].toDouble())
            intent.putExtra(LONG, product.getSellerLocation().split(" ")[1].toDouble())
            intent.putExtra(SELLER_NAME, product.getSellerName())
            intent.putExtra(SELLER_LOCATION, product.getSellerLocation())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
