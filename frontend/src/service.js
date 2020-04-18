import axios from 'axios'

export default {
  
  getTodoList(){
    return axios.get('/findAll',{timeout:5000})
            .then(response=>{
              console.log(response);
              return response
            })
  },
  
  saveTodo(payload){
    return axios.post('/save',payload)
  },
  
  fetchToDobyCriteria(payload){
    return axios.post('/search',payload)
    .then(response=>{
      return response
    })
  },
  
  deleteTodo(payload){
    return axios.delete('/delete',payload)
  }
}
