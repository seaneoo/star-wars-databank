package edu.regis.soconnor005.starwarsdatabank.auth

import android.app.Activity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

object AuthService {
    private var auth: FirebaseAuth = Firebase.auth

    suspend fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signInGitHub(activity: Activity) {
        val provider = OAuthProvider.newBuilder("github.com").build()
        auth.startActivityForSignInWithProvider(activity, provider).await()
    }

    suspend fun forgotPassword(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    fun signOut() {
        auth.signOut()
    }
}
