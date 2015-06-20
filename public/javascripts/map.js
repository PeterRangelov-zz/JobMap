$(document).ready(function(){
    var baseurl = "https://www.googleapis.com/fusiontables/v2/query?"
    var table_id = "1B8h_QWDJaZu4qTrG7_oUGAIbGilSO7wm451AscLz"
    var key = "AIzaSyBacZTiWnjBX4O6Z_-jpfo2WLTl2yRYTzs"
    var query = baseurl + "sql=SELECT * FROM " + table_id + " LIMIT 5" + "&key=" +key


    console.log("INNER WIDTH: " + window.innerWidth)
    console.log("INNER HEIGHT: " + window.innerHeight)

    var zoom = 3;
    var lat = 46.56442758156376;
    var lon = -117.39802309999999;

    if (window.innerWidth <= 500) {
        zoom = 3
        lat = 44.56442758156376
        lon = -100.39802309999999
    }
    else if (window.innerWidth <= 700) {
        zoom = 4
        lat = 39.56442758156376
        lon = -96.39802309999999
    }
    else if (window.innerWidth <= 830) {
        zoom = 4
        lat = 39.56442758156376
        lon = -100.39802309999999
    }
    else if (window.innerWidth <= 1000) {
        zoom = 4
        lat = 39.56442758156376
        lon = -100.39802309999999
    }
    else if (window.innerWidth <= 1200) {
        zoom = 4
        lat = 39.56442758156376
        lon = -97.39802309999999
    }
    else if (window.innerWidth > 1200) {
        zoom = 5
        lat = 39.56442758156376
        lon = -100.39802309999999
    }

        google.maps.visualRefresh = true;

        var mapDiv = document.getElementById('googft-mapCanvas');
        var map = new google.maps.Map(mapDiv, {
            center: new google.maps.LatLng(lat, lon),
            zoom: zoom,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

    var infoWindow = new google.maps.InfoWindow();
    var markers = []
    var points;

    $.ajax({
        dataType: "json",
        url: query,
        success: function (data) {
             points = data.rows;

            points.forEach(function (points) {
                var marker = new google.maps.Marker({
                    position: {
                        lat: parseFloat(points[5]),
                        lng: parseFloat(points[6])
                    }
                });

                google.maps.event.addListener(marker, 'click', function() {
                    map.panTo(this.getPosition());
                    var html = "<h3>" + points[0] + "</h3>" +
                        "<p>" + points[4] + "</p>" +
                        "<button class=button>Apply</button> &nbsp" +
                        "<button class=button>See more details</button>";
                    //var t = '<html>"@views.html.info_window('123')"</html>';



                    infoWindow.setContent(html);
                    infoWindow.open(map, this);
                });

                markers.push(marker)
                //console.log(marker)

            });
            var mcOptions = {gridSize: 12, maxZoom: 15};
            var mc = new MarkerClusterer(map, markers, mcOptions);
        }
    });













});