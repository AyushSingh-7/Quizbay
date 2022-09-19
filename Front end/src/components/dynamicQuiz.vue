<template>
    <div class="wrapper">
        <div class="scroll">
        <div class="list">
            <div  v-for="index in questions" :key="index.id">
                <div @click="displayQuestion(index)" class="questionnumbers">
                    {{ index }}
                </div>
            </div>
            </div>
        </div>
        <div v-if="showquestion" class="questioncontainer">
            <div class="thequestion">
               <b>Question</b> : {{ theQuestion }}

                <div class="options"><b>options</b>
                    <br>
                    <div >{{ options[0] }}</div>
                    <div>{{ options[1] }}</div>
                    <div>{{ options[2] }}</div>
                </div>
            </div>
            <div>
                <button @click="golive">GO LIVE</button>
            </div>
        </div>

    </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    name: "dynamicQuiz",
    data() {
        return {
            questions: ['1', '2', '3', '4', '5','6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '18', '19','20','21','22','23','24'],
            msg: "Welcome to Your Vue.js App",
            theQuestion: '',
            options: [],
            showquestion: false

        };
    },
    beforeMount: function () {
        this.$store.dispatch('actionGetQuestionsList', {
            success: (res) => {
            }
        });
    },
    computed: {
        ...mapGetters(['getQuestionsList'])
    },
    methods: {
        golive() {

        },
        displayQuestion(val) {
            if (this.showquestion)
                this.showquestion = false
            else
                this.showquestion = true

            this.theQuestion = this.getQuestionsList[val-1].question
            for (var i = 0; i < 3; i++) {
                this.options.push(this.getQuestionsList[val-1].answerList[i].value)
            }
            // console.log(this.getQuestionsList[val].question)
        }
    }
}
</script>

<style scoped>
.wrapper {
    margin-left: 10%;
    margin-top: 7%;
    height: 60vh;
    width: 80vw;
    border-radius: 20px;
    background-color: rgb(192, 169, 138);
    box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
    
}

/* .scroll {
    /* width: 80%; */
/* margin-left: 10%;
    height: 100px;
    background-color: black; */
/* padding-left: 80%px; */
/* overflow: scroll; */
/* } */

.questionnumbers {

     padding: 20px 20px 20px 20px;
     margin-top: 10px;
     margin-left:5px ;
     border-radius: 100%;
    background-color: aquamarine; 
    /* overflow: scroll; */
}

.questionnumbers:hover {
    background-color: rgb(230, 211, 211);
}

button {
    background-color: rgb(224, 74, 58);
    border: 0;
    height: 50px;
    width: fit-content;
    margin-left: 10px;
    padding: 10px 10px 10px 10px;
}

.options {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

.questioncontainer {
  
    width: 80%;
    height: 60%;
    margin-left: 10%;
    margin-top: 5%;
}
.scroll{
    /* background-color: black; */
    
    width:100%;
   /* overflow: scroll; */
}

.list {
   
    scroll-margin-top: 10px;
   overflow: scroll;
    /* background-color: blue; */
    margin-left:15%;
    width: 70%;
    margin-top: 100px;
    display: flex;
    flex-direction: row;

}
</style>
