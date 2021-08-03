package com.example.oop_project_fake_anki.utility
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ArrayAdapter
import com.example.oop_project_fake_anki.CardAdapter
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.ShowStackAdapter
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.classes.User
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject

class Storage(db: FirebaseFirestore, context: Context) {
    private var dataBase: FirebaseFirestore = db
    private var scope: Context = context
    var productionDefId : String = ""
    var stacks: MutableList<Stack> = mutableListOf<Stack>()

    init {
        checkForUID()
    }

    fun getStacksForAdapter(adapter: ShowStackAdapter, stacksAdapter: MutableList<Stack>) {
        val colRef = dataBase.collection("userID/${productionDefId}/stacks")
        colRef.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc != null) {
                        stacksAdapter.add(dc.document.toObject<Stack>())
                    }
                }
                adapter.notifyDataSetChanged()

            }
        })
    }

    fun getStacksForSpinner(stacks: MutableList<Stack>, adapter: ArrayAdapter<String>) {
        val colRef = dataBase.collection("userID/${productionDefId}/stacks")
        colRef.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    val doc = dc.document.toObject<Stack>();
                    stacks.add(doc)
                    adapter.add(doc.name)
                }
            }
        })
    }

    fun postStack(data: Stack) {
        val id = generateUniqueId()
        val dataToPost = hashMapOf(
            "stackId" to id,
            "name" to data.name
        )
        dataBase.collection("userID/${productionDefId}/stacks")
            .document(id.toString())
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    //************ CARDS ************\\
    fun getCardsForStackId(stackId: String, adapter: CardAdapter, cards: MutableList<DefaultCard>) {
        val colRef = dataBase.collection("userID/${productionDefId}/cards")
        colRef.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.document.toObject<Card>().stackId == stackId) {
                        cards.add(dc.document.toObject<DefaultCard>())
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun postCard(data: DefaultCard) {
        val dataToPost = hashMapOf(
            "question" to data.question,
            "answer" to data.answer,
            "stackId" to data.stackId
        )
        val randomId = generateUniqueId()
        dataBase.collection("userID/${productionDefId}/cards")
            .document(randomId.toString())
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    fun getUserData() {
        val colRef = dataBase.collection("userID/${productionDefId}/userData").document(productionDefId)
        colRef.get().addOnSuccessListener { document ->
            var userData = document.toObject<User>()
        }
        //return userData
    }

    fun generateUniqueId(): String {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var uniqueId: String = "";
        for (i in 0..20) {
            uniqueId += alphabet[(0..61).random()]
        }
        return uniqueId
    }

    // on function call use requireActivity()
    fun saveData(context : Context) {
        val id = "UID" + generateUniqueId()
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("KEY_ID", id)
        }.apply()
    }

    fun loadData(context : Context): String {
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedId: String = sharedPreferences.getString("KEY_ID", null).toString()
        return savedId
    }

    fun checkForUID() {
        if (productionDefId.isNullOrEmpty()) {
            productionDefId = loadData(scope)
            if (productionDefId.isNullOrEmpty() || productionDefId == "null") {
                saveData(scope)
            }
        }
    }
}