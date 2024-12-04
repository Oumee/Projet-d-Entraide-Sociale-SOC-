import 'package:flutter/material.dart';
import 'map_screen.dart'; // Importer le fichier d'écran de la carte

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // Naviguer vers la page de création d'événement
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => MapScreen()),
            );
          },
          child: const Text("Créer un événement"),
        ),
      ),
    );
  }
}
