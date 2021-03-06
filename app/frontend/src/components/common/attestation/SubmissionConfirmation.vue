<template>
  <div>
    <h1 class="pb-8">
      <v-icon color="success">check_circle</v-icon>Your form has submitted successfully.
    </h1>
    <p>
      Please record the following
      <em>confirmation id</em> in your records:
    </p>
    <h2 class="mb-10">
      <blockquote>{{ completedSubmission.confirmationId }}</blockquote>
    </h2>

    <div class="d-print-none">
      <hr />

      <div
        v-if="formOptions.submissionConfirmation && formOptions.submissionConfirmation.notifyHealthAuth"
      >
        <h2>Download and Notify</h2>
        <ol class="my-4">
          <li>Download or email yourself a copy of your form submission.</li>
          <li>Send a copy of your form submission to the relevant health authority</li>
        </ol>
      </div>
      <div v-else>
        <h3 class="my-4">Download a PDF or email yourself a copy of your form submission</h3>
      </div>

      <v-row class="mb-6">
        <GeneratePdfButton :formName="formName" :submissionId="completedSubmission.submissionId">
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn
                v-on="on"
                color="primary"
                class="ml-5 mr-10"
                data-test="btn-form-generate-pdf"
                fab
                large
              >
                <v-icon>picture_as_pdf</v-icon>
              </v-btn>
            </template>
            <span>Download PDF</span>
          </v-tooltip>
        </GeneratePdfButton>

        <RequestReceipt
          :email="completedSubmission.contacts[0].email"
          :formName="formName"
          :submissionId="completedSubmission.submissionId"
        />
      </v-row>

      <hr />

      <div
        v-if="formOptions.submissionConfirmation && formOptions.submissionConfirmation.notifyHealthAuth"
      >
        <h3 class="mb-8">{{ formOptions.submissionConfirmation.notifyHealthAuth.text }}</h3>

        <p>
          <a :href="formOptions.submissionConfirmation.notifyHealthAuth.link" target="_blank">
            {{ formOptions.submissionConfirmation.notifyHealthAuth.linkText }}
            <v-icon small color="primary">open_in_new</v-icon>
          </a>
        </p>

        <hr />
      </div>

      <p class="my-10">
        To start again and submit another form you can refresh this page (or
        <a
          href="#"
          @click="refresh"
          data-test="btn-form-restart"
        >
          click here
          <v-icon small color="primary">refresh</v-icon>
        </a>)
      </p>
    </div>
  </div>
</template>

<script>
import GeneratePdfButton from '@/components/common/GeneratePdfButton.vue';
import RequestReceipt from '@/components/common/RequestReceipt.vue';
import { getFormOptions } from '@/utils/attestationFormOptions';

export default {
  name: 'SubmissionConfirmation',
  components: {
    GeneratePdfButton,
    RequestReceipt
  },
  props: {
    completedSubmission: {
      type: Object,
      required: true
    },
    formName: {
      type: String,
      required: true
    }
  },
  computed: {
    formOptions() {
      return getFormOptions(this.formName);
    }
  },
  methods: {
    refresh() {
      location.reload();
    }
  }
};
</script>
