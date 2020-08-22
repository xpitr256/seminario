<template>
  <div id="garage">
    <app-header></app-header>
    <vehicle-form v-model="vehicle"
                  :makes="makes"
                  :models="models"
                  :drivers="drivers"
                  @submit="submitNewVehicle()">

    </vehicle-form>
    <vehicle-table :vehicles="vehicles"></vehicle-table>
  </div>
</template>

<script>
import AppHeader from './AppHeader'
import VehicleForm from './form/VehicleForm'
import VehicleTable from './table/VehicleTable'

export default {
  components: {
    AppHeader,
    VehicleForm,
    VehicleTable
  },
  data: function () {
    return {
      vehicles: [],
      vehicle: {name: '', make: null, model: null, driver: null},
      models: [],
      makes: [],
      drivers: [],
      serverURL: process.env.SERVER_URL
    }
  },
  created () {
    this.fetchData()
  },
  methods: {
    fetchData: async function () {
      try {
        await Promise.all([
          this.fetchVehicles(),
          this.fetchModels(),
          this.fetchModels(),
          this.fetchMakes(),
          this.fetchDrivers(),
          this.submitBook()
        ])
      } catch (error) {
        console.log(error)
      }
    },
    fetchVehicles: function () {
      fetch(`${this.serverURL}/vehicle`)
        .then(r => r.json())
        .then(json => {
          this.vehicles = json
        })
        .catch(error => console.error('Error retrieving vehicles: ' + error))
    },
    fetchModels: function () {
      fetch(`${this.serverURL}/model`)
        .then(r => r.json())
        .then(json => {
          this.models = json
        })
        .catch(error => console.error('Error retrieving models: ' + error))
    },
    fetchMakes: function () {
      fetch(`${this.serverURL}/make`)
        .then(r => r.json())
        .then(json => {
          this.makes = json
        })
        .catch(error => console.error('Error retrieving makes: ' + error))
    },
    fetchDrivers: function () {
      fetch(`${this.serverURL}/driver`)
        .then(r => r.json())
        .then(json => {
          this.drivers = json
        })
        .catch(error => console.error('Error retrieving drivers: ' + error))
    },
    submitBook: () => {
      const aBook = {
        title: 'This is one book with no forbidden lettera'
      }
      fetch(`http://localhost:8080/book`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(aBook)
      }).then(r => r.json())
        .then(json => {
          console.log('@@@@@@@@@@@ BOOK ADDED @@@@@@@@@@@@@@@@')
          console.log(json)
        })
        .catch(ex => console.error('Unable to save book', ex))
    },
    submitNewVehicle: function () {
      const vehicle = this.vehicle
      fetch(`${this.serverURL}/vehicle`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(vehicle)
      }).then(r => r.json())
        .then(json => {
          this.vehicles.push(json)
          this.vehicle = {name: '', make: null, model: null, driver: null}
        })
        .catch(ex => console.error('Unable to save vehicle', ex))
    }
  }
}
</script>

<!-- Per Component Custom CSS Rules -->
<style>
#garage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
}
</style>
