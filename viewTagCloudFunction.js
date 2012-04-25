    function viewTagCloud()
    {
        var paper = Raphael(450, 100, 1000, 1000);
        var c = paper.image("http://i.imgur.com/Zbxme.png", 200, 250, 150, 200);
            var alpha=0;
            var radius = 200;
            var dAlpha = ((360.0/data.thesises.length)/57.3);
            var l = Math.sqrt(2*radius*radius - 2*radius*radius*Math.cos(dAlpha));
            var cx = 175;
            var cy = 175;
            var text_mas=[];
            var coords = [];

            for(var i=0; i<data.thesises.length; i++){
                cx += l * Math.cos(alpha);
                cy += l * Math.sin(alpha);
                console.log(cx + " " + cy);
                coords[i] = {"cx":cx,"cy":cy};
                var t = paper.text(cx, cy,data.thesises[i].value).attr({
                    'fill': Raphael.getRGB("green"),
//                    'opacity':(data.thesises[i].positivity/6),
                    'font-size': (data.thesises[i].importance*10 +15) ,
//                    'font-family': 'arial'
                });

               // t.animate({x:20,x:20},2000);

                <!--64800 = 360*180, beacause angle = (360/data.length)-->
                alpha+=dAlpha;
            }

//           text_mas[0].animate({x:cx+l * Math.cos(alpha),y:cy+l * Math.sin(alpha)},1000);
    }

