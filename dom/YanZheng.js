function createRandCode(){
        var chars=['a','b','c','1','2','3'];
        var randcode="";
        for(var i=0;i<4;i++){//3位随机码
            var randpos =Math.floor(Math.random() * chars.length);         
            randcode+= chars[randpos];
        }
        document.getElementById("code").innerHTML=randcode;
    }