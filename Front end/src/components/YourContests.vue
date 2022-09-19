<template>
    <div class="wrapper">
        <!-- {{contests[0].contestName}} -->


        <div class="contests">

            <div class="eachcontest" v-for="data in pageOfItems" :key="data.id">
                <!-- {{data.name}} -->
                <div class="image">
                    <img src="https://image.shutterstock.com/image-vector/quiz-icon-logo-260nw-1298743501.jpg">
                </div>

                <div class="details" v-if="contests[data.name - 1].type === 'static'">
                    <div class="contestname">
                        <b>{{ contests[data.name - 1].contestName }}</b> ({{ contests[data.name - 1].type }}) <br>

                    </div>
                    <div class="detailsinside">
                        <div style="padding-bottom: 15px;">
                            start Date : {{ contests[data.name - 1].startDate }} <br>
                            start Time : {{ contests[data.name - 1].startTime }}<br>
                            Duration : {{ contests[data.name - 1].duration }} mins <br>
                            Maximum Score : {{ contests[data.name - 1].maximumScore }} <br>
                            Difficulty Level: {{ contests[data.name - 1].difficultyLevel }}
                        </div>
                        <div style="[padding-bottom: 15px;]">

                            end Date : {{ contests[data.name - 1].endDate }}<br>
                            end Time : {{ contests[data.name - 1].endTime }} <br>
                            No Of Questions :{{ contests[data.name - 1].numberOfQuestions }}<br>
                            Skips Allowed :{{ contests[data.name - 1].skipAllowed }}<br>
                            Category : {{ contests[data.name - 1].category }}<br>

                        </div>

                    </div>

                </div>



                <div @click="typeofquiz(contests[data.name - 1].type)" class="details"
                    v-if="contests[data.name - 1].type === 'dynamic'">
                    <div class="contestname">
                        <b>{{ contests[data.name - 1].contestName }}</b> ({{ contests[data.name - 1].type }}) <br>

                    </div>
                    <div class="detailsinside">
                        <div style="padding-bottom: 15px;">
                            start Date : {{ contests[data.name - 1].startDate }} <br>
                            start Time : {{ contests[data.name - 1].startTime }}<br>

                            Difficulty Level: {{ contests[data.name - 1].difficultyLevel }}
                        </div>
                        <div style="[padding-bottom: 15px;]">


                            No Of Questions :{{ contests[data.name - 1].numberOfQuestions }}<br>

                            Category : {{ contests[data.name - 1].category }}<br>

                        </div>

                    </div>

                </div>


                <div class="timerr">
                    
                    <p id="subscribers" @click="subscribers(contests[data.name - 1],data.name)">subs</p>
                    <p id="demo" @click="timer(contests[data.name - 1], data.name)">Time Remaining</p>
                    <div @click="deleteContest(contests[data.name - 1].id)"><font-awesome-icon icon="fa-solid fa-trash" /></div>
                </div>
            </div>


        </div>
        <div class="card-footer pb-0 pt-3">
            <jw-pagination class="pagination" :pageSize=10 :items="exampleItems" @changePage="onChangePage">
            </jw-pagination>
            <!-- <button @click="send">UPLOAD</button> -->
        </div>

    </div>

</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            contests: [],
            limit: [1, 2, 3, 4, 5, 6, 7, 8, 9],
            exampleItems: [],
            pageOfItems: [],
            // customStyles:{},

        }
    },
    beforeMount: function () {
        this.contestlist()

    },
    methods: {
        deleteContest(val) {

            console.log(val)

              axios.delete('http://10.20.4.154:8180/contest/delete/' + val, {
                'Content-Type': 'application/json; charset=UTF-8'
              })
                .then((res) => {
                  console.log('hello')
                  window.location.reload()
                })
                .catch((err) => console.log(err))
        },
        typeofquiz(type) {

            if (type === 'dynamic') {
                console.log("type", type)
                this.$router.push('/dynamicquiz')
            }

        },
        subscribers(val,index){
            fetch("http://10.20.4.154:8180/subscription/count/"+val.id)
                .then(response => response.json())
                .then(res => {
                     document.querySelectorAll("#subscribers")[index - 1].innerHTML = "Subscribers total "+res;
                    console.log(res)

                    
                  
                });

        }
        ,
        onChangePage(pageOfItems) {
            console.log("pageee", pageOfItems);
            this.pageOfItems = pageOfItems;

        },
        contestlist() {
            fetch("http://10.20.4.154:8180/contest/list")
                .then(response => response.json())
                .then(res => {
                    //commit("setQuestionsList", res);
                    // commit("setLen",res.length);
                    // console.log(res)

                    this.contests = res
                    this.len = this.contests.length;
                    console.log(this.len, "length")
                    this.exampleItems = [...Array(this.len).keys()].map(i => ({ id: (i + 1), name: (i + 1) }));
                    // this.timer(res)
                    //success && success(res);
                });
        },
        timer(val, index) {


            // console.log("hello", index)
            // console.log("hello", val.startTime)
            var time = val.startTime
            var date = val.startDate
            var timer = new Date(date + " " + time);
            var countDownDate = new Date(timer.getFullYear(), timer.getMonth(), timer.getDate(), timer.getHours(), timer.getMinutes(), timer.getSeconds()).getTime();
            console.log("time", countDownDate)
            // Update the count down every 1 second
            var x = setInterval(function () {

                // Get today's date and time
                var now = new Date().getTime();

                // Find the distance between now and the count down date
                var distance = countDownDate - now;
                // console.log(distance)


                // Time calculations for days, hours, minutes and seconds
                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                // Output the result in an element with id="demo"
                if (distance < 0) {
                    // console.log()
                    clearInterval(x);
                    document.querySelectorAll("#demo")[index - 1].innerHTML = "EXPIRED";
                }
                else {
                    document.querySelectorAll("#demo")[index - 1].innerHTML = days + " days " + hours + " hrs " + minutes + "mins " + seconds + "sec ";
                }

                // If the count down is over, write some text 

            }, 1000);

        }


    }
}
</script>

<style scoped>
.contests {
    background-color: blanchedalmond;
    margin-top: 10vh;
    margin-left: 10vw;
    height: 90vh;
    width: 80vw;
    overflow: scroll;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;

}

img {
    margin-top: 15px;
    margin-left: 15px;
    border-radius: 100%;
    height: 150px;
    width: 150px;

}

.eachcontest {
    margin: 10px 10px 10px 10px;
    background-color: rgb(86, 157, 219);
    box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
    display: flex;
    flex-direction: row;

}

.contestname {
    display: flex;
    flex-direction: column;

}

.details {
    width: 100%;
}

.detailsinside {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;

}

.contestname b {
    display: flex;
    flex-direction: column;
    color: rgb(217, 9, 106);
    font-size: 25px;
}

#demo {
    padding: 10px 10px 10px 10px;
    margin-left: 20px;
    margin-right: 20px;
    background-color: rgb(217, 9, 106);
    color: white;
    border-radius: 10px;
    height: fit-content;
}

.timerr:hover {
    cursor: pointer;
}

</style>
