import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:location/location.dart';
import '../services/location_service.dart';
import '../services/route_service.dart';
import '../services/event_service.dart';
import '../models/event.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  final MapController mapController = MapController();
  LocationData? currentLocation;
  List<LatLng> routePoints = [];
  List<Marker> markers = [];
  final LocationService locationService = LocationService();
  final RouteService routeService = RouteService();
  final EventService eventService = EventService();

  @override
  void initState() {
    super.initState();
    _getCurrentLocation();
  }

  Future<void> _getCurrentLocation() async {
    var userLocation = await locationService.getCurrentLocation();
    setState(() {
      currentLocation = userLocation;
      markers.add(
        Marker(
          width: 80.0,
          height: 80.0,
          point: LatLng(userLocation.latitude!, userLocation.longitude!),
          child: const Icon(Icons.my_location, color: Colors.blue, size: 40.0),
        ),
      );
    });
  }

  void _addDestinationMarker(LatLng point) async {
    // Ajouter un marqueur temporaire
    Marker temporaryMarker = Marker(
      width: 80.0,
      height: 80.0,
      point: point,
      // Utilisation du paramètre `child` au lieu de `builder`
      
        child: const Icon(Icons.location_on, color: Colors.red, size: 40.0),
      
    );

    setState(() {
      markers.add(temporaryMarker);
    });

    // Afficher le dialogue de création d'événement
    bool isCancelled = await _showCreateEventDialog(point);

    // Supprimer le marqueur temporaire si annulé
    if (isCancelled) {
      setState(() {
        markers.remove(temporaryMarker);
      });
    }
  }


// formulaire SUPP OU MODIFICATION

// formulaire de creattion
  Future<bool> _showCreateEventDialog(LatLng location) async {
    final TextEditingController nameController = TextEditingController();
    final TextEditingController descriptionController = TextEditingController();
    final TextEditingController communityNameController =
        TextEditingController();
    final TextEditingController messageController = TextEditingController();
    DateTime? startDate;
    DateTime? endDate;

    return await showDialog<bool>(
          context: context,
          builder: (context) {
            return AlertDialog(
              title: const Text('Créer un événement'),
              content: SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      controller: nameController,
                      decoration: const InputDecoration(
                          labelText: 'Nom de l\'événement'),
                    ),
                    TextField(
                      controller: descriptionController,
                      decoration:
                          const InputDecoration(labelText: 'Description'),
                    ),
                    TextField(
                      controller: communityNameController,
                      decoration: const InputDecoration(
                          labelText: 'Nom de la communauté'),
                    ),
                    TextField(
                      controller: messageController,
                      decoration: const InputDecoration(labelText: 'Message'),
                    ),
                    ListTile(
                      title: Text(
                        startDate == null
                            ? 'Sélectionner la date de début'
                            : 'Début: ${startDate!.toLocal().toIso8601String().split('T')[0]}',
                      ),
                      trailing: const Icon(Icons.calendar_today),
                      onTap: () async {
                        startDate = await showDatePicker(
                          context: context,
                          initialDate: DateTime.now(),
                          firstDate: DateTime(2020),
                          lastDate: DateTime(2101),
                        );
                        setState(() {});
                      },
                    ),
                    ListTile(
                      title: Text(
                        endDate == null
                            ? 'Sélectionner la date de fin'
                            : 'Fin: ${endDate!.toLocal().toIso8601String().split('T')[0]}',
                      ),
                      trailing: const Icon(Icons.calendar_today),
                      onTap: () async {
                        endDate = await showDatePicker(
                          context: context,
                          initialDate: DateTime.now(),
                          firstDate: DateTime(2020),
                          lastDate: DateTime(2101),
                        );
                        setState(() {});
                      },
                    ),
                  ],
                ),
              ),
              actions: [
                TextButton(
                  onPressed: () {
                    Navigator.of(context).pop(true); // Signale une annulation
                  },
                  child: const Text('Annuler'),
                ),
                TextButton(
                  onPressed: () async {
                    if (nameController.text.isNotEmpty &&
                        communityNameController.text.isNotEmpty &&
                        messageController.text.isNotEmpty &&
                        descriptionController.text.isNotEmpty &&
                        startDate != null &&
                        endDate != null) {
                      // Créer un nouvel objet Event
                      Event newEvent = Event(
                        name: nameController.text,
                        description: descriptionController.text,
                        communityName: communityNameController.text,
                        message: messageController.text,
                        startTime: startDate!,
                        endTime: endDate!,
                        coordinates: location,
                        // LatLng de l'emplacement
                      );
                      // Envoyer l'événement au serveur via createEvent
                      await eventService.createEvent(newEvent);

                      setState(() {
                        markers.add(
                          Marker(
                            width: 80.0,
                            height: 80.0,
                            point: location,
                            child: const Icon(Icons.event,
                                color: Colors.green, size: 40.0),
                          ),
                        );
                      });

                      Navigator.of(context)
                          .pop(false); // Signale une création réussie
                    } else {
                      // Gestion des champs manquants
                      showDialog(
                        context: context,
                        builder: (context) {
                          return AlertDialog(
                            title: const Text('Erreur'),
                            content:
                                const Text('Veuillez remplir tous les champs.'),
                            actions: [
                              TextButton(
                                onPressed: () {
                                  Navigator.of(context).pop();
                                },
                                child: const Text('OK'),
                              ),
                            ],
                          );
                        },
                      );
                    }
                  },
                  child: const Text('Créer'),
                ),
              ],
            );
          },
        ) ??
        true; // Par défaut, considérer comme annulé si la boîte est fermée sans action
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Carte avec Événements'),
      ),
      body: currentLocation == null
          ? const Center(child: CircularProgressIndicator())
          : FlutterMap(
              mapController: mapController,
              options: MapOptions(
                initialCenter: LatLng(
                    currentLocation!.latitude!, currentLocation!.longitude!),
                initialZoom: 15.0,
                onTap: (tapPosition, point) => _addDestinationMarker(point),
              ),
              children: [
                TileLayer(
                  urlTemplate:
                      "https://api.maptiler.com/maps/topo-v2/{z}/{x}/{y}.png?key=QUFFQ9AJkYRlYXt0uQhn",
                ),
                MarkerLayer(
                  markers: markers,
                ),
                PolylineLayer(
                  polylines: [
                    Polyline(
                      points: routePoints,
                      strokeWidth: 4.0,
                      color: Colors.blue,
                    ),
                  ],
                ),
              ],
            ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          if (currentLocation != null) {
            mapController.move(
              LatLng(currentLocation!.latitude!, currentLocation!.longitude!),
              15.0,
            );
          }
        },
        child: const Icon(Icons.my_location),
      ),
    );
  }
}
