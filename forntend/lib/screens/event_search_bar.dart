import 'package:flutter/material.dart';

class EventSearchBar extends StatefulWidget {
  final Function(int distance) onDistanceChanged;

  EventSearchBar({required this.onDistanceChanged});

  @override
  _EventSearchBarState createState() => _EventSearchBarState();
}

class _EventSearchBarState extends State<EventSearchBar> {
  TextEditingController _distanceController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            'Distance :',
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          SizedBox(width: 10),
          // Champ de saisie de la distance
          Container(
            width: 100,
            child: TextField(
              controller: _distanceController,
              decoration: InputDecoration(
                hintText: '500',
                border: OutlineInputBorder(),
                labelText: 'Distance (m)',
              ),
              keyboardType: TextInputType.number,
            ),
          ),
          // Bouton pour soumettre la distance
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              if (_distanceController.text.isNotEmpty) {
                int distance = int.tryParse(_distanceController.text) ?? 0;
                widget.onDistanceChanged(distance);
              } else {
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(
                      content: Text('Veuillez entrer une distance valide')),
                );
              }
            },
          ),
        ],
      ),
    );
  }
}
