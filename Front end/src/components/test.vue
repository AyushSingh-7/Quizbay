<template>
    <div class="card text-center m-3">
        <!-- {{getQuestionsList}} -->
        <h3 class="card-header">Vue.js Pagination Tutorial & Example</h3>
        <div class="card-body">
            <div v-for="item in pageOfItems" :key="item.id"><div class="question">
                {{item.name}}
  Q : {{item.name}}
{{getQuestionsList.results[item.name].question}}<br>
<button>ADD</button>
</div>
<div class="answers" >
Answers :<br>  
{{getQuestionsList.results[item.name].correct_answer}}<br>
{{getQuestionsList.results[item.name].incorrect_answers[0]}}<br>
{{getQuestionsList.results[item.name].incorrect_answers[1]}}<br>
{{getQuestionsList.results[item.name].incorrect_answers[2]}}<br>
</div></div>
        </div>
        
    </div>
</template>
 
<script>
// an example array of items to be paged
// const exampleItems = this.getQuestionsList.results;

import { mapGetters } from 'vuex';
export default {
    name: 'test',
    data() {
        return {
            exampleItems:[],
            pageOfItems: [],
            len: 0
        };
    },
    methods: {
        onChangePage(pageOfItems) {
            console.log(pageOfItems)
            // update page of items
            this.pageOfItems = pageOfItems;
        },
        preparearray() {

            for (var i = 0; i < this.getQuestionsList.results.length; i++) {
                // this.exampleItems[i]=(this.getQuestionsList.results[i])
            }

        }
    },
    computed: {
        ...mapGetters(['getQuestionsList', 'getLen'])
    },
    beforeMount: function () {
        this.$store.dispatch('actionGetQuestionsList', {
            success: (res) => {
                // this.getQuestionsList=this.getQuestionsList.results
                this.len = this.getQuestionsList.results.length-1
                // this.preparearray()console.log("type  type",typeof(this.getLen))
              this.exampleItems = [...Array(this.len).keys()].map(i => ({ id: (i + 1), name:  (i + 1) }));
                console.log("res from cat", (this.getLen));
            }
        });
    }
};
</script>