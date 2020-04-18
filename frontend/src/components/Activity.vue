<template>
  <div class="container">
    <h1>TODO ORGANIZER</h1>
<Popup></Popup>
    <div>
      <b-tabs content-class="mt-3">
        <b-tab title="CREATE" active>
          <div>
            <el-row>
              <el-col :span="5">
                <el-input
                  placeholder="Building Name"
                  v-model="buildingName"
                  size="mini"
                >
                </el-input>
              </el-col>
              <el-col :span="5">
                <el-input
                  placeholder="Please input the activity"
                  v-model="activity"
                  size="mini"
                >
                </el-input>
              </el-col>
              <el-col :span="3">
                <el-input
                  placeholder="Assigned To"
                  v-model="assignedTo"
                  size="mini"
                >
                </el-input>
              </el-col>
              <el-col :span="5">
                <el-date-picker
                  v-model="date"
                  type="date"
                  placeholder="Pick a day"
                >
                </el-date-picker>
              </el-col>
              <el-col :span="5">
                <el-button
                  type="primary"
                  icon="el-icon-circle-plus-outline"
                  circle
                  @click="addActivityMethod()"
                >
                </el-button>
              </el-col>
            </el-row>

        <div v-if="isWrongActivity" class="wrongNotification">
            <el-alert
              :title="errorMessage"
              type="error"
              :center="true"
              show-icon
              :closable="false">
           </el-alert>
       </div>

            <div v-if="isEmpty">
              <el-alert
                title="There are not activities at this moment"
                type="info"
                :center="true"
                show-icon
                :closable="false"
              >
              </el-alert>
            </div>
            <div v-else>
              <b-container fluid>
                <b-row class="my-1">
                  <b-col sm="5">
                   <div class="mt-2">Filtering for : {{ filtertext }}</div>

                    <b-form-input v-model="filtertext" placeholder="Enter keyword to Search"
                    @change="processfilter"
                    ></b-form-input>
                  </b-col>
                </b-row>
              </b-container>
              <el-table :data="activities" :row-class-name="tableRowColor"  :header-row-style="headerRowStyle">
                <el-table-column
                  label="Building"
                  class="font-weight-bold"
                >
                 <template slot-scope="scope">
                 <Popup v-bind:buildingName=scope.row.building></Popup>
                 </template>
                </el-table-column>
                <el-table-column prop="name" label="Activity">
                 
                   </el-table-column>
                <el-table-column prop="assigned" label="Assigned To">
                   <template slot-scope="scope">
                 <Popup v-bind:buildingName=scope.row.assigned></Popup>
                 </template>
                </el-table-column>
                <el-table-column label="Date">
                  <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{
                      getDate(scope.row.date)
                    }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="Remove">
                  <template slot-scope="scope">
                    <el-button
                      type="danger"
                      icon="el-icon-delete"
                      @click="removeActivity(scope.row)"
                      circle
                      size="small"
                    >
                    </el-button>
                  </template>
                </el-table-column>
                <el-table-column label="Status">
                  <template slot-scope="scope">
                    <el-button
                      type="success"
                      icon="el-icon-check"
                      circle
                      @click="completeActivity(scope.row)"
                      size="small"
                    >
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <div style="padding: 10px" />
            </div>
            <div>
              <el-row type="flex" justify="center" class="counterSection">
                <el-col :span="4">
                  Completed :
                </el-col>
                <el-col :span="2">
                  {{ completed }}
                </el-col>
                <el-col :span="3">
                  Total :
                </el-col>
                <el-col :span="2">
                  {{ total }}
                </el-col>
              </el-row>
            </div>
          </div>
        </b-tab>
        <b-tab title="STATUS BY BUILDINING">
          <!-- <div>
            <b-table striped hover :items="activities"></b-table>
          </div> -->
          <ActivityTable></ActivityTable>
        </b-tab>
        <b-tab title="STATUS BY ASSIGNED TO"><p>I'm a disabled tab!</p></b-tab>
      </b-tabs>
    </div>

    
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import moment from "moment";
import uuid from "uuid/v4";
import ActivityTable from "./ActivityTable";
import Popup from "./Popup";
export default {
  name: "Activity",
   components: {
    ActivityTable,
    Popup
  },
  data() {
    return {
      filtertext: '',
      buildingName: "",
      activity: "",
      assignedTo: "",
      wrong: false,
      date: "",
      errorMessage: "",
    };
  },
  computed: {
    ...mapGetters({
      activities: "getActivities",
    }),
    processfilter() {
      console.log('I am in filter');
      console.log(this.activities);
        return this.filtertext
				? this.activities.filter(item => item.name.includes(this.filtertext) || item.building.includes(this.filtertext) || item.assigned.includes(this.filtertext))
				: this.activities
    },
    isWrongActivity() {
      return this.wrong;
    },
    completed() {
      return this.activities.filter((val) => val.completed === true).length;
    },
    total() {
      return this.activities.length;
    },
    isEmpty() {
      return this.activities.length === 0;
    },
  },
  methods: {
    ...mapActions(["addActivity", "deleteActivity", "changeActivityState"]),
    addActivityMethod() {
      if (this.validateData() === true) {
        const activity = {
          building: this.buildingName,
          name: this.activity,
          assigned: this.assignedTo,
          completed: false,
          date: this.date,
          id: uuid(),
        };
        var saveObj={
          "id" : 0,
          "building" : activity.building,
          "name" : activity.name,
          "completed" : activity.completed,
          "assigned": activity.assigned,
          "id" : activity.id,
          "date":activity.date
        }
        console.log(JSON.stringify(saveObj));
        this.addActivity({ activity });
        this.buildingName=""
        this.activity = "";
        this.assignedTo="";
        this.date = "";
        this.wrong = false;
      } else {
        this.wrong = true;
        this.setMessageError();
      }
    },
    showModal() {
      console.log(this.$refs);
      console.log(this.Popup);
        this.$refs.child.show();
    },

    removeActivity(item) {
      console.log(item);
      this.deleteActivity({ activity: item });
    },

    completeActivity(item) {
      this.changeActivityState({ activity: item });
    },
    tableRowColor({ row, rowIndex }) {
      if (row.completed === true) {
        return "success-row";
      } else {
        return "warning-row";
      }

      return "";
    },
    headerRowStyle({row, rowIndex}) {
        	return {"backgroundColor":"#777777"};
    },
    validateData() {
      if (this.activity !== "" && this.date !== "") {
        return true;
      } else {
        return false;
      }
    },

    setMessageError() {
      if (this.activity === "" && this.date === "") {
        this.errorMessage = "The activity && the date are empty";
      } else {
        if (this.activity === "") {
          this.errorMessage = "The activity is empty";
        } else {
          this.errorMessage = "The date is empty";
        }
      }
    },

    getDate(item) {
      return moment(item).format("DD/MM/YYYY");
    },
  },
};
</script>

<style>
.main {
  text-align: center;
}

.wrongNotification {
  margin-bottom: 0.2rem;
  width: 80%;
  margin-left: 10%;
  text-align: center;
}

div.cell {
  text-align: center;
}

.counterSection {
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #cbe6bd;
}

.el-input__inner {
  font-size: 14px!important;
}
.el-input--mini {
       padding: 10px;

}
.el-table thead th{
    color: #ffffff;
    font-weight: 600;
      background-color: rgba(0,0,0,0);
}
.el-table th{
  background-color: rgba(0,0,0,0);
}
.el-table .cell {
  background-color: initial !important;
}
</style>
