<template>
    <div class="wrapper">
        <!-- {{ leaderboard }} -->
        <!-- <button @click="showboard">show</button> -->
        <p>LEADERBOARD</p>
        <div class="main">
            <div v-for="(data, index) in leaderboard" :key=index>
                <div class="lb" v-if="data.length > 0">
                <div v-for="(data2,ind) in contests1" :key="data2">
                    <div v-if="data2===data[0].contestId">
                    <div class="name">
                       {{contests1[ind+1]}}
                    </div>
                    </div>
                </div>
                <img src="../assets/crown_png.png" />
                   <div class="lb1" v-if="data.length >= 3"> 
                        <div class="eachname">
                            {{data[1].name}} <br/>
                            {{data[1].score}}
                        </div>
                        <div class="eachname1">
                            {{data[0].name}}<br/>
                            {{data[0].score}}
                        </div>
                        <div class="eachname2">
                            {{data[2].name}}<br/>  
                            {{data[2].score}}
                        </div>
                   </div>

                    <div class="lb1" v-else-if="data.length === 1"> 
                     <div class="eachname3">
                            {{data[0].name}}<br/>
                            {{data[0].score}}
                     </div>
                   </div>

                    <div class="lb1" v-else-if="data.length === 2"> 
                     <div class="eachname4">
                            {{data[1].name}}<br/>
                            {{data[1].score}}
                        </div>
                        <div class="eachname5">
                            {{data[0].name}}<br/>  
                            {{data[0].score}}
                        </div>
                   </div>
                   
                  <!-- {{contests1}} -->
                    <!-- <div v-if="data.length >= 3">
                        <div v-for="indata in 3" :key="indata.id" class="lbmain">
                            <img src="../assets/crown_png.png" />
                            {{ data[indata - 1].score }}<br>
                            {{ data[indata - 1].name }}
                        </div>
                    </div>

                    <div class="lb" v-else-if="data.length > 0 && data.length < 3">
                        <img src="../assets/crown_png.png" />
                        <div style="display: flex;">

                            <div v-for="indata in data.length" :key="indata.id" class="lbmain">
                                {{ data[indata - 1].score }}<br>
                                {{ data[indata - 1].name }}
                            </div>
                        </div>
                    </div> -->
                </div>

                <!-- <div v-else-if="data.length===0">
                   {{changeClass()}}
                </div>
                 -->
                <!-- <div v-for="indata in data.length" :key="indata.id">
                    <img src="../assets/crown_png.png" />
                    <div class="lb1"  v-if="data.length">
                    {{data}}
                         <div class="eachname">
                            {{ data[indata-1].score }}<br>
                            {{ data[indata-1].name }}
                        </div>
                    <div  class="eachname1">
                           
                            {{ data[0].score }}<br>
                            {{ data[0].name }}
                        </div>
                        <div  class="eachname2">
                       
                            {{ data[2].score }}<br>
                            {{ data[2].name }} 
                        </div>
                    </div>
                </div> -->
                <br>
            </div>
            <!-- <div class="lb" v-for="data in contests" :key="data">
                    <div class="lb1" v-for="data1 in leaderboard" :key="data1.contest.contestName">
                        
                            
                        </div>
                    </div>-->
        </div>

    </div>
    <!-- </div> -->
</template>

<script>
import axios from 'axios';



