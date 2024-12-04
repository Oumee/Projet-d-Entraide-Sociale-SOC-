import 'package:location/location.dart';

class LocationService {
  Future<LocationData> getCurrentLocation() async {
    var location = Location();
    try {
      return await location.getLocation();
    } catch (e) {
      throw Exception("Erreur lors de l'obtention de la localisation: $e");
    }
  }
}
