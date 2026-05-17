package com.example.namma_vastraself_employment.data

import android.content.Context
import android.util.Patterns
import org.json.JSONArray
import org.json.JSONObject

private const val AUTH_FILE_NAME = "local_auth_db.json"

object LocalAuthStore {

    fun isLoggedIn(context: Context): Boolean {
        val db = readDb(context)
        return !db.currentUserEmail.isNullOrBlank()
    }

    fun signIn(context: Context, email: String, password: String): String? {
        val db = readDb(context)
        val user = db.users.firstOrNull { it.email.equals(email, ignoreCase = true) }
            ?: return "Account not found. Please sign up first."

        if (user.password != password) {
            return "Invalid password. Please try again."
        }

        db.currentUserEmail = user.email
        writeDb(context, db)
        return null
    }

    fun signUp(context: Context, email: String, password: String): String? {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Please enter a valid email address."
        }

        if (password.length < 6) {
            return "Password must be at least 6 characters."
        }

        val db = readDb(context)
        val existing = db.users.any { it.email.equals(email, ignoreCase = true) }
        if (existing) {
            return "This email is already registered. Please login."
        }

        db.users.add(UserRecord(email = email, password = password))
        db.currentUserEmail = email
        writeDb(context, db)
        return null
    }

    fun signOut(context: Context) {
        val db = readDb(context)
        db.currentUserEmail = null
        writeDb(context, db)
    }

    private fun readDb(context: Context): AuthDb {
        return try {
            val file = context.filesDir.resolve(AUTH_FILE_NAME)
            if (!file.exists()) {
                return AuthDb(mutableListOf(), null)
            }

            val text = file.readText()
            if (text.isBlank()) {
                return AuthDb(mutableListOf(), null)
            }

            val root = JSONObject(text)
            val usersJson = root.optJSONArray("users") ?: JSONArray()
            val users = mutableListOf<UserRecord>()

            for (index in 0 until usersJson.length()) {
                val item = usersJson.optJSONObject(index) ?: continue
                val email = item.optString("email", "")
                val password = item.optString("password", "")
                if (email.isNotBlank() && password.isNotBlank()) {
                    users.add(UserRecord(email = email, password = password))
                }
            }

            val current = root.optString("currentUserEmail", "").ifBlank { null }
            AuthDb(users = users, currentUserEmail = current)
        } catch (_: Exception) {
            AuthDb(mutableListOf(), null)
        }
    }

    private fun writeDb(context: Context, db: AuthDb) {
        val usersJson = JSONArray()
        db.users.forEach { user ->
            usersJson.put(
                JSONObject().apply {
                    put("email", user.email)
                    put("password", user.password)
                }
            )
        }

        val root = JSONObject().apply {
            put("users", usersJson)
            put("currentUserEmail", db.currentUserEmail ?: "")
        }

        context.filesDir.resolve(AUTH_FILE_NAME).writeText(root.toString())
    }
}

private data class AuthDb(
    val users: MutableList<UserRecord>,
    var currentUserEmail: String?
)

private data class UserRecord(
    val email: String,
    val password: String
)
