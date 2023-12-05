package com.example.curriculo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurriculumVitaeApp()
        }
    }
}

@Composable
fun CurriculumVitaeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "perfil") {
        composable("perfil") { PerfilScreen(navController) }
        composable("experiencia") { ExperienciaScreen() }
        composable("mapas") { MapasScreen() }
    }
}

@Composable
fun MapasScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val imagemMapa2Id = R.drawable.m2

    BackgroundImage {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mapas do League of Legends",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Mapa 1
            Mapa(
                nome = "Twisted Treeline",
                descricao = "\n" +
                        "Twisted Treeline é um mapa 3v3 em \"League of Legends\", com duas rotas e uma selva, projetado para partidas mais curtas e intensas em um ambiente sombrio e sinistro.",
                imagemId = R.drawable.m1
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Mapa 2
            Mapa(
                nome = "Modo Tatico",
                descricao = "Clique para dar zoom.",
                imagemId = imagemMapa2Id,
                onImageClick = { showDialog = true }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Modo Tatico Zoom") },
                text = { Image(painter = painterResource(id = imagemMapa2Id), contentDescription = null) },
                confirmButton = {}
            )
        }
    }
}

@Composable
fun Mapa(nome: String, descricao: String, imagemId: Int, onImageClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imagemId),
                contentDescription = "Imagem do $nome",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = onImageClick)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = nome,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = descricao,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}
@Composable
fun PerfilScreen(navController: NavHostController) {
    BackgroundImage {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "League of Legends",
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "\n" +
                            "Campeões no 'League of Legends' são personagens jogáveis com habilidades únicas...",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("experiencia") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5E006F))
            ) {
                Text(text = "Conheça os campeões", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("mapas") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFEB3B)) // Amarelo ovo
            ) {
                Text(text = "Mapas do LoL", color = Color.White)
            }
        }
    }
}
