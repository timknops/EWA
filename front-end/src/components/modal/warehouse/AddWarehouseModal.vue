<template>
  <form>
    <div class="mb-3">
      <label for="warehouse-name" class="form-label fw-bold"
        >Warehouse name</label
      >
      <input
        id="warehouse-name"
        type="text"
        placeholder="New warehouse name"
        class="form-control"
        :class="{ 'border-danger': nameEmpty }"
        v-model.lazy.trim="modalItem.name"
        @blur="validateName"
      />
      <p v-if="nameEmpty" class="text-danger">The name can't be empty!</p>
    </div>
    <div class="mb-3">
      <label for="location" class="form-label fw-bold">location</label>
      <textarea
        id="location"
        placeholder="Street 1, 1234AB Amsterdam"
        class="form-control"
        :class="{ 'border-danger': !locationCorrect }"
        v-model.lazy.trim="modalItem.location"
        @blur="validateLocation"
      >
      </textarea>
      <p v-if="!locationCorrect" class="text-danger">
        The location does not match the format: [street name] [number], [postal
        code] [city]
      </p>
    </div>
  </form>
</template>

<script>
export default {
  name: "AddWarehouseModal",
  data() {
    return {
      modalItem: {
        id: 0,
        name: "",
        location: "",
      },
      nameEmpty: false,
      locationCorrect: true,
    };
  },
  computed: {
    hasError() {
      this.validateName();
      this.validateLocation();
      return this.nameEmpty || !this.locationCorrect;
    }
  },
  methods: {
    validateName() {
      this.nameEmpty = this.modalItem.name.length === 0;
    },
    validateLocation() {
      this.locationCorrect = this.modalItem.location.match(
        "[A-Za-z .-]+[ ][0-9]+([A-Za-z]?)+(([0-9]{1,2})?),[ ][0-9]{4}[A-Za-z]{2}[ ][A-Za-z]+"
      );
    },
  },
};
</script>

<style scoped></style>
