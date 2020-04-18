import Vue from 'vue'
import App from './App.vue'
import store from './store'
import axios from 'axios'

import BootstrapVue from "bootstrap-vue"
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"

Vue.config.productionTip = false;
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en'
Vue.use(BootstrapVue);
Vue.use(ElementUI, { locale });
axios.defaults.baseURL ='http://localhost:8080/api/todo/';
Vue.component('pulse-loader', require('vue-spinner/src/PulseLoader.vue'));

new Vue({
  store,
  render: h => h(App)
}).$mount('#app')

