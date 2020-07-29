/* <script type="text/javascript" src="geoloc.js"></script> */

let Ass = [];

function fetchGeoData (worldBase, callback) {
	fetch(`/${worldBase}/geodata.json`)
		.then(response => {
			if (response.ok)
				return response.json();
			throw new Error("geodata file couldn't be loaded");
		})
		.then(data => callback(data))
		.catch(err => console.error(err));
}

function putMarkerInside (geoData) {
	let point = overviewer.util.fromWorldToLatLng(geoData.position.x, geoData.position.y, geoData.position.z, overviewer.current_layer.world.tileSetConfig);
	let icon = L.icon({
		title: geoData.user.name,
	    iconUrl: overviewerConfig.CONST.image.playerMarker || overviewerConfig.CONST.image.queryMarker,
	    // iconRetinaUrl: overviewerConfig.CONST.image.spawnMarker2x,
	    iconSize: [32, 37],
	    iconAnchor: [15, 33],
	});
	let marker = L.marker(point, {
		'icon': icon,
		'title': geoData.user.name,
		'alt': geoData.user.name
	});
	marker.addTo(overviewer.map);
	Ass.push(marker);
}

function processMarkers (data) {
	Ass.forEach(m => m.removeFrom(overviewer.map));
	Ass = [];
	data.forEach(putMarkerInside);
}

class GeolocService {
	world;
	task;

	constructor (world) {
		this.world = world;
		this.task = null;
	}

	run () {
		this.task = setInterval((world) => {
			fetchGeoData(world, processMarkers);
		}, 2000, this.world);
	}

	stop () {
		if (this.task != null)
			clearInterval(this.task);
	}
}

let tilesetZero = overviewerConfig.tilesets[0];

if (tilesetZero == null) {
	console.error('no tilesets found in overviewerConfig');
}
else {
	let service = new GeolocService(tilesetZero.path);
	service.run();
}