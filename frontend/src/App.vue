<template>
  <div>
    <grid-loader :loading="true" :color="blue" :size="size"></grid-loader>
    <ErrorMessage />
    <Activity></Activity>
  </div>
</template>

<script>
  import Activity from "./components/Activity";
  import ActivityTable from "./components/ActivityTable";
  import ErrorMessage from "./components/ErrorMessage";

  export default {
    components: {Activity,ActivityTable,ErrorMessage},

  }

</script>