<template id="child">
  <div>
    <b-link id="show-btn" @click="showModal">{{ buildingName }}</b-link>

    <b-modal size="lg" ref="my-modal" hide-footer :title="buildingName">
      <div class="container d-block text-center">
        <div>
          <b-card no-body>
            <b-tabs pills card>
              <b-tab title="Pending" active
                ><b-card-text>
                  <div>
                    <PopupTable></PopupTable>
                  </div> </b-card-text
              ></b-tab>
              <b-tab title="Completed" ><b-card-text>
                  <div>
                     <PopupTable></PopupTable>
                  </div>
                  </b-card-text></b-tab
              >
            </b-tabs>
          </b-card>
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
import PopupTable from './PopupTable';
export default {
  name: "Popup",
  props: ["buildingName"],
   components: {
    PopupTable
  },
  filters: {
  capitalize: function (value) {
    if (!value) return ''
    value = value.toString()
    return value.charAt(0).toUpperCase() + value.slice(1)
  }
},
  methods: {
    showModal() {
      this.$refs["my-modal"].show();
    },
    hideModal() {
      this.$refs["my-modal"].hide();
    },
    toggleModal() {
      // We pass the ID of the button that we want to return focus to
      // when the modal has hidden
      this.$refs["my-modal"].toggle("#toggle-btn");
    },
  },
};

</script>
