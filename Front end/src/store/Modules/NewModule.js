import axios from "axios";
export default {
  state: {
    showModal: false,
    questionsList: [],
    len: 0,
    serachedQ: ""
  },
  getters: {
    getShowModal: state => state.showModal,
    getSerachedQ: state => state.serachedQ,
    getQuestionsList: state => state.questionsList,
    getLen: state => state.len
  },
  mutations: {
    setShowModal: function(state, value) {
      state.showModal = value;
    },
    setSerachedQ: function(state, value) {
      state.serachedQ = value;
    },
    setQuestionsList: function(state, value) {
      state.questionsList = value;
    },
    setLen: function(state, value) {
      state.len = value;
    }
  },
  actions: {
    actionGetQuestionsList: ({ commit }, { success }) => {
      fetch("http://10.20.4.154:8181/questionscreen/list")
        .then(response => response.json())
        .then(res => {
          commit("setQuestionsList", res);
          // commit("setLen",res.length);

          success && success(res);
        });
    },
  actionGetSerachedList: ({ commit, getters }, { success }) => {
    axios
      .get(
        "http://10.20.4.154:8181/questionscreen/search/" + getters.getSerachedQ,
        {
          "Content-Type": "application/json; charset=UTF-8"
        }
      )
      .then(res => {
        commit("setQuestionsList", res.data);

        console.log(getters.getQuestionsList.length)
        
        success && success(res.data);
      })
      .catch(err => console.log(err));
  }
}
};
