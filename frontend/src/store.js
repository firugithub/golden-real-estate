import Vue from 'vue'
import Vuex from 'vuex'
import service from './service';

Vue.use(Vuex);
var isLocalStore = true;
export default new Vuex.Store({

  data: () => ({
    errors: []
}),

  state: {
    activities: [],
    errors: "",
    isLocalStore :false,
    filteredTodo:[]

  },
  mutations: {
    updateStore(state,acts){
      console.log('updating the local store');
      state.activities= acts;
      localStorage.setItem('activities', JSON.stringify(state.activities));
    },
    addActivity(state, activity) {
      if(isLocalStore){
        state.activities.push(activity);
        localStorage.setItem('activities', JSON.stringify(state.activities));
      }else{
        var saveObj={
          "id" : 0,
          "building" : activity.building,
          "name" : activity.name,
          "completed" : activity.completed,
          "assigned": activity.assigned,
          "date":activity.date
        }
        console.log(JSON.stringify(saveObj));
        service.saveTodo(saveObj)
          .then(response => {
          // JSON responses are automatically parsed.
          console.log('SAVE SUCCESS');
          console.log(response);
        })
        .catch(e => {
          this.errors.push(e)
        })
        this.getters.getActivities(state);
      }
      
    },

    deleteActivity(state, activity) {
      if(isLocalStore){
      state.activities = state.activities.filter((val) => val.id !== activity.id);
      localStorage.setItem('activities', JSON.stringify(state.activities));
      }else{
        var saveObj={
          "id" : activity.id,
          "building" : activity.building,
          "name" : activity.name,
          "completed" : activity.completed,
          "assigned": activity.assigned,
          "date":activity.date
        }
        console.log(JSON.stringify(saveObj));
        service.deleteTodo(saveObj)
          .then(response => {
          // JSON responses are automatically parsed.
        })
        .catch(e => {
          state.errors.push(e)
        })
        this.getters.getActivities(state);
      }
    },

    changeActivityState(state, activity) {
      if(isLocalStore){
      state.activities.map((val)=> {
        if (val.id === activity.id ) {
          const aux = !val.completed;
          val.completed = aux;
        }

        return val;
      });
    }else{
      var saveObj={
        "id" : activity.id,
        "building" : activity.building,
        "name" : activity.name,
        "completed" : activity.completed,
        "assigned": activity.assigned,
        "date":activity.date
      }
      service.saveObj(saveObj)
          .then(response => {
          // JSON responses are automatically parsed.
        })
        .catch(e => {
          state.errors.push(e)
        })
        this.getters.getActivities(state);
    }

      localStorage.setItem('activities',JSON.stringify(state.activities));
    },

    SET_ERROR: (state, payload) => {
      state.errors = payload;
    }
  },
  actions: {
    updateStore({commit}, {activity}) {
      commit('updateStore', activity)
    },
    addActivity({commit}, {activity}) {
      commit('addActivity', activity)
    },
    deleteActivity({commit}, {activity}) {
      commit('deleteActivity', activity)

    },
    changeActivityState({commit}, {activity}){
      commit('changeActivityState', activity)
    },
    SET_ERROR: ({commit}, errorMsg) => {
      commit('SET_ERROR', errorMsg)
    }
  },
  getters: {
    getActivities(state) {
      if(isLocalStore){
      console.log('isLocalStore getActivities');
      const activities = JSON.parse(localStorage.getItem('activities'));
      if (activities && activities.length > 0) {
        state.activities = activities;
      }
    }else{
      service.getTodoList()
          .then(response => {
          // JSON responses are automatically parsed.
          state.activities = response.data
          console.log('FETCH ALL SUCCESS');
          console.log(state.activities);
        })
        .catch(e => {
          //this.actions.SET_ERROR("SORRY, ERROR IN FETCHING DATA.");
          state.errors ="SORRY, ERROR IN FETCHING DATA.";
          console.log('ERROR IN FETCH');
          console.log(state.errors);
        })
    }
  
      return state.activities;
    }
  }
})
