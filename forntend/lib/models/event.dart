import 'package:latlong2/latlong.dart';

class Event {
  String name;
  String description;
  String communityName; // Nom de la communauté
  String message; // Nom de la communauté
  DateTime startTime;
  DateTime endTime;
  LatLng coordinates; // Latitude et longitude

  Event({
    required this.name,
    required this.description,
    required this.communityName,
    required this.message,
    required this.startTime,
    required this.endTime,
    required this.coordinates,
  });

  @override
  String toString() {
    return 'Event(name: $name, description: $description,  communityName: $communityName , message: $message , startTime: $startTime , endTime: $endTime, '
        'coordinates: ${coordinates.latitude}, ${coordinates.longitude})';
  }
}
