package com.example.login_assignment//package com.example.login_assignment

//import io.realm.gradle.Realm
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_assignment.databinding.ActivityUserDataBinding
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort

class UserData : AppCompatActivity() {
    private lateinit var binding: ActivityUserDataBinding
    lateinit var adapter: RealmAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

//        val spinner = findViewById<Spinner>(R.id.spinner)
        val sortingOptions = arrayOf("Sort by Name", "Sort by Age", "Sort by City")

        val spinAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sortingOptions)
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinAdapter

        binding.textView8.text = intent.getStringExtra("User Email")

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("data.realm")
            .allowWritesOnUiThread(true)
            .build()

        val realm = Realm.getInstance(config)

        val persons = listOf(
            RealmData(name = "John", age = 30, city = "New York"),

        )

        realm.executeTransaction { realmInstance ->
            realmInstance.insertOrUpdate(persons)
        }


        val da = realm.where(RealmData::class.java).findAll()

        val adapter = RealmAdapter(this, da)
        recyclerView.adapter = adapter
            Log.d("Before Spinner ","...............")

        val per = realm.where(RealmData::class.java).findAll()



        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                Log.d("Spinner Launched ","-----______------")
                val selectedItem = sortingOptions[position]

                val sortedResults = when (selectedItem) {
                    "Sort by Name" -> realm.where(RealmData::class.java).findAll().sort("name", Sort.ASCENDING)
                    "Sort by Age" -> realm.where(RealmData::class.java).findAll().sort("age", Sort.ASCENDING)
                    "Sort by City" -> realm.where(RealmData::class.java).findAll().sort("city", Sort.ASCENDING)
                    else -> realm.where(RealmData::class.java).findAll() // Default: no sorting

                }
               val pe = realm.copyFromRealm(sortedResults)
                adapter.updateData(pe)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        //used for adding more data to database on a unique primaryKey number manually

//        realm.beginTransaction()
//
//        var data = realm.createObject(RealmData::class.java,7) //primary key is already exit so when ever execute this code with the same user(database) then change its number or comment it if don't want to use it
////        data.name = "John"
////        data.age = 25
////        data.city = "Pune"
////        data.name = "Sanya"
////        data.age = 18
////        data.city = "Bhopal"
//        data.name = "Sanj"
//        data.age = 29
//        data.city = "Delhi"

//        realm.commitTransaction()
//


        // -------------------------
        //failed approaches
//        val allData = realm.where(RealmData::class.java).findAll()
//        allData.forEach { data ->
//            println("Data : ${data.name}  : ${data.age} : ${data.city}")
//        }





//                val collectionDB = StringBuilder()
//                for(data in productList){
//                    collectionDB.append(data.title)
//                }
//                binding.textView.text =collectionDB
//
//        data = realm.createObject(RealmData::class.java, 2)
//        data.name = "Sanya"
//        data.age = 18
//        data.city = "Bhopal"
//        realm.commitTransaction()
//
//        data = realm.createObject(RealmData::class.java, 3)
//        data.name = "Sunil"
//        data.age = 35
//        data.city = "Mumbai"
//        realm.commitTransaction()
//
//        data = realm.createObject(RealmData::class.java, 4)
//        data.name = "Sahil"
//        data.age = 30
//        data.city = "Indore"
//        realm.commitTransaction()

//        val config = RealmConfiguration.create(schema = setOf(RealmData::class))
//        val realm: Realm = Realm.open(config)
//
//        realm.writeBlocking {
//            copyToRealm(RealmData().apply {
//                name("John")
//                age(25)
//                city("Bhopal")
//            })
////            copyToRealm(RealmData().apply {
////                name("Sanvi")
////                age(18)
////                city("Pune")
////            })
//        }

    }


}