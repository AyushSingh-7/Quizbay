<template>
    <div class="head">
        <!-- {{getQuestionsList[1].question}} -->
        <div class="right">
            <div class="wrapper">
                <div class="search">
                    <input v-model="searchbar" placeholder="search the question">
                    <button @click="searchlist"> SEARCH</button>
                </div>
                <div class="eachquestion" style="width:100%" v-for="item in pageOfItems" :key="item.id">
                    <div class="question">
                        {{ item.name }}
                        Q :
                        {{ getQuestionsList[item.name-1].question }}<br>
                        <button @click="addtolist(item.name-1)">ADD</button>
                    </div>
                    <div class="answers">
                        Answers :<br>
                    
                       <div> a) {{ getQuestionsList[item.name-1].answerList[0].value }}</div>
                        <div> b) {{ getQuestionsList[item.name-1].answerList[1].value }}</div>
                         <div>c) {{ getQuestionsList[item.name-1].answerList[2].value }}</div>

                    </div>
                </div>
            </div>
            <br>
            <br>
            <div class="card-footer pb-0 pt-3">
                <jw-pagination class="pagination" :pageSize=5 :items="exampleItems" @changePage="onChangePage"
                    :styles="customStyles"></jw-pagination>
                <button @click="send">SEND</button>
            </div>
        </div>

    </div>
</template>

<script>
import { mapGetters } from 'vuex'
// import OneQuestion from './OneQuestion.vue'
const customStyles = {
    // onmouseover: function(){this.backgroundCol÷or='red'},
    ul: {
        // border: '2px solid grey',
        // backgroundColor:'blue'
    },
    li: {
        // borderRadius:'100%',
        margin:'2px',
         backgroundColor:'grey',
         color:"white",
     
        display: 'inline-block',
    },
    a: {
        color: 'white'
    }
};
export default {
    name: "Screener",
    data() {
        return {
            exampleItems: [],
            pageOfItems: [],
            id: 0,
            questionBank: [],
            quizType: '',
            quizName: '',
            schedule: 0,
            searchbar: '',
            customStyles
        };
    },
    computed: {
        ...mapGetters(["getQuestionsList", "getLen"])
    },
    methods: {
 searchlist() {
      this.$store.commit('setSerachedQ', this.searchbar)
      console.log(this.searchbar)
      this.$store.dispatch('actionGetSerachedList', {
        success: (res) => {
          console.log("hello", res);
          this.len = res.length

          this.exampleItems = [...Array(this.len).keys()].map(i => ({ id: (i + 1), name: (i + 1) }));
          // console.log(this.exampleItems, "ql")
          //  console.log(this.pageOfItems,"page")
          this.pageOfItems = this.exampleItems

        }
      });



    },

        addtolist(val) {
            this.questionBank.push(this.getQuestionsList[val].id)
            this.questionBank = [...new Set(this.questionBank)]
            console.log("this", this.questionBank)
            console.log(val)

        },
        onChangePage(pageOfItems) {
            console.log(pageOfItems)
            this.pageOfItems = pageOfItems;
        },
        send() {
            var obj = {
                name: this.quizName,
                quizType: this.quizType,
                time: this.schedule,
                questioncode: this.questionBank

            }
            console.log("final obj", obj.questions)
        }
    },
    // components: { OneQuestion },
    beforeMount: function () {
        this.$store.dispatch('actionGetQuestionsList', {
            success: (res) => {
                this.len = this.getQuestionsList.length - 1
                console.log(this.len)
                this.exampleItems = [...Array(this.len).keys()].map(i => ({ id: (i + 1), name: (i + 1) }));
                // console.log("res from cat", (this.getLen));
            }
        });
    }
};
</script>
<style scoped>
.head {
    display: flex;
    flex-direction: row;
    background-color: #daeaf5;
}
.eachquestion{
    margin-top: 10px ;
    margin-left: -20px;
    border-radius:10px ;
    padding: 10px 10px 10px 10px;
    background-color: #47B5FF;
    color: black;
        box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;

}
.eachquestion:hover{
  transform: scale(1.009);
}
.wrapper {
    height: fit-content;
    width: 100%;
    color: #ebebeb;
    /* background-color: rgb(4, 7, 58); */

    /* box-shadow: rgb(204, 219, 232) 3px 3px 6px 0px inset, rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset; */
    padding-left: 2%;
    display: flex;
    align-items: flex-start;
    flex-direction: column;
}
.search button{
    background-color: #aaa7a7;
}

button:hover {
    background-color: rgb(131, 129, 128);
}

.right {
    width: 80%;
    margin: 2% 10% 1% 10%;
}

.search {
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
    margin-top: 10px;
    margin-bottom: 10px;
}

.search input {
    height: 50px;
    width: 80%;
    margin-bottom: 0;
    padding: 0;
    border-style: none none solid;
    border-width: 1.5px;
    background-color: #daeaf5;
    /* border: 5px solid black; */
    border-color: #000 #000 #161515;
    color: #7e7e7e;
    font-size: 30px;
    line-height: 30px;
    font-weight: 300;
    outline: 0;
}

button {
    background-color: rgb(218, 226, 219);
    border: 0;
    height: 51px;
    width: fit-content;
    margin-left: 10px;
    padding: 10px 10px 10px 10px;
}


.eachdiv {
    margin-top: 5px;
    margin-bottom: 5px;
    background-color: rgb(220, 225, 225);
    width: 100%;

}

.question {
    display: flex;
    width: 100%;
    justify-content: space-between;
}
.question button{
    margin-right: 2%;
}
.question button:hover {
    background-color: rgb(55, 217, 131);

}
.card-footer{
    display: flex;
    flex-direction: row;
    justify-content: center;
    
}
.card-footer button{
    background-color: #c82f2f;
    border-radius:10px ;
    color:white;
    width: 80px;
    margin-top: -5px;
    /* margin-left: 200px; */
}

.card-footer button:hover{
    background-color: #2fc865;
   cursor: pointer;
    /* margin-left: 200px; */
}
.answers {
    /* background-color: antiquewhite; */
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    flex-direction: column;

}

ul .pagination .page-item {
    /* background-color: red; */
    background-color: red !important;
}
</style>
