<template>
  <div>
    <b-table striped hover :items="items" :fields="fields"></b-table>
  </div>
</template>

<script>
import PopupTable from "./PopupTable";
import Vue from 'vue'

  export default {
    name: "PopupTable",
    props: ["buildingName"],
    data() {
      return {
        // Note `isActive` is left out and will not appear in the rendered table
        fields: ['name', 'date'],
        items: [...this.$store.state.activities ] 
      }
    },
    methods:{
      filter(status) {
      const cloneSheepsES6 = [...this.$store.state.activities];
      return cloneSheepsES6;
    }
    }
  }

Vue.filter('without', function (values, exclude) {
  debugger
  return values.filter(function(element) {
          element.completed == exclude;
  });
});
</script>