export default {
    name: "Leaderboard",
    data() {
        return {
            msg: "Welcome to Your Vue.js App",
            leaderboard: [],
            contests: [],
            contests1: [],
            contestname: '',
            eyed: '',
            index: 0,
            ind:0,
            classCondition: true
        };
    },
    beforeMount: function () {

        fetch("http://10.20.4.154:8180/contest/list")
            .then(response => response.json())
            .then(res => {
                // console.log(res)
                // console.log(res.length)
                res.forEach(element => {
                    this.contests.push(element.id)
                    this.contests1.push(element.id, element.contestName)
                });
                // console.log(this.contests)
                this.contests.forEach(element => {
                    axios.get("http://10.20.4.154:8180/leaderboard/list/" + element, {
                        "Content-Type": "application/json; charset=UTF-8"
                    }
                    )
                        .then(res => {
                            console.log("conteasdst    ", res.data);
                            this.leaderboard.push(res.data)
                        })
                        .catch(err => console.log(err));

                });
                console.log("leaderboad", this.leaderboard)

            });



        // fetch("http://10.20.4.154:8180/leaderboard/all")
        //     .then(response => response.json())
        //     .then(res => {
        //         console.log(res)
        //         // console.log(res)
        //         // this.leaderboard.push(res)
        //         // console.log(this.leaderboard)
        //         res.forEach(element => {
        //         this.leaderboard.push(element)
        //             // console.log(element.contest)
        //             // console.log(element.contest.contestName)
        //         });
        //         console.log(this.leaderboard)
        //         this.leaderboard.forEach(element => {
        //             this.contests.add(element.contest.contestName)
        //             console.log(this.contests)
        //             console.log(element.score)
        //             console.log(element.player.name)
        //         });
        //     });


    },
    methods: {

        contestdetails() {
            console.log("hello")
        },
        changeClass() {
            this.classCondition = false
        }
    },
    watch: {
        contestname() {

        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url('http://fonts.cdnfonts.com/css/mouse-memoirs');
@import url('http://fonts.cdnfonts.com/css/dancing-script');
.wrapper{
    /* width: 100vw; */
    height: 100vh;
    padding: 1.5%;
    /* overflow-x: hidden; */
    /* background-color: #a8d5f1; */
}
.main {
    display: flex;
    flex-wrap:wrap;
    flex-direction:row ;
    width: 100vw;
    height: 100vh;
}
.name{
    padding-top:2vh;
    font-family: 'Roboto';
    font-weight: 1000;
    font-size: 3vh;
    color: #121776;
    text-transform: lowercase;
    text-transform: capitalize;
    /* width: fit-content; */
}
img {
    width: 5vw;
    height: 5vh;
    /* margin-left:0vw ; */
    margin-top: 4vh;
}
p{
    font-family: 'Mouse Memoirs';
    font-size: 200%;
    color: #ef2a31;
}
/* .lb{
    display: flex;
    flex-direction: column;
} */
.lb {
    width: 30vw;
    height: 50vh;
    background-color: #47B5FF;
    background-image: url("../assets/podium.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: 0 21vh;
    /* display: flex; */
    /*background-size: contain;
    background-attachment: fixed; */
    margin: 2vh;
    float: top;
    border-radius: 2vw;
    /* color: rgb(48, 39, 8); */
}

.lb1 {
    display: flex;
}

.eachname {
    /* background-color: beige; */
    /* margin: 10px 10px 10px 10px; */
    width: 10vw;
    height: 4vh;
    margin-left:2vw;
    margin-top: 5vh;
    text-align: center;
    align-content: center;
    color: #943009;
    font-family: 'Mouse Memoirs';
    font-size:x-large;
    text-transform: uppercase;
    /* margin-left: 12vw; */
    /* padding-top:10vh */
}

.eachname1 {
    /* background-color: beige; */
    /* margin: 10px 10px 10px 10px; */
    width: 10vw;
    height: 4vh;
    margin-left: -7%;
    margin-top: 0vh;
    text-align: center;
    align-content: center;
    color: #943009;
    font-family: 'Mouse Memoirs';
    font-size:x-large;
    text-transform: uppercase;
    /* width: fit-content; */
    /* margin-left: 4vw; */
}

.eachname2 {
    /* background-color: beige;  */
    width: 10vw;
    height: 4vh;
    margin-left: -6%;
    margin-top: 7vh;
    text-align: center;
    align-content: center;
    color: #943009;
    font-family: 'Mouse Memoirs';
    font-size:x-large;
    text-transform: uppercase;
    /* width: fit-content; */
    /* margin-left: 20vw; */
    /* padding-bottom: 20vh; */
    /* position: fixed; */
}
.eachname3 {
    /* background-color: beige;  */
    width: 10vw;
    height: 4vh;
    margin-left: 10vw;
    /* margin-top: 0vh; */
    text-align: center;
    align-content: center;
    color: #943009;
    font-family: 'Mouse Memoirs';
    font-size:x-large;
    text-transform: uppercase;
    /* width: fit-content; */
    /* margin-left: 20vw; */
    /* padding-bottom: 20vh; */
    /* position: fixed; */
}
.eachname5 {
    /* background-color: beige;  */
    width: 10vw;
    height: 4vh;
    margin-left: -7%;
    /* margin-top: 0vh; */
    text-align: center;
    align-content: center;
    color: #943009;
    font-family: 'Mouse Memoirs';
    font-size:x-large;
    text-transform: uppercase;
    /* width: fit-content; */
    /* margin-left: 20vw; */
    /* padding-bottom: 20vh; */
    /* position: fixed; */
}
.eachname4 {
    /* background-color: beige;  */
    width: 10vw;
    height: 4vh;
    margin-left: 2vw;
    margin-top: 5vh;
    /* margin-top: 0vh; */
    text-align: center;
    align-content: center;
    color: #943009;
    font-family: 'Mouse Memoirs';
    font-size:x-large;
    text-transform: uppercase;
    /* width: fit-content; */
    /* margin-left: 20vw; */
    /* padding-bottom: 20vh; */
    /* position: fixed; */
}
</style>
