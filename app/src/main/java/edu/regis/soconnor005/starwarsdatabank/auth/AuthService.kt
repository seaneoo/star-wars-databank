package edu.regis.soconnor005.starwarsdatabank.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

object AuthService {
    suspend fun createAccount(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun signIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun forgotPassword(email: String) {
        Firebase.auth.sendPasswordResetEmail(email).await()
    }

    fun signOut() {
        Firebase.auth.signOut()
    }
}
