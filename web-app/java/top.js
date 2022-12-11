var app = new Vue({
    el:"#app",
    data:{
        number:null,
        answer:0,
        count:null,
        range:0,
        range2:0,
        sensor:null,
        switch:0,
        switch2:0,
        match:null,
        flag: false,
        flag2: false,
        detection_items:[]

    },
    methods:{
        onClick: function(){
            this.sensor=null
            this.switch=null
            this.switch2=null
            const desu = ["です。","ます。","ました。","ません。","でしょう。","ましょう。","でしょうか。"]
            const da = ["だ。","思う。","思われる。","だろう。","だった。","する。","とした。","にした。","いる。","ない。","あった。","である。"]
            var detection_array = []
            var detection_array2 = []
            var detection_list = []
            var detection_list2 = []

            for(var i = 0;i<desu.length;i++){
                detection_list = this.detection(this.sensor,this.number,desu[i],detection_array,detection_list)
                console.log(detection_list)
                console.log(detection_list.length)
            }
            if(detection_array.length>=1){
                this.switch=1
            }

            console.log("切り替え")

            for(var j = 0;j<da.length;j++){
                detection_list2 = this.detection(this.sensor,this.number,da[j],detection_array2,detection_list2)
                console.log(detection_list2)
                console.log(detection_list2.length)
            }
            if(detection_array2.length>=1){
                this.switch2=1
            }

            if(detection_list.length>detection_list2.length){
                this.detection_items = detection_list2
            }
            else if(detection_list.length<detection_list2.length){
                this.detection_items = detection_list
            }


            if(this.switch==1 && this.switch2==1){
                this.match="ですます調とである調が混合"
            }
            else if(this.switch==1){
                this.match="ですます調統一"
            }
            else if(this.switch2==1){
                this.match="である調統一"
            }
            else{
                this.match="未検出"
            }
            this.count = this.number.length+"文字"
            this.range = Math.round(this.number.length/0.8)+"文字"
            this.range2 = Math.round(this.number.length/0.9)+"文字"
            this.flag=true
        },
        clear: function(){
            this.number = null
            this.flag=false
            this.flag2= !this.flag2
        },
        open_detection: function(){
            this.flag2 = !this.flag2
        },
        detection: function(sensor,str,array,detection_arrays,detection_list){
            var s=null
            s=str
            sensor=s.indexOf(array)

            if(sensor>1){
                detection_arrays.push(sensor)
                detection_list.push(str.substr(sensor-15,30))
                s = s.substr(sensor+6)
                sensor=0
                detection_list=this.detection(sensor,s,array,detection_arrays,detection_list)

                sensor=0
            }


            return detection_list;
        }
        
    }

});

