# 🌤️ Aplicación del Clima (AppClima) 

**AppClima** es una aplicación de Android desarrollada en Kotlin que consulta y muestra el clima actual y el pronóstico de 5 días para cualquier ciudad del mundo, utilizando una API REST. El proyecto se enfoca en una interfaz de usuario moderna, manejo asíncrono de datos y buenas prácticas de arquitectura.

## 🚀 Funcionalidades Implementadas

- **🌡️ Consulta de Clima Actual**: Permite a los usuarios buscar y ver el clima actual de una ciudad específica.
- **📅 Pronóstico de 5 Días**: Muestra el pronóstico del tiempo para los próximos 5 días en la ciudad seleccionada.
- **🎨 Interfaz de Usuario Moderna**: Diseño limpio y atractivo con:
  - Efectos de carga
  - Iconos dinámicos
  - Navegación intuitiva
- **⚡ Manejo Asíncrono de Datos**: Las llamadas a la API se gestionan con corrutinas para evitar bloqueos en la UI.

## 🏗️ Estructura del Proyecto

El proyecto sigue una arquitectura modular y organizada:
app/
├── src/main/java/com/example/appclimanueva/
│ ├── MainActivity.kt # Pantalla de clima actual
│ ├── PronosticoActivity.kt # Pantalla de pronóstico
│ ├── adapter/
│ │ └── PronosticoAdapter.kt # Adaptador para RecyclerView
│ ├── api/
│ │ ├── ClimaApiService.kt # Interfaz Retrofit
│ │ └── RetrofitClient.kt # Cliente Retrofit
│ ├── model/
│ │ ├── ClimaResponse.kt # Modelos de datos (clima actual)
│ │ └── PronosticoResponse.kt # Modelos de datos (pronóstico)
│ ├── repository/
│ │ └── ClimaRepository.kt # Lógica de capa de datos
│ └── utils/
│ └── Constants.kt # Constantes de la app
└── src/main/res/
├── drawable/ # Recursos gráficos
├── layout/ # Layouts XML
├── mipmap/ # Iconos de app
└── values/ # Recursos, colores, strings


## ⚙️ Configuración y Dependencias

Necesitarás una **API Key de OpenWeatherMap** para ejecutar esta aplicación.

### 📋 Requisitos
- Android Studio
- API Key de OpenWeatherMap

### 🔧 Pasos para configurar
1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Obtén tu API Key de [OpenWeatherMap](https://openweathermap.org/)
4. Abre `ClimaRepository.kt` y reemplaza `"TU_API_KEY"` con tu clave real
5. Sincroniza el proyecto con Gradle
6. Ejecuta la aplicación en un emulador o dispositivo

## 🤝 Contribuciones
¡Contribuciones son bienvenidas! Siéntete libre de:
- 🐛 Reportar issues
- 💡 Proponer mejoras
- 🔄 Enviar pull requests

**¡Disfruta explorando el clima con AppClima!** ☀️🌧️❄️
