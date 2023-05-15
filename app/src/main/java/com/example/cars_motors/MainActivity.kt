package com.example.cars_motors

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cars_motors.controladores.UsuariosController
import com.example.cars_motors.databinding.ActivityMainBinding
import com.example.cars_motors.ui.Login.Login
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_favoritos, R.id.nav_usuarios,R.id.nav_autos, R.id.nav_colores, R.id.nav_marcas,R.id.nav_tipos
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Update user info in navigation header
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)
        val usernameTextView = headerView.findViewById<TextView>(R.id.nav_header_title)
        val userTypeTextView = headerView.findViewById<TextView>(R.id.nav_header_subtitle)

        usernameTextView.text = UsuariosController.SessionManager.getCurrentUser()?.user.toString()
        userTypeTextView.text = UsuariosController.SessionManager.getCurrentUser()?.tipo.toString()

        if (UsuariosController.SessionManager.getCurrentUser()?.tipo.toString() == "Cliente") {
            navView.menu.findItem(R.id.nav_usuarios)?.isVisible = false
            navView.menu.findItem(R.id.nav_colores)?.isVisible = false
            navView.menu.findItem(R.id.nav_marcas)?.isVisible = false
            navView.menu.findItem(R.id.nav_tipos)?.isVisible = false
            navView.menu.findItem(R.id.nav_autos)?.isVisible = false
        }else{
            navView.menu.findItem(R.id.nav_usuarios)?.isVisible = true
            navView.menu.findItem(R.id.nav_colores)?.isVisible = true
            navView.menu.findItem(R.id.nav_marcas)?.isVisible = true
            navView.menu.findItem(R.id.nav_tipos)?.isVisible = true
            navView.menu.findItem(R.id.nav_autos)?.isVisible = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val menuItem = menu.findItem(R.id.action_logout)
        menuItem.setOnMenuItemClickListener {
            UsuariosController.SessionManager.setCurrentUser(null)
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            true
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}