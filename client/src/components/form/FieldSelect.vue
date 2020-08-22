<template id="driverSelect-template" xmlns="http://www.w3.org/1999/xhtml">
  <div class="form-group">
    <select class="form-control" v-model="selected" @change="updateValue()">
      <option disabled :value="null">Select a {{field}}</option>

      <option v-for="value in values" :value="value.id" :key="value.id">
        {{ value.name }}
      </option>
    </select>
  </div>
</template>

<script>
export default {

  props: ['values', 'field'],
  data: function () {
    return {
      selected: null
    }
  },
  methods: {
    updateValue: function () {
      this.$emit('input', this.selected)
    }
  }
}
</script>

<style>
/* Add custom rules here */
</style>
