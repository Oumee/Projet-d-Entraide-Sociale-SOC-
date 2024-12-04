import 'package:http/http.dart' as http;
import 'dart:convert';
import '../models/event.dart'; // Assurez-vous d'importer le modèle Event

class EventService {
  final String apiUrl = 'http://192.168.1.23:9994/api/event/create';

  Future<void> createEvent(Event event) async {
    // Construire le fichier JSON
    final Map<String, dynamic> eventData = {
      'name': event.name,
      'description': event.description,
      'communityName': event.communityName,
      'message': event.message,
      'sendDate': event.startTime.toIso8601String(),
      'scheduleDate': event.endTime.toIso8601String(),
      'location': {
        'x': event.coordinates.latitude,
        'y': event.coordinates.longitude,
      },
    };

    // Configuration des en-têtes
    final headers = {
      'Content-Type': 'application/json',
    };
    
    // Envoi de la requête POST
    try {
      final response = await http.post(
        Uri.parse(apiUrl),
        headers: headers, // Ajout des en-têtes ici
        body: jsonEncode(eventData),
      );
      print(eventData);

      if (response.statusCode == 201) {
        print('Événement créé avec succès');
      } else {
        print(
            'Erreur lors de la création de l\'événement : ${response.statusCode}');
        print('Message : ${response.body}');
      }
    } catch (e) {
      print('Erreur lors de la connexion au serveur : $e');
    }
  }



// get ids of events
  Future<List<int>> getIds(Event event) async {
    // Construire le fichier JSON
    final Map<String, dynamic> eventData = {
      'communityName': event.communityName,
    };

    // Configuration des en-têtes
    final headers = {
      'Content-Type': 'application/json',
    };

    // Envoi de la requête GET
    try {
      final response = await http.get(
        Uri.parse(apiUrl),
        headers: headers, // Ajout des en-têtes ici
       // body: jsonEncode(eventData), // Le corps doit être dans le bon format pour la requête GET
      );

      if (response.statusCode == 200) {  // Vérifier si la réponse est correcte (200 OK)
        print('Réponse reçue : ${response.body}');
        
        // Parser la réponse JSON
        final data = json.decode(response.body);
        
        // Supposons que la réponse soit un tableau d'IDs
        List<int> ids = List<int>.from(data); // Assurez-vous que la réponse contient bien une liste d'IDs
        
        return ids; // Retourne la liste des IDs
      } else {
        print('Erreur lors de la création de l\'événement : ${response.statusCode}');
        print('Message : ${response.body}');
        return []; // Retourne une liste vide en cas d'erreur
      }
    } catch (e) {
      print('Erreur lors de la connexion au serveur : $e');
      return []; // Retourne une liste vide en cas d'exception
    }
  }
}
