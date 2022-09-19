<template>
  <div class="head">
    <!-- {{pageOfItems}} -->
    <!-- {{getQuestionsList[7]}} -->
    <div class="right">
      <div class="wrapper">
        <div class="search">
          <input v-model="searchbar" placeholder="search the question">
          <button @click="searchlist"> SEARCH</button>
        </div>

        <div class="eachquestion" style="width:100%" v-for="item in pageOfItems" :key="item.id">

          <!-- {{getQuestionsList[3] }} -->
          <!-- <OneQuestion :Question="getQuestionsList.results[item.name]" :Qno="item.name"></OneQuestion> -->
          <div id="eachdiv1">
            <div class="question">

              {{ item.name }} Q : <div id="question"> {{ getQuestionsList[item.name - 1].question }}</div><br>
              <div>
                <button id="edit" @click="edit(item.name - 1)">EDIT</button>
                <button @click="removeFromList(item.name - 1)">REMOVE</button>
              </div>
            </div>
            <div class="answers">
              Answers :<br>
              <div id="option1"> {{ getQuestionsList[item.name - 1].answerList[0].value }}</div>
              <div id="option2">{{ getQuestionsList[item.name - 1].answerList[1].value }}</div>
              <div id="option3">{{ getQuestionsList[item.name - 1].answerList[2].value }}</div>


            </div>
          </div>
        </div>
      </div>
      <div class="card-footer pb-0 pt-3">
        <jw-pagination class="pagination" :pageSize=10 :items="exampleItems" @changePage="onChangePage"
          :styles="customStyles"></jw-pagination>
        <!-- <button @click="send">UPLOAD</button> -->
      </div>
    </div>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
// import OneQuestion from './OneQuestion.vue';
import axios from 'axios';



const customStyles = {
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
      answers: [],
      exampleItems: [],
      pageOfItems: [],
      id: 0,
      deleteThisId: [],
      quizType: "",
      quizName: "",
      schedule: 0,
      searchbar: "",
      customStyles,
      content: 0,
      len: 0
    };
  },
  computed: {
    ...mapGetters(["getQuestionsList", "getLen"])
  },
  watch: {
    searchbar: function (value) {

      if (value === '') {
        window.location.reload()
      }
    }
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
    removeFromList(val) {
      var id = this.getQuestionsList[val].id

      axios.delete('http://10.20.4.154:8181/questionscreen/' + id, {
        'Content-Type': 'application/json; charset=UTF-8'
      })
        .then((res) => {
          console.log('hello')
          window.location.reload()
        })
        .catch((err) => console.log(err))
    },
    edit(val) {
      console.log(val);

      if (document.querySelectorAll("#edit")[(val) % 10].innerHTML === "EDIT") {
        console.log("gartrytfyghjkml")
        document.querySelectorAll("#question")[(val) % 10].contentEditable = true;
        document.querySelectorAll("#option1")[(val) % 10].contentEditable = true;
        document.querySelectorAll("#option2")[(val) % 10].contentEditable = true;
        document.querySelectorAll("#option3")[(val) % 10].contentEditable = true;
        document.querySelectorAll("#edit")[(val) % 10].innerHTML = "SAVE";

      }
      else {

        document.querySelectorAll("#question")[(val) % 10].contentEditable = false;
        document.querySelectorAll("#option1")[(val) % 10].contentEditable = false;
        document.querySelectorAll("#option2")[(val) % 10].contentEditable = false;
        document.querySelectorAll("#option3")[(val) % 10].contentEditable = false;

        this.answers = this.getQuestionsList[val].answerList

        this.answers[0].value = document.querySelectorAll("#option1")[(val) % 10].innerHTML
        this.answers[1].value = document.querySelectorAll("#option2")[(val) % 10].innerHTML
        this.answers[2].value = document.querySelectorAll("#option3")[(val) % 10].innerHTML


        var obj = {
          id: this.getQuestionsList[val].id,
          question: document.querySelectorAll("#question")[(val) % 10].innerHTML,
          answerList: this.answers,
          difficulty: this.getQuestionsList[val].difficulty,
          score: this.getQuestionsList[val].score,
          questionType: this.getQuestionsList[val].questionType


        }
        // console.log("answerlistnew", obj)
        axios.put('http://10.20.4.154:8181/questionscreen/update', obj, {
          headers: {
            "Access-Control-Allow-Origin": "*",
          },
          'mode': 'no-cors',
          'Content-Type': 'application/json; charset=UTF-8'
        })
          .then((res) => {
            window.location.reload()
          })
          .catch((err) => console.log(err))


        document.querySelectorAll("#edit")[(val) % 10].innerHTML = "EDIT";
      }
    },
    onChangePage(pageOfItems) {
      console.log("pageee", pageOfItems);
      this.pageOfItems = pageOfItems;

    },
    send() {
      var obj = {
        name: this.quizName,
        quizType: this.quizType,
        time: this.schedule,
        questions: this.questionBank
      };
      console.log("final obj", obj.questions);
    }
  },
  beforeMount: function () {
    this.$store.dispatch("actionGetQuestionsList", {
      success: (res) => {
        this.len = this.getQuestionsList.length - 1;
        console.log(this.len, "length")
        this.exampleItems = [...Array(this.len).keys()].map(i => ({ id: (i + 1), name: (i + 1) }));
        // console.log("res from cat", (this.getLen));
      }
    });
  },
  // components: { OneQuestion }
};
</script>
<style scoped>
.head {
  display: flex;
  flex-direction: row;
  background-color: #daeaf5;
}

.wrapper {
  height: fit-content;
  width: 100%;
  color: #ebebeb;
  /* background-color: rgb(4, 7, 58); */
  padding-left: 2%;
  padding-bottom: 20px;
  display: flex;
  align-items: flex-start;
  flex-direction: column;
}

button:hover {
  background-color: rgb(191, 188, 183);
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

.card-footer {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.card-footer button{
  background-color: #c82f2f;
    border-radius:10px ;
    margin-top:-5px;
    color: white;
}
.card-footer button:hover{
  background-color: #229f4c;
    border-radius:10px ;
    margin-top:-5px;
    color: white;
}
.search input {
  height: 50px;
  width: 80%;
  margin-bottom: 0;
  padding-bottom: 0;
  border-style: none none solid;
  border-width: 1.5px;
  background-color: #daeaf5;
  border-color: #000 #000 #1e1c1c;
  color: #7e7e7e;
  font-size: 30px;
  line-height: 30px;
  font-weight: 300;
  outline: 0;
}

button {
  background-color: rgb(226, 223, 218);
  border: 0;
  height: 50px;
  width: fit-content;
  margin-left: 10px;
  /* margin: 10px 10px 10px 10px; */
  padding: 10px 10px 10px 10px;
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
.eachdiv {
  margin-top: 5px;
  margin-bottom: 5px;
  background-color: rgb(220, 225, 225);
  width: 100%;

}

.question {
  margin-top: 30px;
  display: flex;
  width: 100%;
  height: 10%;
  /* background-color: #7e7e7e; */
  justify-content: space-between;
}

.question button {
  margin-right: 10px;
}

#question {
  width: 70%;
  text-align: left;
}

.question button:hover {
  background-color: rgb(40, 156, 44);

}

.answers {
  /* background-color: antiquewhite; */
  display: flex;
  align-items: flex-start;
  flex-direction: column;

}
</style>
