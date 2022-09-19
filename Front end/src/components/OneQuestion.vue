<template>
  <div class="head">
    <div id="question">
      <div i>{{Qno}} {{ Question.question }}</div><br>
      <div>
        <button id="edit" @click="edit">EDIT</button>
        <button @click="removeFromList">REMOVE</button>
      </div>
    </div>
    <div class="answers">
      Answers :<br>
      <div id="option1"> {{ Question.correct_answer }}</div><br>
      <div id="option2">{{ Question.incorrect_answers[0] }}</div><br>
      <div id="option3">{{ Question.incorrect_answers[1] }}</div><br>
      <div id="option4">{{ Question.incorrect_answers[2] }}</div><br>

    </div>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "OneQuestion",
  props: {
    Question: {
      type: Object

    },
    Qno:{
      type:Number
    }

  },
  data() {
    return {
    };
  },
  computed: {
    ...mapGetters(["getQuestionsList", "getLen"])
  },
  methods: {
    removeFromList() {

      // var id = this.getQuestionsList.results[val.id]

      // axios.delete('http://10.20.4.158:8080/auth/login', id, {
      //   'Content-Type': 'application/json; charset=UTF-8'
      // })
      //   .then((res) => {
      //     console.log('hello')
      //     window.location.reload()
      //   })
      //   .catch((err) => console.log(err))
    },
    edit() {
      console.log(this.Qno)
      console.log(document.querySelectorAll('#edit')[(this.Qno-1)%10])
      if (document.querySelectorAll('#edit')[(this.Qno-1)%10].innerHTML === "EDIT") {
        console.log("gartrytfyghjkml")
        console.log(document.querySelectorAll('#question')[(this.Qno-1)%10].contentEditable = true)
       document.querySelectorAll('#edit')[(this.Qno-1)%10].innerHTML = "SAVE"

        console.log("edit")
      }
      else {
        // console.log("qqq",document.getElementById('option1').innerHTML)
        console.log(document.querySelectorAll('#question')[(this.Qno-1)%10].contentEditable = false)
        document.querySelectorAll('#edit')[(this.Qno-1)%10].innerHTML = "EDIT"
        console.log("save")
      }


    }
  }
};
</script>
<style scoped>
.head {
  display: flex;
  flex-direction: column;
}


button:hover {
  background-color: rgb(191, 188, 183);
}


button {
  background-color: rgb(226, 223, 218);
  border: 0;
  height: 50px;
  width: fit-content;

  margin: 10px 10px 10px 10px;
  padding: 10px 10px 10px 10px;
}

#question {
  display: flex;
  flex-direction: row;
  width: 100%;
  /* background-color: #7e7e7e; */
  justify-content: space-between;
}

#question button:hover {
  background-color: rgb(40, 156, 44);

}

.answers {
  /* background-color: antiquewhite */
  display: flex;
  align-items: flex-start;
  flex-direction: column;

}
</style>
