
    function Preview(id)
    {
        var paper = Raphael(100, 100, 1000, 1000);
        $.get('http://localhost:8080/thesis/'+id, function(thesises){
        var c = paper.image("http://i.imgur.com/Zbxme.png", 200, 250, 150, 200);
            var alpha=0;
            var radius = 200;
            var dAlpha = ((360.0/thesises.length)/57.3);
            var l = Math.sqrt(2*radius*radius - 2*radius*radius*Math.cos(dAlpha));
            var cx = 175;
            var cy = 175;
            var text_mas=[];
            var coords = [];
            for(var i=0; i<thesises.length; i++){
                cx += l * Math.cos(alpha);
                cy += l * Math.sin(alpha);
                console.log(cx + " " + cy);
                coords[i] = {"cx":cx,"cy":cy};
                var t = paper.text(cx, cy,thesises[i].value).attr({
                    'fill': Raphael.getRGB("green"),
                    'opacity':(thesises[i].positivity/6),
                    'font-size': (thesises[i].importance*100 +15) ,
                    'font-family': 'arial'
                });

               // t.animate({x:20,x:20},2000);

                <!--64800 = 360*180, beacause angle = (360/data.length)-->
                alpha+=dAlpha;
            }

           text_mas[0].animate({x:cx+l * Math.cos(alpha),y:cy+l * Math.sin(alpha)},1000);
           });
       /* while(true){
            cx = 175 + l * Math.cos(alpha);
            cy = 175 + l * Math.sin(alpha);
            for(var i=0,t=1;t<text_mas.length;i++,t++){
                text_mas[i].raphael.animate({'cx':cx,'cy':cy});
                text_mas[t].raphael.animate({'cx':(cx+ l * Math.cos(alpha)),'cy':(cy+ l * Math.sin(alpha))});
                cx+=l * Math.cos(alpha);
                cy+=l * Math.sin(alpha);
            }
        }*/

       /* cx = 175 + l * Math.cos(alpha);
        cy = 175 + l * Math.sin(alpha);
        while(true){
            var anim = Raphael.animation({cx:cx,cy:cy},2e3);
            var anim2 = Raphael.animation({cx:cx+l * Math.cos(alpha),cy:cy+l * Math.sin(alpha)},2e3);
            for(var i=0,t=1;t<Items.lenght;i++,t++){

              Items[i].str.animate(anim).attr({
                   'fill': Raphael.getRGB("green"),
                   'font-size': (Items[i].importance*100 +15) ,
                   'font-family': 'arial'
               });
               Items[t].str.animate(anim2).attr({
                   'fill': Raphael.getRGB("green"),
                   'font-size': (Items[i].importance*100 +15) ,
                   'font-family': 'arial'});
                cx += l * Math.cos(alpha);
                cy += l * Math.sin(alpha);
            }
        }*/

    }

