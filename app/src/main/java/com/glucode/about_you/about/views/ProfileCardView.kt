package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewProfileCardBinding
import com.glucode.about_you.engineers.models.Engineer

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ViewProfileCardBinding = ViewProfileCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
        setOnClickListener {
            // Open gallery to select a new profile image
            // (You need to implement this part with an Intent and handle result)
        }
    }

    fun setProfile(engineer: Engineer) {
        binding.profileName.text = engineer.name
        binding.profileRole.text = engineer.role
        binding.quickStatsYears.text = engineer.quickStats.years.toString()
        binding.quickStatsCoffees.text = engineer.quickStats.coffees.toString()
        binding.quickStatsBugs.text = engineer.quickStats.bugs.toString()
        // Load the profile image using Glide or any other image loading library
       /* Glide.with(context)
            .load(engineer.defaultImageName)
            .placeholder(R.drawable.ic_person) // Add a placeholder image if needed
            .into(binding.profileImage)*/
    }
}
