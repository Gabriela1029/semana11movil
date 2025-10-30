package com.example.vista

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import com.example.vista.ui.theme.VistaTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            AppContent(auth, ::submitDataToFirestore)
        }
    }

    // Función para agregar datos a Firestore
    private fun submitDataToFirestore() {
        val name = "John Doe"  // Ejemplo de datos que podrías agregar
        val grade = "A"  // Ejemplo de calificación

        val studentData = hashMapOf(
            "name" to name,
            "grade" to grade,
            "timestamp" to FieldValue.serverTimestamp() // Utiliza la fecha y hora actuales
        )

        db.collection("grades")  // Aquí agregamos a la colección 'grades'
            .add(studentData)  // Agregar el documento con los datos
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}

@Composable
fun AppContent(auth: FirebaseAuth, onSubmitData: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = grade,
            onValueChange = { grade = it },
            label = { Text("Grade") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSubmitData,
            enabled = name.isNotBlank() && grade.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Data")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    VistaTheme {
        AppContent(FirebaseAuth.getInstance()) {}
    }
}

