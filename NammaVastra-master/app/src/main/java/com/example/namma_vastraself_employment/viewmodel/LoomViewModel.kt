package com.example.namma_vastraself_employment.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.namma_vastraself_employment.model.Saree
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class LoomViewModel : ViewModel() {
    // Session-based local list to ensure the app works even without Firebase
    private fun drawableUri(name: String): String {
        return "android.resource://com.example.namma_vastraself_employment/drawable/$name"
    }

    private val _localSarees = mutableListOf(
        Saree(
            id = "1",
            imageUrl = "https://images.unsplash.com/photo-1610030469668-935142b96fe4?q=80&w=800",
            description = "Elegant Maroon Ilkal Saree with Traditional Border",
            price = 4500.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "2",
            imageUrl = "https://images.unsplash.com/photo-1583391733956-3750e0ff4e8b?q=80&w=800",
            description = "Royal Blue Molakalmuru Silk Saree",
            price = 12000.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "3",
            imageUrl = "https://images.unsplash.com/photo-1617627143750-d86bc21e42bb?q=80&w=800",
            description = "Handwoven Mustard Gold Festive Saree",
            price = 6800.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-1",
            imageUrl = drawableUri("trend1"),
            description = "Emerald Green Silk Ilkal Saree",
            price = 5200.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-2",
            imageUrl = drawableUri("trend2"),
            description = "Magenta Banarasi Brocade Saree",
            price = 9800.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-3",
            imageUrl = drawableUri("trend3"),
            description = "Classic Mustard Gold Zari Saree",
            price = 7300.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-4",
            imageUrl = drawableUri("trend4"),
            description = "Peacock Blue Molakalmuru Saree",
            price = 11200.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-5",
            imageUrl = drawableUri("trend5"),
            description = "Crimson Red Temple Border Saree",
            price = 6900.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-6",
            imageUrl = drawableUri("trend6"),
            description = "Pastel Cotton Jamdani Saree",
            price = 4100.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-7",
            imageUrl = drawableUri("trend7"),
            description = "Midnight Indigo Ajrakh Saree",
            price = 5600.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-8",
            imageUrl = drawableUri("trend8"),
            description = "Sunrise Peach Chanderi Saree",
            price = 6400.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-9",
            imageUrl = drawableUri("trend9"),
            description = "Terracotta Checks Cotton Saree",
            price = 3800.0,
            phoneNumber = "919876543210"
        ),
        Saree(
            id = "trend-10",
            imageUrl = drawableUri("trend10"),
            description = "Royal Blue Patola Saree",
            price = 12800.0,
            phoneNumber = "919876543210"
        )
    )

    private val _sarees = MutableStateFlow<List<Saree>>(_localSarees)
    val sarees: StateFlow<List<Saree>> = _sarees

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchSarees()
    }

    fun fetchSarees() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Attempt to fetch from Firebase if configured
                val firestore = FirebaseFirestore.getInstance()
                firestore.collection("sarees").get().addOnSuccessListener { snapshot ->
                    val firebaseList = snapshot.toObjects(Saree::class.java)
                    if (firebaseList.isNotEmpty()) {
                        // Merge or replace as needed. For now, we prefer cloud data if available.
                        _sarees.value = firebaseList + _localSarees.filter { local -> 
                            firebaseList.none { it.id == local.id } 
                        }
                    }
                }
            } catch (e: Exception) {
                // If Firebase fails (e.g. no config), we already have local data in _sarees
            } finally {
                delay(1000) // Small delay for UX feel
                _isLoading.value = false
            }
        }
    }

    fun uploadSaree(imageUri: Uri, description: String, price: Double, phoneNumber: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val id = UUID.randomUUID().toString()
            
            // 1. Add to local list immediately so it shows up in Gallery for this session
            val newSaree = Saree(
                id = id,
                imageUrl = imageUri.toString(), // Local URI will work with Coil for this session
                description = description,
                price = price,
                phoneNumber = phoneNumber
            )
            _localSarees.add(0, newSaree)
            _sarees.value = _localSarees.toList()

            // 2. Attempt Background Firebase Upload
            try {
                val storage = FirebaseStorage.getInstance()
                val firestore = FirebaseFirestore.getInstance()
                
                val ref = storage.reference.child("images/$id")
                ref.putFile(imageUri).continueWithTask { task ->
                    if (!task.isSuccessful) task.exception?.let { throw it }
                    ref.downloadUrl
                }.addOnSuccessListener { downloadUrl ->
                    val uploadedSaree = newSaree.copy(imageUrl = downloadUrl.toString())
                    firestore.collection("sarees").document(id).set(uploadedSaree)
                }
            } catch (e: Exception) {
                // Continue with local success if Firebase fails
            }

            delay(1500) // Simulate network delay
            _isLoading.value = false
            onSuccess()
        }
    }
}