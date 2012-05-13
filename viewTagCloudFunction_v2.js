function getThesises(cloud) {
    this.listOfPositiveItems = [];
    this.listOfNegativeItems = [];
    var j = 0;
    for (var i = 0; i < cloud.thesises.length; i++) {
        if (cloud.thesises[i].positivity > 0) {

            var k = 0;
            while (k < this.listOfPositiveItems.length) {
                if (this.listOfPositiveItems[k].positivity < cloud.thesises[i].positivity) {
                    break;
                }
                k++;
            }
            this.listOfPositiveItems.splice(k, 0, cloud.thesises[i]);
        } else {
            var k = 0;
            while (k < this.listOfNegativeItems.length) {
                if (this.listOfNegativeItems[k].positivity > cloud.thesises[i].positivity) {
                    break;
                }
                k++;
            }
            this.listOfNegativeItems.splice(k, 0, cloud.thesises[i]);

        }
    }
}


function drawClouds() {

    cloud = data;
    var thesises = new getThesises(cloud);

    var paper = Raphael(100, 100, 1000, 1000);

    var c = paper.image("http://i.imgur.com/Zbxme.png", 200, 250, 150, 200);
    var alphaGreen = 90.0 / 57.3;
    var alphaRed = 270.0 / 57.3;
    var radius = 250;
    var dAlphaGreen = (60.0 / (thesises.listOfNegativeItems.length)) / 57.3;
    var dAlphaRed = (60.0 / (thesises.listOfPositiveItems.length)) / 57.3;
    var lRed = Math.sqrt(2 * radius * radius - 2 * radius * radius * Math.cos(dAlphaRed));
    var lGreen = Math.sqrt(2 * radius * radius - 2 * radius * radius * Math.cos(dAlphaGreen));
    var cxRed = 100;
    var cyRed = 0;
    var cxGreen = 450;
    var cyGreen = 0;
    var y = 350;
    var t = paper.text(200 + 75 - cloud.product_name.length / 2, 150, cloud.product_name).attr({

        'fill':Raphael.getRGB("black"),
        'opacity':1,
        'font-size': (30) ,
        'font-family': 'arial'
    });


    cxGreen += lGreen * Math.cos(alphaGreen);
    for (var j = 0; j < thesises.listOfPositiveItems.length; j++) {


        t = paper.text(cxGreen, y + Math.pow(-1, j) * cyGreen, thesises.listOfPositiveItems[j].value).attr({

            'fill':Raphael.getRGB("green"),
            'opacity':Math.abs(thesises.listOfPositiveItems[j].positivity / 6),
            'font-size': thesises.listOfPositiveItems[j].importance * 10 + 15 ,
            'font-family': 'arial'
        });
        alphaGreen += dAlphaGreen;
        if (j % 2 == 0) {
            cxGreen += lGreen * Math.cos(alphaGreen);
            cyGreen += lGreen * Math.sin(alphaGreen);

        }


    }

    cxRed += lRed * Math.cos(alphaRed);
    for (var j = 0; j < thesises.listOfNegativeItems.length; j++) {


        t = paper.text(cxRed, y + Math.pow(-1, j) * cyRed, thesises.listOfNegativeItems[j].value).attr({

            'fill':Raphael.getRGB("red"),
            'opacity':Math.abs(thesises.listOfNegativeItems[j].positivity / 6),
            'font-size': thesises.listOfNegativeItems[j].importance * 10 + 15 ,
            'font-family': 'arial'
        });
        alphaRed += dAlphaRed;
        if (j % 2 == 0) {
            cxRed += lRed * Math.cos(alphaRed);
            cyRed += lRed * Math.sin(alphaRed);

        }
    }
}


