<template>
  <transition name="fadeAway">
    <div
      class="card errorMessage"
      v-if="this.$store.state.errors"
    >
      <h2 class="card-title">
        Error
      </h2>
      <p class="p-left p-right">
        {{ this.$store.state.errors }}
      </p>
      <div class="card-actions">
        <button
          class="dismiss btn-red"
          @click.prevent="dismiss"
          aria-label="Dismiss Error"
        >
          Dismiss Error
        </button>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "ErrorMessage",
  methods: {
    dismiss() {
      this.$store.state.errors = "";
    }
  }
};
</script>

<style scoped>
/* Not the cleanest CSS I've ever written - trying to loop my extra helper classes into as minimal css as possible 🍻*/

.card {
  background: hsl(0, 80%, 95%);
  margin-top: 15px;
  margin-left: auto; 
  margin-right: auto; 
  border-radius: 7px; 
  padding: 0 0 5px;
  text-align: center;
}

.card-title {
  text-align: left;
  background-color: hsl(0, 80%, 70%);
  margin: 0;
  border-radius: 7px 7px 0 0;
  color: #f3f3f3;
  padding: 5px 10px;
  font-size: 1.17em;
}

.card-actions {
  text-align: right; 
  padding-right: 1rem;
  padding-bottom: .5rem;
}

.card-actions > button {
  padding: .2rem 1rem;
}

.btn-red {
  background-color: #eb4747;
  border-color: #eb4747;
  color: #f3f3f3;
  border-radius: 4px;
}

.fadeAway-enter-active {
  transition: all 0.3s ease;
}

.fadeAway-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.fadeAway-leave-to,
.fadeAway-enter {
  opacity: 0;
  transform: translateX(100px);
}

.errorMessage {
  z-index: 5;
  position: fixed;
  right: 50px;
  bottom: 50px;
  max-width: 400px;
  min-width: 200px;
}
</style>