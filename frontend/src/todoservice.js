import service from './service';
import store  from './store'


var isLocalStore = false;
export default {

  data: () => ({
    errors: []
}),

  methods: {

    getActivities() {
      console.log(store.state.activities);
      service.getTodoList()
          .then(response => {
          // JSON responses are automatically parsed.
          var newArray = [...response.data];
          store.state.activities =newArray;
          console.log('FETCH ALL SUCCESS');
          console.log(store.state.activities);
          store.commit('updateStore',newArray);
          return store.state.activities;
        })
        .catch(e => {
          //this.actions.SET_ERROR("SORRY, ERROR IN FETCHING DATA.");
          store.state.errors ="SORRY, ERROR IN FETCHING DATA.";
          console.log('ERROR IN FETCH');
          console.log(store.state.errors);
        })
  
    },
    addActivity(activity) {
      console.log('DB SAVE START');
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
          this.getActivities();
        })
        .catch(e => {
          store.state.errors = "ERROR DB SAVE";
        })
        
    },

    deleteActivity(activity) {
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
          this.getActivities();
        })
        .catch(e => {
          store.state.errors = "ERROR DB DELETE";
        })
        
    },

    changeActivityState(activity) {
      var saveObj={
        "id" : activity.id,
        "building" : activity.building,
        "name" : activity.name,
        "completed" : activity.completed,
        "assigned": activity.assigned,
        "date":activity.date
      }
      service.saveTodo(saveObj)
          .then(response => {
          // JSON responses are automatically parsed.
          this.getActivities();
        })
        .catch(e => {
          store.state.errors = "ERROR DB MODIFY";
        })
       

    },
    fetchToDobyCriteria() {
    
      service.fetchToDobyCriteria( store.state.searchObj)
          .then(response => {
          // JSON responses are automatically parsed.
          store.state.filteredTodo =  response.data;
          store.state.showpopup=true;
        })
        .catch(e => {
          store.state.errors = "ERROR DB MODIFY";
        })
    }
  }
 
}
