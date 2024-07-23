package com.glucode.about_you.about.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewProfileCardBinding
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.engineers.models.Engineer

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val REQUEST_IMAGE_PICK = 1001
    private val binding: ViewProfileCardBinding = ViewProfileCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))

        // Set an OnClickListener to pick an image from the gallery
        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (context is Activity) {
                context.startActivityForResult(intent, REQUEST_IMAGE_PICK)
            }
        }
    }

    fun setProfile(engineer: Engineer) {
        binding.profileName.text = engineer.name
        binding.profileRole.text = engineer.role
        binding.quickStatsYears.text = engineer.quickStats.years.toString()
        binding.quickStatsCoffees.text = engineer.quickStats.coffees.toString()
        binding.quickStatsBugs.text = engineer.quickStats.bugs.toString()

        // Load the profile image
        val sharedPreferences = context.getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE)
        val imageUriString = sharedPreferences.getString("profileImageUri", null)
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            binding.profileImage.setImageURI(imageUri)
        } else {
            // Load default image if no URI is saved
            binding.profileImage.setImageResource(R.drawable.ic_person)
        }
    }

    fun setProfileImage(imageUri: Uri) {
        binding.profileImage.setImageURI(imageUri)
    }

    fun saveImageUri(uri: Uri) {
        val sharedPreferences = context.getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("profileImageUri", uri.toString())
        editor.apply()

        // Update MockData
        val engineerName = (context as Activity).intent.getStringExtra("name")
        val engineer = MockData.engineers.firstOrNull { it.name == engineerName }
        engineer?.let {
            it.defaultImageName = uri.toString()
        }
    }
}
